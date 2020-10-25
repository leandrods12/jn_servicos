package controller;

public class TipoServico {

    public TipoServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    private String nomeServico;

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

}
