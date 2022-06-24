package controller.builderTabuleiro;

import global.EnumTipoTabuleiro;
import model.Maquina;
import model.Tabuleiro;
import model.Terreno;

import java.util.HashMap;

public class ConstruirTabuleiroSemMaquinas {
    protected Tabuleiro tabuleiro;

    public void construirTipoTabuleiro(EnumTipoTabuleiro tipoTabuleiro) {
        tabuleiro.setTipoTabuleiro(tipoTabuleiro);
    }

    public void construirTerrenos(HashMap<String, Terreno> terrenos){
        tabuleiro.setTerrenos(terrenos);
    }

    public void construirMaquinas(HashMap<String, Maquina> maquinas){
        tabuleiro.setMaquinas(new HashMap<>());
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public void reset() {
        tabuleiro = new Tabuleiro();
    }
}
