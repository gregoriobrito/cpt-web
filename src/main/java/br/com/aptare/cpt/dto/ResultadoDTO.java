package br.com.aptare.cpt.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ResultadoDTO {

    private String agrupador;

    private String apelido;

    private String nome;

    private BigDecimal pontuacao;

    public ResultadoDTO(String agrupador, String apelido, String nome, BigDecimal pontuacao) {
        this.apelido = apelido;
        this.pontuacao = pontuacao;
        this.agrupador = agrupador;
        this.nome = nome;
    }

}
