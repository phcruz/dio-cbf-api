package br.com.phc.brasileiraoapi.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.phc.brasileiraoapi.dto.ClassificacaoDTO;
import br.com.phc.brasileiraoapi.dto.ClassificacaoTimeDTO;
import br.com.phc.brasileiraoapi.dto.JogoDTO;
import br.com.phc.brasileiraoapi.entity.Jogo;
import br.com.phc.brasileiraoapi.entity.Time;
import br.com.phc.brasileiraoapi.exception.NotFoundException;
import br.com.phc.brasileiraoapi.mapper.JogoMapper;
import br.com.phc.brasileiraoapi.repository.JogoRepository;
import br.com.phc.brasileiraoapi.repository.TimeRepository;

@Service
public class JogoService {

	@Autowired
	private JogoRepository jogoRepository;
	
	@Autowired
	private TimeRepository timeRepository;
	
	@Autowired
	private JogoMapper jogoMapper;
	
	@Autowired
	private TimeService timeService;

	public List<JogoDTO> listarJogos() {
		return jogoRepository.findAll()
				.stream()
				.map(jogoMapper::toDTO)
				.collect(Collectors.toList());
	}

	public JogoDTO buscarJogoId(Long id) {
		Optional<Jogo> time = jogoRepository.findById(id);
		if (time.isEmpty()) {
			throw new NotFoundException("Nenhum jogo cadastrado com o id informado.");
		}
		return jogoMapper.toDTO(time.get());
	}

	public JogoDTO buscarJogoUuId(String uuid) {
		Optional<Jogo> time = jogoRepository.findByUuId(uuid);
		if (time.isEmpty()) {
			throw new NotFoundException("Nenhum jogo cadastrado com o id informado.");
		}
		return jogoMapper.toDTO(time.get());
	}

	public JogoDTO salvarJogo(JogoDTO jogoDTO) {
		jogoDTO.setUuId(UUID.randomUUID().toString());
		Jogo jogo = jogoMapper.toModel(jogoDTO);
		
		Time timeCasa = timeService.verificaTimeExistente(jogo.getTimeCasa().getUuId());
		Time timeVisitante = timeService.verificaTimeExistente(jogo.getTimeVisitante().getUuId());
		jogo.getTimeCasa().setId(timeCasa.getId());
		jogo.getTimeVisitante().setId(timeVisitante.getId());

		Jogo jogoSalvo = jogoRepository.save(jogo);
		return jogoMapper.toDTO(jogoSalvo);
	}

	public JogoDTO atualizaJogo(JogoDTO jogoDTO) {
		Jogo jogoExiste = verificaJogoExistente(jogoDTO.getUuId());
		Jogo jogo = jogoMapper.toModel(jogoDTO);
		jogo.setId(jogoExiste.getId());
		
		Time timeCasa = timeService.verificaTimeExistente(jogo.getTimeCasa().getUuId());
		Time timeVisitante = timeService.verificaTimeExistente(jogo.getTimeVisitante().getUuId());
		jogo.getTimeCasa().setId(timeCasa.getId());
		jogo.getTimeVisitante().setId(timeVisitante.getId());

		return jogoMapper.toDTO(jogoRepository.save(jogo));
	}

	public void deleteByUuId(String uuid) {
		Jogo jogo = verificaJogoExistente(uuid);
		jogoRepository.deleteById(jogo.getId());
	}

	private Jogo verificaJogoExistente(String uuId) {
		return jogoRepository.findByUuId(uuId)
				.orElseThrow(() -> new NotFoundException("Nenhum jogo cadastrado com o id informado."));
	}
	
	public void gerarJogos(LocalDateTime primeiraRodada, List<LocalDate> datasInvalidas) {
		final List<Time> times = timeRepository.findAll();
        List<Time> all1 = new ArrayList<>();
        List<Time> all2 = new ArrayList<>();
        all1.addAll(times);
        all2.addAll(times);

        jogoRepository.deleteAll();

        List<Jogo> jogos = new ArrayList<>();

        int t = times.size();
        int m = times.size() / 2;
        LocalDateTime dataJogo = primeiraRodada;
        Integer rodada = 0;
        for (int i = 0; i < t - 1; i++) {
            rodada = i + 1;
            for (int j = 0; j < m; j++) {
                //Teste para ajustar o mando de campo
                Time time1;
                Time time2;
                if (j % 2 == 1 || i % 2 == 1 && j == 0) {
                    time1 = times.get(t - j - 1);
                    time2 = times.get(j);
                } else {
                    time1 = times.get(j);
                    time2 = times.get(t - j - 1);
                }
                if (time1 == null) {
                    System.out.println("Time  1 null");
                }
                jogos.add(gerarJogo(dataJogo, rodada, time1, time2));
                dataJogo = dataJogo.plusDays(7);
            }
            //Gira os times no sentido horário, mantendo o primeiro no lugar
            times.add(1, times.remove(times.size() - 1));
        }

        jogos.forEach(jogo -> System.out.println(jogo));

        jogoRepository.saveAll(jogos);

        List<Jogo> jogos2 = new ArrayList<>();

        jogos.forEach(jogo -> {
            Time time1 = jogo.getTimeVisitante();
            Time time2 = jogo.getTimeCasa();
            jogos2.add(gerarJogo(jogo.getData().plusDays(7 * jogos.size()), jogo.getRodada() + jogos.size(), time1, time2));
        });
        jogoRepository.saveAll(jogos2);
	}
	
