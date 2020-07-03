package br.com.hoffmann.produto.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Table(name = "PRODUTO")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SQ_PRODUTO")
    @SequenceGenerator(sequenceName = "SQ_PRODUTO", allocationSize = 1, name = "SQ_PRODUTO")
    @Column(name = "ID")
    private Long id;

    @Column(name = "DESCRICAO", length = 80, nullable = false)
    private String descricao;

    @Column(name = "QTD_ESTOQUE", nullable = false)
    private Long qtdEstoque;

    @Column(name = "PRECO_UNITARIO", nullable = false)
    private Double precoUnitario;

    @Column(name = "CNPJ_FORNECEDOR", nullable = false)
    private String cnpjFornecedor;

}
