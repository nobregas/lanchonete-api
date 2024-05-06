package br.com.lanchonete.lanchoneteapi.cliente;

import br.com.lanchonete.lanchoneteapi.cliente.dtos.ClienteResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    private String id;

    private String nome;

    @Indexed(unique = true)
    private String email;

    @Indexed(unique = true)
    private String telefone;

    private String endereco;

    public Cliente(String nome, String email, String telefone, String endereco) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public ClienteResponse toDto() {
        return new ClienteResponse(this.id, this.nome, this.email, this.telefone, this.endereco);
    }
}
