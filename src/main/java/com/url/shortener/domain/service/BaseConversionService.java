package com.url.shortener.domain.service;

import org.springframework.stereotype.Service;

@Service
public class BaseConversionService {

    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private char[] allCharacters = ALPHABET.toCharArray();
    private int base = allCharacters.length;

    public String encode(long input) { //método para codificar o input

        var encoded = new StringBuilder(); //cria um objeto StringBuilder para armazenar o resultado da conversão

        if(input == 0) {
           return String.valueOf(allCharacters[0]); //se o input for 0, retorna o caracter 0
        }

        while(input > 0) {
            encoded.append(allCharacters[(int) (input % base)]); //adiciona o caractere correspondente ao resto da divisão do número por base
            input = input / base; //divide o número por base
        }

        return encoded.reverse().toString(); //retorna o resultado da conversão invertida

    }

    public long decode(String input) { //método para decodificar o input

        var chars = input.toCharArray(); //converte o input em um array de caracteres
        var length = chars.length; //armazena o tamanho do array de caracteres

        var decoded = 0; //cria um objeto long para armazenar o resultado da conversão

        var counter =1; //cria um contador para controlar o índice do array de caracteres
        for (int i = 0; i < length; i++) { //percorre o array de caracteres
            decoded += ALPHABET.indexOf(chars[i]) * Math.pow(base, length - counter); //adiciona o índice do caractere no array de caracteres multiplicado por base elevado ao valor do contador
        }
        return decoded; //retorna o resultado da conversão

    }

}
