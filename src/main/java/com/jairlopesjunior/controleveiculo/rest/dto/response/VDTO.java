package com.jairlopesjunior.controleveiculo.rest.dto.response;

import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

public class VDTO {

    @ApiModelProperty(name = "valor", value = "Valor do Veiculo", example = "50.00", position = 0)
    private String Valor;

    @ApiModelProperty(name = "marca", value = "Marca do Veiculo", example = "Fiat", position = 1)
    private String marca;

    @ApiModelProperty(name = "modelo", value = "Modelo do Veiculo", example = "Uno", position = 2)
    private String modelo;

    @ApiModelProperty(name = "anoModelo", value = "anoModelo", example = "01/01/2021", position = 3)
    private LocalDate anoModelo;

    @ApiModelProperty(name = "combustivel", value = "Combustivel", position = 4)
    private String Combustivel;

    @ApiModelProperty(name = "CodigoFipe", value = "CodigoFipe", position = 5)
    private String CodigoFipe;

    @ApiModelProperty(name = "MesReferencia", value = "MesReferencia", position = 6)
    private String MesReferencia;

    @ApiModelProperty(name = "TipoVeiculo", value = "TipoVeiculo", position = 7)
    private Integer TipoVeiculo;

    @ApiModelProperty(name = "SiglaCombustivel", value = "SiglaCombustivel", position = 8)
    private String SiglaCombustivel;

    public String getValor() {
        return Valor;
    }

    public void setValor(String valor) {
        Valor = valor;
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

    public LocalDate getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(LocalDate anoModelo) {
        this.anoModelo = anoModelo;
    }

    public String getCombustivel() {
        return Combustivel;
    }

    public void setCombustivel(String combustivel) {
        Combustivel = combustivel;
    }

    public String getCodigoFipe() {
        return CodigoFipe;
    }

    public void setCodigoFipe(String codigoFipe) {
        CodigoFipe = codigoFipe;
    }

    public String getMesReferencia() {
        return MesReferencia;
    }

    public void setMesReferencia(String mesReferencia) {
        MesReferencia = mesReferencia;
    }

    public Integer getTipoVeiculo() {
        return TipoVeiculo;
    }

    public void setTipoVeiculo(Integer tipoVeiculo) {
        TipoVeiculo = tipoVeiculo;
    }

    public String getSiglaCombustivel() {
        return SiglaCombustivel;
    }

    public void setSiglaCombustivel(String siglaCombustivel) {
        SiglaCombustivel = siglaCombustivel;
    }
}


