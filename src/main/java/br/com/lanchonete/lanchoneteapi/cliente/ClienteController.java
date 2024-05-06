package br.com.lanchonete.lanchoneteapi.cliente;

import br.com.lanchonete.lanchoneteapi.cliente.dtos.ClienteCadastroDTO;
import br.com.lanchonete.lanchoneteapi.cliente.dtos.ClienteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping("/cadastrar")
    public ResponseEntity<ClienteResponse> cadastrar(@RequestBody ClienteCadastroDTO cliente) {
        return new ResponseEntity<>(clienteService.cadastrar(cliente), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> listar() {
        return new ResponseEntity<>(clienteService.listar(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> buscar(@PathVariable String id) {
        return new ResponseEntity<>(clienteService.buscarPorId(id), HttpStatus.OK);
    }


}
