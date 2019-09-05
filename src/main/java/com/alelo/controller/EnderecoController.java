package com.alelo.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("consultaCep")
public class EnderecoController {

	RestTemplate restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(EnderecoController.class,
				args);
	}

	@GetMapping("/{cep}")
	public ResponseEntity<String> consultaCep(@PathVariable(value = "cep") String cep) {
		restTemplate = new RestTemplate();
		String endpoint = "http://viacep.com.br/ws/" + cep + "/json";
		return restTemplate.getForEntity(endpoint, String.class);
	}

}
