package controller;

//CONTROLLER
import controller.singleton.SingletonConfiguracaoJogo;
import controller.observer.ObserverTelaConfigurarJogo;

//GLOBAL
import global.*;

//JAVA
import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

//MODEL
import model.Terreno;
import model.Maquina;
import model.Jogador;
import model.Tabuleiro;
import model.builderMaquina.DirectorMaquina;
import model.builderTerreno.DirectorTerreno;
import model.builderMaquina.ConstruirMaquina;
import model.builderTerreno.ConstruirTerreno;
import model.builderTabuleiro.DirectorTabuleiro;
import model.state.stateDirecao.StateDirecaoSul;
import model.builderTabuleiro.ConstruirTabuleiroComMaquinas;
import model.builderTabuleiro.ConstruirTabuleiroSemMaquinas;

//VIEW ABSTRACT FACTORY
import view.abstractFactoryTela.AbstractFactoryTela;

public class ControladorTelaConfigurarJogo {

    private DirectorTerreno directorTerreno;
    private final File[] arquivosTabuleiros;
    private ConstruirMaquina construirMaquina;
    private DirectorTabuleiro directorTabuleiro;
    private LinkedHashMap<EnumJogador, Jogador> jogadores;
    private List<ObserverTelaConfigurarJogo> observers;
    private LinkedHashMap<String, Tabuleiro> tabuleiros;
    private ConstruirTabuleiroSemMaquinas construirTabuleiro;

    public ControladorTelaConfigurarJogo() {
        observers = new ArrayList<>();
        arquivosTabuleiros = new File("src/arquivosTabuleiros").listFiles();
        construirTabuleiros();
        iniciarJogadores();
    }

