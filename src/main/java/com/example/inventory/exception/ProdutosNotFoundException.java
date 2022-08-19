package com.example.inventory.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ProdutosNotFoundException extends RuntimeException {
    public ProdutosNotFoundException(String id) {
        super("Produto não encontrado pelo id" + id);
    }
}