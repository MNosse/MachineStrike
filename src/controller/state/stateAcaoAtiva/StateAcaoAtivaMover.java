package controller.state.stateAcaoAtiva;

//CONTROLLER
import controller.command.Command;
import controller.ControladorTelaJogo;

public class StateAcaoAtivaMover extends StateAcaoAtiva{

    public StateAcaoAtivaMover(ControladorTelaJogo controladorTelaJogo) {
        super(controladorTelaJogo);
    }

    @Override
    public void fazerAcao(String posicao) {
        controladorTelaJogo.apagarCampos();
        int linha = Integer.parseInt(String.valueOf(posicao.charAt(0)));
        int coluna = Integer.parseInt(String.valueOf(posicao.charAt(1)));
        Command comm = cf.getComando("mover", new Object[]{controladorTelaJogo.getMaquinaSelecionada(), linha, coluna});
        ci.execute(comm);
        controladorTelaJogo.getJogo().addMaquinaQueAtacou(controladorTelaJogo.getMaquinaSelecionada());
        controladorTelaJogo.setMaquinaSelecionada(null);
        controladorTelaJogo.desativarPainel();
        ativarNeutro();
    }
}
