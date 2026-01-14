package br.com.aptare.cpt.request;

import lombok.Data;

@Data
public class AlterarSenhaRequest {
    private String senhaAtual;
    private String novaSenha;
}