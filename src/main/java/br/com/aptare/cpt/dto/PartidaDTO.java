package br.com.aptare.cpt.dto;

import br.com.aptare.cpt.entity.Partida;

import java.util.Date;

public record PartidaDTO(Long codigo, Date data, Long codigoRacha, Integer situacao, String identificador) {
    public static PartidaDTO fromEntity(Partida p) {
        return new PartidaDTO(p.getCodigo(), p.getData(), p.getCodigoRacha(), p.getSituacao(), p.getIdentificador());
    }
}