package com.alelo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Endereco {
	@Id
	@Column(name = "cep",unique=true,columnDefinition="VARCHAR(64)")
	String cep;
	String logradouro;
	String complemento;
	String bairro; 
	String uf;
	String unidade;
	String ibge;
	String gia;
	
	public String getCep() {
		return cep;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public String getComplemento() {
		return complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public String getUf() {
		return uf;
	}
	public String getUnidade() {
		return unidade;
	}
	public String getIbge() {
		return ibge;
	}
	public String getGia() {
		return gia;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	public void setIbge(String ibge) {
		this.ibge = ibge;
	}
	public void setGia(String gia) {
		this.gia = gia;
	}
}
