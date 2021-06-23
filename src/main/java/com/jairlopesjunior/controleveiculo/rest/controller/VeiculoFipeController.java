package com.jairlopesjunior.controleveiculo.rest.controller;

import com.jairlopesjunior.controleveiculo.domain.entities.TodasMarcasVeiculoFipe;
import com.jairlopesjunior.controleveiculo.service.VeiculoFipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class VeiculoFipeController {

    @Autowired
    private VeiculoFipeService veiculoFipeService;

    @Autowired
    private VeiculoFipeController veiculoFipeController;

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
}
