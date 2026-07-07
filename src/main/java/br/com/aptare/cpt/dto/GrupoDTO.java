package br.com.aptare.cpt.dto;

import br.com.aptare.cpt.entity.Grupo;
import br.com.aptare.cpt.entity.GrupoUsuario;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public record GrupoDTO (Long codigo,
                        String descricao,
                        List<UsuarioDTO> listaUsuario
                        ) {

    public static GrupoDTO fromEntity(Grupo entity) {
        GrupoDTO retorno = null;

        if (entity != null) {

            UsuarioDTO add = null;
            List<UsuarioDTO> listaUsuario = null;

            if (entity.getListaGrupoUsuario() != null
                    && !entity.getListaGrupoUsuario().isEmpty()) {
                listaUsuario = new ArrayList<UsuarioDTO>();
                for(GrupoUsuario elemento : entity.getListaGrupoUsuario()) {
                    add = new UsuarioDTO(new BigDecimal(elemento.getUsuario().getCodigo()),
                                            elemento.getUsuario().getNome(),
                                            elemento.getUsuario().getApelido(),
                                            elemento.getUsuario().getLogin(),
                                            'N');
                    listaUsuario.add(add);
                }

                listaUsuario.sort(Comparator.comparing(UsuarioDTO::getApelido));
            }

            retorno = new GrupoDTO(entity.getCodigo(), entity.getDescricao(), listaUsuario);
        }

        return retorno;
    }

}
