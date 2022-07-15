package model.builderTabuleiro;

//GLOBAL
import global.Enum.EnumTipoTabuleiro;

//JAVA
import java.util.List;
import java.util.HashMap;

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
