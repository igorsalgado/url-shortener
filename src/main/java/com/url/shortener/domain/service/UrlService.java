package com.url.shortener.domain.service;

import com.url.shortener.api.dto.UrlDTO;
import com.url.shortener.api.mapper.UrlMapper;
import com.url.shortener.domain.entity.Url;
import com.url.shortener.domain.repository.UrlRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

@Service
@AllArgsConstructor
public class UrlService {

    private UrlRepository urlRepository;
    private BaseConversionService baseConversionService;
    private UrlMapper urlMapper;

    public String encurtarUrl(UrlDTO request){
        Url url = urlMapper.toEntity(request); //converte o objeto UrlDTO para o objeto Url
        url.setExpirationDate(request.getExpirationDate()); //seta a data de expiração do url
        url.setCreatedDate(new Date()); //seta a data de criação do url

        var entity = urlRepository.save(url); //salva o url no banco de dados

        return baseConversionService.encode(entity.getId()); //retorna o id do url encurtado
    }

    public String descriptorUrl(String shortUrl){
        var id = baseConversionService.decode(shortUrl); //converte o id do url encurtado para um inteiro
        var url = urlRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Conversão para: " + shortUrl + " Não foi encontrada no banco de dados")); //busca o url no banco de dados

        if(url.getExpirationDate().before(new Date())){ //verifica se a data de expiração do url já passou
            throw new EntityNotFoundException("Conversão para: " + shortUrl + " expirou"); //lança exceção caso a data de expiração do url já tenha passado
        }

        return url.getLongUrl(); //retorna a url
    }


}
