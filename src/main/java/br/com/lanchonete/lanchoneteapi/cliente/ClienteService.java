package br.com.lanchonete.lanchoneteapi.cliente;

import br.com.lanchonete.lanchoneteapi.cliente.dtos.ClienteCadastroDTO;
import br.com.lanchonete.lanchoneteapi.cliente.dtos.ClienteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteResponse cadastrar(ClienteCadastroDTO dto) {
        Cliente cliente = clienteRepository.save(dto.toCliente());
        return cliente.toDto();
    }

    public List<ClienteResponse> listar() {
        return clienteRepository.findAll()
                .stream()
                .map(Cliente::toDto)
                .collect(Collectors.toList());
    }

    public ClienteResponse buscarPorId(String id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id inválido")).toDto();
    }

}
