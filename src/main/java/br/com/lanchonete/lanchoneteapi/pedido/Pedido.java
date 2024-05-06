package br.com.lanchonete.lanchoneteapi.pedido;

import br.com.lanchonete.lanchoneteapi.cliente.Cliente;
import br.com.lanchonete.lanchoneteapi.pedido.enummeration.StatusPedido;
import br.com.lanchonete.lanchoneteapi.produto.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

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
}
