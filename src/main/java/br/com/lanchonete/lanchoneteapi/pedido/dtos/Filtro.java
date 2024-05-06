package br.com.lanchonete.lanchoneteapi.pedido.dtos;

import br.com.lanchonete.lanchoneteapi.pedido.enummeration.StatusPedido;

public record Filtro(
        String cliente_id,
        StatusPedido status
) {
}
