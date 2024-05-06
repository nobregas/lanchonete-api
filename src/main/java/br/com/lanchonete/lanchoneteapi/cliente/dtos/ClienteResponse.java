package br.com.lanchonete.lanchoneteapi.cliente.dtos;

import br.com.lanchonete.lanchoneteapi.cliente.Cliente;

public record ClienteResponse(
        String id,
        String nome,
        String email,
        String telefone,
        String endereco
) {
    public Cliente toCliente() {
        return new Cliente(id, nome, email, telefone, endereco);
    }
}
