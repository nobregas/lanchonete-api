package br.com.lanchonete.lanchoneteapi.pedido.dtos;

import br.com.lanchonete.lanchoneteapi.cliente.Cliente;
import br.com.lanchonete.lanchoneteapi.cliente.dtos.ClienteResponse;
import br.com.lanchonete.lanchoneteapi.pedido.Pedido;
import br.com.lanchonete.lanchoneteapi.pedido.enummeration.StatusPedido;
import br.com.lanchonete.lanchoneteapi.produto.Produto;
import br.com.lanchonete.lanchoneteapi.produto.dtos.ProdutoResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record PedidoResponse(
        String id,
        ClienteResponse cliente,
        List<ProdutoResponse> produtos,
        LocalDateTime data,
        StatusPedido status
) {
    public Pedido toPedido() {
        Cliente clienteEntity = cliente.toCliente();
        List<Produto> produtoEntities = produtos.stream()
                .map(ProdutoResponse::toProduto)
                .collect(Collectors.toList());

        return new Pedido(id, clienteEntity, produtoEntities, data, status);
    }

}
