package controller;

//CONTROLLER

import controller.command.Command;
import controller.command.CommandFactory;
import controller.command.CommandInvoker;
import controller.observer.ObserverCommand;
import controller.observer.ObserverTelaJogo;
import controller.singleton.SingletonConfiguracaoJogo;
import controller.state.stateAcaoAtiva.StateAcaoAtiva;
import controller.state.stateAcaoAtiva.StateAcaoAtivaNeutro;
import global.Enum.EnumTipoTerreno;
import global.Exception.MinimoDeMovimentoException;
import model.Jogador;
import model.Jogo;
import model.Tabuleiro;
import model.Terreno;
import model.maquinas.Maquina;
import view.abstractFactoryTela.AbstractFactoryTela;

import java.util.*;

public class ControladorTelaJogo implements ObserverCommand {
    
    private Jogo jogo;
    private List<ObserverTelaJogo> observers;
    private Set<String> campoDeMovimento;
    private Set<String> campoDeCorrida;
    private Set<String> campoDeAtaque;
    private Maquina maquinaSelecionada;
    private StateAcaoAtiva stateAcaoAtiva;
    
    public ControladorTelaJogo() {
        observers = new ArrayList<>();
        campoDeMovimento = new HashSet<>();
        campoDeCorrida = new HashSet<>();
        campoDeAtaque = new HashSet<>();
        stateAcaoAtiva = new StateAcaoAtivaNeutro(this);
        maquinaSelecionada = null;
        jogo = new Jogo(SingletonConfiguracaoJogo.getInstancia().getTabuleiro(), SingletonConfiguracaoJogo.getInstancia().getJogadores());
    }
    
    public void attach(ObserverTelaJogo observer) {
        observers.add(observer);
    }
    
    public void desenharJogoInicial() {
        Tabuleiro tabuleiro = jogo.getTabuleiro();
        HashMap<String, Terreno> terrenos = tabuleiro.getTerrenos();
        Set<String> posicoes = terrenos.keySet();
        HashMap<String, EnumTipoTerreno> tiposTerrenos = new HashMap<>();
        for(String posicao : posicoes) {
            tiposTerrenos.put(posicao, terrenos.get(posicao).getTipo());
        }
        List<Maquina> maquinas = tabuleiro.getMaquinas();
        HashMap<String, String> desenhosMaquinas = new HashMap<>();
        for(Maquina maquina : maquinas) {
            desenhosMaquinas.put(maquina.getLinha() + "" + maquina.getColuna(), maquina.caminhoImagemDirecaoAtual());
        }
        for(ObserverTelaJogo observer : observers) {
            observer.desenharTabuleiro(tiposTerrenos);
        }
        for(ObserverTelaJogo observer : observers) {
            observer.desenharQuadrados(desenhosMaquinas);
        }
    }
    
    @Override
    public void redesenharMaquinas() {
        Tabuleiro tabuleiro = jogo.getTabuleiro();
        List<Maquina> maquinas = tabuleiro.getMaquinas();
        HashMap<String, String> desenhosMaquinas = new HashMap<>();
        for(Maquina maquina : maquinas) {
            desenhosMaquinas.put(maquina.getLinha() + "" + maquina.getColuna(), maquina.caminhoImagemDirecaoAtual());
        }
        for(ObserverTelaJogo observer : observers) {
            observer.apagarCamposSelecionados(tabuleiro.getTerrenos().keySet());
            observer.desenharQuadrados(desenhosMaquinas);
        }
    }
    
    public void atualizarLblJogadorAtivo() {
        for(ObserverTelaJogo observer : observers) {
            observer.atualizarLblJogadorAtivo(getInformacoesHeaderJogadorAtual());
        }
    }
    
    public void anunciarGanhador(Jogador jogador) {
        for(ObserverTelaJogo observer : observers) {
            observer.anunciarGanhador(jogador.getEnumNome().getNome());
        }
    }
    
