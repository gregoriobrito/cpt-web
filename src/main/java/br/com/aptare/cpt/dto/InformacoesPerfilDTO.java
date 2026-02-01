package br.com.aptare.cpt.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InformacoesPerfilDTO {

    private Long totalPartida;

    private Long vitorias;

    private Long pontos;

    public InformacoesPerfilDTO(Long totalPartida, Long vitorias, BigDecimal pontos) {
        this.totalPartida = totalPartida != null ? totalPartida : 0L;
        this.vitorias = vitorias != null ? vitorias : 0L;
        this.pontos = pontos != null ? pontos.longValue() : 0L;
    }

}
