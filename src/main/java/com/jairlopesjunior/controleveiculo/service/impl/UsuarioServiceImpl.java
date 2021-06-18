package com.jairlopesjunior.controleveiculo.service.impl;

import com.jairlopesjunior.controleveiculo.domain.entities.Usuario;
import com.jairlopesjunior.controleveiculo.domain.repositories.UsuarioRepository;
import com.jairlopesjunior.controleveiculo.rest.dto.UsuarioDTO;
import com.jairlopesjunior.controleveiculo.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioDTO save( UsuarioDTO usuarioDTO ){
        Usuario usuarioConvertido = converterDtoParaEntity(usuarioDTO);
        usuarioRepository.save(usuarioConvertido)
    }

    @Override
    public UsuarioDTO getUserById( Integer id ) {
        return usuarioRepository.findById(id)
                .map( usuarioEncontrado -> {
                    UsuarioDTO usuarioDTO = converterEntityParaDto(usuarioEncontrado);
                    return usuarioDTO;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado." ));
    }

    private Usuario converterDtoParaEntity( UsuarioDTO dto ){
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setCpf(dto.getCpf());
        usuario.setEmail(dto.getEmail());
        usuario.setDataNascimento(dto.getDataNascimento());
        return usuario;
    }

    private UsuarioDTO converterEntityParaDto( Usuario usuario ){
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNome(usuario.getNome());
        dto.setCpf(usuario.getCpf());
        dto.setEmail(usuario.getEmail());
        dto.setDataNascimento(usuario.getDataNascimento());
        dto.setVeiculos(usuario.getVeiculos());
        return dto;
    }
}
