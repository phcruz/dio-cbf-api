package br.com.phc.brasileiraoapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.phc.brasileiraoapi.dto.TimeDTO;
import br.com.phc.brasileiraoapi.exception.StandardError;
import br.com.phc.brasileiraoapi.service.TimeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/time")
public class TimeController {

	@Autowired
	private TimeService timeService;
	
	@ApiOperation(value = "Obtem a lista de times")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Created", response = List.class),
			@ApiResponse(code = 400, message = "Bad request", response = StandardError.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = StandardError.class),
			@ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
			@ApiResponse(code = 404, message = "Not found", response = StandardError.class),
			@ApiResponse(code = 500, message = "Internal server error", response = StandardError.class) })
	@ApiImplicitParams({@ApiImplicitParam(name = "x-transaction-id", paramType = "header",
			defaultValue = "MDowOjA6MDowOjA6MDoxOndlYi1hcHBsaWNhdGlvbjoxMi8wOS8yMDIwIDE4OjE3OjA4", required = true) })
	@GetMapping
	public ResponseEntity<List<TimeDTO>> listarTimes() {
		return ResponseEntity.ok().body(timeService.listarTimes());
	}
	
	@ApiOperation(value = "Busca um time por id")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Created", response = TimeDTO.class),
			@ApiResponse(code = 400, message = "Bad request", response = StandardError.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = StandardError.class),
			@ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
			@ApiResponse(code = 404, message = "Not found", response = StandardError.class),
			@ApiResponse(code = 500, message = "Internal server error", response = StandardError.class) })
	@ApiImplicitParams({@ApiImplicitParam(name = "x-transaction-id", paramType = "header",
			defaultValue = "MDowOjA6MDowOjA6MDoxOndlYi1hcHBsaWNhdGlvbjoxMi8wOS8yMDIwIDE4OjE3OjA4", required = true) })
	@GetMapping("/{uuid}")
	public ResponseEntity<TimeDTO> buscarTimeId(@PathVariable("uuid") String uuid) {
		return ResponseEntity.ok().body(timeService.buscarTimeUuId(uuid));
	}
	
	@ApiOperation(value = "Criar um novo time")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Created", response = TimeDTO.class),
			@ApiResponse(code = 400, message = "Bad request", response = StandardError.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = StandardError.class),
			@ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
			@ApiResponse(code = 404, message = "Not found", response = StandardError.class),
			@ApiResponse(code = 500, message = "Internal server error", response = StandardError.class) })
	@ApiImplicitParams({@ApiImplicitParam(name = "x-transaction-id", paramType = "header",
			defaultValue = "MDowOjA6MDowOjA6MDoxOndlYi1hcHBsaWNhdGlvbjoxMi8wOS8yMDIwIDE4OjE3OjA4", required = true) })
	@PostMapping
	public ResponseEntity<TimeDTO> salvarTime(@Valid @RequestBody TimeDTO timeDTO) {
		TimeDTO timeSalvo = timeService.salvarTime(timeDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(timeSalvo);
	}
	
	@ApiOperation(value = "Atualizar um time")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = TimeDTO.class),
			@ApiResponse(code = 400, message = "Bad request", response = StandardError.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = StandardError.class),
			@ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
			@ApiResponse(code = 404, message = "Not found", response = StandardError.class),
			@ApiResponse(code = 500, message = "Internal server error", response = StandardError.class) })
	@ApiImplicitParams({@ApiImplicitParam(name = "x-transaction-id", paramType = "header",
			defaultValue = "MDowOjA6MDowOjA6MDoxOndlYi1hcHBsaWNhdGlvbjoxMi8wOS8yMDIwIDE4OjE3OjA4", required = true) })
	@PutMapping
	public ResponseEntity<TimeDTO> atualizarTime(@Valid @RequestBody TimeDTO timeDTO) {
		TimeDTO timeAtualizado = timeService.atualizaTime(timeDTO);
		return ResponseEntity.ok(timeAtualizado);
	}
	
	@ApiOperation(value = "Deletar um time")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "No content", response = Void.class),
			@ApiResponse(code = 400, message = "Bad request", response = StandardError.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = StandardError.class),
			@ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
			@ApiResponse(code = 404, message = "Not found", response = StandardError.class),
			@ApiResponse(code = 500, message = "Internal server error", response = StandardError.class) })
	@ApiImplicitParams({@ApiImplicitParam(name = "x-transaction-id", paramType = "header",
			defaultValue = "MDowOjA6MDowOjA6MDoxOndlYi1hcHBsaWNhdGlvbjoxMi8wOS8yMDIwIDE4OjE3OjA4", required = true) })
	@DeleteMapping("/{uuid}")
	public ResponseEntity<Void> deletarTime(@PathVariable("uuid") String uuid) {
		timeService.deleteByUuId(uuid);
		return ResponseEntity.noContent().build();
	}
}
