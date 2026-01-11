package br.com.aptare.cpt.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RachaDTO {

    private Long codigo;

    private String nome;

    private String flagUsuarioAdmin;

    public RachaDTO(BigDecimal codigo, String nome, Character flagUsuarioAdmin) {
        this.codigo = codigo.longValue();
        this.nome = nome;
        this.flagUsuarioAdmin = flagUsuarioAdmin.toString();
    }

}
