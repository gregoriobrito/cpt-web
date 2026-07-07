package br.com.aptare.cpt.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import java.util.Date;
import java.util.List;

@Entity
@Table(schema = "SC_PRT", name = "TBL_PRT")
@Getter
@Setter
public class Partida {

    @Id
    @Column(name = "CD_PRT")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PRT")
    @SequenceGenerator(name = "SQ_PRT", sequenceName = "SC_PRT.SQ_PRT", allocationSize = 1)
    private Long codigo;

    @Column(name = "DT_PRT")
    private Date data;

    @Formula("CAST(DT_PRT AS DATE)")
    private Date dataSemHora;

    @Column(name = "IDT_PRT")
    private String identificador;

    @Column(name = "CD_RCH")
    private Long codigoRacha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CD_RCH", insertable = false, updatable = false)
    private Racha racha;

    @Column(name = "ST_PRT")
    private Integer situacao;

    @Column(name = "DT_LMT_PRT")
    private Date dataLimite;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "CD_PRT", insertable = false, updatable = false)
    @JsonManagedReference
    private List<Time> listaTime;

}
