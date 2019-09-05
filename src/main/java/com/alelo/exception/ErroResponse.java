package com.alelo.exception;

import java.util.Date;

public class ErroResponse {
	private Date data;
	private String status;
	private String messagem;
	private String detalhes;

	public ErroResponse(Date data, String status, String messagem, String detalhes) {
		this.data = data;
		this.status = status;
		this.messagem = messagem;
		this.detalhes = detalhes;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessagem() {
		return messagem;
	}

	public void setMessagem(String messagem) {
		this.messagem = messagem;
	}

	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}
}
