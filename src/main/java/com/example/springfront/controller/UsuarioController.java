package com.example.springfront.controller;


import com.example.springfront.model.Categoria;
import com.example.springfront.model.Produto;
import com.example.springfront.service.CategoriaService;
import com.example.springfront.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaService categoriaService;



  //  @GetMapping("home/{categoriaId}")
 //   public String listarProdutosPorCategoria(@PathVariable Long categoriaId, Model model) {
        //Categoria categoria = categoriaService.buscasPorId()


    //    List<Produto> produtos = produtoService.getProduto();
     //   model.addAttribute("produtos", produtos);
     //   List<Produto> produtosC = produtoService.ListarporC();
    //    model.addAttribute("produtosC",produtosC);
    //    List<Categoria> categorias = categoriaService.listarTodas();
    //    model.addAttribute("categorias",categorias);
    //    // Passando a lista de produtos para o template
   //     return "usuariosPag"; // Retorna o nome do template (produtos.html)
    }






    //@GetMapping("/detalhes/{id}")
   /// public String detalhesProduto(@PathVariable Long id, Model model) {
    //    Produto produto = produtoService.getProdutoPorId(id);
    //    if (produto != null) {
    //      model.addAttribute("produto", produto);
     //       return "produtodetalhes";
     //   }
      //  return "redirect:/produtos"; // Redireciona caso o produto não seja encontrado
  //  }

