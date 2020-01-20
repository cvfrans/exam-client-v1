package com.exam.client.examclient.service;

import com.exam.client.examclient.domain.ClientComputeResponse;
import com.exam.client.examclient.domain.ClientRequest;
import com.exam.client.examclient.domain.ClientResponse;


public interface ClientService {
	
	ClientResponse retrieveAllClients();
	ClientComputeResponse computeAge();
	void saveClient(ClientRequest clientRequest) throws Exception;
}
