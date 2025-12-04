package br.com.aptare.cpt.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResultadoAgrupadoDTO {

    private String agrupador;

    private List<ResultadoDTO> listaResultado;

}
