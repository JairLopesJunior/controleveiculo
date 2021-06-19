package com.jairlopesjunior.controleveiculo.rest.controller;

import com.jairlopesjunior.controleveiculo.rest.dto.VeiculoDTO;
import com.jairlopesjunior.controleveiculo.service.VeiculoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {

    private VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @PostMapping
    public VeiculoDTO save( @Valid @RequestBody VeiculoDTO veiculoDTO ){
        return veiculoService.save(veiculoDTO);
    }
}
