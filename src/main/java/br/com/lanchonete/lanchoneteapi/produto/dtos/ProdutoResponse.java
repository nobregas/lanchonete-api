package br.com.lanchonete.lanchoneteapi.produto.dtos;

import br.com.lanchonete.lanchoneteapi.produto.Produto;

public record ProdutoResponse(
        String id,
        String nome,
        double preco
) {
    public Produto toProduto() {
        return new Produto(id, nome, preco);
    }
}
