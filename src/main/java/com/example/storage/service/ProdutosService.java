package com.example.storage.service;


import com.example.storage.exception.ProdutosNotFoundException;
import com.example.storage.model.Produtos;
import com.example.storage.repository.ProdutosRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class ProdutosService  {



    private final ProdutosRepository produtosRepository;

    public ProdutosService(ProdutosRepository produtosRepository) {

        this.produtosRepository = produtosRepository;
    }

    @Transactional
    public List<Produtos> findAll() {

        return produtosRepository.findAll();
    }

    public Produtos findById(String id) {
        return produtosRepository.findById(id).orElseThrow(() ->
                new ProdutosNotFoundException(id));
    }


    public Produtos create(Produtos produtosCreate) {
        String uuid = getUUID();
        produtosCreate.setId(uuid);
        produtosRepository.save(produtosCreate);

        return produtosCreate;
    }

    public void delete(String id) {
        findById(id);
        produtosRepository.deleteById(id);
    }


    public Produtos update(String id, Produtos produtosCreate) {
        Produtos produtos = findById(id);
        produtos.setMarca(produtosCreate.getMarca());
        produtos.setQuantidade(produtosCreate.getQuantidade());
        produtos.setTipo(produtosCreate.getTipo());
        produtosRepository.save(produtos);
        return produtos;
    }

    private static String getUUID() {

        return UUID.randomUUID().toString().replace("-", "");
    }

}
