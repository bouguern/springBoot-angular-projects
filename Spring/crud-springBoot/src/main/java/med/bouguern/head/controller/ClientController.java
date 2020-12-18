package med.bouguern.head.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import med.bouguern.head.model.Client;
import med.bouguern.head.repository.ClientRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepo;
	
	@GetMapping("/clients")
	public List<Client> getAllClients() {
		System.out.println("Get all clients.....");
		
		List<Client> clients = new ArrayList<Client>();
		clientRepo.findAll().forEach(clients::add);
		
		return clients;
	}
	
	@PostMapping("/clients/create")
	public Client addClient(@RequestBody Client newClient) {
		Client client = clientRepo.save(new Client(newClient.getName(), newClient.getAge()));
		
		return client;
	}
	
	@DeleteMapping("/clients/{id}")
	public ResponseEntity<String> removeClient(@PathVariable("id") long id) {
		System.out.println("Delete Client with ID = " + id);
		
		clientRepo.deleteById(id);
		return new ResponseEntity<>("Client has been deleted!", HttpStatus.OK);
	}
	
	@DeleteMapping("/clients/delete")
	public ResponseEntity<String> removeAllClient() {
		System.out.println("Delete all clients...");
		
		clientRepo.deleteAll();
		
		return new ResponseEntity<>("All Clients have been deleted!", HttpStatus.OK);
	}
	
	@GetMapping("/clients/age/{age}")
	public List<Client> findByAge(@PathVariable("age") int age) {
		
		List<Client> clients = clientRepo.findByAge(age);
		
		return clients; 
	}
	
	@PutMapping("/clients/{id}")
	public ResponseEntity<Client> updateClient(@PathVariable("id") long id, @RequestBody Client newClient) {
		System.out.println("Update Client with id = " + id);
		
		Optional<Client> clientData = clientRepo.findById(id);
		
		if( clientData.isPresent()) {
			Client clt = clientData.get();
			clt.setName(newClient.getName());
			clt.setAge(newClient.getAge());
			clt.setAvailable(newClient.isAvailable());
			return new ResponseEntity<>(clientRepo.save(clt), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	

}
