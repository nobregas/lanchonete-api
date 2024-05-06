package br.com.lanchonete.lanchoneteapi.produto;

import br.com.lanchonete.lanchoneteapi.produto.dtos.ProdutoCadastrarDto;
import br.com.lanchonete.lanchoneteapi.produto.dtos.ProdutoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public List<ProdutoResponse> listar() {
        return produtoRepository.findAll()
                .stream()
                .map(Produto::toDto)
                .collect(Collectors.toList());
    }

    public ProdutoResponse cadastrar(ProdutoCadastrarDto dto) {
        Produto produto = produtoRepository.save(dto.toProduto());
        return produto.toDto();
    }

    public ProdutoResponse buscarPorId(String id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado")).toDto();
    }

    public ProdutoResponse alterar(ProdutoCadastrarDto dto, String id) {
        Produto produto = buscarPorId(id).toProduto();
        if (!dto.nome().equals(produto.getNome()) && !dto.nome().isBlank()) {
            produto.setNome(dto.nome());
        }
        if (!(dto.preco() == produto.getPreco()) && !(dto.preco() > 0)) {
            produto.setPreco(dto.preco());
        }

        produtoRepository.save(produto);
        return produto.toDto();
    }

    public void excluir(String id) {
        Produto produto = buscarPorId(id).toProduto();
        produtoRepository.delete(produto);
    }
}
