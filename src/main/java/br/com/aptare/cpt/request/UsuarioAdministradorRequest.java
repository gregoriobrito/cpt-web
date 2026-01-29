package br.com.aptare.cpt.request;

import lombok.Data;

@Data
public class UsuarioAdministradorRequest {

    private Long codigoUsuario;

    private Long codigoRacha;

    private String flagAdministrador;

}
