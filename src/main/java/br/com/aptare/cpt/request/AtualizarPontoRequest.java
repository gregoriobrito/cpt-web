package br.com.aptare.cpt.request;

import lombok.Data;

import java.util.List;

@Data
public class AtualizarPontoRequest {

    private List<DetalheAtualizarPontoRequest> lista;
}
