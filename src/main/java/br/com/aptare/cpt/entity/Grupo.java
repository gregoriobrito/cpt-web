package br.com.aptare.cpt.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

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

    @Column(name = "CD_RCH")
    private Long codigoRacha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CD_RCH", insertable = false, updatable = false)
    private Racha racha;

    @Column(name = "DS_GRP")
    private String descricao;

    @Column(name = "ST_GRP")
    private Integer situacao;

    @Column(name = "DT_INC_USR")
    private Date dataInclusao;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "CD_GRP", insertable = false, updatable = false)
    @JsonManagedReference
    private List<GrupoUsuario> listaGrupoUsuario;
}
