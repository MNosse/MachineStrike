package controller.command;

//CONTROLLER
import controller.observer.ObserverCommand;

//MODEL
import model.Maquina;

import java.util.List;

public class MoverCommand extends Command{
    private Maquina maquina;
    private int novaLinha;
    private int novaColuna;
    private List<Maquina> maquinasEmJogo;

    public MoverCommand(ObserverCommand observer, Object[] args) {
        super(observer);
        maquina = (Maquina) args[0];
        novaLinha = (int) args[1];
        novaColuna = (int) args[2];
        maquinasEmJogo = (List) args[3];
    }

    @Override
    public void execute() throws Exception {
        maquina.mover(novaLinha, novaColuna, maquinasEmJogo);
        observer.redesenharMaquinas();
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
