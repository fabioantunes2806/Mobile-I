package com.example.agendaordorealitas;

public class infoMissao {
    private int codigo;
    private String nomeAgente;
    private String patenteAgente;
    private String local;
    private String organizacao;

    public infoMissao(int codigo, String nomeAgente, String patenteAgente, String local, String organizacao) {
        this.codigo = codigo;
        this.nomeAgente = nomeAgente;
        this.patenteAgente = patenteAgente;
        this.local = local;
        this.organizacao = organizacao;
    }

    public infoMissao(String nomeAgente, String patenteAgente, String local, String organizacao) {
        this.nomeAgente = nomeAgente;
        this.patenteAgente = patenteAgente;
        this.local = local;
        this.organizacao = organizacao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNomeAgente() {
        return nomeAgente;
    }

    public void setNomeAgente(String nomeAgente) {
        this.nomeAgente = nomeAgente;
    }

    public String getPatenteAgente() {
        return patenteAgente;
    }

    public void setPatenteAgente(String patenteAgente) {
        this.patenteAgente = patenteAgente;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getOrganizacao() {
        return organizacao;
    }

    public void setOrganizacao(String organizacao) {
        this.organizacao = organizacao;
    }
}
