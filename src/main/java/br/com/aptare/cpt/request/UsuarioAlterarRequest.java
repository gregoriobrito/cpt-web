package br.com.aptare.cpt.request;

import lombok.Data;

@Data
public class UsuarioAlterarRequest {

    private Long codigo;
    private String nome;
    private String apelido;
    private String login;
}
