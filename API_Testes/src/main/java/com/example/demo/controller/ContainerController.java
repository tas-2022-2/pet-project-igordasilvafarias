package com.example.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.MethodArgumentNotValidException;

//import java.util.HashMap;
import java.net.URI;
//import java.util.Map;
//import java.util.Set;
import java.util.List;
//import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
//import javax.validation.ConstraintViolation;
//import javax.validation.ConstraintViolationException;

import com.example.demo.app.model.Carga;
//import com.example.demo.app.model.Container;
import com.example.demo.app.services.CargaService;
import com.example.demo.app.services.ContainerService;
import com.example.demo.app.services.dto.CargaRequest;
import com.example.demo.app.services.dto.CondicaoRequest;
import com.example.demo.app.services.dto.ContainerRequest;
import com.example.demo.controller.dto.ContainerResponse;
import com.example.demo.controller.dto.ResponseWrapper;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/v1/containers")
public class ContainerController {

    private final ContainerService containerService;
    private final CargaService cargaService;

    /* @Autowired
    // Injecao de dependencia (gerenciado pelo framework - spring)
    public ContainerController(ContainerService service) {
        this.service = service;
    } */
    
    @ApiOperation(value = "Submeter um container",
    notes = "Endpoint para submeter novos containers",
    consumes = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 500, message = "Erro no Servidor"),
        @ApiResponse(code = 201, message = "Container Criado"),
        @ApiResponse(code = 400, message = "Parametros invalidos")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> newContainer(
        @RequestBody @Valid ContainerRequest body) {

        //try {
            containerService.save(body);
            return ResponseEntity.status(HttpStatus.OK).build();
        //} catch (IllegalArgumentException ex) {
            /* Map<String, String> error = new HashMap<>(); 
            error.put("error", argInvalid.getMessage()); */
           // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", ex.getMessage()));
        //}
        
    }

