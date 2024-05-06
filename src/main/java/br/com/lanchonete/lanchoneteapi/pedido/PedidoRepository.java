package br.com.lanchonete.lanchoneteapi.pedido;

import br.com.lanchonete.lanchoneteapi.cliente.Cliente;
import br.com.lanchonete.lanchoneteapi.pedido.enummeration.StatusPedido;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PedidoRepository extends MongoRepository<Pedido, String> {

    List<Pedido> findAllByCliente(Cliente cliente);
    List<Pedido> findAllByClienteAndStatus(Cliente cliente, StatusPedido status);
    List<Pedido> findAllByStatus(StatusPedido status);
}
