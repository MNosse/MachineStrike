package controller.builderTabuleiro;

//GLOBAL
import global.EnumTipoTabuleiro;

//JAVA
import java.util.HashMap;

//MODEL
import model.Maquina;
import model.Terreno;


public class DirectorTabuleiro {

    private ConstruirTabuleiroSemMaquinas construirTabuleiro;

    public DirectorTabuleiro(ConstruirTabuleiroSemMaquinas construirTabuleiro) {
        this.construirTabuleiro = construirTabuleiro;
    }

    public void construir(EnumTipoTabuleiro tipoTabuleiro, HashMap<String, Terreno> terrenos, HashMap<String, Maquina> maquinas) {
        construirTabuleiro.reset();
        construirTabuleiro.construirTipoTabuleiro(tipoTabuleiro);
        construirTabuleiro.construirTerrenos(terrenos);
        construirTabuleiro.construirMaquinas(maquinas);
    }
}
