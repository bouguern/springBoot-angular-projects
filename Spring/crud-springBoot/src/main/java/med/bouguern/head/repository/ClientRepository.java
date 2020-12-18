package med.bouguern.head.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import med.bouguern.head.model.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {
	
	List<Client> findByAge(int age);

}
