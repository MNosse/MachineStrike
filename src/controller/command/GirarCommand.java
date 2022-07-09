package controller.command;

//CONTROLLER

import controller.observer.ObserverCommand;
import model.Maquina;

import java.util.List;

public class GirarCommand extends Command{
    private Maquina maquina;

    public GirarCommand(List<ObserverCommand> observers, Object[] args) {
        super(observers);
        maquina = (Maquina) args[0];
    }

    @Override
    public void execute() {
        maquina.girar();
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
