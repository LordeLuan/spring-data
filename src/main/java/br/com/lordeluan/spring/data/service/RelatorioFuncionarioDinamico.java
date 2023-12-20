package br.com.lordeluan.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.lordeluan.spring.data.entities.Funcionario;
import br.com.lordeluan.spring.data.repository.FuncionarioRepository;
import br.com.lordeluan.spring.data.specification.SpecificationFuncionario;

@Service
public class RelatorioFuncionarioDinamico {

	private final FuncionarioRepository funcionarioRepository;

	private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	public void inicial(Scanner scanner) {
		System.out.println("Digite o nome: ");
		String nome = scanner.next();
		if (nome.equalsIgnoreCase("NULL")) {
			nome = null;
		}

		System.out.println("Digite o cpf: ");
		String cpf = scanner.next();
		if (cpf.equalsIgnoreCase("NULL")) {
			cpf = null;
		}

		System.out.println("Digite o salario: ");
		Double salario = scanner.nextDouble();
		if (salario == 0) {
			salario = null;
		}

		System.out.println("Digite a data de contratação: ");
		String dataContratacao = scanner.next();
		LocalDate dataContratacaoDate;
		if (dataContratacao.equalsIgnoreCase("NULL")) {
			dataContratacaoDate = null;
		} else {
			dataContratacaoDate = LocalDate.parse(dataContratacao, dtf);
		}

//		Consulta dinamica com Specifications
		List<Funcionario> funcionarios = funcionarioRepository
				.findAll(Specification.where(SpecificationFuncionario.nome(nome))
						.or(SpecificationFuncionario.cpf(cpf))
						.or(SpecificationFuncionario.salario(salario))
						.or(SpecificationFuncionario.dataContratacao(dataContratacaoDate)));

		funcionarios.forEach(System.out::println);
	}

}