    @ApiOperation(value = "Atualizar um container", 
    notes = "Endpoint para atualizar campos de um determinado container", 
    consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Erro no Servidor"),
            @ApiResponse(code = 201, message = "Container Atualizado"),
            @ApiResponse(code = 400, message = "Parametros invalidos")
    })
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public void updateContainer(
        @PathVariable(name = "id")
        String id,
        @RequestBody @Valid ContainerRequest body) {

            UUID uuid = UUID.fromString(id);

            containerService.update(uuid, body);

        }

    @ApiOperation(value = "Listar Containers", 
    notes = "Endpoint para listar todos os containers submetidos", 
    consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Erro no Servidor"),
            @ApiResponse(code = 200, message = "Lista com sucesso")
    })
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> list() {

        //List<Container> containers = containerService.list();

        // FIXME: user um mapeador (Model Mapper)
        List<ContainerResponse> containers =
        containerService.list().stream().map(container -> ContainerResponse.builder()
            .numero(container.getNumero())
            .tipo(container.getTipo())
            .tamanho(container.getTamanho())
            .id(container.getId())
            .build()).collect(Collectors.toList());

        ResponseWrapper<List<ContainerResponse>, Void> wrapper = ResponseWrapper.wrap(containers);

        return ResponseEntity.ok(wrapper);

    }

    @ApiOperation(value = "Mostrar um container", 
    notes = "Endpoint para mostrar um determinado container", 
    consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Erro no Servidor"),
            @ApiResponse(code = 201, message = "Mostra Container")
    })
    @GetMapping(path = "/{numero}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> showContainer(
        @Pattern(regexp = "^\\w{4}\\d{7}$", message = "Formato invalido, ex: MSCU7327327")
        @PathVariable String numero) {

        /* if ( ! numero.matches("^\\w{4}\\d{7}$") ) {
            return ResponseEntity.badRequest().body(Map.of("error", "Formato numero invalido"));
        } */

        /* Optional<Container> container = service.find(numero);

        if (container.isEmpty()) return ResponseEntity.notFound().build(); */
        
        return ResponseEntity.ok(containerService.load(numero));

    }

    @ApiOperation(value = "Deletar um container", 
    notes = "Endpoint para deletar um determinado container", 
    consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Erro no Servidor"),
            @ApiResponse(code = 201, message = "Container Deletado")
    })
    @DeleteMapping(path = "/{numero}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteContainer(
        @Pattern(regexp = "^\\w{4}\\d{7}$", message = "Numero com formato invalido, ex: MSCU7327327")
        @PathVariable String numero) {
            containerService.delete(numero);
        }

    @ApiOperation(value = "Submeter uma carga para um container", 
    notes = "Endpoint para submeter uma carga para um determinado container", 
    consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Erro no Servidor"),
            @ApiResponse(code = 201, message = "Carga Submetida"),
            @ApiResponse(code = 400, message = "Parametros invalidos")
    })
    @PostMapping(path = "/{numero}/cargas")
    public ResponseEntity<?> carregar(@RequestBody CargaRequest carga,
    @PathVariable(name = "numero") String numero) {

        Carga persistida = cargaService.submeter(numero, carga);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(persistida.getId()).toUri();

        return ResponseEntity.created(location).build();
    
    }

    @ApiOperation(value = "Listar Cargas de um Containers", 
    notes = "Endpoint para listar todas as cargas de um containers", 
    consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Erro no Servidor"),
            @ApiResponse(code = 200, message = "Lista com sucesso")
    })
    @GetMapping(path = "/{numero}/cargas/all")
    public ResponseEntity<List<Carga>> listCargas(
        @PathVariable(name = "numero") String numero
    ) {

        List<Carga> cargas = cargaService.list(numero);

        return ResponseEntity.ok(cargas);

    }

    @ApiOperation(value = "Mostrar uma Carga", 
    notes = "Endpoint para mostrar um determinado Carga", 
    consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Erro no Servidor"),
            @ApiResponse(code = 201, message = "Mostra Carga")
    })
    @GetMapping(path = "/{numero}/cargas/{id}")
    public ResponseEntity<Carga> showCarga(
        @PathVariable(name = "numero") String numero, 
        @PathVariable(name = "id") Integer idCarga) {
            return ResponseEntity.ok(cargaService.loadCarga(numero, idCarga));
        }

    @ApiOperation(value = "Atualizar uma carga", 
    notes = "Endpoint para atualizar campos de um determinado carga", 
    consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Erro no Servidor"),
            @ApiResponse(code = 201, message = "Carga Atualizada"),
            @ApiResponse(code = 400, message = "Parametros invalidos")
    })
    @PutMapping(path = "/{numero}/cargas/{id}")
    public void updateCarga(@RequestBody CargaRequest carga,
    @PathVariable(name = "numero") String numero,
    @PathVariable(name = "id") Integer idCarga) {

        cargaService.update(numero, idCarga, carga);

    }

    @ApiOperation(value = "Atualizar a condiçao de uma carga", 
    notes = "Endpoint para atualizar a condiçao de um determinado carga", 
    consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Erro no Servidor"),
            @ApiResponse(code = 201, message = "Carga Atualizada"),
            @ApiResponse(code = 400, message = "Parametros invalidos")
    })
    @PatchMapping(path = "/{numero}/cargas/{id}")
    public void patchCondicaoCarga(@RequestBody CondicaoRequest novaCondicao,
    @PathVariable(name = "numero") String numero,
    @PathVariable(name = "id") Integer idCarga) {

        /* Carga condicaoAtualizada = cargaService.updateCondicao(numero, idCarga, novaCondicao);

        return ResponseEntity.ok(condicaoAtualizada); */

        cargaService.updateCondicao(numero, idCarga, novaCondicao);


    }

/*
    Criado um ErrorControllerAdvice para cuidar desta regra de negocio
     @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    Map<String, List<String>> trataConstraintViolationException(ConstraintViolationException ex) {

        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        List<String> erros = violations.stream().map(v -> v.getMessage()).collect(Collectors.toList());
        return Map.of("erros", erros);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    Map<String, List<String>> trataMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> erros = ex.getFieldErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
        return Map.of("erros", erros);
    } */

}
