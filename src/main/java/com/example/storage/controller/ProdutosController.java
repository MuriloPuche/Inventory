package com.example.storage.controller;


import com.example.storage.controller.dto.ProdutosDto;
import com.example.storage.controller.mapper.ProdutosMapper;
import com.example.storage.model.Produtos;
import com.example.storage.repository.ProdutosRepository;
import com.example.storage.service.ProdutosService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materiais")
public class ProdutosController {

    private final ProdutosService produtosService;
    private final ProdutosRepository produtosRepository;
    private final ProdutosMapper produtosMapper;


    public ProdutosController(ProdutosService produtosService, ProdutosRepository produtosRepository, ModelMapper modelMapper, ProdutosMapper produtosMapper) {
        this.produtosService = produtosService;
        this.produtosRepository = produtosRepository;
        this.produtosMapper = produtosMapper;

    }

    @GetMapping
    public ResponseEntity<List<ProdutosDto>> findAll() {
        List<Produtos> produtosList = produtosService.findAll();
        List<ProdutosDto> result = produtosMapper.produtosDtoList(produtosList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutosDto> findById(@PathVariable String id) {
        Produtos produtos = produtosService.findById(id);
        ProdutosDto result = produtosMapper.toProdutosDto(produtos);
        return ResponseEntity.ok(result);
    }


    @PostMapping
    public ResponseEntity<ProdutosDto> create(@RequestBody ProdutosDto dto) {
        var produtosCreate = produtosMapper.toProdutosCreate(dto);
        var produtos = produtosService.create(produtosCreate);
        var result = produtosMapper.toProdutosDto(produtos);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping("{/id}")
    public ResponseEntity delete(String id) {
        produtosService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("{/id}")
    public ResponseEntity<ProdutosDto> update(String id, @RequestBody ProdutosDto produtosCreateDto) {
        var produtosUpdate = produtosMapper.toProdutosCreate(produtosCreateDto);
        var produtos = produtosService.update(id, produtosUpdate);
        return ResponseEntity.ok(produtosMapper.toProdutosDto(produtos));
    }


}
