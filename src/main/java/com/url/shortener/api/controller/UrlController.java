package com.url.shortener.api.controller;

import com.url.shortener.api.dto.UrlDTO;
import com.url.shortener.domain.service.UrlService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/url")
@AllArgsConstructor
public class UrlController {

    @Autowired //injeta o objeto UrlService no controller
    private UrlService urlservice;

    @PostMapping //mapeia o endpoint para criar um url
    public String gerarUrl(@RequestBody UrlDTO request) { //recebe o objeto UrlDTO com a url e a data de expiração
        return urlservice.encurtarUrl(request);//retorna o id do url encurtado
    }

    @GetMapping(value = "/{shortUrl}") //mapeia o endpoint para buscar um url
    public ResponseEntity<Void> buscarUrl(@PathVariable String shortUrl) { //recebe o id do url encurtado
        var url = urlservice.descriptorUrl(shortUrl); //chama o método descriptorUrl do objeto UrlService
        return ResponseEntity.status(HttpStatus.FOUND) //retorna o status de encontrado
                .location //seta o cabeçalho de resposta com o código de status e a url encontrada
                        (URI //cria o cabeçalho de resposta com o caminho da url encurtada
                        .create(url)) //retorna o código de status e a url encontrada
                .build(); //indica que ocorreu um redirect para a url encontrada
    }
}
