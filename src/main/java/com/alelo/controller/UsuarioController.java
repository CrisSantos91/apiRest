package com.alelo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alelo.exception.RecursoNaoEncontradoException;
import com.alelo.model.Endereco;
import com.alelo.model.Usuario;
import com.alelo.repository.UsuarioRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1/usuario")
public class UsuarioController {
	@Resource
	private UsuarioRepository usuarioRepository;
	
	@ApiOperation(value = "Retorna todos os usuarios")
	@GetMapping
	public List<Usuario> index() {
		return usuarioRepository.findAll();
	}
	
	@ApiOperation(value = "Consulta usuario pelo id")
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> show(@PathVariable(value = "id") Integer usuarioId) throws RecursoNaoEncontradoException {
		return ResponseEntity.ok().body(usuarioExistente(usuarioId));
	}
	
	@ApiOperation(value = "Cria um usuario e preenche o endereco pelo CEP")
	@PostMapping
	public Usuario post(@Valid @RequestBody Usuario usuario) {
		String cep = usuario.getEndereco().getCep();
		Endereco enderecoTemplate = new RestTemplate().getForObject("http://viacep.com.br/ws/" + cep + "/json", Endereco.class);
		usuario.setEndereco(enderecoTemplate);
		return usuarioRepository.save(usuario);
	}
	
	@ApiOperation(value = "Atualiza um usuario")
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> put(@PathVariable(value = "id") Integer usuarioId,
			@Valid @RequestBody Usuario usuarioModificado) throws RecursoNaoEncontradoException {
		Usuario usuario = usuarioExistente(usuarioId);
		usuario.setNome(usuarioModificado.getNome());
		usuario.setEmail(usuarioModificado.getEmail());
		return ResponseEntity.ok(usuarioRepository.save(usuario));
	}
	
	@ApiOperation(value = "Remove um usuario")
	@DeleteMapping("/{id}")
	public Map<String, String> delete(@PathVariable(value = "id") Integer usuarioId) throws Exception {
		Usuario usuario = usuarioExistente(usuarioId);
		usuarioRepository.delete(usuario);
		Map<String, String> resposta = new HashMap<>();
		resposta.put("mensagem", String.format("Usuário %d removido com sucesso", usuarioId));
		return resposta;
	}
	
	private Usuario usuarioExistente(Integer usuarioId) throws RecursoNaoEncontradoException {
		return usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new RecursoNaoEncontradoException(String.format("Usuário %d não encontrado", usuarioId)));
	}
}
