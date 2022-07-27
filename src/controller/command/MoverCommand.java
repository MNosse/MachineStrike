package controller.command;

import controller.observer.ObserverCommand;
import model.Terreno;
import model.maquinas.Maquina;

import java.util.List;

public class MoverCommand extends Command {
    private Maquina maquina;
    private int novaLinha;
    private int novaColuna;
    private Terreno terreno;
    private List<Maquina> maquinasEmJogo;
    
    public MoverCommand(ObserverCommand observer, Object[] args) {
        super(observer);
        maquina = (Maquina) args[0];
        novaLinha = (int) args[1];
        novaColuna = (int) args[2];
        terreno = (Terreno) args[3];
        maquinasEmJogo = (List) args[4];
    }
    
    @Override
    public void execute() throws Exception {
        maquina.mover(novaLinha, novaColuna, terreno, maquinasEmJogo);
        observer.redesenharMaquinas();
    }
    
    @Override
    public void undo() {
    
    }
    
    @Override
    public void redo() {
    
    }
}
