package controller.state.stateAcaoAtiva;

//CONTROLLER
import controller.command.Command;
import controller.ControladorTelaJogo;

public class StateAcaoAtivaCorrer extends StateAcaoAtiva {

    public StateAcaoAtivaCorrer(ControladorTelaJogo controladorTelaJogo) {
        super(controladorTelaJogo);
    }

    @Override
    public void fazerAcao(String posicao) throws Exception {
        controladorTelaJogo.apagarCampos();
        int linha = Integer.parseInt(String.valueOf(posicao.charAt(0)));
        int coluna = Integer.parseInt(String.valueOf(posicao.charAt(1)));
        Command comm = cf.getComando("correr", new Object[]{controladorTelaJogo.getMaquinaSelecionada(), linha, coluna});
        ci.execute(comm);
        controladorTelaJogo.getJogo().addMaquinaQueAtacou(controladorTelaJogo.getMaquinaSelecionada());
        controladorTelaJogo.setMaquinaSelecionada(null);
        controladorTelaJogo.desativarPainel();
        ativarNeutro();
    }
}
