package com.exam.client.examclient.component;

import java.text.ParseException;

import org.springframework.stereotype.Component;

import com.exam.client.examclient.common.Helper;
import com.exam.client.examclient.domain.Client;
import com.exam.client.examclient.domain.ClientRequest;
import com.exam.client.examclient.model.ClientEntity;

@Component
public class ClientAdapter {
	
	public Client toClient(ClientEntity entity) {
		Client client = new Client();
		client.setId(entity.getId());
		client.setFirstName(entity.getFirstName());
		client.setLastName(entity.getLastName());
		client.setAge(entity.getAge());
		client.setBirthday(Helper.dateFormat(entity.getBirthday()));
		return client;
	}
	public ClientEntity toClientEntity(ClientRequest request) throws ParseException {
		ClientEntity entity = new ClientEntity();
		entity.setFirstName(request.getFirstName());
		entity.setLastName(request.getLastName());
		entity.setAge(request.getAge());
		entity.setBirthday(Helper.parseToDate(request.getBirthday()));
		return entity;
	}
}
