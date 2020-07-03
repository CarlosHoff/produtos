package br.com.hoffmann.produto.service.auxiliar;

import br.com.hoffmann.produto.client.FornecedorClient;
import br.com.hoffmann.produto.client.response.FornecedorResponse;
import br.com.hoffmann.produto.domain.request.ProdutoRequest;
import br.com.hoffmann.produto.entity.Produto;
import br.com.hoffmann.produto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceAuxiliar {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FornecedorClient fornecedorClient;

    public void salvaProduto(ProdutoRequest request) {
        Produto produto = new Produto();
        FornecedorResponse cnpjValidado = validaCNPJ(request);
        produto.setDescricao(request.getDescricao());
        produto.setQtdEstoque(request.getQtdEstoque());
        produto.setPrecoUnitario(request.getPrecoUnitario());
        produto.setCnpjFornecedor(cnpjValidado.getCnpj());
        produtoRepository.save(produto);
    }

    public void atualizaProduto(Produto produto, ProdutoRequest request) {
        produto.setDescricao(request.getDescricao());
        produto.setQtdEstoque(request.getQtdEstoque());
        produto.setPrecoUnitario(request.getPrecoUnitario());
        produto.setCnpjFornecedor(request.getCnpjFornecedor());
        produtoRepository.save(produto);
    }

    private FornecedorResponse validaCNPJ(ProdutoRequest request) {
        return fornecedorClient.buscaFornecedorPorCNPJ(request.getCnpjFornecedor());
    }
}
