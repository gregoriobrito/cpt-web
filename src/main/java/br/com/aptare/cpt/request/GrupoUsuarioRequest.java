package br.com.aptare.cpt.request;

import lombok.Data;

@Data
public class GrupoUsuarioRequest {

    private Long codigo;

    private Long codigoUsuario;

    private Integer situacao;
}
