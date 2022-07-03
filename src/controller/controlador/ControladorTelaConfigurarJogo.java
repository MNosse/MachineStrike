package controller.controlador;

//CONTROLLER
import controller.stateDirecao.StateDirecao;
import controller.stateDirecao.StateDirecaoSul;
import controller.stateDirecao.StateDirecaoNorte;
import controller.builderMaquina.DirectorMaquina;
import controller.builderTerreno.DirectorTerreno;
import controller.builderMaquina.ConstruirMaquina;
import controller.builderTerreno.ConstruirTerreno;
import controller.builderTabuleiro.DirectorTabuleiro;
import controller.observer.ObserverTelaConfigurarJogo;
import controller.abstractFactoryTela.AbstractFactoryTela;
import controller.abstractFactoryTela.ConcretFactoryTelaInicial;
import controller.builderTabuleiro.ConstruirTabuleiroSemMaquinas;

//GLOBAL
import global.*;

//JAVA
import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

//MODEL
import model.Jogador;
import model.Maquina;
import model.Terreno;
import model.Tabuleiro;

public class ControladorTelaConfigurarJogo {

    private DirectorTerreno directorTerreno;
    private final File[] arquivosTabuleiros;
    private ConstruirMaquina construirMaquina;
    private DirectorTabuleiro directorTabuleiro;
    private LinkedHashMap<String, Jogador> jogadores;
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
                        ConstruirTerreno builder = (ConstruirTerreno) Class.forName("controller.builderTerreno.Construir"+tiposTerrenos.get(linha).get(coluna)).getDeclaredConstructor().newInstance();
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
        Jogador jogador1 = new Jogador("Jogador 1");
        Jogador jogador2 = new Jogador("Jogador 2");
        jogadores.put(jogador1.getNome(), jogador1);
        jogadores.put(jogador2.getNome(), jogador2);
    }

    public void desenharTabuleiro(String chaveTabuleiro) {
        Tabuleiro tabuleiro = tabuleiros.get(chaveTabuleiro);
        HashMap<String, EnumTipoTerreno> terrenos = new HashMap<>();
        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                terrenos.put((linha+""+coluna), tabuleiro.getTerrenos().get(linha+""+coluna).getTipo());
            }
        }
        for (ObserverTelaConfigurarJogo observer:observers) {
            observer.desenharTabuleiro(terrenos);
        }
    }

    public void attach(ObserverTelaConfigurarJogo observer) {
        observers.add(observer);
    }

    public HashMap<String, Tabuleiro> getTabuleiros() {
        return tabuleiros;
    }

    public LinkedHashMap<String, Jogador> getJogadores() {
        return jogadores;
    }

    public HashMap<String, String> getInformacoesMaquina(EnumMaquinas nomeMaquina) {
        HashMap<String, String> resposta = new HashMap<>();
        try {
            construirMaquina = (ConstruirMaquina) Class.forName(nomeMaquina.getCaminhoBuilder()).getDeclaredConstructor().newInstance();
            DirectorMaquina directorMaquina = new DirectorMaquina(construirMaquina);
            directorMaquina.construir(null, 0, 0);
            Maquina maquina = construirMaquina.getMaquina();
            maquina.setDirecaoAtual(new StateDirecaoNorte(maquina));
            resposta.put("CaminhoImagem", maquina.getDirecaoAtual().getCaminhoImagem());
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

    public void adicionarMaquinaAoJogador(String nomeJogador, String posicao, EnumMaquinas nomeMaquina, String nomeTabuleiro) {
        try {
            boolean continua = false;
            if ((nomeJogador.equals("Jogador 1") && Integer.parseInt(posicao.substring(0, 1)) > 5)
                || (nomeJogador.equals("Jogador 2") && Integer.parseInt(posicao.substring(0, 1)) < 2)) {
                continua = true;
            }
            if (continua) {
                construirMaquina = (ConstruirMaquina) Class.forName(nomeMaquina.getCaminhoBuilder()).getDeclaredConstructor().newInstance();
                DirectorMaquina directorMaquina = new DirectorMaquina(construirMaquina);
                Jogador jogador = jogadores.get(nomeJogador);
                int linha = Integer.parseInt(String.valueOf(posicao.charAt(0)));
                int coluna = Integer.parseInt(String.valueOf(posicao.charAt(1)));
                directorMaquina.construir(jogador, linha, coluna);
                Maquina maquina = construirMaquina.getMaquina();
                if ((tabuleiros.get(nomeTabuleiro).getTerrenoPorIndice(posicao).getTipo().equals(EnumTipoTerreno.ABISMO)
                        && maquina.getTipo().equals(EnumTipoMaquinas.MERGULHO))
                        || (!tabuleiros.get(nomeTabuleiro).getTerrenoPorIndice(posicao).getTipo().equals(EnumTipoTerreno.ABISMO))) {
                    StateDirecao direcao = new StateDirecaoSul(maquina);
                    if (nomeJogador.equals("Jogador 1")) {
                        direcao = new StateDirecaoNorte(maquina);
                    }
                    maquina.setDirecaoAtual(direcao);
                    jogador.addMaquinas(posicao, maquina);
                    for (ObserverTelaConfigurarJogo observer : observers) {
                        observer.desenharMaquina(maquina.getDirecaoAtual().getCaminhoImagem(), posicao);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void removerMaquinaDoJogador(String nomeJogador, String posicao) {
        boolean continua = false;
        if ((nomeJogador.equals("Jogador 1") && Integer.parseInt(posicao.substring(0, 1)) > 5)
                || (nomeJogador.equals("Jogador 2") && Integer.parseInt(posicao.substring(0, 1)) < 2)) {
            continua = true;
        }
        if (continua) {
            Jogador jogador = jogadores.get(nomeJogador);
            jogador.removeMaquinas(posicao);
            for (ObserverTelaConfigurarJogo observer:observers) {
                observer.desenharMaquina("src/images/Vazio.png", posicao);
            }
        }
    }

    public void removerMaquinasDosJogadores() {
        jogadores.get("Jogador 1").getMaquinas().clear();
        jogadores.get("Jogador 2").getMaquinas().clear();
    }

    public void alterouComboboxJogadores(String nomeNovoJogador) {
        Set<String> chavesMaquinasSuperiores = jogadores.get("Jogador 2").getMaquinas().keySet();
        Set<String> chavesMaquinasInferiores = jogadores.get("Jogador 1").getMaquinas().keySet();
        Set<String> chavesLinhasSuperiores = new HashSet<>();
        Set<String> chavesLinhasInferiores = new HashSet<>();
        for (int linha = 0; linha < 2; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                String chave1 = linha+""+coluna;
                String chave2 = (linha+6)+""+(coluna);
                if (!chavesMaquinasSuperiores.contains(chave1)) {
                    chavesLinhasSuperiores.add(chave1);
                }
                if (!chavesMaquinasInferiores.contains(chave2)) {
                    chavesLinhasInferiores.add(chave2);
                }
            }
        }
        String caminho = "";
        boolean isJogador1 = false;
        if (nomeNovoJogador.equals("Jogador 1")){
            isJogador1 = true;
        }
        if (isJogador1) {
            caminho = "Bloqueado";
        } else {
            caminho = "Vazio";
        }
        for (String chave : chavesLinhasSuperiores) {

            for (ObserverTelaConfigurarJogo observer:observers) {
                observer.desenharBloquadoOuVazio(caminho, chave);
            }
        }
        if (isJogador1) {
            caminho = "Vazio";
        } else {
            caminho = "Bloqueado";
        }
        for (String chave : chavesLinhasInferiores) {
            for (ObserverTelaConfigurarJogo observer:observers) {
                observer.desenharBloquadoOuVazio(caminho, chave);
            }
        }
    }

    public void navegarParaTelaInicial() {
        AbstractFactoryTela factoryTela = new ConcretFactoryTelaInicial();
        for (ObserverTelaConfigurarJogo observer:observers) {
            observer.navegarParaOutraTela(factoryTela.construirTela());
        }
    }
}
