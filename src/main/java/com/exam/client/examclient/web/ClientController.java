package com.exam.client.examclient.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.exam.client.examclient.domain.ClientComputeResponse;
import com.exam.client.examclient.domain.ClientRequest;
import com.exam.client.examclient.domain.ClientResponse;
import com.exam.client.examclient.service.ClientService;

@RestController
@RequestMapping(path = "/rest")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@GetMapping(path="/listclientes", produces = "application/json")
	public ClientResponse listClients() {
		return clientService.retrieveAllClients();
	}
	
	@GetMapping(path="/kpideclientes", produces = "application/json")
	public ClientComputeResponse computeAgeOfAllClients() {
		return clientService.computeAge();
	}
	
	@PostMapping(path="/creacliente", consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public void saveClient(@RequestBody final ClientRequest clientRequest) throws Exception {
		clientService.saveClient(clientRequest);
	}
	
}