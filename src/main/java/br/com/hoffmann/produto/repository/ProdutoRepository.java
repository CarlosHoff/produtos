package br.com.hoffmann.produto.repository;

import br.com.hoffmann.produto.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Optional<Produto> findProdutoByDescricao(String descricao);

    List<Produto> findAllByOrderByPrecoUnitarioAsc();

    List<Produto> findAllByOrderByPrecoUnitarioDesc();

}