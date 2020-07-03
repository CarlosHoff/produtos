package br.com.hoffmann.produto.domain.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class ProdutoRequest implements Serializable {

    @ApiModelProperty(value = "Descricao do produto")
    @NotNull
    @Size(min = 1, max = 80)
    private String descricao;

    @ApiModelProperty(value = "Quantidade no estoque")
    @NotNull
    private Long qtdEstoque;

    @ApiModelProperty(value = "Preco unitario do produto")
    @NotNull
    private Double precoUnitario;

    @ApiModelProperty(value = "Cnpj do fornecedor do produto")
    @NotNull
    private String cnpjFornecedor;

    public interface createProduto {
    }

    public interface updateProduto {
    }

}