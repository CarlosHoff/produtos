package br.com.hoffmann.produto.domain.response;

import br.com.hoffmann.produto.entity.Produto;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class ProdutoResponse implements Serializable {

    @ApiModelProperty(value = "Produto id")
    private Long id;

    @ApiModelProperty(value = "Descricao do produto")
    private String descricao;

    @ApiModelProperty(value = "Quantidade no estoque")
    private Long qtdEstoque;

    @ApiModelProperty(value = "Preco unitario do produto")
    private Double precoUnitario;

    @ApiModelProperty(value = "Cnpj do fornecedor do produto")
    private String cnpjFornecedor;

    public ProdutoResponse() {
    }

    public ProdutoResponse(Produto produto) {
        this.id = produto.getId();
        this.descricao = produto.getDescricao();
        this.qtdEstoque = produto.getQtdEstoque();
        this.precoUnitario = produto.getPrecoUnitario();
        this.cnpjFornecedor = produto.getCnpjFornecedor();
    }
}

