package br.com.lanchonete.lanchoneteapi.pedido;

import br.com.lanchonete.lanchoneteapi.cliente.Cliente;
import br.com.lanchonete.lanchoneteapi.cliente.ClienteService;
import br.com.lanchonete.lanchoneteapi.pedido.dtos.AtualizarPedidoDTO;
import br.com.lanchonete.lanchoneteapi.pedido.dtos.CriarPedidoDTO;
import br.com.lanchonete.lanchoneteapi.pedido.dtos.Filtro;
import br.com.lanchonete.lanchoneteapi.pedido.dtos.PedidoResponse;
import br.com.lanchonete.lanchoneteapi.pedido.enummeration.StatusPedido;
import br.com.lanchonete.lanchoneteapi.produto.Produto;
import br.com.lanchonete.lanchoneteapi.produto.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    private final ClienteService clienteService;
    private final ProdutoService produtoService;

    public PedidoResponse criarPedido(CriarPedidoDTO dto) {
        Cliente cliente = obterCliente(dto.cliente_id());
        List<Produto> produtos = obterProdutos(dto.produtos_id());
        StatusPedido status = StatusPedido.PENDENTE;

        Pedido pedido = new Pedido(cliente, produtos, status);
        Pedido createdPedido = pedidoRepository.save(pedido);

        return createdPedido.toDto();
    }

    public List<PedidoResponse> listarPedidos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidos
                .stream()
                .map(Pedido::toDto)
                .collect(Collectors.toList());
    }

    public List<PedidoResponse> listarProdutosPorFiltro(Filtro filtro) {
        List<Pedido> pedidos;

        if (filtro.status() == null && filtro.cliente_id() == null) {
          throw new RuntimeException("Deve-se inserir pelo menos um campo");
        } else if (filtro.status() == null) {
            pedidos = obterPedidosPorClienteId(filtro.cliente_id());
        } else if (filtro.cliente_id() == null) {
            pedidos = obterPedidosPorStatus(filtro.status());
        } else {
            pedidos = obterPedidosPorClienteIdEStatus(filtro.cliente_id(), filtro.status());
        }

        return pedidos
                .stream()
                .map(Pedido::toDto)
                .collect(Collectors.toList());
    }

    public PedidoResponse obterPedidoPorId(String id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        return pedido.toDto();
    }

    public PedidoResponse atualizarStatus(AtualizarPedidoDTO dto) {
        Pedido pedido = obterPedidoPorId(dto.id()).toPedido();
        if (dto.status() == null) {
            throw new RuntimeException("O campo status é obrigatório");
        }
        pedido.setStatus(dto.status());
        Pedido pedidoAtualizado = pedidoRepository.save(pedido);
        return pedidoAtualizado.toDto();
    }

    public void deletarPedido(String id) {
        Pedido pedido = obterPedidoPorId(id).toPedido();
        pedidoRepository.delete(pedido);
    }

    private Cliente obterCliente(String id) {
        return clienteService.buscarPorId(id).toCliente();
    }

    private List<Produto> obterProdutos(List<String> ids) {
        List<Produto> produtos = new ArrayList<>();

        for (String produto_id : ids) {
            Produto produto = produtoService.buscarPorId(produto_id).toProduto();
            if(produto != null) {
                produtos.add(produto);
            }
        }
        return produtos;
    }

    private List<Pedido> obterPedidosPorClienteId(String cliente_id) {
        Cliente cliente = obterCliente(cliente_id);
        return pedidoRepository.findAllByCliente(cliente);
    }

    private List<Pedido> obterPedidosPorStatus(StatusPedido status) {
        return pedidoRepository.findAllByStatus(status);
    }

    private List<Pedido> obterPedidosPorClienteIdEStatus(String cliente_id, StatusPedido status) {
        Cliente cliente = obterCliente(cliente_id);
        return pedidoRepository.findAllByClienteAndStatus(cliente, status);
    }
}
