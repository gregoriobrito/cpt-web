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

    private String flagImagem;

    public UsuarioDTO(BigDecimal codigo, String nome, String apelido, String login, Character flagUsuarioAdmin, String flagImagem) {
        this.codigo = codigo.longValue();
        this.nome = nome;
        this.apelido = apelido;
        this.login = login;
        this.flagUsuarioAdmin = flagUsuarioAdmin.toString();
        this.flagImagem = flagImagem;
    }
}
