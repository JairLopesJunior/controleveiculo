package com.jairlopesjunior.controleveiculo.service.impl;

import com.jairlopesjunior.controleveiculo.domain.entities.*;
import com.jairlopesjunior.controleveiculo.domain.repositories.UsuarioRepository;
import com.jairlopesjunior.controleveiculo.domain.repositories.VeiculoRepository;
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
        veiculo.setValor(buscarTodasMarcas(dto.getMarca(), dto.getModelo(), dto.getAno()));
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
    private BigDecimal buscarTodasMarcas(String marca, String modelo, LocalDate ano){
        try{
            System.out.println(marca);
            System.out.println(modelo);
            System.out.println(ano);
            List<TodasMarcasVeiculoFipe> lista = veiculoFipeService.buscaTudo();
            System.out.println(lista.get(0).getFipe_name());
            for(TodasMarcasVeiculoFipe marcaVeiculoFipe: lista){
                if(marcaVeiculoFipe.getName().equalsIgnoreCase(marca))
                   return buscarMarca(marcaVeiculoFipe.getId().toString(), modelo, ano);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    private BigDecimal buscarMarca(String marcaEncontrada, String modelo, LocalDate ano){
        List<MarcaEspecificaVeiculoFipe> marcaEspecifica = veiculoFipeService.buscaMarcas(marcaEncontrada);
        System.out.println(marcaEspecifica.get(0).getName());
        for(MarcaEspecificaVeiculoFipe marcaEspecificaVeiculoFipe: marcaEspecifica){
            if(marcaEspecificaVeiculoFipe.getName().equalsIgnoreCase(modelo))
                return buscarModelo(marcaEncontrada, marcaEspecificaVeiculoFipe.getId().toString(), ano);
        }
        return null;
    }

    private BigDecimal buscarModelo(String marcaEncontrada, String modelo, LocalDate ano){
        String anoCompletoConvertido = ano.getMonth().toString() + "-" + String.valueOf(ano.getYear());
        List<ModeloEspecificoVeiculoFipe> modeloEspecifico = veiculoFipeService.buscaModelos(marcaEncontrada ,modelo);
        System.out.println(anoCompletoConvertido);
        for(ModeloEspecificoVeiculoFipe modeloEspecificoVeiculoFipe: modeloEspecifico){
            if(modeloEspecificoVeiculoFipe.getId().equalsIgnoreCase(anoCompletoConvertido))
                return buscarAno(marcaEncontrada, modeloEspecificoVeiculoFipe.getId().toString(), anoCompletoConvertido);
        }
        return null;
    }

    private BigDecimal buscarAno(String marcaEncontrada, String modeloEncontrado, String ano){
        System.out.println("Entrou");
        AnoEspecificoVeiculoFipe anoEspecificoVeiculoFipe = veiculoFipeService.buscaVeiculoPeloAno(marcaEncontrada, modeloEncontrado, ano);
        Integer tamanho = anoEspecificoVeiculoFipe.getPreco().length();
        System.out.println(tamanho);
        String anoConvertido = anoEspecificoVeiculoFipe.getPreco().substring(3, tamanho);
        System.out.println(anoConvertido);
        return new BigDecimal(anoConvertido);
    }

}
