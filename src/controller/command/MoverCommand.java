package controller.command;

//CONTROLLER
import controller.observer.ObserverCommand;

//MODEL
import model.Maquina;

public class MoverCommand extends Command{
    private Maquina maquina;
    private int novaLinha;
    private int novaColuna;

    public MoverCommand(ObserverCommand observer, Object[] args) {
        super(observer);
        maquina = (Maquina) args[0];
        novaLinha = (int) args[1];
        novaColuna = (int) args[2];
    }

    @Override
    public void execute() {
        maquina.mover(novaLinha, novaColuna);
        observer.redesenharMaquinas();
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
