package br.com.aptare.cpt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(schema = "SC_RCH", name = "TBL_GRP")
@Getter
@Setter
public class Grupo {

    @Id
    @Column(name = "CD_GRP")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_GRP")
    @SequenceGenerator(name = "SQ_GRP", sequenceName = "SC_RCH.SQ_GRP", allocationSize = 1)
    private Long codigo;

    @Column(name = "DS_GRP")
    private String descricao;

    @Column(name = "ST_GRP")
    private Integer situacao;

    @Column(name = "DT_INC_USR")
    private Date dataInclusao;
}
