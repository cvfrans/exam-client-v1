package com.exam.client.examclient.service.impl;

import java.text.ParseException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.client.examclient.common.Helper;
import com.exam.client.examclient.component.ClientAdapter;
import com.exam.client.examclient.component.DoubleStatistics;
import com.exam.client.examclient.domain.Client;
import com.exam.client.examclient.domain.ClientComputeResponse;
import com.exam.client.examclient.domain.ClientRequest;
import com.exam.client.examclient.domain.ClientResponse;
import com.exam.client.examclient.model.ClientEntity;
import com.exam.client.examclient.repository.ClientRepository;
import com.exam.client.examclient.service.ClientService;

import lombok.extern.slf4j.Slf4j;

@Transactional(readOnly = true)
@Service
@Slf4j
public class ClientServiceImpl implements ClientService {
	
	private static final int LIFE_EXPECTANCY = 75;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ClientAdapter clientAdapter;

	@Override
	public ClientResponse retrieveAllClients() {
		log.info("Get client list");
		
		ArrayList<Client> clients = new ArrayList<>();
		clientRepository.findAll().forEach(entity -> {
			log.info("Entity ID: " + entity.getId());
			Client client = clientAdapter.toClient(entity);
			client.setDateDeath(Helper.addYearsToDate(entity.getBirthday(), LIFE_EXPECTANCY));
			clients.add(client);
			
		});
		log.info("Total Clients: " + clients.size());
		ClientResponse clientRes = new ClientResponse();
		clientRes.setClients(clients);
		return clientRes;	
		
	}

	@Override
	public ClientComputeResponse computeAge() {
		log.info("Calculate Age of all Clients");
		ClientComputeResponse computeRes = new ClientComputeResponse();
		clientRepository.findAllAsStream()
						.mapToLong(ClientEntity::getAge)
						.average()
						.ifPresent(computeRes::setAverageAge);
		double sdr = clientRepository.findAllAsStream()
						.mapToDouble(ClientEntity::getAge)
						.boxed()
						.collect(DoubleStatistics.collector());
		computeRes.setStandardDeviation(sdr);
		return computeRes;
	}

	@Override
	public void saveClient(ClientRequest clientRequest) throws ParseException {
		clientRepository.save(clientAdapter.toClientEntity(clientRequest));
	}
	

}