    private void desenharCampoDeMovimento(Maquina maquina) {
        apagarCampoDeAtaque();
        apagarCampoDeCorrida();
        gerarSetCampoDeMovimento(maquina);
        for(ObserverTelaJogo observer : observers) {
            observer.desenharCamposSelecionados(campoDeMovimento);
        }
    }
    
    private void apagarCampoDeMovimento() {
        for(ObserverTelaJogo observer : observers) {
            observer.apagarCamposSelecionados(campoDeMovimento);
        }
        limparSetCampoDeMovimento();
    }
    
    private void desenharCampoDeCorrida(Maquina maquina) {
        apagarCampoDeAtaque();
        apagarCampoDeMovimento();
        gerarSetCampoDeCorrida(maquina);
        for(ObserverTelaJogo observer : observers) {
            observer.desenharCamposSelecionados(campoDeCorrida);
        }
    }
    
    private void apagarCampoDeCorrida() {
        for(ObserverTelaJogo observer : observers) {
            observer.apagarCamposSelecionados(campoDeCorrida);
        }
        limparSetCampoDeCorrida();
    }
    
    private void desenharCampoDeAtaque(Maquina maquina) {
        apagarCampoDeCorrida();
        apagarCampoDeMovimento();
        gerarSetCampoDeAtaque(maquina);
        for(ObserverTelaJogo observer : observers) {
            observer.desenharCamposSelecionados(campoDeAtaque);
        }
    }
    
    private void apagarCampoDeAtaque() {
        for(ObserverTelaJogo observer : observers) {
            observer.apagarCamposSelecionados(campoDeAtaque);
        }
        limparSetCampoDeAtaque();
    }
    
    public void clicarBotaoMover() throws Exception {
        if(!stateAcaoAtiva.estaMovendo()) {
            if(jogo.getMaquinasQueRealizaramAcoes().contains(maquinaSelecionada) || jogo.getMaquinasQueRealizaramAcoes().size() < 2) {
                stateAcaoAtiva.ativarMover();
                apagarCampos();
                desenharCampoDeMovimento(maquinaSelecionada);
            } else {
                throw new RuntimeException();
            }
        } else {
            apagarCampos();
            stateAcaoAtiva.ativarNeutro();
        }
    }
    
    public void clicarBotaoCorrer() throws Exception {
        if(!stateAcaoAtiva.estaCorrendo()) {
            if(jogo.getMaquinasQueRealizaramAcoes().contains(maquinaSelecionada) || jogo.getMaquinasQueRealizaramAcoes().size() < 2) {
                stateAcaoAtiva.ativarCorrer();
                apagarCampos();
                desenharCampoDeCorrida(maquinaSelecionada);
            } else {
                throw new RuntimeException();
            }
        } else {
            apagarCampos();
            stateAcaoAtiva.ativarNeutro();
        }
    }
    
    public void clicarBotaoAtacar() throws Exception {
        if(!stateAcaoAtiva.estaAtacando()) {
            if(jogo.getMaquinasQueRealizaramAcoes().contains(maquinaSelecionada) || jogo.getMaquinasQueRealizaramAcoes().size() < 2) {
                stateAcaoAtiva.ativarAtacar();
                apagarCampos();
                desenharCampoDeAtaque(maquinaSelecionada);
            } else {
                throw new RuntimeException();
            }
        } else {
            apagarCampos();
            stateAcaoAtiva.ativarNeutro();
        }
    }
    
    public void clicarBotaoSobrecarregar() throws Exception {
        if(jogo.getMaquinasQueRealizaramAcoes().contains(maquinaSelecionada) || jogo.getMaquinasQueRealizaramAcoes().size() < 2) {
            stateAcaoAtiva.ativarSobrecarregar();
            stateAcaoAtiva.fazerAcao(null);
            apagarCampos();
        } else {
            throw new RuntimeException();
        }
    }
    
