package com.jairlopesjunior.controleveiculo.service.impl;

import com.jairlopesjunior.controleveiculo.domain.entities.Veiculo;
import com.jairlopesjunior.controleveiculo.domain.repositories.UsuarioRepository;
import com.jairlopesjunior.controleveiculo.domain.repositories.VeiculoRepository;
import com.jairlopesjunior.controleveiculo.rest.dto.VeiculoDTO;
import com.jairlopesjunior.controleveiculo.service.VeiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Service
public class VeiculoServiceImpl implements VeiculoService {

    private VeiculoRepository veiculoRepository;

    private UsuarioRepository usuarioRepository;

    public VeiculoServiceImpl(VeiculoRepository veiculoRepository, UsuarioRepository usuarioRepository) {
        this.veiculoRepository = veiculoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public VeiculoDTO save(VeiculoDTO veiculoDTO) {
        return usuarioRepository.findById(veiculoDTO.getId())
                .map( usuarioEncontrado -> {
                    Veiculo veiculoConvertido = converterDtoParaEntity(veiculoDTO);
                    veiculoConvertido.setUsuario(usuarioEncontrado);
                    Veiculo veiculoSalvo = veiculoRepository.save(veiculoConvertido);
                    VeiculoDTO veiculoDesconvertido = converterEntityParaDto(veiculoSalvo);
                    return veiculoDesconvertido;
                }).orElseThrow( () -> new ResponseStatusException( HttpStatus.NOT_FOUND, "Usuario não encontrado" ));
    }

    private Veiculo converterDtoParaEntity( VeiculoDTO dto ){
        Veiculo veiculo = new Veiculo();
        veiculo.setAno(dto.getAno());
        veiculo.setMarca(dto.getMarca());
        veiculo.setModelo(dto.getModelo());
        veiculo.setValor(dto.getValor());
        return veiculo;
    }

    private VeiculoDTO converterEntityParaDto( Veiculo usuario ){
        VeiculoDTO dto = new VeiculoDTO();
        dto.setId(usuario.getId());
        dto.setAno(usuario.getAno());
        dto.setModelo(usuario.getModelo());
        dto.setMarca(usuario.getMarca());
        dto.setValor(usuario.getValor());
        return dto;
    }
}
