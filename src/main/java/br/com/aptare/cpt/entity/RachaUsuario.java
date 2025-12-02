package br.com.aptare.cpt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "SC_RCH", name = "TBL_RCH_USR")
@Getter
@Setter
public class RachaUsuario {

    @Id
    @Column(name = "CD_RCH_USR")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SQ_RCH_USR")
    @SequenceGenerator(name = "SQ_RCH_USR", sequenceName = "SC_RCH.SQ_RCH_USR")
    private Long codigo;

    @Column(name = "CD_RCH")
    private Long codigoRacha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CD_RCH", insertable = false, updatable = false)
    private Racha racha;

    @Column(name = "CD_USR")
    private Long codigoUsuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CD_USR", insertable = false, updatable = false)
    private Usuario usuario;

    @Column(name = "ST_RCH_USR")
    private Integer situacao;

    @Column(name = "FG_ADM_RCH_USR")
    private String flagAdministrador;
}
