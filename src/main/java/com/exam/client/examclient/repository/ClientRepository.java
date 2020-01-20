package com.exam.client.examclient.repository;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.exam.client.examclient.model.ClientEntity;

@Repository
public interface ClientRepository extends CrudRepository<ClientEntity, Long>  {
	
	@Query("select c from ClientEntity c")
	Stream<ClientEntity> findAllAsStream();
	
}