	private Jogo gerarJogo(LocalDateTime dataJogo, Integer rodada, Time time1, Time time2) {
        Jogo jogo = new Jogo();
        jogo.setTimeCasa(time1);
        jogo.setTimeVisitante(time2);
        jogo.setRodada(rodada);
        jogo.setData(dataJogo);
        jogo.setEncerrado(false);
        jogo.setGolsTimeCasa(0);
        jogo.setGolsTimeVisitante(0);
        jogo.setPublicoPagante(0);
        return jogo;
    }
	
	public ClassificacaoDTO getClassificacao() {
        ClassificacaoDTO dto = new ClassificacaoDTO();
        final List<Time> times = timeRepository.findAll();
        times.forEach(time -> {
            final List<Jogo> jogosTimeMandante = jogoRepository.findByTimeCasaAndEncerrado(time, true);
            final List<Jogo> jogosTimeVisitante = jogoRepository.findByTimeVisitanteAndEncerrado(time, true);
            AtomicReference<Integer> vitorias = new AtomicReference<>(0);
            AtomicReference<Integer> empates = new AtomicReference<>(0);
            AtomicReference<Integer> derrotas = new AtomicReference<>(0);
            AtomicReference<Integer> golsMarcados = new AtomicReference<>(0);
            AtomicReference<Integer> golsSofridos = new AtomicReference<>(0);

            jogosTimeMandante.forEach(jogo -> {
                if (jogo.getGolsTimeCasa() > jogo.getGolsTimeVisitante()) {
                    vitorias.getAndSet(vitorias.get() + 1);
                } else if (jogo.getGolsTimeCasa() < jogo.getGolsTimeVisitante()) {
                    derrotas.getAndSet(derrotas.get() + 1);
                } else {
                    empates.getAndSet(empates.get() + 1);
                }
                golsMarcados.set(golsMarcados.get() + jogo.getGolsTimeCasa());
                golsSofridos.getAndSet(golsSofridos.get() + jogo.getGolsTimeVisitante());
            });
            jogosTimeVisitante.forEach(jogo -> {
                if (jogo.getGolsTimeVisitante() > jogo.getGolsTimeCasa()) {
                    vitorias.getAndSet(vitorias.get() + 1);
                } else if (jogo.getGolsTimeVisitante() < jogo.getGolsTimeCasa()) {
                    derrotas.getAndSet(derrotas.get() + 1);
                } else {
                    empates.getAndSet(empates.get() + 1);
                }
                golsMarcados.set(golsMarcados.get() + jogo.getGolsTimeVisitante());
                golsSofridos.getAndSet(golsSofridos.get() + jogo.getGolsTimeCasa());
            });

            ClassificacaoTimeDTO classificacaoTimeDto = new ClassificacaoTimeDTO();
            classificacaoTimeDto.setIdTime(time.getId());
            classificacaoTimeDto.setTime(time.getNome());
            classificacaoTimeDto.setPontos((vitorias.get() * 3) + empates.get());
            classificacaoTimeDto.setDerrotas(derrotas.get());
            classificacaoTimeDto.setEmpates(empates.get());
            classificacaoTimeDto.setVitorias(vitorias.get());
            classificacaoTimeDto.setGolsMarcados(golsMarcados.get());
            classificacaoTimeDto.setGolsSofridos(golsSofridos.get());
            classificacaoTimeDto.setJogos(derrotas.get() + empates.get() + vitorias.get());
            dto.getTimes().add(classificacaoTimeDto);
        });
        Collections.sort(dto.getTimes(), Collections.reverseOrder());
        int posicao = 0;
        for (ClassificacaoTimeDTO time : dto.getTimes()) {
            time.setPosicao(posicao++);
        }
        return dto;
    }

    public void finalizarJogo(String uuid, JogoDTO jogoDTO) throws Exception {
        Jogo jogo = jogoRepository.findByUuId(uuid).orElseThrow(() -> new Exception("Jogo não encontrado"));
        if (jogo.getEncerrado()) {
            throw new Exception("Jogo já foi encerrado");
        }
        jogo.setGolsTimeCasa(jogoDTO.getGolsTimeCasa());
        jogo.setGolsTimeVisitante(jogoDTO.getGolsTimeVisitante());
        jogo.setEncerrado(true);
        jogo.setPublicoPagante(jogoDTO.getPublicoPagante());
        jogoRepository.save(jogo);
    }
}
