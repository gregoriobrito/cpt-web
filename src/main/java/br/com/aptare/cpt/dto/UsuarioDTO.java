package br.com.aptare.cpt.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UsuarioDTO {

    private Long codigo;

    private String nome;

    private String apelido;

    private String login;

    private String flagUsuarioAdmin;

    public UsuarioDTO(BigDecimal codigo, String nome, String apelido, String login, Character flagUsuarioAdmin) {
        this.codigo = codigo.longValue();
        this.nome = nome;
        this.apelido = apelido;
        this.login = login;
        this.flagUsuarioAdmin = flagUsuarioAdmin.toString();
    }
}
