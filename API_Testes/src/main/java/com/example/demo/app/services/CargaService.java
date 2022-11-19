package com.example.demo.app.services;

import com.example.demo.app.exceptions.NotFoundException;
import com.example.demo.app.model.Carga;
import com.example.demo.app.model.Condicao;
import com.example.demo.app.model.Container;
import com.example.demo.app.model.Tipo;
import com.example.demo.app.repository.CargaRepository;
import com.example.demo.app.repository.ContainerRepository;
import com.example.demo.app.services.dto.CargaRequest;
import com.example.demo.app.services.dto.CondicaoRequest;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CargaService {

    private final ContainerRepository containerRepository;
    private final CargaRepository cargaRepository;
    
    public Carga submeter(String numero, CargaRequest req) {

        Container container = containerRepository.findByNumero(numero).orElseThrow(() -> new NotFoundException("Container nao encontrado"));

        List<Carga> cargas = cargaRepository.findByArmazenado(container);

        if (cargas.isEmpty() || cargas.get(cargas.size()-1).getCondicao().toString() == "CANCELADA" || cargas.get(cargas.size()-1).getCondicao().toString() == "CONCLUIDA") {
            Carga carga = Carga.builder()
                    .descricao(req.getDescricao())
                    .tipo(Tipo.fromInteger(req.getIdTipo()))
                    .armazenado(container)
                    .condicao(Condicao.DISPONIVEL)
                    .build();

            return cargaRepository.save(carga);

        } else {
            throw new IllegalArgumentException("Carga ainda em transito");
        }

        /* Carga carga = Carga.builder()
            .descricao(req.getDescricao())
            .tipo(Tipo.fromInteger(req.getIdTipo()))
            .armazenado(container)
            .condicao(Condicao.DISPONIVEL)
            .build(); */

        /* Carga carga = new Carga();
        carga.setCarga(container);
        carga.setDescricao(req.getDescricao());
        carga.setTipo(Tipo.fromInteger(req.getIdTipo())); */

        //return cargaRepository.save(carga);

    }

    public List<Carga> list(String numero) {
        Container container = containerRepository.findByNumero(numero).orElseThrow(() -> new NotFoundException("Container nao encontrado"));
        List<Carga> cargas = cargaRepository.findByArmazenado(container);
        return cargas;
    }

    public Carga loadCarga(String numero, Integer idCarga) {

        Container container = containerRepository.findByNumero(numero)
                .orElseThrow(() -> new NotFoundException("Container nao encontrado"));

        Carga carga = cargaRepository.findById(idCarga)
                .orElseThrow(() -> new NotFoundException("Carga nao encontrada"));

        if ( ! carga.getArmazenado().getId().equals(container.getId())) {
            throw new IllegalStateException("Carga nao pertence ao container");
        }

        return carga;

    }

    public Carga update(String numero, Integer idCarga, CargaRequest req) {
        
        if (! containerRepository.existsByNumero(numero)) {
            throw new NotFoundException("Container nao encontrado");
        }

        Carga carga = cargaRepository.findById(idCarga).orElseThrow(() -> new NotFoundException("Carga nao encontrada"));
        
        carga.setDescricao(req.getDescricao());
        carga.setTipo(Tipo.fromInteger(req.getIdTipo()));
        
        return cargaRepository.save(carga);
        
    }
    
    public Carga updateCondicao(String numero, Integer idCarga, CondicaoRequest req) {
        
        if (!containerRepository.existsByNumero(numero)) {
            throw new NotFoundException("Container nao encontrado");
        }
        
        Carga carga = cargaRepository.findById(idCarga).orElseThrow(() -> new NotFoundException("Carga nao encontrada"));
        
        Condicao novaCondicao = Condicao.valueOf(req.getCondicao());

        if (novaCondicao.equals(Condicao.CANCELADA)) {
            switch (carga.getCondicao()) {
                case SUSPENSA: throw new IllegalArgumentException("Ja esta SUSPENSA");
                case CANCELADA: throw new IllegalArgumentException("Ja esta CANCELADA");
                case RESERVADA: throw new IllegalArgumentException("Ja esta RESERVADA");
                case CONCLUIDA: throw new IllegalArgumentException("Ja esta CONCLUIDA");
                case DISPONIVEL: throw new IllegalArgumentException("Ja esta DISPONIVEL");
                default:
                    break;
            }
            carga.setCondicao(novaCondicao);
            return cargaRepository.save(carga);
        }
        carga.setCondicao(novaCondicao);
        return cargaRepository.save(carga);
        //throw new UnsupportedOperationException("Nao implementado");
    }
    
}
