package br.com.hoffmann.produto.client;

import br.com.hoffmann.produto.client.response.FornecedorResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.constraints.NotNull;
import java.util.List;

@FeignClient(url = "${fornecedor.client}", name = "FornecedorClient")
public interface FornecedorClient {

    @RequestMapping(value = "/buscaFornecedores", method = RequestMethod.GET)
    List<FornecedorResponse> buscaFornecedores();


    @RequestMapping(value = "/buscaFornecedor/{cnpj}", method = RequestMethod.GET)
    FornecedorResponse buscaFornecedorPorCNPJ(@PathVariable(value = "cnpj") @NotNull String cnpj);
}
