package controller.command;

import controller.observer.ObserverCommand;
import global.Exception.ForaDoCampoAtaqueException;
import global.Exception.JaAtacouException;
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
    public void execute() throws ForaDoCampoAtaqueException, JaAtacouException {
        if(maquinaDefensora != null && maquinaAtacante.podeAtacar(maquinaDefensora, tabuleiro)) {
            maquinaAtacante.atacar(maquinaDefensora, tabuleiro);
            observer.redesenharMaquinas();
        } else {
            throw new ForaDoCampoAtaqueException();
        }
    }
}
