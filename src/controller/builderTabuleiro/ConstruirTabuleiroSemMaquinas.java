package controller.builderTabuleiro;

//GLOBAL
import global.EnumTipoTabuleiro;

//JAVA
import java.util.HashMap;

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

    public void construirMaquinas(HashMap<String, Maquina> maquinas) {
        tabuleiro.setMaquinas(new HashMap<>());
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public void reset() {
        tabuleiro = new Tabuleiro();
    }
}
