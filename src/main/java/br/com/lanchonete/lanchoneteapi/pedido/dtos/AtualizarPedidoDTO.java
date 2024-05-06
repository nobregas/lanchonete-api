package br.com.lanchonete.lanchoneteapi.pedido.dtos;

import br.com.lanchonete.lanchoneteapi.pedido.enummeration.StatusPedido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizarPedidoDTO(
      @NotBlank String id,
      @NotNull StatusPedido status
) {
}
