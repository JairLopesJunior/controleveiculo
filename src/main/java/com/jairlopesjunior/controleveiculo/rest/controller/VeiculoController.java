package com.jairlopesjunior.controleveiculo.rest.controller;

import com.jairlopesjunior.controleveiculo.domain.entities.AnoEspecificoVeiculoFipe;
import com.jairlopesjunior.controleveiculo.domain.entities.MarcaEspecificaVeiculoFipe;
import com.jairlopesjunior.controleveiculo.domain.entities.ModeloEspecificoVeiculoFipe;
import com.jairlopesjunior.controleveiculo.domain.entities.TodasMarcasVeiculoFipe;
import com.jairlopesjunior.controleveiculo.rest.dto.request.VeiculoRequestDTO;
import com.jairlopesjunior.controleveiculo.rest.dto.response.VeiculoResponseDTO;
import com.jairlopesjunior.controleveiculo.service.VeiculoFipeService;
import com.jairlopesjunior.controleveiculo.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    private VeiculoFipeService veiculoFipeService;

    @Autowired
    private VeiculoFipeController veiculoFipeController;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VeiculoResponseDTO save(@Valid @RequestBody VeiculoRequestDTO veiculoDTO ){
        return veiculoService.save(veiculoDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<TodasMarcasVeiculoFipe> getVeiculo() {
        return veiculoFipeService.buscaTudo()
            .stream()
            .map(marca -> {
                TodasMarcasVeiculoFipe marcaVeiculoFipe = new TodasMarcasVeiculoFipe();
                marcaVeiculoFipe.setFipe_name(marca.getFipe_name());
                marcaVeiculoFipe.setId(marca.getId());
                marcaVeiculoFipe.setKey(marca.getKey());
                marcaVeiculoFipe.setName(marca.getName());
                marcaVeiculoFipe.setOrder(marca.getOrder());
                return marcaVeiculoFipe;
            })
            .collect(Collectors.toList());
    }

    @GetMapping("/veiculos/{marca}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<MarcaEspecificaVeiculoFipe> getMarca( @PathVariable("marca") String marca ) {
        return veiculoFipeService.buscaMarcas(marca)
            .stream()
            .map(marcaEncontrada -> {
                MarcaEspecificaVeiculoFipe marcaEspecificaVeiculoFipe = new MarcaEspecificaVeiculoFipe();
                marcaEspecificaVeiculoFipe.setMarca(marcaEncontrada.getMarca());
                marcaEspecificaVeiculoFipe.setFipe_marca(marcaEncontrada.getFipe_marca());
                marcaEspecificaVeiculoFipe.setName(marcaEncontrada.getName());
                marcaEspecificaVeiculoFipe.setFipe_name(marcaEncontrada.getFipe_name());
                marcaEspecificaVeiculoFipe.setKey(marcaEncontrada.getKey());
                marcaEspecificaVeiculoFipe.setId(marcaEncontrada.getId());
                return marcaEspecificaVeiculoFipe;
            })
            .collect(Collectors.toList());
    }

    @GetMapping("/veiculos/{marca}/{modelo}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<ModeloEspecificoVeiculoFipe> getModelo( @PathVariable("marca") String marca, @PathVariable("modelo") String modelo ) {
        return veiculoFipeService.buscaModelos( marca, modelo )
                .stream()
                .map(modeloEncontrado -> {
                    ModeloEspecificoVeiculoFipe modeloEspecificoVeiculoFipe = new ModeloEspecificoVeiculoFipe();
                    modeloEspecificoVeiculoFipe.setVeiculo(modeloEncontrado.getVeiculo());
                    modeloEspecificoVeiculoFipe.setFipe_codigo(modeloEncontrado.getFipe_codigo());
                    modeloEspecificoVeiculoFipe.setId(modeloEncontrado.getId());
                    modeloEspecificoVeiculoFipe.setFipe_marca(modeloEncontrado.getFipe_marca());
                    modeloEspecificoVeiculoFipe.setKey(modeloEncontrado.getKey());
                    modeloEspecificoVeiculoFipe.setName(modeloEncontrado.getName());
                    modeloEspecificoVeiculoFipe.setMarca(modeloEncontrado.getMarca());
                    return modeloEspecificoVeiculoFipe;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/veiculos/{marca}/{modelo}/{ano}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<AnoEspecificoVeiculoFipe> getVeiculo(@PathVariable("marca") String marca, @PathVariable("modelo") String modelo,
                                               @PathVariable("ano") String ano ) {
        AnoEspecificoVeiculoFipe anoEspecificoVeiculoFipeEncontrado = veiculoFipeService.buscaVeiculoPeloAno( marca, modelo, ano );

        if(anoEspecificoVeiculoFipeEncontrado == null)
            return ResponseEntity.notFound().build();

        AnoEspecificoVeiculoFipe anoEspecificoVeiculoFipe = new AnoEspecificoVeiculoFipe();
        anoEspecificoVeiculoFipe.setVeiculo(anoEspecificoVeiculoFipeEncontrado.getVeiculo());
        anoEspecificoVeiculoFipe.setFipe_codigo(anoEspecificoVeiculoFipeEncontrado.getFipe_codigo());
        anoEspecificoVeiculoFipe.setId(anoEspecificoVeiculoFipeEncontrado.getId());
        anoEspecificoVeiculoFipe.setKey(anoEspecificoVeiculoFipeEncontrado.getKey());
        anoEspecificoVeiculoFipe.setCombustivel(anoEspecificoVeiculoFipeEncontrado.getCombustivel());
        anoEspecificoVeiculoFipe.setMarca(anoEspecificoVeiculoFipeEncontrado.getMarca());
        anoEspecificoVeiculoFipe.setPreco(anoEspecificoVeiculoFipeEncontrado.getPreco());
        anoEspecificoVeiculoFipe.setReferencia(anoEspecificoVeiculoFipeEncontrado.getReferencia());
        anoEspecificoVeiculoFipe.setTime(anoEspecificoVeiculoFipeEncontrado.getTime());
        anoEspecificoVeiculoFipe.setAno_modelo(anoEspecificoVeiculoFipeEncontrado.getAno_modelo());
        anoEspecificoVeiculoFipe.setName(anoEspecificoVeiculoFipeEncontrado.getName());
        return ResponseEntity.accepted().body(anoEspecificoVeiculoFipeEncontrado);
    }

}
