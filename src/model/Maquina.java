package model;

import global.EnumDirecao;
import global.EnumResistencia;
import global.EnumTipoMaquinas;

public class Maquina {
    private Jogador jogador;
    private int vida;
    private int linha;
    private int coluna;
    private int ataque;
    private int alcance;
    private String nome;
    private int movimento;
    private int pontosVitoria;
    private EnumTipoMaquinas tipo;
    private EnumResistencia tras;
    private EnumResistencia frente;
    private EnumResistencia direita;
    private EnumResistencia esquerda;
    private EnumDirecao direcaoAtual;

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

    public EnumDirecao getDirecaoAtual() {
        return direcaoAtual;
    }

    public void setDirecaoAtual(EnumDirecao direcaoAtual) {
        this.direcaoAtual = direcaoAtual;
    }
}
