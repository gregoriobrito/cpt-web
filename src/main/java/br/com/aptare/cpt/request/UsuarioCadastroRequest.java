package br.com.aptare.cpt.request;

import lombok.Data;

@Data
public class UsuarioCadastroRequest {
    private String nome;
    private String apelido;
    private String login;
    private String senha;
    private Long codigoRacha;
}