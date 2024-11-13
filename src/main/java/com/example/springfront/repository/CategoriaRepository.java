package com.example.springfront.repository;

import com.example.springfront.model.Categoria;
import com.example.springfront.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {



    Optional<Categoria> findByNome(String nome);

    Optional<Categoria> findById(Long id);



}
