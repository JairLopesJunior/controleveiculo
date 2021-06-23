package com.jairlopesjunior.controleveiculo.service;

import com.jairlopesjunior.controleveiculo.domain.entities.AnoEspecificoVeiculoFipe;
import com.jairlopesjunior.controleveiculo.domain.entities.MarcaEspecificaVeiculoFipe;
import com.jairlopesjunior.controleveiculo.domain.entities.ModeloEspecificoVeiculoFipe;
import com.jairlopesjunior.controleveiculo.domain.entities.TodasMarcasVeiculoFipe;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url= "http://fipeapi.appspot.com/api/1/carros" , name = "veiculoFipe")
public interface VeiculoFipeService {

    @GetMapping(value = "/marcas.json", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<TodasMarcasVeiculoFipe> buscaTudo();

    @GetMapping(value = "/veiculos/{marca}.json", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<MarcaEspecificaVeiculoFipe> buscaMarcas(@PathVariable("marca") String marca);

    @GetMapping(value = "/veiculo/{marca}/{modelo}.json", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<ModeloEspecificoVeiculoFipe> buscaModelos(@PathVariable("marca") String marca, @PathVariable("modelo") String modelo);

    @GetMapping(value = "/veiculo/{marca}/{modelo}/{ano}.json", consumes = MediaType.APPLICATION_JSON_VALUE)
    AnoEspecificoVeiculoFipe buscaVeiculoPeloAno(@PathVariable("marca") String marca, @PathVariable("modelo") String modelo, @PathVariable("ano") String ano);

}
