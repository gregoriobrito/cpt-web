package br.com.aptare.cpt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "SC_PRT", name = "TBL_TIM")
@Getter
@Setter
public class Time {

    @Id
    @Column(name = "CD_TIM")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TIM")
    @SequenceGenerator(name = "SQ_TIM", sequenceName = "SC_PRT.SQ_TIM", allocationSize = 1)
    private Long codigo;

    @Column(name = "IDT_TIM")
    private String nome;

    @Column(name = "PNT_TIM")
    private Integer pontuacao;

    @Column(name = "CD_PRT")
    private Long codigoPartida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CD_PRT", insertable = false, updatable = false)
    private Partida partida;

    @Column(name = "ST_TIM")
    private Integer situacao;
}