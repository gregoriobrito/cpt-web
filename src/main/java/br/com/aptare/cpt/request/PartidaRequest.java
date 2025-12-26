package br.com.aptare.cpt.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PartidaRequest {

    private Long codigoRacha;

    private Long codigo;

    private List<TimeRequest> listaTime;
}
