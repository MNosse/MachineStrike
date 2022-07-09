package model.builderTabuleiro;

//JAVA
import java.util.List;

//MODEL
import model.Maquina;

public class ConstruirTabuleiroComMaquinas extends ConstruirTabuleiroSemMaquinas {

    @Override
    public void construirMaquinas(List<Maquina> maquinas) {
        tabuleiro.setMaquinas(maquinas);
    }
}