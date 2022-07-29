package controller.command;

import controller.observer.ObserverCommand;
import global.Exception.ForaDoCampoCorridaException;
import global.Exception.JaCorreuException;
import model.Terreno;
import model.maquinas.Maquina;

import java.util.List;

public class CorrerCommand extends Command {
    private Maquina maquina;
    private int novaLinha;
    private int novaColuna;
    private Terreno terreno;
    private List<Maquina> maquinasEmJogo;
    
    public CorrerCommand(ObserverCommand observer, Object[] args) {
        super(observer);
        maquina = (Maquina) args[0];
        novaLinha = (int) args[1];
        novaColuna = (int) args[2];
        terreno = (Terreno) args[3];
        maquinasEmJogo = (List) args[4];
    }
    
    @Override
    public void execute() throws ForaDoCampoCorridaException, JaCorreuException {
        if(maquina.podeCorrer(novaLinha, novaColuna, terreno, maquinasEmJogo)) {
            maquina.correr(novaLinha, novaColuna, terreno, maquinasEmJogo);
            observer.redesenharMaquinas();
        } else {
            throw new ForaDoCampoCorridaException();
        }
    }
}
