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

import br.com.phc.brasileiraoapi.dto.ClassificacaoDTO;
import br.com.phc.brasileiraoapi.dto.JogoDTO;
import br.com.phc.brasileiraoapi.entity.Jogo;
import br.com.phc.brasileiraoapi.exception.StandardError;
import br.com.phc.brasileiraoapi.service.JogoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/jogo")
public class JogoController {

	@Autowired
	private JogoService jogoService;
	
	@ApiOperation(value = "Obtem a lista de jogos")
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
	public ResponseEntity<List<JogoDTO>> listarJogos() {
		return ResponseEntity.ok().body(jogoService.listarJogos());
	}
	
	@ApiOperation(value = "Obtem um jogo pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Created", response = Jogo.class),
			@ApiResponse(code = 400, message = "Bad request", response = StandardError.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = StandardError.class),
			@ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
			@ApiResponse(code = 404, message = "Not found", response = StandardError.class),
			@ApiResponse(code = 500, message = "Internal server error", response = StandardError.class) })
	@ApiImplicitParams({@ApiImplicitParam(name = "x-transaction-id", paramType = "header",
			defaultValue = "MDowOjA6MDowOjA6MDoxOndlYi1hcHBsaWNhdGlvbjoxMi8wOS8yMDIwIDE4OjE3OjA4", required = true) })
	@GetMapping("/{uuid}")
	public ResponseEntity<JogoDTO> buscarJogoId(@PathVariable("uuid") String uuid) {
		return ResponseEntity.ok().body(jogoService.buscarJogoUuId(uuid));
	}
	
	@ApiOperation(value = "Cria um jogo")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Created", response = Object.class),
			@ApiResponse(code = 400, message = "Bad request", response = StandardError.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = StandardError.class),
			@ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
			@ApiResponse(code = 404, message = "Not found", response = StandardError.class),
			@ApiResponse(code = 500, message = "Internal server error", response = StandardError.class) })
	@ApiImplicitParams({@ApiImplicitParam(name = "x-transaction-id", paramType = "header",
			defaultValue = "MDowOjA6MDowOjA6MDoxOndlYi1hcHBsaWNhdGlvbjoxMi8wOS8yMDIwIDE4OjE3OjA4", required = true) })
	@PostMapping
	public ResponseEntity<Object> salvarJogo(@Valid @RequestBody JogoDTO jogoDTO) {
		JogoDTO jogoSalvo = jogoService.salvarJogo(jogoDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(jogoSalvo);
	}
	
	@ApiOperation(value = "Atualizar um jogo")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = JogoDTO.class),
			@ApiResponse(code = 400, message = "Bad request", response = StandardError.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = StandardError.class),
			@ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
			@ApiResponse(code = 404, message = "Not found", response = StandardError.class),
			@ApiResponse(code = 500, message = "Internal server error", response = StandardError.class) })
	@ApiImplicitParams({@ApiImplicitParam(name = "x-transaction-id", paramType = "header",
			defaultValue = "MDowOjA6MDowOjA6MDoxOndlYi1hcHBsaWNhdGlvbjoxMi8wOS8yMDIwIDE4OjE3OjA4", required = true) })
	@PutMapping
	public ResponseEntity<JogoDTO> atualizarJogo(@Valid @RequestBody JogoDTO jogoDTO) {
		JogoDTO jogoAtualizado = jogoService.atualizaJogo(jogoDTO);
		return ResponseEntity.ok(jogoAtualizado);
	}
	
	@ApiOperation(value = "Deletar um jogo")
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
		jogoService.deleteByUuId(uuid);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Retorna a classificação")
    @GetMapping(value = "/classificacao")
    public ResponseEntity<ClassificacaoDTO> getClassificacao() {
        return ResponseEntity.ok().body(jogoService.getClassificacao());
    }

    @ApiOperation(value = "Finaliza um jogo")
    @PostMapping(value = "/finalizar/{uuid}")
    public ResponseEntity<Void> finalizarJogo(@PathVariable("uuid") String uuid, @RequestBody JogoDTO jogoDTO) throws Exception {
        jogoService.finalizarJogo(uuid, jogoDTO);
        return ResponseEntity.ok().build();
    }
}
