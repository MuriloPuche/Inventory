package com.example.inventory.controller.mapper;


import com.example.inventory.model.dto.ProdutosDto;
import com.example.inventory.model.Produtos;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component

public class ProdutosMapper {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    @Bean
    public ModelMapper ModelMapper() {
        return new ModelMapper();
    }

    public ProdutosDto toProdutosDto(Produtos produtos) {
            return MODEL_MAPPER.map(produtos, ProdutosDto.class);
    }

    public List<ProdutosDto> produtosDtoList(List<Produtos> produtosList) {
        return produtosList.stream().map(this::toProdutosDto).collect(Collectors.toList());
    }


    public Produtos toProdutos(ProdutosDto dto) {

        return MODEL_MAPPER.map(dto, Produtos.class);
    }

    public Produtos toProdutosCreate(ProdutosDto dto) {

        return MODEL_MAPPER.map(dto, Produtos.class);
    }


}
