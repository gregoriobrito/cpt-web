package br.com.aptare.cpt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "SC_PRT", name = "TBL_TIM_USR")
@Getter
@Setter
public class TimeUsuario {

    @Id
    @Column(name = "CD_TIM_USR")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TIM_USR")
    @SequenceGenerator(name = "SQ_TIM_USR", sequenceName = "SC_PRT.SQ_TIM_USR", allocationSize = 1)
    private Long codigo;

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

    @Column(name = "ST_TIM_USR")
    private Integer situacao;
}