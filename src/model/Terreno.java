package model;

//GLOBAL
import global.Enum.EnumTipoTerreno;

public class Terreno {

    private int pontosDeCombate;
    private EnumTipoTerreno tipo;

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
}
