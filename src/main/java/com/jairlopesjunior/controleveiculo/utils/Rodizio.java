package com.jairlopesjunior.controleveiculo.utils;

import java.time.LocalDate;

public class Rodizio {

    public String verificarDiaDoRodizio(Integer ano){
        String ultimoDigitoString = (ano.toString()).substring(3, 4);
        Integer ultimoDigitoInteger = Integer.parseInt(ultimoDigitoString);
        if(ultimoDigitoInteger < 2){
            return "Segunda-feira";
        }else if(ultimoDigitoInteger <  4){
            return "Terça-feira";
        }else if(ultimoDigitoInteger < 6){
            return "Quarta-feira";
        }else if(ultimoDigitoInteger < 8){
            return "Quarta-feira";
        }
        return "Sexta-feira";
    }

    public boolean isAtivo(Integer ano){
        String diaSemanaAtual = LocalDate.now().getDayOfWeek().name();
        String diaSemanaAtualConvertido = converterPalavra(diaSemanaAtual);
        String diaVeiculoAtivo = verificarDiaDoRodizio(ano);
        return diaVeiculoAtivo.equals(diaSemanaAtualConvertido) ? true : false ;
    }

    private String converterPalavra(String diaSemana){
        switch(diaSemana){
            case "Monday":
                return "Segunda-feira";
            case "Tuesday":
                return "Terça-feira";
            case "Wednesday":
                return "Quarta-feira";
            case "Thursday":
                return "Quinta-feira";
            case "Friday":
                return "Sexta-feira";
            default:
                return "";
        }
    }
}
