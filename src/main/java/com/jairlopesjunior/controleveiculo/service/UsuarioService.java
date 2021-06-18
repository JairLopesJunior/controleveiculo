package com.jairlopesjunior.controleveiculo.service;

import com.jairlopesjunior.controleveiculo.rest.dto.UsuarioDTO;
import org.springframework.stereotype.Service;

public interface UsuarioService {

    UsuarioDTO save( UsuarioDTO usuarioDTO );
    UsuarioDTO getUserById( Integer id );
}