    public void clicarBotaoEncerrar() throws MinimoDeMovimentoException {
        if(jogo.getContagemMovimentos() == 2 || (jogo.jogadorAtivo().getMaquinas().size() < 2 && jogo.getContagemMovimentos() > 0)) {
            jogo.passarTurno();
            stateAcaoAtiva.ativarNeutro();
            maquinaSelecionada = null;
            desativarPainel();
            apagarCampos();
            redesenharMaquinas();
            for(ObserverTelaJogo observer : observers) {
                observer.atualizarLblJogadorAtivo(getInformacoesHeaderJogadorAtual());
            }
        } else if(jogo.jogadorAtivo().getMaquinas().size() < 2) {
            throw new MinimoDeMovimentoException("Voce deve mover sua maquina para poder encerrar seu turno");
        } else {
            throw new MinimoDeMovimentoException();
        }
    }
    
    public void clicarBotaoGirar() throws Exception {
        if(jogo.getMaquinasQueRealizaramAcoes().contains(maquinaSelecionada) || jogo.getMaquinasQueRealizaramAcoes().size() < 2) {
            apagarCampos();
            CommandFactory cf = CommandFactory.getInstancia();
            CommandInvoker ci = CommandInvoker.getInstancia();
            cf.setObserver(this);
            Command comm = cf.getComando("girar", new Object[]{maquinaSelecionada});
            ci.execute(comm);
        } else {
            throw new RuntimeException();
        }
    }
    
    public void ativarPainel() {
        if(maquinaSelecionada != null) {
            for(ObserverTelaJogo observer : observers) {
                observer.mudarEstadoBtnMover(maquinaSelecionada.isMoverAtivo());
                observer.mudarEstadoBtnCorrer(maquinaSelecionada.isCorrerAtivo());
                observer.mudarEstadoBtnAtacar(maquinaSelecionada.isAtacarAtivo());
                observer.mudarEstadoBtnSobrecarregar(maquinaSelecionada.isSobrecarregarAtivo());
                observer.mudarEstadoBtnGirar(true);
                observer.atualizarCardMaquinaAtacante(getInformacoesMaquina(maquinaSelecionada));
            }
        }
    }
    
    public void desativarPainel() {
        for(ObserverTelaJogo observer : observers) {
            observer.desativarBotoes();
            observer.atualizarCardMaquinaAtacante(getInformacoesMaquina());
        }
    }
    
    public void apagarCampos() {
        apagarCampoDeAtaque();
        apagarCampoDeCorrida();
        apagarCampoDeMovimento();
    }
    
    public void selecionarQuadrado(String posicao) throws Exception {
        stateAcaoAtiva.fazerAcao(posicao);
    }
    
    private void gerarSetCampoDeMovimento(Maquina maquina) {
        Set<String> todasChaves = jogo.getTabuleiro().getTerrenos().keySet();
        for(String chave : todasChaves) {
            int linha = Integer.parseInt(String.valueOf(chave.charAt(0)));
            int coluna = Integer.parseInt(String.valueOf(chave.charAt(1)));
            if(maquina.podeMover(linha, coluna, jogo.getTabuleiro().getTerrenoPorPosicao(linha + "" + coluna), jogo.getTabuleiro().getMaquinas())) {
                campoDeMovimento.add(chave);
            }
        }
        for(Maquina m : jogo.getTabuleiro().getMaquinas()) {
            campoDeCorrida.remove(m.getLinha() + "" + m.getColuna());
        }
    }
    
    private void limparSetCampoDeMovimento() {
        campoDeMovimento = new HashSet<>();
    }
    
    private void gerarSetCampoDeCorrida(Maquina maquina) {
        Set<String> todasChaves = jogo.getTabuleiro().getTerrenos().keySet();
        for(String chave : todasChaves) {
            int linha = Integer.parseInt(String.valueOf(chave.charAt(0)));
            int coluna = Integer.parseInt(String.valueOf(chave.charAt(1)));
            if(maquina.podeCorrer(linha, coluna, jogo.getTabuleiro().getTerrenoPorPosicao(linha + "" + coluna), jogo.getTabuleiro().getMaquinas())) {
                campoDeCorrida.add(chave);
            }
        }
        for(Maquina m : jogo.getTabuleiro().getMaquinas()) {
            campoDeCorrida.remove(m.getLinha() + "" + m.getColuna());
        }
        gerarSetCampoDeMovimento(maquina);
        for(String posicao : campoDeMovimento) {
            campoDeCorrida.remove(posicao);
        }
    }
    
