package controller.state.stateAcaoAtiva;

import controller.ControladorTelaJogo;
import controller.command.Command;

public class StateAcaoAtivaMover extends StateAcaoAtiva {
    
    public StateAcaoAtivaMover(ControladorTelaJogo controladorTelaJogo) {
        super(controladorTelaJogo);
    }
    
    @Override
    public void fazerAcao(String posicao) throws Exception {
        controladorTelaJogo.apagarCampos();
        try {
            int linha = Integer.parseInt(String.valueOf(posicao.charAt(0)));
            int coluna = Integer.parseInt(String.valueOf(posicao.charAt(1)));
            Command comm = cf.getComando("mover", new Object[]{controladorTelaJogo.getMaquinaSelecionada(), linha, coluna, controladorTelaJogo.getJogo().getTabuleiro().getTerrenoPorPosicao(linha + "" + coluna), controladorTelaJogo.getJogo().getTabuleiro().getMaquinas()});
            ci.execute(comm);
            controladorTelaJogo.getJogo().addMaquinaQueRealizouAcao(controladorTelaJogo.getMaquinaSelecionada());
            controladorTelaJogo.getJogo().addContagemMovimentos();
        } catch(Exception e) {
            throw new RuntimeException();
        } finally {
            controladorTelaJogo.setMaquinaSelecionada(null);
            controladorTelaJogo.desativarPainel();
            ativarNeutro();
        }
    }
    
    @Override
    public boolean estaMovendo() {
        return true;
    }
}
