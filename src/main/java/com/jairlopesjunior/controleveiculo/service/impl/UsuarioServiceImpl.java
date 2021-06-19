package com.jairlopesjunior.controleveiculo.service.impl;

import com.jairlopesjunior.controleveiculo.domain.entities.Usuario;
import com.jairlopesjunior.controleveiculo.domain.repositories.UsuarioRepository;
import com.jairlopesjunior.controleveiculo.rest.dto.UsuarioDTO;
import com.jairlopesjunior.controleveiculo.rest.dto.VeiculosDTO;
import com.jairlopesjunior.controleveiculo.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public UsuarioDTO save( UsuarioDTO usuarioDTO ){
        Usuario usuarioConvertido = converterDtoParaEntity(usuarioDTO);
        Usuario usuarioSalvo = usuarioRepository.save(usuarioConvertido);
        UsuarioDTO usuarioDesconvertido = converterEntityParaDto(usuarioSalvo);
        return usuarioDesconvertido;
    }

    @Override
    public VeiculosDTO getUserById(Integer id ) {
        return usuarioRepository.findById(id)
                .map( usuarioEncontrado -> {
                    VeiculosDTO veiculosDTO = converterVeiculosParaDto(usuarioEncontrado);
                    return veiculosDTO;
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
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setCpf(usuario.getCpf());
        dto.setEmail(usuario.getEmail());
        dto.setDataNascimento(usuario.getDataNascimento());
        return dto;
    }

    private VeiculosDTO converterVeiculosParaDto( Usuario usuario ){
        VeiculosDTO dto = new VeiculosDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setCpf(usuario.getCpf());
        dto.setEmail(usuario.getEmail());
        dto.setDataNascimento(usuario.getDataNascimento());
        dto.setVeiculos(usuario.getVeiculos());
        return dto;
    }
}
