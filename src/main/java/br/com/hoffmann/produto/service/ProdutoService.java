package br.com.hoffmann.produto.service;

import br.com.hoffmann.produto.domain.request.ProdutoRequest;
import br.com.hoffmann.produto.domain.response.ProdutoResponse;
import br.com.hoffmann.produto.entity.Produto;
import br.com.hoffmann.produto.repository.ProdutoRepository;
import br.com.hoffmann.produto.service.auxiliar.ServiceAuxiliar;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@Transactional(value = "transactionManager")
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private ServiceAuxiliar auxiliar;

    public void createProduto(ProdutoRequest request) throws NotFoundException {
        auxiliar.salvaProduto(request);
    }
    
    public void deletaProduto(Long id) { produtoRepository.deleteById(id); }

    public void updateProduto(ProdutoRequest request, Long id) throws NotFoundException {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (!produto.isPresent()) {
            throw new NotFoundException(format("[%s] não encontrado", id));
        }
        auxiliar.atualizaProduto(produto.get(), request);
    }

    public List<ProdutoResponse> buscaProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream().map(ProdutoResponse::new).collect(Collectors.toList());
    }

    public List<ProdutoResponse> buscaProdutosMaiorPreco() {
        List<Produto> produtos = produtoRepository.findAllByOrderByPrecoUnitarioDesc();
        return produtos.stream().map(ProdutoResponse::new).collect(Collectors.toList());
    }

    public List<ProdutoResponse> buscaProdutosMenorPreco() {
        List<Produto> produtos = produtoRepository.findAllByOrderByPrecoUnitarioAsc();
        return produtos.stream().map(ProdutoResponse::new).collect(Collectors.toList());
    }

    public ProdutoResponse buscaProdutoPelaDescricao(String descricao) throws NotFoundException {
        Optional<Produto> produto = produtoRepository.findProdutoByDescricao(descricao);
        if (!produto.isPresent()) {
            throw new NotFoundException(format("Produto com a Descrição: [%s] não foi encontrado", descricao));
        }
        return new ProdutoResponse(produto.get());
    }
}