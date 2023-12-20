package br.com.lordeluan.spring.data.specification;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import br.com.lordeluan.spring.data.entities.Funcionario;

//criar queries dinâmicas baseada na API de Criteria do JPA
public class SpecificationFuncionario {

//	criando uma Specification com o campo nome da tabela funcionario utilizando LIKE
	public static Specification<Funcionario> nome(String nome) {
		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
	}

	public static Specification<Funcionario> cpf(String cpf) {
		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("cpf"), cpf);
	}

	public static Specification<Funcionario> salario(Double salario) {
		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("salario"), salario);
	}

	public static Specification<Funcionario> dataContratacao(LocalDate dataContratacao) {
		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("dataContratacao"),
				dataContratacao);
	}
}
