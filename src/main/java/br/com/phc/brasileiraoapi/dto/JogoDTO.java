package br.com.phc.brasileiraoapi.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JogoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private TimeDTO timeCasa;
	private TimeDTO timeVisitante;
	private Integer golsTimeCasa;
	private Integer golsTimeVisitante;
	private Integer publicoPagante;
	private String uuId;
	private LocalDateTime data;
	private Boolean encerrado;
    private Integer rodada;
}
