package br.com.aptare.cpt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "SC_RCH", name = "TBL_TIM")
@Getter
@Setter
public class Time {

    @Id
    @Column(name = "CD_TIM")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SQ_TIM")
    @SequenceGenerator(name = "SQ_TIM", sequenceName = "SC_RCH.SQ_TIM")
    private Long codigo;

    @Column(name = "NM_TIM")
    private String nome;

    @Column(name = "NR_PNT_TIM")
    private Integer pontuacao;

    @Column(name = "CD_PRT")
    private Long codigoPartida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CD_PRT", insertable = false, updatable = false)
    private Partida partida;
}