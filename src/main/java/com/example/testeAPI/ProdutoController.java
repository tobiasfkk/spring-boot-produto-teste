package com.example.testeAPI;

import com.example.testeAPI.Produto;
import com.example.testeAPI.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Método para criar um novo produto
    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
        Produto novoProduto = produtoRepository.save(produto);
        return ResponseEntity.ok(novoProduto);
    }

    // Buscar produto pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);

        // Verifica se o produto existe
        if (produto.isPresent()) {
            return ResponseEntity.ok(produto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Buscar produto pelo id em JSON
    @GetMapping("/getProdutoById")
    public ResponseEntity<Produto> getProdutoById(@RequestBody ProductRequest productRequest) {
        Long id = productRequest.getId();
        Optional<Produto> produto = produtoRepository.findById(id);

        // Verifica se o produto existe
        if (produto.isPresent()) {
            return ResponseEntity.ok(produto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/updateProdutoById")
    public ResponseEntity<Produto> updateProdutoById(@RequestBody ProductRequest productRequest) {
        Long id = productRequest.getId();
        Optional<Produto> produtoAux = produtoRepository.findById(id);

        // Verifica se o produto existe
        if (produtoAux.isPresent()) {
            Produto produto;
            produto = produtoAux.get();
            produto.setNome(productRequest.getNome());
            produto.setPreco(productRequest.getPreco());
            Produto updatedProduto = produtoRepository.save(produto);
            return ResponseEntity.ok(updatedProduto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}