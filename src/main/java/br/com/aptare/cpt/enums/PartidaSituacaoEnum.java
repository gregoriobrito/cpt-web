package br.com.aptare.cpt.enums;

public enum PartidaSituacaoEnum {

    CADASTRADO(1),
    EXCLUIDO(2),
    PENDENTE(3);

    private final Integer codigo;

    PartidaSituacaoEnum(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getValor() {
        return codigo;
    }

}
