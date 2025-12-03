package br.com.aptare.cpt.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TimeRequest {

    private List<UsuarioRequest> listaUsuario;

}
