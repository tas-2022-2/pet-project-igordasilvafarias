package com.example.demo.app.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.Repository;


import com.example.demo.app.model.Container;

public interface ContainerRepository extends CrudRepository<Container, UUID> {

    Container save(Container container);

    List<Container> findAll();

    Optional<Container> findByNumero(String numero);

    void delete(Container container);

    Optional<Container> findById(UUID id);

    boolean existsByNumero(String numero);

}

