package controller.command;

//CONTROLLER
import controller.observer.ObserverCommand;

//MODEL
import model.Maquina;


public class CorrerCommand extends Command{
    private Maquina maquina;
    private int novaLinha;
    private int novaColuna;

    public CorrerCommand(ObserverCommand observer, Object[] args) {
        super(observer);
        maquina = (Maquina) args[0];
        novaLinha = (int) args[1];
        novaColuna = (int) args[2];
    }

    @Override
    public void execute() throws Exception {
        maquina.correr(novaLinha, novaColuna);
        observer.redesenharMaquinas();
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
