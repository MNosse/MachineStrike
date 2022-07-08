package model;

//CONTROLLER
import global.EnumDirecao;
import model.state.stateDirecao.StateDirecao;

//GLOBAL
import global.EnumResistencia;
import global.EnumTipoMaquinas;
import model.state.stateMover.StateMover;

public class Maquina {
    private int vida;
    private int linha;
    private int coluna;
    private int ataque;
    private int alcance;
    private String nome;
    private int movimento;
    private Jogador jogador;
    private int pontosVitoria;
    private EnumResistencia tras;
    private EnumTipoMaquinas tipo;
    private EnumResistencia frente;
    private EnumResistencia direita;
    private EnumResistencia esquerda;
    private StateDirecao direcaoAtual;
    private StateMover moverAtual;

    public void girar() {
        direcaoAtual.girar();
    }

    public void mover(int novaLinha, int novaColuna) {
        moverAtual.mover(novaLinha, novaColuna);
    }

    public EnumDirecao direcaoAtualDaMaquina() {
        return direcaoAtual.getDirecaoAtual();
    }

    public String caminhoImagemDirecaoAtual() {
        return direcaoAtual.getCaminhoImagem();
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getAlcance() {
        return alcance;
    }

    public void setAlcance(int alcance) {
        this.alcance = alcance;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMovimento() {
        return movimento;
    }

    public void setMovimento(int movimento) {
        this.movimento = movimento;
    }

    public int getPontosVitoria() {
        return pontosVitoria;
    }

    public void setPontosVitoria(int pontosVitoria) {
        this.pontosVitoria = pontosVitoria;
    }

    public EnumTipoMaquinas getTipo() {
        return tipo;
    }

    public void setTipo(EnumTipoMaquinas tipo) {
        this.tipo = tipo;
    }

    public EnumResistencia getTras() {
        return tras;
    }

    public void setTras(EnumResistencia tras) {
        this.tras = tras;
    }

    public EnumResistencia getFrente() {
        return frente;
    }

    public void setFrente(EnumResistencia frente) {
        this.frente = frente;
    }

    public EnumResistencia getDireita() {
        return direita;
    }

    public void setDireita(EnumResistencia direita) {
        this.direita = direita;
    }

    public EnumResistencia getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(EnumResistencia esquerda) {
        this.esquerda = esquerda;
    }

    public StateDirecao getDirecaoAtual() {
        return direcaoAtual;
    }

    public void setDirecaoAtual(StateDirecao direcaoAtual) {
        this.direcaoAtual = direcaoAtual;
    }

    public StateMover getMoverAtual() {
        return moverAtual;
    }

    public void setMoverAtual(StateMover moverAtual) {
        this.moverAtual = moverAtual;
    }
}
