package com.jairlopesjunior.controleveiculo.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VeiculoRequestDTO {

    @ApiModelProperty(name = "Id do Usuário", value = "Id do Usuario", example = "1", position = 0)
    private Integer idUsuario;

    @Size(message = "A marca do veiculo não pode ultrapassar {max} caracteres.", max = 80)
    @ApiModelProperty(name = "marca", value = "Marca do Veiculo", example = "FIAT", position = 1)
    @NotEmpty(message = "Campo marca é obrigatório.")
    private String marca;

    @Size(message = "O modelo do veiculo não pode ultrapassar {max} caracteres.", max = 80)
    @ApiModelProperty(name = "modelo", value = "Modelo do Veiculo", example = "Palio 1.0 ECONOMY Fire Flex 8V 4p", position = 2)
    @NotEmpty(message = "Campo modelo é obrigatório.")
    private String modelo;

    @ApiModelProperty(name = "ano", value = "Ano do Veiculo", example = "01/01/2013", position = 3)
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate ano;

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public LocalDate getAno() {
        return ano;
    }

    public void setAno(LocalDate ano) {
        this.ano = ano;
    }

}

