package com.jairlopesjunior.controleveiculo.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class UsuarioResponseDTO {

    @ApiModelProperty(name = "id", value = "Id do Usuario", position = 0)
    private Integer idUsuario;

    @Size(message = "O nome do usuário não pode ultrapassar {max} caracteres.", max = 80)
    @ApiModelProperty(name = "nome", value = "Nome do Usuario", position = 1)
    @NotNull(message = "Campo nome é obrigatório.")
    private String nome;

    @ApiModelProperty(name = "email", value = "Email do Usuario", position = 2)
    @Email(message = "Informe um Email valido.")
    private String email;

    @ApiModelProperty(name = "cpf", value = "CPF do Usuario", position = 3)
    @CPF(message = "Informe um CPF valido.")
    private String cpf;

    @ApiModelProperty(name = "data de nascimento", value = "Data de nascimento do Usuario", position = 4)
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
