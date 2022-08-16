package controller.state.stateAcaoAtiva;

import controller.ControladorTelaJogo;

public class StateAcaoAtivaNeutro extends StateAcaoAtiva {
    
    public StateAcaoAtivaNeutro(ControladorTelaJogo controladorTelaJogo) {
        super(controladorTelaJogo);
    }
    
    @Override
    public void fazerAcao(String posicao) {
        int linha = Integer.parseInt(String.valueOf(posicao.charAt(0)));
        int coluna = Integer.parseInt(String.valueOf(posicao.charAt(1)));
        if(controladorTelaJogo.getJogo().jogadorAtivo().getMaquinaPorPosicao(linha, coluna) != null) {
            controladorTelaJogo.setMaquinaSelecionada(controladorTelaJogo.getJogo().getTabuleiro().getMaquinaPorPosicao(linha, coluna));
            controladorTelaJogo.ativarPainelAtacante();
        } else if(controladorTelaJogo.getJogo().jogadorDefensor().getMaquinaPorPosicao(linha, coluna) != null) {
            controladorTelaJogo.ativarPainelDefensor(controladorTelaJogo.getJogo().jogadorDefensor().getMaquinaPorPosicao(linha, coluna));
        } else {
            controladorTelaJogo.setMaquinaSelecionada(null);
            controladorTelaJogo.desativarPainelAtacante();
            controladorTelaJogo.desativarPainelDefensor();
        }
    }
}
