package controller.command;

//CONTROLLER
import controller.observer.ObserverCommand;

//MODEL
import model.Maquina;

public class SobrecarregarCommand extends Command{
    private Maquina maquina;

    public SobrecarregarCommand(ObserverCommand observer, Object[] args) {
        super(observer);
        maquina = (Maquina) args[0];
    }

    @Override
    public void execute() throws Exception {
        maquina.sobrecarregar();
        observer.redesenharMaquinas();
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
