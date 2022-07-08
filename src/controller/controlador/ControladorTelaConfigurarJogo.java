package controller.controlador;

//CONTROLLER
import model.state.stateDirecao.StateDirecao;
import model.state.stateDirecao.StateDirecaoSul;
import model.state.stateDirecao.StateDirecaoNorte;
import controller.builderMaquina.DirectorMaquina;
import controller.builderTerreno.DirectorTerreno;
import controller.builderMaquina.ConstruirMaquina;
import controller.builderTerreno.ConstruirTerreno;
import controller.builderTabuleiro.DirectorTabuleiro;
import controller.singleton.SingletonConfiguracaoJogo;
import controller.observer.ObserverTelaConfigurarJogo;
import controller.abstractFactoryTela.AbstractFactoryTela;
import controller.builderTabuleiro.ConstruirTabuleiroComMaquinas;
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
            directorMaquina.construir(null, 0, 0, new StateDirecaoNorte(construirMaquina.getMaquina()));
            Maquina maquina = construirMaquina.getMaquina();
//            maquina.setDirecaoAtual(new StateDirecaoNorte(maquina));
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

    public void adicionarMaquinaAoJogador(EnumJogador nomeJogador, String posicao, EnumMaquinas nomeMaquina, String nomeTabuleiro) {
        try {
            boolean continua = false;
            int linha = Integer.parseInt(String.valueOf(posicao.charAt(0)));
            int coluna = Integer.parseInt(String.valueOf(posicao.charAt(1)));
            if ((nomeJogador.equals(EnumJogador.JOGADOR1) && linha > 5)
                    || (nomeJogador.equals(EnumJogador.JOGADOR2) && linha < 2)) {
                continua = true;
            }
            if (continua) {
                Jogador jogador = jogadores.get(nomeJogador);
                construirMaquina = (ConstruirMaquina) Class.forName(nomeMaquina.getNomeBuilder()).getDeclaredConstructor().newInstance();
                DirectorMaquina directorMaquina = new DirectorMaquina(construirMaquina);
                StateDirecao direcao = new StateDirecaoSul(construirMaquina.getMaquina());
                if (nomeJogador.equals(EnumJogador.JOGADOR1)) {
                    direcao = new StateDirecaoNorte(construirMaquina.getMaquina());
                }
                directorMaquina.construir(jogador, linha, coluna, direcao);
                Maquina maquina = construirMaquina.getMaquina();
                if (jogador.getMaquinas().containsKey(posicao)) {
                    if (jogador.podeSubstituirMaquinas(jogador.getMaquinas().get(posicao), maquina)) {
                        removerMaquinaDoJogador(nomeJogador, posicao);
                    }
                }
                if (jogador.podeAdicionarMaquinas(maquina)) {
                    if ((tabuleiros.get(nomeTabuleiro).getTerrenoPorIndice(posicao).getTipo().equals(EnumTipoTerreno.ABISMO)
                            && maquina.getTipo().equals(EnumTipoMaquinas.MERGULHO))
                            || (!tabuleiros.get(nomeTabuleiro).getTerrenoPorIndice(posicao).getTipo().equals(EnumTipoTerreno.ABISMO))) {
                        jogador.addMaquinas(posicao, maquina);
                        for (ObserverTelaConfigurarJogo observer : observers) {
                            observer.desenharMaquina(maquina.caminhoImagemDirecaoAtual(), posicao);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void removerMaquinaDoJogador(EnumJogador nomeJogador, String posicao) {
        boolean continua = false;
        if ((nomeJogador.equals(EnumJogador.JOGADOR1) && Integer.parseInt(posicao.substring(0, 1)) > 5)
                || (nomeJogador.equals(EnumJogador.JOGADOR2) && Integer.parseInt(posicao.substring(0, 1)) < 2)) {
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
        jogadores.get(EnumJogador.JOGADOR1).getMaquinas().clear();
        jogadores.get(EnumJogador.JOGADOR2).getMaquinas().clear();
    }

    public void alterouComboboxJogadores(EnumJogador nomeNovoJogador) {
        Set<String> chavesMaquinasSuperiores = jogadores.get(EnumJogador.JOGADOR2).getMaquinas().keySet();
        Set<String> chavesMaquinasInferiores = jogadores.get(EnumJogador.JOGADOR1).getMaquinas().keySet();
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
        if (nomeNovoJogador.equals(EnumJogador.JOGADOR1)){
            isJogador1 = true;
        }
        if (isJogador1) {
            caminho = "BloqueadoPequeno";
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
            caminho = "BloqueadoPequeno";
        }
        for (String chave : chavesLinhasInferiores) {
            for (ObserverTelaConfigurarJogo observer:observers) {
                observer.desenharBloquadoOuVazio(caminho, chave);
            }
        }
    }

    public void navegarParaTelaJogo(String nomeTabuleiro) {
        if (jogadores.get(EnumJogador.JOGADOR1).contagemPVMaquinas() > 0
                && jogadores.get(EnumJogador.JOGADOR2).contagemPVMaquinas() > 0) {
            construirTabuleiro = new ConstruirTabuleiroComMaquinas();
            directorTabuleiro = new DirectorTabuleiro(construirTabuleiro);
            HashMap<String, Maquina> maquinas = new HashMap<>();
            Map<String, Maquina> maquinasJogador1 = jogadores.get(EnumJogador.JOGADOR1).getMaquinas();
            Map<String, Maquina> maquinasJogador2 = jogadores.get(EnumJogador.JOGADOR2).getMaquinas();
            for (String chave : maquinasJogador1.keySet()) {
                maquinas.put(chave, maquinasJogador1.get(chave));
            }
            for (String chave : maquinasJogador2.keySet()) {
                maquinas.put(chave, maquinasJogador2.get(chave));
            }
            directorTabuleiro.construir(tabuleiros.get(nomeTabuleiro).getTipoTabuleiro(), tabuleiros.get(nomeTabuleiro).getTerrenos(), maquinas);
            SingletonConfiguracaoJogo.getInstancia().setTabuleiro(construirTabuleiro.getTabuleiro());
            SingletonConfiguracaoJogo.getInstancia().setJogadores(jogadores);
            navegarParaOutraTela("controller.abstractFactoryTela.ConcretFactoryTelaJogo");
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
