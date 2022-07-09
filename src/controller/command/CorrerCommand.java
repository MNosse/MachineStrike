package controller.command;

//CONTROLLER

import controller.observer.ObserverCommand;
import model.Maquina;

import java.util.List;

public class CorrerCommand extends Command{
    private Maquina maquina;
    private int novaLinha;
    private int novaColuna;

    public CorrerCommand(List<ObserverCommand> observers, Object[] args) {
        super(observers);
        maquina = (Maquina) args[0];
        novaLinha = (int) args[1];
        novaColuna = (int) args[2];
    }

    @Override
    public void execute() {
        String posicao = maquina.getLinha()+""+ maquina.getColuna();
        maquina.correr(novaLinha, novaColuna);
        for (ObserverCommand observer : observers) {
            observer.redesenharMaquinas();
        }
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
