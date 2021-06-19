package com.jairlopesjunior.controleveiculo.service;

import com.jairlopesjunior.controleveiculo.rest.dto.UsuarioDTO;
import com.jairlopesjunior.controleveiculo.rest.dto.UsuarioVeiculosDTO;

public interface UsuarioService {

    UsuarioDTO save( UsuarioDTO usuarioDTO );
    UsuarioVeiculosDTO getUserById(Integer id );
}
