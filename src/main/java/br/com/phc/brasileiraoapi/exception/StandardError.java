package br.com.phc.brasileiraoapi.exception;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;

public class StandardError implements Serializable {

	private static final long serialVersionUID = 1L;

	private String timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
	private ErrorDetails errorDetails;

	public StandardError() {
	}

	public StandardError(HttpStatus httpStatus, String message, String path, ErrorDetails errorDetails) {
		super();
		this.status = httpStatus.value();
		this.error = httpStatus.name();
		this.timestamp = DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now());
		this.message = message;
		this.path = path;
		this.errorDetails = errorDetails;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public ErrorDetails getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(ErrorDetails errorDetails) {
		this.errorDetails = errorDetails;
	}

	@Override
	public String toString() {
		return "StandardError [timestamp=" + timestamp + ", status=" + status + ", error=" + error + ", message="
				+ message + ", path=" + path + ", errorDetails=" + errorDetails + "]";
	}

}