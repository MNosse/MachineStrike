package model.builderTabuleiro;

import global.Enum.EnumTipoTabuleiro;
import model.Terreno;
import model.maquinas.Maquina;

import java.util.HashMap;
import java.util.List;


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
