package com.alelo;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.alelo.model.Usuario;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuarioRestTest {
	@LocalServerPort
	private Integer port;
	
	@Resource
	private TestRestTemplate restTemplate;
	private String resource = "usuario";

	private String getUrl() {
		return String.format("http://127.0.0.1:%d/%s/", port, resource);
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void testDeveListarUsuarios() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getUrl(), HttpMethod.GET, entity, String.class);
		Assert.assertNotNull(response.getBody());
	}

	@Test
	public void testDeveRetornarUsuarioPorId() {
		Assert.assertNotNull(restTemplate.getForObject(obterUrlFormatada(1), Usuario.class));
	}

	@Test
	public void testDeveCriarUsuario() {
		Usuario usuario = new Usuario();
		usuario.setEmail("cristiano@alelo.com");
		usuario.setNome("Cristiano Santos");
		ResponseEntity<Usuario> response = restTemplate.postForEntity(getUrl(), usuario, Usuario.class);
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getBody());
	}

	@Test
	public void testDeveModificarUsuarioViaMetodoPut() {
		Integer idUsuario = 1;
		Usuario usuario = restTemplate.getForObject(obterUrlFormatada(idUsuario), Usuario.class);
		usuario.setNome("Cristiano Santos");
		restTemplate.put(obterUrlFormatada(idUsuario), usuario);
		Usuario usuarioModificado = restTemplate.getForObject(obterUrlFormatada(idUsuario), Usuario.class);
		Assert.assertNotNull(usuarioModificado);
	}

	private String obterUrlFormatada(Integer id) {
		return getUrl() + id;
	}

	@Test
	public void testDeveRemoverUsuario() {
		Integer idUsuario = 77;
		Usuario usuarioRemovido = restTemplate.getForObject(obterUrlFormatada(idUsuario), Usuario.class);
		Assert.assertNotNull(usuarioRemovido);
		restTemplate.delete(obterUrlFormatada(idUsuario));
		try {
			usuarioRemovido = restTemplate.getForObject(obterUrlFormatada(idUsuario), Usuario.class);
		} catch (final HttpClientErrorException e) {
			Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
	
}
