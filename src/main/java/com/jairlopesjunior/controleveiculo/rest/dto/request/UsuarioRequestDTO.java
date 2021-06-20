package com.jairlopesjunior.controleveiculo.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsuarioRequestDTO {

    @ApiModelProperty(name = "id", value = "Id do Usuario", example = "1", position = 0)
    private Integer idUsuario;

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
