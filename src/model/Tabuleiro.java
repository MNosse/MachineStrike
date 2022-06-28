package model;

//GLOBAL
import global.EnumTipoTabuleiro;

//JAVA
import java.util.HashMap;

public class Tabuleiro {
    //Chaves sao as posicoes linha+coluna
    private EnumTipoTabuleiro tipoTabuleiro;
    private HashMap<String, Terreno> terrenos;
    private HashMap<String, Maquina> maquinas;

    public Terreno getTerrenoPorIndice (String linhaColuna) {
        return terrenos.get(linhaColuna);
    }

    public Maquina getMaquinaPorIndice (String linhaColuna) {
        return maquinas.get(linhaColuna);
    }

    public void atualizaTerrenoPorIndice (String linhaColuna, Terreno novoTerreno) {
        terrenos.replace(linhaColuna, novoTerreno);
    }

    public void atualizaTerrenoPorIndice (String linhaColuna, Maquina novaMaquina) {
        maquinas.replace(linhaColuna, novaMaquina);
    }

    public HashMap<String, Terreno> getTerrenos() {
        return terrenos;
    }

    public void setTerrenos(HashMap<String, Terreno> terrenos) {
        this.terrenos = terrenos;
    }

    public HashMap<String, Maquina> getMaquinas() {
        return maquinas;
    }

    public void setMaquinas(HashMap<String, Maquina> maquinas) {
        this.maquinas = maquinas;
    }

    public EnumTipoTabuleiro getTipoTabuleiro() {
        return tipoTabuleiro;
    }

    public void setTipoTabuleiro(EnumTipoTabuleiro tipoTabuleiro) {
        this.tipoTabuleiro = tipoTabuleiro;
    }
}
