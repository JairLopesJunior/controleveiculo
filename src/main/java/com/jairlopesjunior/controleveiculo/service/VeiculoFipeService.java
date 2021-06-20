package com.jairlopesjunior.controleveiculo.service;

import com.jairlopesjunior.controleveiculo.rest.dto.response.VDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url= "https://parallelum.com.br/fipe/api/v1/carros/marcas" , name = "veiculoFipe")
public interface VeiculoFipeService {

    @PostMapping(value = "{marca}/modelos/{modelo}/anos/{ano}", produces = MediaType.APPLICATION_JSON_VALUE)
    VDTO buscaValorVeiculo(@PathVariable("marca") String marca, @PathVariable("modelo") String modelo,
                           @PathVariable("ano") String ano );
}