    private void construirTabuleiros() {
        tabuleiros = new LinkedHashMap<>();
        BufferedReader bufferedReader;
        for (File arquivo:arquivosTabuleiros) {
            try {
                bufferedReader = new BufferedReader(new FileReader(arquivo));
                List<String> linhas = new ArrayList<>(bufferedReader.lines().toList());
                bufferedReader.close();
                String nome = linhas.get(0);
                EnumTipoTabuleiro tipoTabuleiro = EnumTipoTabuleiro.PADRAO;
                if (!linhas.get(1).equals("padrao")) {
                    tipoTabuleiro = EnumTipoTabuleiro.CRIADO;
                }
                ArrayList<ArrayList<String>> tiposTerrenos = new ArrayList<>();
                for (int linha = 2; linha < linhas.size(); linha++) {
                    tiposTerrenos.add(new ArrayList<>(Arrays.asList(linhas.get(linha).split(" "))));
                }
                HashMap<String, Terreno> terrenos = new HashMap<>();
                construirTabuleiro = new ConstruirTabuleiroSemMaquinas();
                directorTabuleiro = new DirectorTabuleiro(construirTabuleiro);
                for (int linha = 0; linha < 8; linha++) {
                    for (int coluna = 0; coluna < 8; coluna++) {
                        ConstruirTerreno builder = (ConstruirTerreno) Class.forName("model.builderTerreno.Construir"+tiposTerrenos.get(linha).get(coluna)).getDeclaredConstructor().newInstance();
                        directorTerreno = new DirectorTerreno(builder);
                        directorTerreno.construir();
                        terrenos.put(linha+""+coluna, builder.getTerreno());
                    }
                }
                directorTabuleiro.construir(tipoTabuleiro, terrenos, null);
                tabuleiros.put(nome, construirTabuleiro.getTabuleiro());
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        }
    }

    private void iniciarJogadores() {
        jogadores = new LinkedHashMap<>();
        Jogador jogador1 = new Jogador(EnumJogador.JOGADOR1);
        Jogador jogador2 = new Jogador(EnumJogador.JOGADOR2);
        jogadores.put(EnumJogador.JOGADOR1, jogador1);
        jogadores.put(EnumJogador.JOGADOR2, jogador2);
    }

    public void desenharTabuleiro(String chaveTabuleiro) {
        HashMap<String, Terreno> terrenos = tabuleiros.get(chaveTabuleiro).getTerrenos();
        Set<String> posicoes = terrenos.keySet();
        HashMap<String, EnumTipoTerreno> tiposTerreno = new HashMap<>();
        for(String posicao : posicoes) {
            tiposTerreno.put(posicao, terrenos.get(posicao).getTipo());
        }
        for (ObserverTelaConfigurarJogo observer:observers) {
            observer.desenharTabuleiro(tiposTerreno);
        }
    }

    public void attach(ObserverTelaConfigurarJogo observer) {
        observers.add(observer);
    }

    public HashMap<String, Tabuleiro> getTabuleiros() {
        return tabuleiros;
    }

    public LinkedHashMap<EnumJogador, Jogador> getJogadores() {
        return jogadores;
    }

    public HashMap<String, String> getInformacoesMaquina(EnumMaquinas nomeMaquina) {
        HashMap<String, String> resposta = new HashMap<>();
        try {
            construirMaquina = (ConstruirMaquina) Class.forName(nomeMaquina.getNomeBuilder()).getDeclaredConstructor().newInstance();
            DirectorMaquina directorMaquina = new DirectorMaquina(construirMaquina);
            directorMaquina.construir(null, 0, 0);
            Maquina maquina = construirMaquina.getMaquina();
            resposta.put("CaminhoImagem", maquina.caminhoImagemDirecaoAtual());
            resposta.put("Nome", maquina.getNome());
            resposta.put("PV", String.valueOf(maquina.getPontosVitoria()));
            resposta.put("Vida", String.valueOf(maquina.getVida()));
            resposta.put("Ataque", String.valueOf(maquina.getAtaque()));
            resposta.put("Alcance", String.valueOf(maquina.getAlcance()));
            resposta.put("Movimento", String.valueOf(maquina.getMovimento()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resposta;
    }

    public void adicionarMaquinaAoJogador(EnumJogador nomeJogador, int linha, int coluna, EnumMaquinas nomeMaquina, String nomeTabuleiro) {
        try {
            Jogador jogador = jogadores.get(nomeJogador);
            construirMaquina = (ConstruirMaquina) Class.forName(nomeMaquina.getNomeBuilder()).getDeclaredConstructor().newInstance();
            DirectorMaquina directorMaquina = new DirectorMaquina(construirMaquina);
            directorMaquina.construir(jogador, linha, coluna);
            Maquina maquina = construirMaquina.getMaquina();
            if (nomeJogador == EnumJogador.JOGADOR2) {
                maquina.setDirecaoAtual(new StateDirecaoSul(maquina));
            }
            if (!jogador.podeAdicionarMaquinaNaPosicao(linha, coluna)) {
                if (jogador.podeSubstituirMaquinas(jogador.getMaquinaPorPosicao(linha, coluna), maquina)) {
                    jogador.removeMaquina(linha, coluna);
                }
            }
            if (jogador.podeAdicionarMaquinas(maquina)) {
                if ((tabuleiros.get(nomeTabuleiro).getTerrenoPorPosicao(linha+""+coluna).getTipo().equals(EnumTipoTerreno.ABISMO)
                        && maquina.getTipo().equals(EnumTipoMaquinas.MERGULHO))
                        || (!tabuleiros.get(nomeTabuleiro).getTerrenoPorPosicao(linha+""+coluna).getTipo().equals(EnumTipoTerreno.ABISMO))) {
                    jogador.addMaquinas(maquina);
                    for (ObserverTelaConfigurarJogo observer : observers) {
                        observer.desenharMaquina(maquina.caminhoImagemDirecaoAtual(), linha+""+coluna);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void removerMaquinaDoJogador(EnumJogador nomeJogador, int linha, int coluna) {
        Jogador jogador = jogadores.get(nomeJogador);
        jogador.removeMaquina(linha, coluna);
        for (ObserverTelaConfigurarJogo observer:observers) {
            observer.desenharMaquina("src/images/Vazio.png", linha+""+coluna);
        }
    }

    public void removerMaquinasDosJogadores() {
        jogadores.get(EnumJogador.JOGADOR1).getMaquinas().clear();
        jogadores.get(EnumJogador.JOGADOR2).getMaquinas().clear();
    }

    public void alterouComboboxJogadores(EnumJogador nomeNovoJogador) {
        HashMap<String, String> chavesLinhasSuperiores = new HashMap<>();
        HashMap<String, String> chavesLinhasInferiores = new HashMap<>();
        Jogador jogador1 = jogadores.get(EnumJogador.JOGADOR1);
        Jogador jogador2 = jogadores.get(EnumJogador.JOGADOR2);
        for (int linha = 0; linha < 2; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                if (jogador2.getMaquinaPorPosicao(linha, coluna) == null) {
                    String nome = (nomeNovoJogador.equals(EnumJogador.JOGADOR1) ? "BloqueadoPequeno" : "Vazio");
                    chavesLinhasSuperiores.put(linha+""+coluna, nome);
                }
                int linhaInferior = linha + 6;
                if (jogador1.getMaquinaPorPosicao(linhaInferior, coluna) == null) {
                    String nome = (nomeNovoJogador.equals(EnumJogador.JOGADOR1) ? "Vazio" : "BloqueadoPequeno");
                    chavesLinhasInferiores.put(linhaInferior+""+coluna, nome);
                }
            }
        }
        for (ObserverTelaConfigurarJogo observer:observers) {
            observer.desenharBloqueadosOuVazios(chavesLinhasSuperiores);
            observer.desenharBloqueadosOuVazios(chavesLinhasInferiores);
        }
    }

    public void navegarParaTelaJogo(String nomeTabuleiro) {
        if (jogadores.get(EnumJogador.JOGADOR1).contagemPVMaquinas() > 0
                && jogadores.get(EnumJogador.JOGADOR2).contagemPVMaquinas() > 0) {
            construirTabuleiro = new ConstruirTabuleiroComMaquinas();
            directorTabuleiro = new DirectorTabuleiro(construirTabuleiro);
            List<Maquina> maquinas = new ArrayList<>();
            for (Maquina maquina : jogadores.get(EnumJogador.JOGADOR1).getMaquinas()) {
                maquinas.add(maquina);
            }
            for (Maquina maquina : jogadores.get(EnumJogador.JOGADOR2).getMaquinas()) {
                maquinas.add(maquina);
            }
            directorTabuleiro.construir(tabuleiros.get(nomeTabuleiro).getTipoTabuleiro(), tabuleiros.get(nomeTabuleiro).getTerrenos(), maquinas);
            SingletonConfiguracaoJogo.getInstancia().setTabuleiro(construirTabuleiro.getTabuleiro());
            SingletonConfiguracaoJogo.getInstancia().setJogadores(jogadores);
            navegarParaOutraTela("view.abstractFactoryTela.ConcretFactoryTelaJogo");
        }
    }

    public void navegarParaOutraTela(String caminho) {
        try {
            AbstractFactoryTela factoryTela = (AbstractFactoryTela) Class.forName(caminho).getDeclaredConstructor().newInstance();
            for (ObserverTelaConfigurarJogo observer : observers) {
                observer.navegarParaOutraTela(factoryTela.construirTela());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
