package br.com.aptare.cpt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "SC_RCH", name = "TBL_TIM_USR")
@Getter
@Setter
public class TimeUsuario {

    @Id
    @Column(name = "CD_TIM_USR")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SQ_TIM_USR")
    @SequenceGenerator(name = "SQ_TIM_USR", sequenceName = "SC_RCH.SQ_TIM_USR")
    private Long codigo;

    @Column(name = "NR_PNT_IND")
    private Integer pontosIndividuais;

    @Column(name = "CD_TIM")
    private Long codigoTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CD_TIM", insertable = false, updatable = false)
    private Time time;

    @Column(name = "CD_USR")
    private Long codigoUsuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CD_USR", insertable = false, updatable = false)
    private Usuario usuario;
}