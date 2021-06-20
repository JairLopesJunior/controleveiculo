package com.jairlopesjunior.controleveiculo.rest.controller;

import com.jairlopesjunior.controleveiculo.rest.dto.request.UsuarioRequestDTO;
import com.jairlopesjunior.controleveiculo.rest.dto.response.UsuarioResponseDTO;
import com.jairlopesjunior.controleveiculo.rest.dto.response.UsuarioVeiculosResponseDTO;
import com.jairlopesjunior.controleveiculo.service.UsuarioService;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponseDTO save( @Valid @RequestBody UsuarioRequestDTO dto ){
        return usuarioService.save(dto);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UsuarioVeiculosResponseDTO getUserById( @PathVariable("id") Integer id ){
        return usuarioService.getUserById(id);
    }
}
