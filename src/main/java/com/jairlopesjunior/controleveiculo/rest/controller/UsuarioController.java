package com.jairlopesjunior.controleveiculo.rest.controller;

import com.jairlopesjunior.controleveiculo.rest.dto.UsuarioDTO;
import com.jairlopesjunior.controleveiculo.rest.dto.UsuarioVeiculosDTO;
import com.jairlopesjunior.controleveiculo.service.UsuarioService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/{id}")
    public UsuarioDTO save( @Valid @RequestBody UsuarioDTO dto ){
        return usuarioService.save(dto);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UsuarioVeiculosDTO getUserById(@PathVariable("id") Integer id ){
        return usuarioService.getUserById(id);
    }
}
