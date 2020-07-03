package br.com.hoffmann.produto.controller;

import br.com.hoffmann.produto.domain.request.ProdutoRequest;
import br.com.hoffmann.produto.domain.response.ProdutoResponse;
import br.com.hoffmann.produto.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


@Api(value = "Cadastro de Produtos - endPoints API", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@Validated
@RequestMapping(path = "/v1/produtos")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @ApiOperation(value = "EndPoint para cadastro de Produtos")
    @RequestMapping(value = "/cadastraProduto", method = RequestMethod.POST)
    @Validated(ProdutoRequest.createProduto.class)
    public ResponseEntity<ProdutoResponse> cadastraProdutos(
            @RequestBody @Valid ProdutoRequest request) throws NotFoundException {
        produtoService.createProduto(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "EndPoint para exclusão de Produtos")
    @RequestMapping(value = "/deletaProduto/{id}", method = RequestMethod.DELETE)
    public void deletaProduto(
            @PathVariable(value = "id") @NotNull Long id) {
        produtoService.deletaProduto(id);
    }

    @ApiOperation(value = "EndPoint para alteração de informações dos Produtos")
    @RequestMapping(value = "/updateProduto/{id}", method = RequestMethod.PUT)
    @Validated(ProdutoRequest.updateProduto.class)
    public ResponseEntity<ProdutoResponse> updateProduto(
            @PathVariable(value = "id") @NotNull Long id,
            @RequestBody @Valid ProdutoRequest request) throws NotFoundException {
        produtoService.updateProduto(request, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "EndPoint para fazer a busca de Produtos")
    @RequestMapping(value = "/buscaProdutos", method = RequestMethod.GET)
    public ResponseEntity<List<ProdutoResponse>> buscaProdutos() {
        List<ProdutoResponse> response = produtoService.buscaProdutos();
        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "EndPoint para fazer a busca de Produtos Ordenando pelo maior preço")
    @RequestMapping(value = "/buscaProdutosMaiorPreco", method = RequestMethod.GET)
    public ResponseEntity<List<ProdutoResponse>> buscaProdutosMaiorPreco() {
        List<ProdutoResponse> response = produtoService.buscaProdutosMaiorPreco();
        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "EndPoint para fazer a busca de Produtos Ordenando pelo menor preço")
    @RequestMapping(value = "/buscaProdutosMenorPreco", method = RequestMethod.GET)
    public ResponseEntity<List<ProdutoResponse>> buscaProdutosMenorPreco() {
        List<ProdutoResponse> response = produtoService.buscaProdutosMenorPreco();
        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "EndPoint para fazer a busca de Produtos pela Descrição")
    @RequestMapping(value = "/buscaProdutoPelaDescricao/{descricao}", method = RequestMethod.GET)
    public ResponseEntity<ProdutoResponse> buscaProdutoPelaDescricao(
            @RequestParam(value = "descricao") String descricao) throws NotFoundException {
        ProdutoResponse response = produtoService.buscaProdutoPelaDescricao(descricao);
        return ResponseEntity.ok().body(response);
    }
}