package com.jairlopesjunior.controleveiculo.service.impl;

import com.jairlopesjunior.controleveiculo.domain.entities.Veiculo;
import com.jairlopesjunior.controleveiculo.domain.repositories.UsuarioRepository;
import com.jairlopesjunior.controleveiculo.domain.repositories.VeiculoRepository;
import com.jairlopesjunior.controleveiculo.rest.dto.request.VeiculoRequestDTO;
import com.jairlopesjunior.controleveiculo.rest.dto.response.VDTO;
import com.jairlopesjunior.controleveiculo.rest.dto.response.VeiculoResponseDTO;
import com.jairlopesjunior.controleveiculo.service.VeiculoFipeService;
import com.jairlopesjunior.controleveiculo.service.VeiculoService;
import com.jairlopesjunior.controleveiculo.utils.Rodizio;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class VeiculoServiceImpl implements VeiculoService {

    private VeiculoRepository veiculoRepository;

    private UsuarioRepository usuarioRepository;

    private VeiculoFipeService veiculoFipeService;

    public VeiculoServiceImpl(VeiculoRepository veiculoRepository, UsuarioRepository usuarioRepository, VeiculoFipeService veiculoFipeService) {
        this.veiculoRepository = veiculoRepository;
        this.usuarioRepository = usuarioRepository;
        this.veiculoFipeService = veiculoFipeService;
    }

    @Override
    @Transactional
    public VeiculoResponseDTO save(VeiculoRequestDTO veiculoDTO) {
        return usuarioRepository.findById(veiculoDTO.getId())
            .map( usuarioEncontrado -> {
                Veiculo veiculoConvertido = converterDtoParaEntity(veiculoDTO);
                veiculoConvertido.setUsuario(usuarioEncontrado);
                return converterEntityParaDto(veiculoRepository.save(veiculoConvertido));
            }).orElseThrow( () -> new ResponseStatusException( HttpStatus.NOT_FOUND, "Usuario não encontrado" ));
    }

    private Veiculo converterDtoParaEntity( VeiculoRequestDTO dto ){
        Veiculo veiculo = new Veiculo();
        veiculo.setAno(dto.getAno());
        veiculo.setMarca(dto.getMarca());
        veiculo.setModelo(dto.getModelo());
        veiculo.setValor(buscar(dto.getMarca(), dto.getModelo(), dto.getAno()));
        return veiculo;
    }

    private VeiculoResponseDTO converterEntityParaDto(Veiculo veiculo ){
        VeiculoResponseDTO dto = new VeiculoResponseDTO();
        Rodizio rodizio = new Rodizio();
        Integer anoVeiculo = veiculo.getAno().getYear();
        dto.setId(veiculo.getId());
        dto.setAno(veiculo.getAno());
        dto.setModelo(veiculo.getModelo());
        dto.setMarca(veiculo.getMarca());
        dto.setValor(veiculo.getValor());
        dto.setDiaRodizio(rodizio.verificarDiaDoRodizio(anoVeiculo));
        dto.setRodizio(rodizio.isAtivo(anoVeiculo));
        return dto;
    }

    private BigDecimal buscar(String marca, String modelo, LocalDate ano){
        String anoConvertido = ano.getYear() + "-" + ano.getDayOfMonth();
        VDTO vDTO = veiculoFipeService.buscaValorVeiculo(marca, modelo, anoConvertido);
        return new BigDecimal(vDTO.getValor());
    }
}
