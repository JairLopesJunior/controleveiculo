package com.jairlopesjunior.controleveiculo.service.impl;

import com.jairlopesjunior.controleveiculo.domain.entities.Usuario;
import com.jairlopesjunior.controleveiculo.domain.entities.Veiculo;
import com.jairlopesjunior.controleveiculo.domain.repositories.UsuarioRepository;
import com.jairlopesjunior.controleveiculo.rest.dto.UsuarioDTO;
import com.jairlopesjunior.controleveiculo.rest.dto.UsuarioVeiculosDTO;
import com.jairlopesjunior.controleveiculo.rest.dto.VeiculoDTO;
import com.jairlopesjunior.controleveiculo.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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
    public UsuarioVeiculosDTO getUserById(Integer id ) {
        return usuarioRepository.findById(id)
                .map( usuarioEncontrado -> {
                    UsuarioVeiculosDTO dto = converterUsuarioVeiculosParaDto(usuarioEncontrado);
                    return dto;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado." ));
    }

    // Poderia utilizar MapStruct aqui
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

    private UsuarioVeiculosDTO converterUsuarioVeiculosParaDto( Usuario usuario ){
        UsuarioVeiculosDTO dto = new UsuarioVeiculosDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setCpf(usuario.getCpf());
        dto.setEmail(usuario.getEmail());
        dto.setDataNascimento(usuario.getDataNascimento());
        List<VeiculoDTO> lista = new ArrayList<>();
        for(Veiculo v : usuario.getVeiculos()){
            VeiculoDTO veiculoDTO = new VeiculoDTO();
            veiculoDTO.setId(v.getId());
            veiculoDTO.setValor(v.getValor());
            veiculoDTO.setMarca(v.getMarca());
            veiculoDTO.setModelo(v.getModelo());
            veiculoDTO.setAno(v.getAno());
            lista.add(veiculoDTO);
        }
        dto.setVeiculos(lista);
        return dto;
    }
}
