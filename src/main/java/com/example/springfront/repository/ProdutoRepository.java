package com.example.springfront.repository;




import com.example.springfront.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT p FROM Produto p WHERE p.nome LIKE :prefix%")
    List<Produto> findProdutosComecandoCom(@Param("prefix") String prefix);


    Optional<Produto> findByNome(String nome);


    List<Produto> findByNomeContaining(String nome);

    Optional<Produto> findByDescricao(String descricao);



   //@Query("SELECT p.descricao FROM Produto p WHERE p.id == id")


}
