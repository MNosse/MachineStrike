package controller.controlador;

//CONTROLLER
import controller.builderMaquina.DirectorMaquina;
import controller.builderTerreno.DirectorTerreno;
import controller.builderMaquina.ConstruirMaquina;
import controller.builderTerreno.ConstruirTerreno;
import controller.builderTabuleiro.DirectorTabuleiro;
import controller.observer.ObserverTelaConfigurarJogo;
import controller.builderTabuleiro.ConstruirTabuleiroSemMaquinas;

//GLOBAL
import controller.stateDirecao.StateDirecao;
import controller.stateDirecao.StateDirecaoNorte;
import controller.stateDirecao.StateDirecaoSul;
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

    private DirectorMaquina directorMaquina;
    private DirectorTerreno directorTerreno;
    private ConstruirMaquina construirMaquina;
    private DirectorTabuleiro directorTabuleiro;
    private LinkedHashMap<String, Jogador> jogadores;
    private List<ObserverTelaConfigurarJogo> observers;
    private LinkedHashMap<String, Tabuleiro> tabuleiros;
    private ConstruirTabuleiroSemMaquinas construirTabuleiro;
    private Map<EnumMaquinas, String> nomeConstrutoresMaquinas;

    public ControladorTelaConfigurarJogo() {
        observers = new ArrayList<>();
        construirTabuleiros();
        iniciarJogadores();
        iniciarNomeConstrutoresMaquinas();
    }

    private void construirTabuleiros() {
        tabuleiros = new LinkedHashMap<>();
        BufferedReader bufferedReader;
        File todosArquivos = new File("src/arquivosTabuleiros");
        File[] arquivos = todosArquivos.listFiles();
        for (File arquivo:arquivos) {
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

    public void iniciarJogadores() {
        jogadores = new LinkedHashMap<>();
        Jogador jogador1 = new Jogador("Jogador 1");
        Jogador jogador2 = new Jogador("Jogador 2");
        jogadores.put(jogador1.getNome(), jogador1);
        jogadores.put(jogador2.getNome(), jogador2);
    }

    public void iniciarNomeConstrutoresMaquinas() {
        nomeConstrutoresMaquinas = new HashMap<>();
        nomeConstrutoresMaquinas.put(EnumMaquinas.ARIETE1, "ConstruirAriete1");
        nomeConstrutoresMaquinas.put(EnumMaquinas.ARIETE2, "ConstruirAriete2");
        nomeConstrutoresMaquinas.put(EnumMaquinas.ARRANCADA, "ConstruirArrancada");
        nomeConstrutoresMaquinas.put(EnumMaquinas.ATIRADOR1, "ConstruirAtirador1");
        nomeConstrutoresMaquinas.put(EnumMaquinas.ATIRADOR2, "ConstruirAtirador2");
        nomeConstrutoresMaquinas.put(EnumMaquinas.CORPO_A_CORPO1, "ConstruirCorpoACorpo1");
        nomeConstrutoresMaquinas.put(EnumMaquinas.CORPO_A_CORPO2, "ConstruirCorpoACorpo2");
        nomeConstrutoresMaquinas.put(EnumMaquinas.MERGULHO1, "ConstruirMergulho1");
        nomeConstrutoresMaquinas.put(EnumMaquinas.MERGULHO2, "ConstruirMergulho2");
        nomeConstrutoresMaquinas.put(EnumMaquinas.PUXAO, "ConstruirPuxao");
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

    public void mostrar() {
        for (ObserverTelaConfigurarJogo observer:observers) {
            observer.mostrarTela();
        }
    }

    public void ocultar() {
        for (ObserverTelaConfigurarJogo observer:observers) {
            observer.ocultarTela();
        }
    }

    public HashMap<String, Tabuleiro> getTabuleiros() {
        return tabuleiros;
    }

    public LinkedHashMap<String, Jogador> getJogadores() {
        return jogadores;
    }

    public void adicionarMaquinaAoJogador(String nomeJogador, String posicao, EnumMaquinas nomeMaquina) {
        try {
            construirMaquina = (ConstruirMaquina) Class.forName("controller.builderMaquina."+nomeConstrutoresMaquinas.get(nomeMaquina)).getDeclaredConstructor().newInstance();
            directorMaquina = new DirectorMaquina(construirMaquina);
            Jogador jogador = jogadores.get(nomeJogador);
            int linha = Integer.parseInt(String.valueOf(posicao.charAt(0)));
            int coluna = Integer.parseInt(String.valueOf(posicao.charAt(1)));
            directorMaquina.construir(jogador, linha, coluna);
            Maquina maquina = construirMaquina.getMaquina();
            StateDirecao direcao = new StateDirecaoSul(maquina);
            if (nomeJogador.equals("Jogador 1")) {
                direcao = new StateDirecaoNorte(maquina);
            }
            maquina.setDirecaoAtual(direcao);
            jogador.addMaquinas(posicao, maquina);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void removerMaquinaDoJogador(String nomeJogador, String posicao) {
        Jogador jogador = jogadores.get(nomeJogador);
        jogador.removeMaquinas(posicao);
    }

    public void removerMaquinasDosJogadores() {
        jogadores.get("Jogador 1").getMaquinas().clear();
        jogadores.get("Jogador 2").getMaquinas().clear();
    }

    public String imagemAtualMaquina(String nomeJogador, String posicao) {
        return jogadores.get(nomeJogador).getMaquinas().get(posicao).getDirecaoAtual().getCaminhoImagem();
    }

    public Set<String> getMaquinasJogadorKeySet(String nome) {
        return jogadores.get(nome).getMaquinas().keySet();
    }
}
