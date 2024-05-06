package br.com.lanchonete.lanchoneteapi.produto;

import br.com.lanchonete.lanchoneteapi.produto.dtos.ProdutoCadastrarDto;
import br.com.lanchonete.lanchoneteapi.produto.dtos.ProdutoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> listar() {
        return new ResponseEntity<>(produtoService.listar(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> obterPorId(@PathVariable String id) {
        return new ResponseEntity<>(produtoService.buscarPorId(id), HttpStatus.OK);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ProdutoResponse> cadastrar(@RequestBody ProdutoCadastrarDto dto) {
        return new ResponseEntity<>(produtoService.cadastrar(dto), HttpStatus.CREATED);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<ProdutoResponse> editar(@PathVariable String id, @RequestBody ProdutoCadastrarDto dto) {
        return new ResponseEntity<>(produtoService.alterar(dto, id), HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable String id) {
        produtoService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
