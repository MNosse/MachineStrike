package controller.command;

import controller.observer.ObserverCommand;
import global.Exception.ForaDoCampoMovimentoException;
import global.Exception.JaMovimentouException;
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
    public void execute() throws ForaDoCampoMovimentoException, JaMovimentouException {
        if (maquina.podeMover(novaLinha, novaColuna, terreno, maquinasEmJogo)) {
            maquina.mover(novaLinha, novaColuna, terreno, maquinasEmJogo);
            observer.redesenharMaquinas();
        } else {
            throw new ForaDoCampoMovimentoException();
        }
    }
}
