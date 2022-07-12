package controller.state.stateAcaoAtiva;

//CONTROLLER
import controller.ControladorTelaJogo;
import controller.command.Command;

//MODEL
import model.Maquina;

public class StateAcaoAtivaSobrecarregar extends StateAcaoAtiva{

    public StateAcaoAtivaSobrecarregar(ControladorTelaJogo controladorTelaJogo) {
        super(controladorTelaJogo);
    }

    @Override
    public void fazerAcao(String posicao) {
        controladorTelaJogo.apagarCampos();
        Maquina maquina = controladorTelaJogo.getMaquinaSelecionada();
        if (maquina != null) {
            Command comm = cf.getComando("sobrecarregar", new Object[]{maquina});
            ci.execute(comm);
            controladorTelaJogo.getJogo().addMaquinaQueAtacou(controladorTelaJogo.getMaquinaSelecionada());
            controladorTelaJogo.setMaquinaSelecionada(null);
            controladorTelaJogo.desativarPainel();
            ativarNeutro();
        }
    }
}
