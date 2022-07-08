package controller.command;

import controller.observer.ObserverCommand;
import model.Maquina;

import java.util.List;

public class MoverCommand extends Command{
    private Maquina maquina;
    private int novaLinha;
    private int novaColuna;

    public MoverCommand(List<ObserverCommand> observers, Object[] args) {
        super(observers);
        maquina = (Maquina) args[0];
        novaLinha = (int) args[1];
        novaColuna = (int) args[2];
    }

    @Override
    public void execute() {
        String posicao = maquina.getLinha()+""+ maquina.getColuna();
        maquina.setLinha(novaLinha);
        maquina.setColuna(novaColuna);
        for (ObserverCommand observer : observers) {
            observer.atualizarListasDeMaquinas(posicao);
        }
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
