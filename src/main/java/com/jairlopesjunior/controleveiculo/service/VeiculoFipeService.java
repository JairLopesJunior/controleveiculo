package com.jairlopesjunior.controleveiculo.service;

import com.jairlopesjunior.controleveiculo.domain.entities.MarcaEspecificaVeiculoFipe;
import com.jairlopesjunior.controleveiculo.domain.entities.MarcaVeiculoFipe;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(url= "http://fipeapi.appspot.com/api/1/carros" , name = "veiculoFipe")
public interface VeiculoFipeService {

    @GetMapping(value = "/marcas.json", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<MarcaVeiculoFipe> buscaTudo();

    @PostMapping(value = "/veiculos/{marca}", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<MarcaEspecificaVeiculoFipe> buscaMarcas(@PathVariable("marca") String marca);

//    @GetMapping(value = "/veiculo/{marca}/{modelo}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    List<VeiculoFipe> buscaModelos(@PathVariable("marca") String marca, @PathVariable("modelo") String modelo);
//
//    @GetMapping(value = "/veiculo/{marca}/{modelo}/{ano}.json", consumes = MediaType.APPLICATION_JSON_VALUE)
//    VeiculoFipe buscaVeiculoPeloAno(@PathVariable("marca") String marca, @PathVariable("modelo") String modelo, @PathVariable("ano") String ano);

}
