package br.com.aptare.cpt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "SC_RCH", name = "TBL_RCH")
@Getter
@Setter
public class Racha {

    @Id
    @Column(name = "CD_RCH")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SQ_RCH")
    @SequenceGenerator(name = "SQ_RCH", sequenceName = "SC_RCH.SQ_RCH")
    private Long codigo;

    @Column(name = "NM_RCH")
    private String nome;

    @Column(name = "ST_RCH")
    private Integer situacao;
}
