package br.com.lordeluan.spring.data.entities;

// Classe de projecao da entidade funcionario, utilizada para retornar apenas alguns atributos
// TAMBEM PODE SER UTILIZADO A PROJECAO COM DTO
public interface FuncionarioProjecao {

//	Faz o bind com os dados que retornarem da consulta como se fossem atributos da classe
	Integer getId();

	String getNome();

	Double getSalario();
}
