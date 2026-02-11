package br.com.aptare.cpt.request;

import lombok.Data;

import java.util.List;

@Data
public class GrupoAtualizarRequest {

    private Long codigoGrupo;

    private String descricao;

    private List<GrupoUsuarioRequest> listaUsuario;
}
