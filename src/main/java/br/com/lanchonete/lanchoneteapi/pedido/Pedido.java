package br.com.lanchonete.lanchoneteapi.pedido;

import br.com.lanchonete.lanchoneteapi.cliente.Cliente;
import br.com.lanchonete.lanchoneteapi.cliente.dtos.ClienteResponse;
import br.com.lanchonete.lanchoneteapi.pedido.dtos.PedidoResponse;
import br.com.lanchonete.lanchoneteapi.pedido.enummeration.StatusPedido;
import br.com.lanchonete.lanchoneteapi.produto.Produto;
import br.com.lanchonete.lanchoneteapi.produto.dtos.ProdutoResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Document(collection = "pedidos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    @Id
    private String id;

    @DBRef
    private Cliente cliente;

    @DBRef
    private List<Produto> produtos;

    private LocalDateTime dataCriacao;

    private StatusPedido status;

    public Pedido(Cliente cliente, List<Produto> produtos, StatusPedido status) {
        this.cliente = cliente;
        this.produtos = produtos;
        this.status = status;
        this.dataCriacao = LocalDateTime.now();
    }

    public PedidoResponse toDto() {
        ClienteResponse clienteResponse = this.cliente.toDto();
        List<ProdutoResponse> produtosResponse = this.produtos
                .stream()
                .map(Produto::toDto)
                .collect(Collectors.toList());

        return new PedidoResponse(
                this.id, clienteResponse, produtosResponse, this.dataCriacao, this.status
        );
    }
}
