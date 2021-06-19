package com.jairlopesjunior.controleveiculo.service.impl;

import com.jairlopesjunior.controleveiculo.domain.entities.Usuario;
import com.jairlopesjunior.controleveiculo.domain.entities.Veiculo;
import com.jairlopesjunior.controleveiculo.domain.repositories.UsuarioRepository;
import com.jairlopesjunior.controleveiculo.rest.dto.request.UsuarioRequestDTO;
import com.jairlopesjunior.controleveiculo.rest.dto.request.VeiculoRequestDTO;
import com.jairlopesjunior.controleveiculo.rest.dto.response.UsuarioResponseDTO;
import com.jairlopesjunior.controleveiculo.rest.dto.response.UsuarioVeiculosResponseDTO;
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
    public UsuarioResponseDTO save(UsuarioRequestDTO usuarioDTO ){
        Usuario usuarioConvertido = converterDtoParaEntity(usuarioDTO);
        Usuario usuarioSalvo = usuarioRepository.save(usuarioConvertido);
        UsuarioResponseDTO usuarioDesconvertido = converterEntityParaDto(usuarioSalvo);
        return usuarioDesconvertido;
    }

    @Override
    public UsuarioVeiculosResponseDTO getUserById(Integer id ) {
        return usuarioRepository.findById(id)
                .map( usuarioEncontrado -> {
                    UsuarioVeiculosResponseDTO dto = converterUsuarioVeiculosParaDto(usuarioEncontrado);
                    return dto;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado." ));
    }

    // Poderia utilizar MapStruct aqui
    private Usuario converterDtoParaEntity( UsuarioRequestDTO dto ){
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setCpf(dto.getCpf());
        usuario.setEmail(dto.getEmail());
        usuario.setDataNascimento(dto.getDataNascimento());
        return usuario;
    }

    private UsuarioResponseDTO converterEntityParaDto(Usuario usuario ){
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setIdUsuario(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setCpf(usuario.getCpf());
        dto.setEmail(usuario.getEmail());
        dto.setDataNascimento(usuario.getDataNascimento());
        return dto;
    }

    private UsuarioVeiculosResponseDTO converterUsuarioVeiculosParaDto( Usuario usuario ){
        UsuarioVeiculosResponseDTO dto = new UsuarioVeiculosResponseDTO();
        dto.setIdUsuario(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setCpf(usuario.getCpf());
        dto.setEmail(usuario.getEmail());
        dto.setDataNascimento(usuario.getDataNascimento());
        List<VeiculoRequestDTO> lista = new ArrayList<>();
        for(Veiculo v : usuario.getVeiculos()){
            VeiculoRequestDTO veiculoDTO = new VeiculoRequestDTO();
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
