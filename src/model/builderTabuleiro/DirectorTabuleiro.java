package model.builderTabuleiro;

//GLOBAL
import global.EnumTipoTabuleiro;

//JAVA
import java.util.HashMap;
import java.util.List;

//MODEL
import model.Maquina;
import model.Terreno;


public class DirectorTabuleiro {

    private ConstruirTabuleiroSemMaquinas construirTabuleiro;

    public DirectorTabuleiro(ConstruirTabuleiroSemMaquinas construirTabuleiro) {
        this.construirTabuleiro = construirTabuleiro;
    }

    public void construir(EnumTipoTabuleiro tipoTabuleiro, HashMap<String, Terreno> terrenos, List<Maquina> maquinas) {
        construirTabuleiro.reset();
        construirTabuleiro.construirTipoTabuleiro(tipoTabuleiro);
        construirTabuleiro.construirTerrenos(terrenos);
        construirTabuleiro.construirMaquinas(maquinas);
    }
}
