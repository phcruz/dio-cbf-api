package br.com.phc.brasileiraoapi.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String sigla;
	private String uf;
	private String uuId;
}
