package com.example.springfront.service;

import com.example.springfront.Error.ResourceNotFoundException;
import com.example.springfront.model.Produto;
import com.example.springfront.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepositorio;

    public List<Produto> getProduto() {
        return this.produtoRepositorio.findAll();
    }

    public List<Produto> ListarporC() {
        return this.produtoRepositorio.findProdutosComecandoCom("C");
    }

    @Transactional
    public Produto inserir(Produto produto) {
      //  if (produto.getNome().length() >= 20) {
        //    throw new ResourceNotFoundException("O limite de caracteres para a mensagem é de 20");

      //  }
        if (produto.getPreco() <= 0.0) {
            throw new ResourceNotFoundException("O preço do produto deve ser maior que 0");
        }
        Optional<Produto> produtoExistente = produtoRepositorio.findByNome(produto.getNome());
        if (produtoExistente.isPresent()) {
            throw new ResourceNotFoundException("Nao pode adicinar o nome igual");
        }
        if (produto.getDescricao().length() >= 225) {
            throw new ResourceNotFoundException("O limite de caracteres para a descrição é de 225");

        }
        return this.produtoRepositorio.save(produto);
    }

    public void apagar(Long id) {
        if (produtoRepositorio.existsById(id)) {
            produtoRepositorio.deleteById(id);
        } else {
        }
    }

        public Produto getProdutoPorId (Long idProduto){
            return this.produtoRepositorio.findById(idProduto).orElse(null);
        }

        public Produto getProdutoPorNome (String nomeProduto){
        return this.produtoRepositorio.findByNome(nomeProduto).orElse(null);
        }


        public List<Produto> getProdutosPorNomes(String nomeProduto) {
        return this.produtoRepositorio.findByNomeContaining(nomeProduto); // Usando "Containing" para fazer uma busca por substrings
    }

    public void editar (Produto produto){
        if (produtoRepositorio.existsById(produto.getId())) {
            produtoRepositorio.save(produto);
        } else {
        }
    }

    }
