package com.exam.client.examclient.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/rest")
public class ClientController {

	@GetMapping(path="/listclientes", produces = "application/json")
	public String listClientes() {
		return "Any String ClientAPP";
	}
}