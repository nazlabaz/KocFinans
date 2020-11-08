package com.creditsapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creditsapp.model.Client;
import com.creditsapp.model.Request;
import com.creditsapp.repository.ClientRepository;
import com.creditsapp.repository.RequestRepository;

@RestController
@RequestMapping("")
public class Controller {
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private RequestRepository requestRepository;

	@GetMapping("/clients")
	public List<Client> getAllClients() {
		return clientRepository.findAll();
	}

	@GetMapping("/clients/{id}")
	public ResponseEntity<Client> getClientById(@PathVariable(value = "id") Long clientId)
			throws Exception {
		Client client = clientRepository.findById(clientId)
				.orElseThrow(() -> new Exception("Client not found"));
		return ResponseEntity.ok().body(client);
	}

	@PostMapping("/clients")
	public Client createClient(@Valid @RequestBody Client client) {
		return clientRepository.save(client);
	}

	@PutMapping("/client/{id}")
	public ResponseEntity<Client> updateClient(@PathVariable(value = "id") Long clientId,
			@Valid @RequestBody Client clientDet) throws Exception {
		
		Client client = clientRepository.findById(clientId)
				.orElseThrow(() -> new Exception("Client not found"));

		client.setIncome(clientDet.getIncome());
		client.setPhoneNo(clientDet.getPhoneNo());
		client.setLastName(clientDet.getLastName());
		client.setFirstName(clientDet.getFirstName());
		client.setCreditScore(clientDet.getCreditScore());
		
		final Client updatedClient = clientRepository.save(client);
		return ResponseEntity.ok(updatedClient);
	}
	
	@PostMapping("/client/credit/{id}")
	public String calculateCredit(@PathVariable(value = "id") Long clientId,
			@Valid @RequestBody Request req) throws Exception {
		
		Client client = clientRepository.findById(clientId)
				.orElseThrow(() -> new Exception("Client not found" ));				

		Request request = requestRepository.save(req);	
		
		request.calcCredit();		
		
		client.setLimit(request.getLimit());		
		
		requestRepository.save(request);		
		clientRepository.save(client);
		
		return request.getResponse();
	}

	@DeleteMapping("/clints/{id}")
	public Map<String, Boolean> deleteClient(@PathVariable(value = "id") Long clientId)
			throws Exception {
		Client client = clientRepository.findById(clientId)
				.orElseThrow(() -> new Exception("Client not found"));

		clientRepository.delete(client);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
