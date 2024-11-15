package com.example.springfront.model;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double preco;
    private String descricao;

    // Getters e Setters

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;


}
