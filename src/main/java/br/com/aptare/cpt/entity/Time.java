package br.com.aptare.cpt.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private String identificador;

    @Column(name = "PNT_TIM")
    private Integer pontuacao;

    @Column(name = "PNT_EXT_TIM")
    private Integer pontuacaoExtra;

    @Column(name = "PNT2_TIM")
    private Integer pontuacao2;

    @Column(name = "PNT2_EXT_TIM")
    private Integer pontuacaoExtra2;

    @Column(name = "PNT3_TIM")
    private Integer pontuacao3;

    @Column(name = "PNT3_EXT_TIM")
    private Integer pontuacaoExtra3;

    @Column(name = "CD_PRT")
    private Long codigoPartida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CD_PRT", insertable = false, updatable = false)
    @JsonBackReference
    private Partida partida;

    @Column(name = "ST_TIM")
    private Integer situacao;
}