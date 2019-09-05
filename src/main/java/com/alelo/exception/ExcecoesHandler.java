package com.alelo.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExcecoesHandler {

	@ExceptionHandler(RecursoNaoEncontradoException.class)
	public ResponseEntity<ErroResponse> recursoNaoEncontradoException(RecursoNaoEncontradoException ex, WebRequest request) {
		return new ResponseEntity<ErroResponse>(new ErroResponse(new Date(),
				HttpStatus.NOT_FOUND.toString(), ex.getMessage(),
				request.getDescription(false)), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErroResponse> errosInternosHandler(Exception ex, WebRequest request) {
		return new ResponseEntity<ErroResponse>(new ErroResponse(new Date(),
				HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage(),
				request.getDescription(false)),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
