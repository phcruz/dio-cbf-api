package br.com.phc.brasileiraoapi.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ClassificacaoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<ClassificacaoTimeDTO> times = new ArrayList<>();
}
