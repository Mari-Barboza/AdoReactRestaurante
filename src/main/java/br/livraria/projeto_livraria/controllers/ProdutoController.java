package br.livraria.projeto_livraria.controllers;

import br.livraria.projeto_livraria.model.Produto;
import br.livraria.projeto_livraria.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/produtos") // Endpoint para acessar os produtos
    public List<ProdutoResponse> getAllProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        
        // Mapear a lista de produtos para uma lista de ProdutoResponse com apenas id e titulo
        return produtos.stream()
                .map(produto -> new ProdutoResponse(produto.getId(), produto.getTitulo()))
                .collect(Collectors.toList());
    }

    // Classe interna que irá representar os dados que queremos expor no endpoint
    public static class ProdutoResponse {
        private String id;
        private String titulo;

        public ProdutoResponse(String id, String titulo) {
            this.id = id;
            this.titulo = titulo;
        }

        public String getId() {
            return id;
        }

        public String getTitulo() {
            return titulo;
        }
    }
}
