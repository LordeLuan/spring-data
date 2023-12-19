package br.com.lordeluan.spring.data.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.lordeluan.spring.data.entities.Funcionario;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer> {

	List<Funcionario> findByNome(String nome);

	List<Funcionario> findByNomeStartingWith(String nome);

//	List<Funcionario> findByNomeAndSalarioGreatherThanAndDataContratacao(String nome, Double salario,
//			LocalDate dataContratacao);

	// JPQL
	@Query("SELECT f FROM Funcionario f WHERE f.nome = :nome AND f.salario >= :salario "
			+ "AND f.dataContratacao = :dataContratacao")
	List<Funcionario> findNomeSalarioMaiorDataContratacao(String nome, Double salario, LocalDate dataContratacao);

	@Query(value = "SELECT * FROM funcionarios f WHERE f.data_contratacao >= :data", nativeQuery = true)
	List<Funcionario> findDataContratacaoMaior(LocalDate data);
}
