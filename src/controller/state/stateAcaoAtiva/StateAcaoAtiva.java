package controller.state.stateAcaoAtiva;

//CONTROLLER
import controller.ControladorTelaJogo;
import controller.command.CommandFactory;
import controller.command.CommandInvoker;

public abstract class StateAcaoAtiva {
    protected ControladorTelaJogo controladorTelaJogo;
    protected CommandFactory cf = CommandFactory.getInstancia();
    protected CommandInvoker ci = CommandInvoker.getInstancia();

    public StateAcaoAtiva(ControladorTelaJogo controladorTelaJogo) {
        this.controladorTelaJogo = controladorTelaJogo;
        cf.setObserver(controladorTelaJogo);
    }

    public abstract void fazerAcao(String posicao) throws Exception;

    public void ativarNeutro() {
        controladorTelaJogo.setStateAcaoAtiva(new StateAcaoAtivaNeutro(controladorTelaJogo));
    }

    public void ativarAtacar() {
        controladorTelaJogo.setStateAcaoAtiva(new StateAcaoAtivaAtacar(controladorTelaJogo));
    }

    public void ativarCorrer() {
        controladorTelaJogo.setStateAcaoAtiva(new StateAcaoAtivaCorrer(controladorTelaJogo));
    }

    public void ativarMover() {
        controladorTelaJogo.setStateAcaoAtiva(new StateAcaoAtivaMover(controladorTelaJogo));
    }

    public void ativarSobrecarregar() {
        controladorTelaJogo.setStateAcaoAtiva(new StateAcaoAtivaSobrecarregar(controladorTelaJogo));
    }
}
