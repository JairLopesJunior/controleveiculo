package com.jairlopesjunior.controleveiculo.service;

import com.jairlopesjunior.controleveiculo.rest.dto.request.UsuarioRequestDTO;
import com.jairlopesjunior.controleveiculo.rest.dto.response.UsuarioResponseDTO;
import com.jairlopesjunior.controleveiculo.rest.dto.response.UsuarioVeiculosResponseDTO;

public interface UsuarioService {

    UsuarioResponseDTO save(UsuarioRequestDTO usuarioDTO );
    UsuarioVeiculosResponseDTO getUserById(Integer id );
}
