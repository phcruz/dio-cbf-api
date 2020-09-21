package br.com.phc.brasileiraoapi.exception;

import java.io.Serializable;
import java.util.List;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class ErrorDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	String uniqueId;
	String informationCode;
	String message;
	transient List<Object> args;
	transient Object nested;

	@ApiModelProperty(required = true, value = "Indentificador único do erro. Pode ser utilizado para rastrear a propagação do erro.")
	@NotNull
	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	@ApiModelProperty(required = true, value = "Código do erro.")
	@NotNull
	public String getInformationCode() {
		return informationCode;
	}

	public void setInformationCode(String informationCode) {
		this.informationCode = informationCode;
	}

	@ApiModelProperty(required = true, value = "Descrição do erro.")
	@NotNull
	public String getMessage() {
		return message;
	}

	@ApiModelProperty(required = true, value = "Dados complementares do erro.")
	@NotNull
	public void setMessage(String message) {
		this.message = message;
	}

	public List<Object> getArgs() {
		return args;
	}

	public void setArgs(List<Object> args) {
		this.args = args;
	}

	public Object getNested() {
		return nested;
	}

	public void setNested(Object nested) {
		this.nested = nested;
	}

}
