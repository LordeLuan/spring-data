package br.com.lordeluan.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.lordeluan.spring.data.entities.Funcionario;
import br.com.lordeluan.spring.data.entities.FuncionarioProjecao;
import br.com.lordeluan.spring.data.repository.CargoRepository;
import br.com.lordeluan.spring.data.repository.FuncionarioRepository;
import br.com.lordeluan.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class RelatoriosService {

	private Boolean system = true;
	private final DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private final CargoRepository cargoRepository;
	private final FuncionarioRepository funcionarioRepository;
	private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;

	public RelatoriosService(FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository,
			UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
		this.cargoRepository = cargoRepository;
		this.funcionarioRepository = funcionarioRepository;
		this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
	}

	public void inicial(Scanner scanner) {
		while (system) {
			System.out.println("Qual acao de unidade de trabalho deseja executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Busca funcionario por nome");
			System.out.println("2 - Busca funcionario por nome, salario e data contratacao");
			System.out.println("3 - Busca funcionario por data contratacao");
			System.out.println("4 - Pesquisa funcionario salário");

			int action = scanner.nextInt();

			switch (action) {
			case 1:
				buscaFuncionarioNome(scanner);
				break;
			case 2:
				buscaFuncionarioNomeSalarioMaiorData(scanner);
				break;
			case 3:
				buscaFuncionarioDataContratacao(scanner);
				break;
			case 4:
				pesquisaFuncionarioSalario();
				break;
			default:
				system = false;
				break;
			}

		}

	}

	private void buscaFuncionarioNome(Scanner scanner) {
		System.out.println("Qual o nome deseja pesquisar.");
		String nome = scanner.next();
		List<Funcionario> funcionarios = funcionarioRepository.findByNome(nome);
		funcionarios.forEach(System.out::println);
	}

	private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner) {
		System.out.println("Qual o nome deseja pesquisar.");
		String nome = scanner.next();

		System.out.println("Qual o nome deseja pesquisar.");
		Double salario = scanner.nextDouble();

		System.out.println("Qual o nome deseja pesquisar.");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, df);

		List<Funcionario> funcionarios = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario,
				localDate);
		funcionarios.forEach(System.out::println);
	}

	private void buscaFuncionarioDataContratacao(Scanner scanner) {
		System.out.println("Qual a data de contratacao deseja pesquisar.");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, df);

		List<Funcionario> funcionarios = funcionarioRepository.findDataContratacaoMaior(localDate);
		funcionarios.forEach(System.out::println);
	}

	private void pesquisaFuncionarioSalario() {
		List<FuncionarioProjecao> list = funcionarioRepository.findFuncionarioSalario();
		list.forEach(f -> System.out.println("Funcionario: id: " + f.getId()+ 
				" | nome: " + f.getNome() + 
				" | salario: " + f.getSalario()));
	}
}
