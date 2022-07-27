package controller.command;

import controller.observer.ObserverCommand;
import model.Tabuleiro;
import model.maquinas.Maquina;

public class AtacarCommand extends Command {
    private Maquina maquinaAtacante;
    private Maquina maquinaDefensora;
    private Tabuleiro tabuleiro;
    
    public AtacarCommand(ObserverCommand observer, Object[] args) {
        super(observer);
        maquinaAtacante = (Maquina) args[0];
        maquinaDefensora = (Maquina) args[1];
        tabuleiro = (Tabuleiro) args[2];
    }
    
    @Override
    public void execute() throws Exception {
        maquinaAtacante.atacar(maquinaDefensora, tabuleiro);
        observer.redesenharMaquinas();
    }
    
    @Override
    public void undo() {
    
    }
    
    @Override
    public void redo() {
    
    }
}
