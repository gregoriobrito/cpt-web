package br.com.aptare.cpt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(schema = "SC_PRT", name = "VW_PRT")
@Getter
@Setter
public class VwPartida {

    @Id
    @Column(name = "CD_PRT")
    private Long codigo;

    @Column(name = "CD_RCH")
    private Long codigoRacha;

    @Column(name = "DT_PRT")
    private Date data;

    @Column(name = "IDT_TIM_A")
    private String identificadorTimeA;

    @Column(name = "PNT_TIM_A")
    private Long pontosTimeA;

    @Column(name = "IDT_TIM_B")
    private String identificadorTimeB;

    @Column(name = "PNT_TIM_B")
    private Long pontosTimeB;
}
