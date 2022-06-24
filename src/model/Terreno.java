package model;

import global.EnumTipoTerreno;

public class Terreno {

    private EnumTipoTerreno tipo;
    private int pontosDeCombate;
    private String caminhoDaImagem;

    public EnumTipoTerreno getTipo() {
        return tipo;
    }

    public void setTipo(EnumTipoTerreno tipo) {
        this.tipo = tipo;
    }

    public int getPontosDeCombate() {
        return pontosDeCombate;
    }

    public void setPontosDeCombate(int pontosDeCombate) {
        this.pontosDeCombate = pontosDeCombate;
    }

    public String getCaminhoDaImagem() {
        return caminhoDaImagem;
    }

    public void setCaminhoDaImagem(String caminhoDaImagem) {
        this.caminhoDaImagem = caminhoDaImagem;
    }
}
