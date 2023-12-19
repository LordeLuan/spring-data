package br.com.lordeluan.spring.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.lordeluan.spring.data.entities.*;

@Repository
public interface CargoRepository extends CrudRepository<Cargo, Integer> {

}
