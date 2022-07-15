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

//GLOBAL
import global.Enum.EnumTipoTerreno;

//JAVA
import java.util.*;

//MODEL
import model.*;

//VIEW ABSTRACT FACTORY
import view.abstractFactoryTela.AbstractFactoryTela;

public class ControladorTelaJogo implements ObserverCommand {

    private Jogo jogo;
    private List<ObserverTelaJogo> observers;
    private Set<String> campoDeMovimento;
    private Set<String> campoDeCorrida;
    private Maquina maquinaSelecionada;
    private StateAcaoAtiva stateAcaoAtiva;

    public ControladorTelaJogo() {
        observers = new ArrayList<>();
        campoDeMovimento = new HashSet<>();
        campoDeCorrida = new HashSet<>();
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
        for (String posicao : posicoes) {
            tiposTerrenos.put(posicao, terrenos.get(posicao).getTipo());
        }
        List<Maquina> maquinas = tabuleiro.getMaquinas();
        HashMap<String, String> desenhosMaquinas = new HashMap<>();
        for (Maquina maquina : maquinas) {
            desenhosMaquinas.put(maquina.getLinha()+""+maquina.getColuna(), maquina.caminhoImagemDirecaoAtual());
        }
        for (ObserverTelaJogo observer:observers) {
            observer.desenharTabuleiro(tiposTerrenos);
        }
        for (ObserverTelaJogo observer:observers) {
            observer.desenharQuadrados(desenhosMaquinas);
        }
    }

    @Override
    public void redesenharMaquinas() {
        Tabuleiro tabuleiro = jogo.getTabuleiro();
        List<Maquina> maquinas = tabuleiro.getMaquinas();
        HashMap<String, String> desenhosMaquinas = new HashMap<>();
        for (Maquina maquina : maquinas) {
            desenhosMaquinas.put(maquina.getLinha()+""+maquina.getColuna(), maquina.caminhoImagemDirecaoAtual());
        }
        for (ObserverTelaJogo observer:observers) {
            observer.apagarCampoDeMovimento(tabuleiro.getTerrenos().keySet());
            observer.desenharQuadrados(desenhosMaquinas);
        }
    }

    private void desenharCampoDeMovimento(Maquina maquina) {
        apagarCampoDeCorrida();
        gerarSetCampoDeMovimento(maquina);
        for (ObserverTelaJogo observer:observers) {
            observer.desenharCampoDeMovimento(campoDeMovimento);
        }
    }

    private void apagarCampoDeMovimento() {
        for (ObserverTelaJogo observer:observers) {
            observer.apagarCampoDeMovimento(campoDeMovimento);
        }
        limparSetCampoDeMovimento();
    }

    private void desenharCampoDeCorrida(Maquina maquina) {
        apagarCampoDeMovimento();
        gerarSetCampoDeCorrida(maquina);
        for (ObserverTelaJogo observer:observers) {
            observer.desenharCampoDeMovimento(campoDeCorrida);
        }
    }

    private void apagarCampoDeCorrida() {
        for (ObserverTelaJogo observer:observers) {
            observer.apagarCampoDeMovimento(campoDeCorrida);
        }
        limparSetCampoDeCorrida();
    }

    public void clicarBotaoMover() throws Exception {
        if (jogo.getMaquinasQueAtacaram().contains(maquinaSelecionada) || jogo.getMaquinasQueAtacaram().size() < 2) {
            stateAcaoAtiva.ativarMover();
            apagarCampos();
            desenharCampoDeMovimento(maquinaSelecionada);
        } else {
            throw new RuntimeException();
        }
    }

    public void clicarBotaoCorrer() throws Exception {
        if (jogo.getMaquinasQueAtacaram().contains(maquinaSelecionada) || jogo.getMaquinasQueAtacaram().size() < 2) {
            stateAcaoAtiva.ativarCorrer();
            apagarCampos();
            desenharCampoDeCorrida(maquinaSelecionada);
        } else {
            throw new RuntimeException();
        }
    }

    public void clicarBotaoAtacar() throws Exception {
        if (jogo.getMaquinasQueAtacaram().contains(maquinaSelecionada) || jogo.getMaquinasQueAtacaram().size() < 2) {
            stateAcaoAtiva.ativarAtacar();
            apagarCampos();
        } else {
            throw new RuntimeException();
        }
    }

    public void clicarBotaoSobrecarregar() throws Exception {
        if (jogo.getMaquinasQueAtacaram().contains(maquinaSelecionada) || jogo.getMaquinasQueAtacaram().size() < 2) {
            stateAcaoAtiva.ativarSobrecarregar();
            stateAcaoAtiva.fazerAcao(null);
            apagarCampos();
        } else {
            throw new RuntimeException();
        }
    }

    public void clicarBotaoEncerrar() {
        jogo.passarTurno();
        maquinaSelecionada = null;
        desativarPainel();
        apagarCampos();
        redesenharMaquinas();
        for (ObserverTelaJogo observer:observers) {
            observer.atualizarLblJogadorAtivo(jogo.jogadorAtivo().getNome().getNome());
        }
    }

    public void clicarBotaoGirar() throws Exception {
        if (jogo.getMaquinasQueAtacaram().contains(maquinaSelecionada) || jogo.getMaquinasQueAtacaram().size() < 2) {
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
        if (maquinaSelecionada != null) {
            for (ObserverTelaJogo observer : observers) {
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
        for (ObserverTelaJogo observer : observers) {
            observer.desativarBotoes();
            observer.atualizarCardMaquinaAtacante(getInformacoesMaquina());
        }
    }

    public void apagarCampos() {
        apagarCampoDeMovimento();
        apagarCampoDeCorrida();
    }

    public void selecionarQuadrado(String posicao) throws Exception {
        stateAcaoAtiva.fazerAcao(posicao);
    }

    private void gerarSetCampoDeMovimento(Maquina maquina) {
        Set<String> todasChaves = jogo.getTabuleiro().getTerrenos().keySet();
        for (String chave : todasChaves) {
            int linha = Integer.parseInt(String.valueOf(chave.charAt(0)));
            int coluna = Integer.parseInt(String.valueOf(chave.charAt(1)));
            if (maquina.podeMover(linha, coluna, jogo.getTabuleiro().getMaquinas())) {
                campoDeMovimento.add(chave);
            }
        }
        for (Maquina m : jogo.getTabuleiro().getMaquinas()) {
            campoDeMovimento.remove(m.getLinha()+""+ m.getColuna());
        }
    }

    private void limparSetCampoDeMovimento() {
        campoDeMovimento = new HashSet<>();
    }

    private void gerarSetCampoDeCorrida(Maquina maquina) {
        Set<String> todasChaves = jogo.getTabuleiro().getTerrenos().keySet();
        for (String chave : todasChaves) {
            int linha = Integer.parseInt(String.valueOf(chave.charAt(0)));
            int coluna = Integer.parseInt(String.valueOf(chave.charAt(1)));
            if (maquina.podeCorrer(linha, coluna)) {
                campoDeCorrida.add(chave);
            }
        }
        for (Maquina m : jogo.getTabuleiro().getMaquinas()) {
            campoDeCorrida.remove(m.getLinha()+""+ m.getColuna());
        }
        gerarSetCampoDeMovimento(maquina);
        for (String posicao : campoDeMovimento) {
            campoDeCorrida.remove(posicao);
        }
        limparSetCampoDeMovimento();
    }

    private void limparSetCampoDeCorrida() {
        campoDeCorrida = new HashSet<>();
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
        for (ObserverTelaJogo observer : observers) {
            observer.navegarParaOutraTela(factoryTela.construirTela());
        }
    }
}
