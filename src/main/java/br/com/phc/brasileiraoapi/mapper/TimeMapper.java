package br.com.phc.brasileiraoapi.mapper;

import org.springframework.stereotype.Service;

import br.com.phc.brasileiraoapi.dto.TimeDTO;
import br.com.phc.brasileiraoapi.entity.Time;

@Service
public class TimeMapper {

	public Time toModel(TimeDTO timeDTO) {
		Time time = new Time();
		time.setNome(timeDTO.getNome());
		time.setSigla(timeDTO.getSigla());
		time.setUf(timeDTO.getUf());
		time.setUuId(timeDTO.getUuId());
		
		return time;
	}

	public TimeDTO toDTO(Time time) {
		return new TimeDTO(time.getNome(), time.getSigla(), time.getUf(), time.getUuId());
	}
}
