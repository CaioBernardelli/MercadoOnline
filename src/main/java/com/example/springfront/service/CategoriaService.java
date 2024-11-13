package com.example.springfront.service;


import com.example.springfront.Error.ResourceNotFoundException;
import com.example.springfront.model.Categoria;
import com.example.springfront.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;


    public Categoria inserir(Categoria categoria) {
        Optional<Categoria> categoriaexiste = categoriaRepository.findByNome(categoria.getNome());
        if (categoriaexiste.isPresent()) {
            throw new ResourceNotFoundException("nome já existe");
        }

        return categoriaRepository.save(categoria);

    }

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    public void apagar(Long id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
        }
    }
    public Categoria buscasPorId(Long id){
        return this.categoriaRepository.findById(id).orElse(null);
        
    }

}