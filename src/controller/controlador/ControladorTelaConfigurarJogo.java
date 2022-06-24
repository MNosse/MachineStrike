package controller.controlador;

import controller.builderTabuleiro.ConstruirTabuleiroSemMaquinas;
import controller.builderTabuleiro.DirectorTabuleiro;
import controller.builderTerreno.ConstruirTerreno;
import controller.builderTerreno.DirectorTerreno;
import controller.observer.ObserverTelaConfigurarJogo;
import controller.observer.ObserverTelaTabuleiros;
import global.EnumTipoMaquinas;
import global.EnumTipoTabuleiro;
import global.EnumTipoTerreno;
import model.Jogador;
import model.Maquina;
import model.Tabuleiro;
import model.Terreno;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class ControladorTelaConfigurarJogo {
    private LinkedHashMap<String, Jogador> jogadores;
    private LinkedHashMap<String, Tabuleiro> tabuleiros;
    private List<ObserverTelaConfigurarJogo> observers;
    private DirectorTerreno directorTerreno;
    private DirectorTabuleiro directorTabuleiro;
    private ConstruirTabuleiroSemMaquinas construirTabuleiro;

    public ControladorTelaConfigurarJogo() {
        observers = new ArrayList<>();
        construirTabuleiros();
        iniciarJogadores();
    }

    private void construirTabuleiros() {
        tabuleiros = new LinkedHashMap<>();
        BufferedReader bufferedReader;
        File todosArquivos = new File("src/arquivosTabuleiros");
        File[] arquivos = todosArquivos.listFiles();
        for (File arquivo:arquivos){
            try {
                bufferedReader = new BufferedReader(new FileReader(arquivo));
                List<String> linhas = new ArrayList<>(bufferedReader.lines().toList());
                bufferedReader.close();
                String nome = linhas.get(0);
                EnumTipoTabuleiro tipoTabuleiro = EnumTipoTabuleiro.PADRAO;
                if (!linhas.get(1).equals("padrao")){
                    tipoTabuleiro = EnumTipoTabuleiro.CRIADO;
                }
                ArrayList<ArrayList<String>> tiposTerrenos = new ArrayList<>();
                for (int linha = 2; linha < linhas.size(); linha++){
                    tiposTerrenos.add(new ArrayList<>(Arrays.asList(linhas.get(linha).split(" "))));
                }
                HashMap<String, Terreno> terrenos = new HashMap<>();
                construirTabuleiro = new ConstruirTabuleiroSemMaquinas();
                directorTabuleiro = new DirectorTabuleiro(construirTabuleiro);
                for (int linha = 0; linha < 8; linha++){
                    for (int coluna = 0; coluna < 8; coluna++){
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

    public void iniciarJogadores(){
        jogadores = new LinkedHashMap<>();
        Jogador jogador1 = new Jogador("Jogador 1");
        Jogador jogador2 = new Jogador("Jogador 2");
        jogadores.put(jogador1.getNome(), jogador1);
        jogadores.put(jogador2.getNome(), jogador2);
    }

    public void desenharTabuleiro(String chaveTabuleiro){
        Tabuleiro tabuleiro = tabuleiros.get(chaveTabuleiro);
        HashMap<String, EnumTipoTerreno> terrenos = new HashMap<>();
        for (int linha = 0; linha < 8; linha++){
            for (int coluna = 0; coluna < 8; coluna++){
                terrenos.put((linha+""+coluna), tabuleiro.getTerrenos().get(linha+""+coluna).getTipo());
            }
        }
        for (ObserverTelaConfigurarJogo observer:observers) {
            observer.desenharTabuleiro(terrenos);
        }
    }

    public void attach(ObserverTelaConfigurarJogo observer){
        observers.add(observer);
    }

    public void mostrar(){
        for (ObserverTelaConfigurarJogo observer:observers) {
            observer.mostrarTela();
        }
    }

    public void ocultar(){
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

    public void adicionarMaquinaAoJogador(String nomeJogador, EnumTipoMaquinas nomeMaquina){

    }
}
