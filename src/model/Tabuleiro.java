package model;

import global.Enum.EnumTipoTabuleiro;
import model.maquinas.Maquina;

import java.util.HashMap;
import java.util.List;

public class Tabuleiro {
    //Chaves sao as posicoes linha+coluna
    private EnumTipoTabuleiro tipoTabuleiro;
    private HashMap<String, Terreno> terrenos;
    private List<Maquina> maquinas;
    
    public Terreno getTerrenoPorPosicao(String linhaColuna) {
        return terrenos.get(linhaColuna);
    }
    
    public Maquina getMaquinaPorPosicao(int linha, int coluna) {
        for(Maquina maquina : maquinas) {
            if(maquina.getLinha() == linha && maquina.getColuna() == coluna) {
                return maquina;
            }
        }
        return null;
    }
    
    public void removerMaquina(Maquina maquina) {
        maquinas.remove(maquina);
    }
    
    public HashMap<String, Terreno> getTerrenos() {
        return terrenos;
    }
    
    public void setTerrenos(HashMap<String, Terreno> terrenos) {
        this.terrenos = terrenos;
    }
    
    public List<Maquina> getMaquinas() {
        return maquinas;
    }
    
    public void setMaquinas(List<Maquina> maquinas) {
        this.maquinas = maquinas;
    }
    
    public EnumTipoTabuleiro getTipoTabuleiro() {
        return tipoTabuleiro;
    }
    
    public void setTipoTabuleiro(EnumTipoTabuleiro tipoTabuleiro) {
        this.tipoTabuleiro = tipoTabuleiro;
    }
}
