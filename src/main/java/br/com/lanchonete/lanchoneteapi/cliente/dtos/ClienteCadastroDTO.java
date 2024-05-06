package br.com.lanchonete.lanchoneteapi.cliente.dtos;

import br.com.lanchonete.lanchoneteapi.cliente.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ClienteCadastroDTO(
        @NotBlank String nome,
        @Email String email,
        @Min(14) @Max(14) String telefone,
        @NotBlank String endereco
) {
    public Cliente toCliente() {
        return new Cliente(
                nome, email, telefone, endereco
        );
    }
}
