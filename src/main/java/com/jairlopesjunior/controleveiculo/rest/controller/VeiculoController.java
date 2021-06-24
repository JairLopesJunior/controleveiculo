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

    @GetMapping("/veiculos/{idMarca}")
    public ResponseEntity<List<MarcaEspecificaVeiculoFipe>> getMarca( @PathVariable("idMarca") String marca ) {
        List<MarcaEspecificaVeiculoFipe> marcasEncontradas = veiculoFipeService.buscaMarcas(marca)
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

        if(marcasEncontradas == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.accepted().body(marcasEncontradas);
    }

    @GetMapping("/veiculos/{idMarca}/{idModelo}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<ModeloEspecificoVeiculoFipe>> getModelo( @PathVariable("idMarca") String marca, @PathVariable("idModelo") String modelo ) {
        List<ModeloEspecificoVeiculoFipe> modelosEncontrados = veiculoFipeService.buscaModelos( marca, modelo )
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

        if(modelosEncontrados == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.accepted().body(modelosEncontrados);
    }

    @GetMapping("/veiculos/{idMarca}/{idModelo}/{idAno}")
    public ResponseEntity<AnoEspecificoVeiculoFipe> getVeiculo(@PathVariable("idMarca") String marca, @PathVariable("idModelo") String modelo,
                                                               @PathVariable("idAno") String ano ) {
        AnoEspecificoVeiculoFipe anoVeiculoEncontrado = veiculoFipeService.buscaVeiculoPeloAno( marca, modelo, ano );

        if(anoVeiculoEncontrado == null)
            return ResponseEntity.notFound().build();

        AnoEspecificoVeiculoFipe anoVeiculo = new AnoEspecificoVeiculoFipe();
        anoVeiculo.setVeiculo(anoVeiculoEncontrado.getVeiculo());
        anoVeiculo.setFipe_codigo(anoVeiculoEncontrado.getFipe_codigo());
        anoVeiculo.setId(anoVeiculoEncontrado.getId());
        anoVeiculo.setKey(anoVeiculoEncontrado.getKey());
        anoVeiculo.setCombustivel(anoVeiculoEncontrado.getCombustivel());
        anoVeiculo.setMarca(anoVeiculoEncontrado.getMarca());
        anoVeiculo.setPreco(anoVeiculoEncontrado.getPreco());
        anoVeiculo.setReferencia(anoVeiculoEncontrado.getReferencia());
        anoVeiculo.setTime(anoVeiculoEncontrado.getTime());
        anoVeiculo.setAno_modelo(anoVeiculoEncontrado.getAno_modelo());
        anoVeiculo.setName(anoVeiculoEncontrado.getName());
        return ResponseEntity.accepted().body(anoVeiculoEncontrado);
    }

}