    private void limparSetCampoDeCorrida() {
        campoDeCorrida = new HashSet<>();
    }
    
    private void gerarSetCampoDeAtaque(Maquina maquina) {
        Set<String> todasChaves = jogo.getTabuleiro().getTerrenos().keySet();
        for(String chave : todasChaves) {
            int linha = Integer.parseInt(String.valueOf(chave.charAt(0)));
            int coluna = Integer.parseInt(String.valueOf(chave.charAt(1)));
            int diferencaLinha = Math.abs(maquina.getLinha() - linha);
            int diferencaColuna = Math.abs(maquina.getColuna() - coluna);
            if((diferencaLinha + diferencaColuna) != 0 && (diferencaLinha + diferencaColuna) <= maquina.getAlcance()) {
                campoDeAtaque.add(chave);
            }
        }
        for(Maquina m : jogo.getTabuleiro().getMaquinas()) {
            campoDeAtaque.remove(m.getLinha() + "" + m.getColuna());
        }
    }
    
    private void limparSetCampoDeAtaque() {
        campoDeAtaque = new HashSet<>();
    }
    
    private HashMap<String, String> getInformacoesMaquina() {
        HashMap<String, String> resposta = new HashMap<>();
        resposta.put("CaminhoImagem", "src/images/Bloqueado.png");
        resposta.put("Nome", "");
        resposta.put("PV", "");
        resposta.put("Vida", "");
        resposta.put("Ataque", "");
        resposta.put("Alcance", "");
        resposta.put("Movimento", "");
        return resposta;
    }
    
    private HashMap<String, String> getInformacoesMaquina(Maquina maquina) {
        HashMap<String, String> resposta = new HashMap<>();
        resposta.put("CaminhoImagem", maquina.caminhoImagemDirecaoAtual());
        resposta.put("Nome", maquina.getNome());
        resposta.put("PV", String.valueOf(maquina.getPontosVitoria()));
        resposta.put("Vida", String.valueOf(maquina.getVida()));
        resposta.put("Ataque", String.valueOf(maquina.getAtaque()));
        resposta.put("Alcance", String.valueOf(maquina.getAlcance()));
        resposta.put("Movimento", String.valueOf(maquina.getMovimento()));
        return resposta;
    }
    
    public String getNomeJogadorAtual() {
        return jogo.nomeJogadorAtivo().getNome();
    }
    
    public String getInformacoesHeaderJogadorAtual() {
        return jogo.nomeJogadorAtivo().getNome() + " PV: " + jogo.getJogador(jogo.nomeJogadorAtivo()).getPontosVitoria();
    }
    
    public StateAcaoAtiva getStateAcaoAtiva() {
        return stateAcaoAtiva;
    }
    
    public void setStateAcaoAtiva(StateAcaoAtiva stateAcaoAtiva) {
        this.stateAcaoAtiva = stateAcaoAtiva;
    }
    
    public Jogo getJogo() {
        return jogo;
    }
    
    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }
    
    public Maquina getMaquinaSelecionada() {
        return maquinaSelecionada;
    }
    
    public void setMaquinaSelecionada(Maquina maquinaSelecionada) {
        this.maquinaSelecionada = maquinaSelecionada;
    }
    
    public void navegarParaOutraTela(String caminho) throws Exception {
        AbstractFactoryTela factoryTela = (AbstractFactoryTela) Class.forName(caminho).getDeclaredConstructor().newInstance();
        for(ObserverTelaJogo observer : observers) {
            factoryTela.construirTela();
            observer.navegarParaOutraTela();
        }
    }
}
