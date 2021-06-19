package com.jairlopesjunior.controleveiculo.service;

import com.jairlopesjunior.controleveiculo.rest.dto.request.VeiculoRequestDTO;
import com.jairlopesjunior.controleveiculo.rest.dto.response.VeiculoResponseDTO;

public interface VeiculoService {

    VeiculoResponseDTO save(VeiculoRequestDTO veiculoDTO );
}
