package br.com.phc.brasileiraoapi.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.phc.brasileiraoapi.dto.JogoDTO;
import br.com.phc.brasileiraoapi.entity.Jogo;

@Service
public class JogoMapper {

	@Autowired
	private TimeMapper timeMapper;
	
	public Jogo toModel(JogoDTO jogoDTO) {
		Jogo jogo = new Jogo();
		jogo.setTimeCasa(timeMapper.toModel(jogoDTO.getTimeCasa()));
		jogo.setTimeVisitante(timeMapper.toModel(jogoDTO.getTimeVisitante()));
		jogo.setGolsTimeCasa(jogoDTO.getGolsTimeCasa());
		jogo.setGolsTimeVisitante(jogoDTO.getGolsTimeVisitante());
		jogo.setPublicoPagante(jogoDTO.getPublicoPagante());
		jogo.setUuId(jogoDTO.getUuId());
		jogo.setData(jogoDTO.getData());
		jogo.setRodada(jogoDTO.getRodada());
		jogo.setEncerrado(jogoDTO.getEncerrado());
		
		return jogo;
	}

	public JogoDTO toDTO(Jogo jogo) {
		return new JogoDTO(timeMapper.toDTO(jogo.getTimeCasa()),
				timeMapper.toDTO(jogo.getTimeVisitante()),
				jogo.getGolsTimeCasa(),
				jogo.getGolsTimeVisitante(),
				jogo.getPublicoPagante(),
				jogo.getUuId(),
				jogo.getData(),
				jogo.getEncerrado(),
				jogo.getRodada());
	}
	
}
