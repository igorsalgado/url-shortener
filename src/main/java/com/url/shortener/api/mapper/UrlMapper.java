package com.url.shortener.api.mapper;

import com.url.shortener.api.dto.UrlDTO;
import com.url.shortener.domain.entity.Url;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class UrlMapper {

    private ModelMapper modelMapper;

    public UrlDTO toDTO(Url url) {
        return modelMapper.map(url, UrlDTO.class);
    } //mapeia o objeto url para o objeto UrlDTO

    public Url toEntity(UrlDTO urlDTO) {
        return modelMapper.map(urlDTO, Url.class);
    } //mapeia o objeto UrlDTO para o objeto Url

//    public List<UrlDTO> toDTO(List<Url> urls) {
//        return modelMapper.map(urls, List.class);
//    }
}
