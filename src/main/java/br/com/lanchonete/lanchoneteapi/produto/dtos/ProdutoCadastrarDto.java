package br.com.lanchonete.lanchoneteapi.produto.dtos;

import br.com.lanchonete.lanchoneteapi.produto.Produto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ProdutoCadastrarDto(
       @NotBlank String nome,
       @Min(0) double preco
) {
    public Produto toProduto() {
        return new Produto(nome, preco);
    }
}
