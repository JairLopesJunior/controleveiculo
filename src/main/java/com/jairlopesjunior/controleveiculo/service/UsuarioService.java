package com.jairlopesjunior.controleveiculo.service;

import com.jairlopesjunior.controleveiculo.rest.dto.UsuarioDTO;
import com.jairlopesjunior.controleveiculo.rest.dto.VeiculosDTO;
import org.springframework.stereotype.Service;

public interface UsuarioService {

    UsuarioDTO save( UsuarioDTO usuarioDTO );
    VeiculosDTO getUserById(Integer id );
}
