package controller.state.stateAcaoAtiva;

import controller.ControladorTelaJogo;
import controller.command.Command;
import model.maquinas.Maquina;

public class StateAcaoAtivaSobrecarregar extends StateAcaoAtiva {
    
    public StateAcaoAtivaSobrecarregar(ControladorTelaJogo controladorTelaJogo) {
        super(controladorTelaJogo);
    }
    
    @Override
    public void fazerAcao(String posicao) throws Exception {
        controladorTelaJogo.apagarCampos();
        Maquina maquina = controladorTelaJogo.getMaquinaSelecionada();
        if(maquina != null) {
            Command comm = cf.getComando("sobrecarregar", new Object[]{maquina});
            ci.execute(comm);
            controladorTelaJogo.getJogo().addMaquinaQueRealizouAcao(controladorTelaJogo.getMaquinaSelecionada());
            controladorTelaJogo.setMaquinaSelecionada(null);
            controladorTelaJogo.desativarPainel();
            ativarNeutro();
        }
    }
}
