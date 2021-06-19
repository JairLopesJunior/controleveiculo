package com.jairlopesjunior.controleveiculo.utils;

public class DiaRodizio {

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
}
