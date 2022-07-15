package controller.state.stateAcaoAtiva;

//CONTROLLER
import controller.command.Command;
import controller.ControladorTelaJogo;

//MODEL
import model.Maquina;
import model.Terreno;

public class StateAcaoAtivaAtacar extends StateAcaoAtiva{

    public StateAcaoAtivaAtacar(ControladorTelaJogo controladorTelaJogo) {
        super(controladorTelaJogo);
    }

    @Override
    public void fazerAcao(String posicao) throws Exception {
        controladorTelaJogo.apagarCampos();
        try {
            Maquina maquinaDefensora = controladorTelaJogo.getJogo().getTabuleiro().getMaquinaPorPosicao(Integer.parseInt(String.valueOf(posicao.charAt(0))), Integer.parseInt(String.valueOf(posicao.charAt(1))));
            if (maquinaDefensora != null && controladorTelaJogo.getMaquinaSelecionada().podeAtacar(maquinaDefensora, controladorTelaJogo.getJogo().jogadorAtivo().getMaquinas())) {
                Terreno terrenoAtacante = controladorTelaJogo.getJogo().getTabuleiro().getTerrenoPorPosicao(controladorTelaJogo.getMaquinaSelecionada().getLinha() + "" + controladorTelaJogo.getMaquinaSelecionada().getColuna());
                Terreno terrenoDefensor = controladorTelaJogo.getJogo().getTabuleiro().getTerrenoPorPosicao(posicao);
                Command comm = cf.getComando("atacar", new Object[]{controladorTelaJogo.getMaquinaSelecionada(), terrenoAtacante, maquinaDefensora, controladorTelaJogo.getJogo().jogadorAtivo().getMaquinas(), terrenoDefensor});
                ci.execute(comm);
                controladorTelaJogo.getJogo().verificarMaquinasJogadorDefensor();
                controladorTelaJogo.redesenharMaquinas();
                controladorTelaJogo.getJogo().addMaquinaQueAtacou(controladorTelaJogo.getMaquinaSelecionada());
            }
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            controladorTelaJogo.setMaquinaSelecionada(null);
            controladorTelaJogo.desativarPainel();
            ativarNeutro();
        }
    }
}
