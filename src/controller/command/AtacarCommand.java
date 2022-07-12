package controller.command;

//CONTROLLER
import controller.observer.ObserverCommand;

//MODEL
import model.Maquina;
import model.Terreno;
import model.visitor.VisitorAtaque;

public class AtacarCommand extends Command{
    private Maquina maquinaAtacante;
    private Terreno terrenoAtacante;
    private Maquina maquinaDefensora;
    private Terreno terrenoDefensor;

    public AtacarCommand(ObserverCommand observer, Object[] args) {
        super(observer);
        maquinaAtacante = (Maquina) args[0];
        terrenoAtacante = (Terreno) args[1];
        maquinaDefensora = (Maquina) args[2];
        terrenoDefensor = (Terreno) args[3];
    }

    @Override
    public void execute() {
        try {
            VisitorAtaque visitorAtaque = VisitorAtaque.criarVisitorAtaque(maquinaAtacante, terrenoAtacante);
            maquinaAtacante.atacar(maquinaDefensora, terrenoDefensor, visitorAtaque);
            observer.redesenharMaquinas();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
