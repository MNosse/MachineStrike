package controller.command;

import controller.observer.ObserverCommand;
import model.maquinas.Maquina;

public class GirarCommand extends Command {
    private Maquina maquina;
    
    public GirarCommand(ObserverCommand observer, Object[] args) {
        super(observer);
        maquina = (Maquina) args[0];
    }
    
    @Override
    public void execute() {
        maquina.girar();
        observer.redesenharMaquinas();
    }
}
