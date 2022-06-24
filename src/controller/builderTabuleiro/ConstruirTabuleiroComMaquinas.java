package controller.builderTabuleiro;

import model.Maquina;

import java.util.HashMap;

public class ConstruirTabuleiroComMaquinas extends ConstruirTabuleiroSemMaquinas{
    @Override
    public void construirMaquinas(HashMap<String, Maquina> maquinas){
        tabuleiro.setMaquinas(maquinas);
    }
}
