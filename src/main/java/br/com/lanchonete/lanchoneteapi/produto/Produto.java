package br.com.lanchonete.lanchoneteapi.produto;

import br.com.lanchonete.lanchoneteapi.produto.dtos.ProdutoResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "produtos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    @Id
    private String id;

    private String nome;

    private Double preco;

    public ProdutoResponse toDto() {
        return new ProdutoResponse(this.id, this.nome, this.preco);
    }

    public Produto(String nome, Double preco) {
        this.nome = nome;
        this.preco = preco;
    }
}
