package com.alelo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("consultaCep")
public class EnderecoController {

	RestTemplate restTemplate;

	@ApiOperation(value = "Consulta endereco pelo CEP")
	@GetMapping("/{cep}")
	public ResponseEntity<String> consultaCep(@PathVariable(value = "cep") String cep) {
		restTemplate = new RestTemplate();
		String endpoint = "http://viacep.com.br/ws/" + cep + "/json";
		return restTemplate.getForEntity(endpoint, String.class);
	}

}
