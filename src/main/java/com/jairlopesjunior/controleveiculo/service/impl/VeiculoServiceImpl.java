package com.jairlopesjunior.controleveiculo.service.impl;

import com.jairlopesjunior.controleveiculo.domain.entities.*;
import com.jairlopesjunior.controleveiculo.domain.repositories.UsuarioRepository;
import com.jairlopesjunior.controleveiculo.domain.repositories.VeiculoRepository;
import com.jairlopesjunior.controleveiculo.exception.MarcaNotFoundException;
import com.jairlopesjunior.controleveiculo.exception.ModeloNotFoundException;
import com.jairlopesjunior.controleveiculo.rest.dto.request.VeiculoRequestDTO;
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
import java.util.List;

@Service
public class VeiculoServiceImpl implements VeiculoService {

    private final VeiculoRepository veiculoRepository;

    private final UsuarioRepository usuarioRepository;

    private final VeiculoFipeService veiculoFipeService;

    public VeiculoServiceImpl(VeiculoRepository veiculoRepository, UsuarioRepository usuarioRepository, VeiculoFipeService veiculoFipeService) {
        this.veiculoRepository = veiculoRepository;
        this.usuarioRepository = usuarioRepository;
        this.veiculoFipeService = veiculoFipeService;
    }

    @Override
    @Transactional
    public VeiculoResponseDTO save(VeiculoRequestDTO veiculoDTO) {
        return usuarioRepository.findById(veiculoDTO.getIdUsuario())
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
        veiculo.setValor(new BigDecimal(buscarTodasMarcas(dto.getMarca(), dto.getModelo(), dto.getAno())));
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

    @Transactional
    private String buscarTodasMarcas(String marca, String modelo, LocalDate ano){
        List<TodasMarcasVeiculoFipe> lista = veiculoFipeService.buscaTudo();
        for(TodasMarcasVeiculoFipe marcaVeiculoFipe: lista){
            if(marcaVeiculoFipe.getName().equalsIgnoreCase(marca))
               return buscarMarca(marcaVeiculoFipe.getId().toString(), modelo, ano);
        }
        throw new MarcaNotFoundException("Nenhuma marca encontrada com o parametro fornecido.");
    }

    private String buscarMarca(String marcaEncontrada, String modelo, LocalDate ano) {
        List<MarcaEspecificaVeiculoFipe> marcasEncontradas = veiculoFipeService.buscaMarcas(marcaEncontrada);
        for(MarcaEspecificaVeiculoFipe marcaEspecificaVeiculoFipe: marcasEncontradas){
            if(marcaEspecificaVeiculoFipe.getName().equalsIgnoreCase(modelo))
                return buscarModelo(marcaEncontrada, marcaEspecificaVeiculoFipe.getId().toString(), ano);
        }
        throw new ModeloNotFoundException("Nenhum modelo encontrado com o parametro fornecido.");
    }

    private String buscarModelo(String marcaEncontrada, String modeloEncontrado, LocalDate ano){
        String anoCompletoConvertido = String.valueOf(ano.getYear()) + "-" + String.valueOf(ano.getDayOfMonth());
        List<ModeloEspecificoVeiculoFipe> modeloEspecifico = veiculoFipeService.buscaModelos(marcaEncontrada, modeloEncontrado);
        for(ModeloEspecificoVeiculoFipe modeloEspecificoVeiculoFipe: modeloEspecifico){
            if(modeloEspecificoVeiculoFipe.getId().equalsIgnoreCase(anoCompletoConvertido))
                return buscarAno(marcaEncontrada, modeloEncontrado, anoCompletoConvertido);
        }
        return null;
    }

    private String buscarAno(String marcaEncontrada, String modeloEncontrado, String ano){
        AnoEspecificoVeiculoFipe anoEspecificoVeiculoFipe = veiculoFipeService.buscaVeiculoPeloAno(marcaEncontrada, modeloEncontrado, ano);
        Integer tamanho = anoEspecificoVeiculoFipe.getPreco().length();
        String anoConvertido = anoEspecificoVeiculoFipe.getPreco().substring(3, tamanho);
        return anoConvertido.replace(",", "");
    }

}
