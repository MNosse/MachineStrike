package model.builderTabuleiro;

import model.maquinas.Maquina;

import java.util.List;

public class ConstruirTabuleiroComMaquinas extends ConstruirTabuleiroSemMaquinas {

    @Override
    public void construirMaquinas(List<Maquina> maquinas) {
        tabuleiro.setMaquinas(maquinas);
    }
}