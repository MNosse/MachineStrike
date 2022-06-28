package controller.builderTabuleiro;

//JAVA
import java.util.HashMap;

//MODEL
import model.Maquina;

public class ConstruirTabuleiroComMaquinas extends ConstruirTabuleiroSemMaquinas {

    @Override
    public void construirMaquinas(HashMap<String, Maquina> maquinas) {
        tabuleiro.setMaquinas(maquinas);
    }
}