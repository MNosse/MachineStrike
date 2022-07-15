package model.builderTabuleiro;

//GLOBAL
import global.Enum.EnumTipoTabuleiro;

//JAVA
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

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
