package com.example.testeAPI;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.testeAPI.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
