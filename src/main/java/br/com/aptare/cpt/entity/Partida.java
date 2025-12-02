package br.com.aptare.cpt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(schema = "SC_RCH", name = "TBL_PTD")
@Getter
@Setter
public class Partida {

    @Id
    @Column(name = "CD_PRT")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SQ_PRT")
    @SequenceGenerator(name = "SQ_PRT", sequenceName = "SC_RCH.SQ_PRT")
    private Long codigo;

    @Column(name = "DT_PRT")
    private Date data;

    @Column(name = "DS_IDT_PRT")
    private String identificador;

    @Column(name = "CD_RCH")
    private Long codigoRacha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CD_RCH", insertable = false, updatable = false)
    private Racha racha;


}
