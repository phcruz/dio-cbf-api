package br.com.phc.brasileiraoapi.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.phc.brasileiraoapi.dto.TimeDTO;
import br.com.phc.brasileiraoapi.entity.Time;
import br.com.phc.brasileiraoapi.exception.NotFoundException;
import br.com.phc.brasileiraoapi.mapper.TimeMapper;
import br.com.phc.brasileiraoapi.repository.TimeRepository;

@Service
public class TimeService {

	@Autowired
	private TimeMapper timeMapper;

	@Autowired
	private TimeRepository timeRepository;

	public List<TimeDTO> listarTimes() {
		return timeRepository.findAll().stream().map(timeMapper::toDTO).collect(Collectors.toList());
	}

	public TimeDTO buscarTimeId(Long id) {
		Optional<Time> time = timeRepository.findById(id);
		if (time.isEmpty()) {
			throw new NotFoundException("Nenhum time cadastrado com o id informado.");
		}
		return timeMapper.toDTO(time.get());
	}

	public TimeDTO buscarTimeUuId(String uuid) {
		Optional<Time> time = timeRepository.findByUuId(uuid);
		if (time.isEmpty()) {
			throw new NotFoundException("Nenhum time cadastrado com o id informado.");
		}
		return timeMapper.toDTO(time.get());
	}

	public TimeDTO salvarTime(TimeDTO timeDTO) {
		timeDTO.setUuId(UUID.randomUUID().toString());
		Time time = timeMapper.toModel(timeDTO);
		Time timeSalvo = timeRepository.save(time);
		return timeMapper.toDTO(timeSalvo);
	}

	public TimeDTO atualizaTime(TimeDTO timeDTO) {
		Time timeExiste = verificaTimeExistente(timeDTO.getUuId());
		Time time = timeMapper.toModel(timeDTO);
		time.setId(timeExiste.getId());

		return timeMapper.toDTO(timeRepository.save(time));
	}

	public void deleteByUuId(String uuid) {
		Time time = verificaTimeExistente(uuid);
		timeRepository.deleteById(time.getId());
	}

	public Time verificaTimeExistente(String uuId) {
		return timeRepository.findByUuId(uuId)
				.orElseThrow(() -> new NotFoundException("Nenhum time cadastrado com o id informado."));
	}
}
