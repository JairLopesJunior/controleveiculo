package com.jairlopesjunior.controleveiculo.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jairlopesjunior.controleveiculo.domain.entities.Veiculo;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.Authorization;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

public class UsuarioDTO {

    @ApiModelProperty(name = "id", value = "Id do Usuario", example = "null", position = 0)
    private Integer id;

    @Size(message = "O nome do usuário não pode ultrapassar {max} caracteres.", max = 80)
    @ApiModelProperty(name = "nome", value = "Nome do Usuario", example = "James Gosling", position = 1)
    @NotNull(message = "Campo nome é obrigatório.")
    private String nome;

    @ApiModelProperty(name = "email", value = "Email do Usuario", example = "james@gmail.com", position = 2)
    @Email(message = "Informe um Email valido.")
    private String email;

    @ApiModelProperty(name = "cpf", value = "CPF do Usuario", example = "38900232070", position = 3)
    @CPF(message = "Informe um CPF valido.")
    private String cpf;

    @ApiModelProperty(name = "data de nascimento", value = "Data de nascimento do Usuario", example = "01/01/1998", position = 4)
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
