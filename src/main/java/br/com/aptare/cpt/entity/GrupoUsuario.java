package br.com.aptare.cpt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(schema = "SC_RCH", name = "TBL_GRP_USR")
@Getter
@Setter
public class GrupoUsuario {

    @Id
    @Column(name = "CD_GRP_USR")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_GRP_USR")
    @SequenceGenerator(name = "SQ_GRP_USR", sequenceName = "SC_RCH.SQ_GRP_USR", allocationSize = 1)
    private Long codigo;

    @Column(name = "CD_GRP")
    private Long codigoGrupo;

    @Column(name = "CD_USR")
    private Long codigoUsuario;

    @Column(name = "ST_GRP_USR")
    private Integer situacao;

    @Column(name = "DT_INC_USR")
    private Date dataInclusao;
}
