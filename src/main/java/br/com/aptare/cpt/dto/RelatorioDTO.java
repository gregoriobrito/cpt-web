package br.com.aptare.cpt.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RelatorioDTO {

    private String nomeRelatorio;

    private List<ResultadoAgrupadoDTO> lista;

}
