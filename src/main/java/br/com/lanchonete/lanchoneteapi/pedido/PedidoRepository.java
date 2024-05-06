package br.com.lanchonete.lanchoneteapi.pedido;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PedidoRepository extends MongoRepository<Pedido, String> {
}
