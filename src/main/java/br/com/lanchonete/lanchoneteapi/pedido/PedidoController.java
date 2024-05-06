package br.com.lanchonete.lanchoneteapi.pedido;

import br.com.lanchonete.lanchoneteapi.pedido.dtos.AtualizarPedidoDTO;
import br.com.lanchonete.lanchoneteapi.pedido.dtos.CriarPedidoDTO;
import br.com.lanchonete.lanchoneteapi.pedido.dtos.Filtro;
import br.com.lanchonete.lanchoneteapi.pedido.dtos.PedidoResponse;
import br.com.lanchonete.lanchoneteapi.pedido.enummeration.StatusPedido;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<PedidoResponse>> listar() {
        return ResponseEntity.ok(pedidoService.listarPedidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> listarPorId(@PathVariable String id) {
        return ResponseEntity.ok(pedidoService.obterPedidoPorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<PedidoResponse>> listarPorFiltro(
        @RequestParam(name = "cliente_id", required = false) String cliente_id,
        @RequestParam(name = "status", required = false) StatusPedido status
    ) {
        Filtro filtro = new Filtro(cliente_id, status);
        return ResponseEntity.ok(pedidoService.listarProdutosPorFiltro(filtro));
    }

    @PostMapping("/criar")
    public ResponseEntity<PedidoResponse> criarPedido(@RequestBody CriarPedidoDTO pedido) {
        return new ResponseEntity<>(pedidoService.criarPedido(pedido), HttpStatus.CREATED);
    }

    @PatchMapping("/atualizar-status")
    public ResponseEntity<PedidoResponse> atualizarStatus(@RequestBody AtualizarPedidoDTO dto) {
        return ResponseEntity.ok(pedidoService.atualizarStatus(dto));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable String id) {
        pedidoService.deletarPedido(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
