package model.builderTabuleiro;

import global.Enum.EnumTipoTabuleiro;
import model.Tabuleiro;
import model.Terreno;
import model.maquinas.Maquina;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConstruirTabuleiroSemMaquinas {
    
    protected Tabuleiro tabuleiro;
    
    public void construirTipoTabuleiro(EnumTipoTabuleiro tipoTabuleiro) {
        tabuleiro.setTipoTabuleiro(tipoTabuleiro);
    }
    
    public void construirTerrenos(HashMap<String, Terreno> terrenos) {
        tabuleiro.setTerrenos(terrenos);
    }
    
    public void construirMaquinas(List<Maquina> maquinas) {
        tabuleiro.setMaquinas(new ArrayList<>());
    }
    
    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }
    
    public void reset() {
        tabuleiro = new Tabuleiro();
    }
}
