package model.builderTabuleiro;

//GLOBAL
import global.EnumTipoTabuleiro;

//JAVA
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//MODEL
import model.Maquina;
import model.Terreno;
import model.Tabuleiro;

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
