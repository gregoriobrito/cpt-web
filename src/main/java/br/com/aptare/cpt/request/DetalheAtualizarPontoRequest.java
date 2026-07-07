package br.com.aptare.cpt.request;

import lombok.Data;

@Data
public class DetalheAtualizarPontoRequest {
    private Long idTime;
    private Integer pontos;
    private Integer pontosExtra;
    private Integer pontos2;
    private Integer pontosExtra2;
    private Integer pontos3;
    private Integer pontosExtra3;
}
