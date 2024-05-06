package br.com.lanchonete.lanchoneteapi.pedido.dtos;

import java.util.List;

public record CriarPedidoDTO(
        String cliente_id,
        List<String> produtos_id
) {
}
