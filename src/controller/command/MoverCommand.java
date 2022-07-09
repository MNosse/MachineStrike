package controller.command;

//CONTROLLER
import controller.observer.ObserverCommand;

//JAVA
import java.util.List;

//MODEL
import model.Maquina;

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
        maquina.mover(novaLinha, novaColuna);
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
