package br.com.lanchonete.lanchoneteapi.cliente.dtos;

public record ClienteResponse(
        String id,
        String nome,
        String email,
        String telefone,
        String endereco
) {
}
