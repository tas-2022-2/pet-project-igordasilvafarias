package com.example.demo.app.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.app.model.Carga;
import com.example.demo.app.model.Container;

import org.springframework.data.repository.Repository;

public interface CargaRepository extends Repository<Carga, Integer> {

    public Carga save(Carga carga);

    List<Carga> findByArmazenado(Container armazenado);

    //List<Carga> findAllByContainer(UUID container_id);

    public Optional<Carga> findById(Integer idCarga);
    
}
