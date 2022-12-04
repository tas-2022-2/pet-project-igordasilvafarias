package com.example.demo.app.services;

import java.util.List;
//import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

//import javax.naming.NameNotFoundException;
import javax.validation.Valid;
//import javax.validation.constraints.Pattern;

import com.example.demo.app.exceptions.NotFoundException;
import com.example.demo.app.model.Container;
import com.example.demo.app.services.dto.ContainerRequest;
import com.example.demo.app.repository.ContainerRepository;

import org.springframework.stereotype.Service;

//import ch.qos.logback.core.pattern.color.BoldYellowCompositeConverter;

//import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ContainerService {

    private final ContainerRepository repository;

    @Autowired
    public ContainerService(ContainerRepository repository) {
        this.repository = repository;
    }
    
    public void save(ContainerRequest request) {

        // validacao 
        /* Objects.requireNonNull(request, "Necessario uma requisiçao");
        if (request.getId() == null) throw new IllegalArgumentException("ID deve ser informado");
        if (request.getNumero() == null) throw new IllegalArgumentException("Numero do container deve ser informado");
        if (request.getTipo() == null) throw new IllegalArgumentException("Tipo do container deve ser informado");
        if (request.getTamanho() == null) throw new IllegalArgumentException("Tamanho do container deve ser informado"); */

        if(this.repository.existsByNumero(request.getNumero())) {
            throw new ServiceException("Numero ja existente");
        }

        // mapeamento
        Container container = new Container();
        //container.setId(request.getId());
        container.setNumero(request.getNumero());
        container.setTipo(request.getTipo());
        container.setTamanho(request.getTamanho());

        repository.save(container);

    }

    public List<Container> list() {
        return repository.findAll();
    }

    public Optional<Container> find(String numero) {
        return repository.findByNumero(numero);
    }

    public Container load(String numero) {
        return this.find(numero).orElseThrow(() -> new NotFoundException("Container nao encontrado"));
    }

    public void delete(String numero) {
        Container container = repository.findByNumero(numero).orElseThrow(() -> new NotFoundException("Container nao encontrado"));
        repository.delete(container);
    }

    public void update(UUID uuid, @Valid ContainerRequest body) {
        Container container = repository.findById(uuid).orElseThrow(() -> new NotFoundException("Container nao encontrado"));
        
        container.setNumero(body.getNumero());
        container.setTamanho(body.getTamanho());
        container.setTipo(body.getTipo());
        
        repository.save(container);
    }

}
