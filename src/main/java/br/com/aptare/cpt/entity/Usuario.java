package br.com.aptare.cpt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "SC_SGR", name = "TBL_USR")
@Getter
@Setter
public class Usuario {

    @Id
    @Column(name = "CD_USR")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SQ_USR")
    @SequenceGenerator(name = "SQ_UR", sequenceName = "SC_SGR.SQ_UR")
    private Long codigo;

    @Column(name = "NM_USR")
    private String nome;

    @Column(name = "APL_USR")
    private String apelido;

    @Column(name = "ST_USR")
    private Integer situacao;

    @Column(name = "LGN_USR")
    private String login;

    @Column(name = "SNH_USR")
    private String senha;
}
