package br.com.aptare.cpt.request;

import br.com.aptare.cpt.entity.Time;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PartidaRequest {

    private Long codigoRacha;

    private List<TimeRequest> listaTime;
}
