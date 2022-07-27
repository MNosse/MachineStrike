package controller.state.stateAcaoAtiva;

import controller.ControladorTelaJogo;
import controller.command.Command;
import model.maquinas.Maquina;
import model.visitor.VisitorJogadorAtivoGanhador;
import model.visitor.VisitorJogadorDefensorGanhador;
import model.visitor.VisitorMaquinasMortas;

public class StateAcaoAtivaAtacar extends StateAcaoAtiva {
    
    public StateAcaoAtivaAtacar(ControladorTelaJogo controladorTelaJogo) {
        super(controladorTelaJogo);
    }
    
    @Override
    public void fazerAcao(String posicao) throws Exception {
        controladorTelaJogo.apagarCampos();
        try {
            Maquina maquinaDefensora = controladorTelaJogo.getJogo().getTabuleiro().getMaquinaPorPosicao(Integer.parseInt(String.valueOf(posicao.charAt(0))), Integer.parseInt(String.valueOf(posicao.charAt(1))));
            if(maquinaDefensora != null && controladorTelaJogo.getMaquinaSelecionada().podeAtacar(maquinaDefensora, controladorTelaJogo.getJogo().getTabuleiro())) {
                Command comm = cf.getComando("atacar", new Object[]{controladorTelaJogo.getMaquinaSelecionada(), maquinaDefensora, controladorTelaJogo.getJogo().getTabuleiro()});
                ci.execute(comm);
                if (controladorTelaJogo.getJogo().accept(new VisitorMaquinasMortas())) {
                    controladorTelaJogo.atualizarLblJogadorAtivo();
                }
                controladorTelaJogo.redesenharMaquinas();
                controladorTelaJogo.getJogo().addMaquinaQueRealizouAcao(controladorTelaJogo.getMaquinaSelecionada());
                if (controladorTelaJogo.getJogo().accept(new VisitorJogadorAtivoGanhador())) {
                    controladorTelaJogo.anunciarGanhador(controladorTelaJogo.getJogo().jogadorAtivo());
                }
                if (controladorTelaJogo.getJogo().accept(new VisitorJogadorDefensorGanhador())) {
                    controladorTelaJogo.anunciarGanhador(controladorTelaJogo.getJogo().jogadorDefensor());
                }
            }
        } catch(Exception e) {
            throw new RuntimeException();
        } finally {
            controladorTelaJogo.setMaquinaSelecionada(null);
            controladorTelaJogo.desativarPainel();
            ativarNeutro();
        }
    }
    
    @Override
    public boolean estaAtacando() {
        return true;
    }
}
