package controller.command;

//CONTROLLER
import controller.observer.ObserverCommand;

//MODEL
import model.Maquina;
import model.Terreno;
import model.visitor.VisitorAtaque;

import java.util.List;

public class AtacarCommand extends Command{
    private Maquina maquinaAtacante;
    private Terreno terrenoAtacante;
    private Maquina maquinaDefensora;
    private Terreno terrenoDefensor;
    private List<Maquina> maquinasMesmoJogador;

    public AtacarCommand(ObserverCommand observer, Object[] args) {
        super(observer);
        maquinaAtacante = (Maquina) args[0];
        terrenoAtacante = (Terreno) args[1];
        maquinaDefensora = (Maquina) args[2];
        terrenoDefensor = (Terreno) args[3];
        maquinasMesmoJogador = (List) args[4];
    }

    @Override
    public void execute() throws Exception {
        VisitorAtaque visitorAtaque = VisitorAtaque.criarVisitorAtaque(maquinaAtacante, terrenoAtacante);
        maquinaAtacante.atacar(maquinaDefensora, terrenoDefensor, maquinasMesmoJogador, visitorAtaque);
        observer.redesenharMaquinas();
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
