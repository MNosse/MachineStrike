package model;

//GLOBAL
import global.EnumDirecao;
import global.EnumTipoMaquinas;

//MODEL
import model.visitor.VisitorAtaque;
import model.state.stateMover.StateMover;
import model.state.stateCorrer.StateCorrer;
import model.state.stateAtacar.StateAtacar;
import model.state.stateDirecao.StateDirecao;
import model.state.stateMover.StateMoverAtivo;
import model.state.stateMover.StateMoverInativo;
import model.state.stateCorrer.StateCorrerAtivo;
import model.state.stateAtacar.StateAtacarAtivo;
import model.state.stateAtacar.StateAtacarInativo;
import model.state.stateCorrer.StateCorrerInativo;
import model.state.StateSobrecarregar.StateSobrecarregar;
import model.state.StateSobrecarregar.StateSobrecarregarInativo;

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
    private int resistenciaTras;
    private StateMover moverAtual;
    private EnumTipoMaquinas tipo;
    private int resistenciaFrente;
    private int resistenciaDireita;
    private int resistenciaEsquerda;
    private StateCorrer correrAtual;
    private StateAtacar atacarAtual;
    private boolean jaSobrecarregou;
    private StateDirecao direcaoAtual;
    private StateSobrecarregar sobrecarregarAtual;

    public void girar() {
        direcaoAtual.girar();
    }

    public void mover(int novaLinha, int novaColuna) {
        moverAtual.mover(novaLinha, novaColuna);
    }

    public void correr(int novaLinha, int novaColuna) {
        correrAtual.correr(novaLinha, novaColuna);
    }

    public void atacar(Maquina outraMaquina, Terreno outraTerreno, VisitorAtaque visitor) {
        atacarAtual.atacar(outraMaquina, outraTerreno, visitor);
    }

    public void sobrecarregar() {
        sobrecarregarAtual.sobrecarregar();
    }

    public boolean podeMover(int novaLinha, int novaColuna) {
        int diferencaLinha = Math.abs(linha - novaLinha);
        int diferencaColuna = Math.abs(coluna - novaColuna);
        if ((diferencaLinha+diferencaColuna) != 0 && (diferencaLinha+diferencaColuna) <= movimento) {
            return true;
        }
        return false;
    }

    public boolean podeCorrer(int novaLinha, int novaColuna) {
        int diferencaLinha = Math.abs(linha - novaLinha);
        int diferencaColuna = Math.abs(coluna - novaColuna);
        if ((diferencaLinha+diferencaColuna) != 0 && (diferencaLinha+diferencaColuna) <= movimento+1) {
            return true;
        }
        return false;
    }

    public boolean podeAtacar(Maquina outraMaquina) {
        int outraLinha = outraMaquina.getLinha();
        int outraColuna = outraMaquina.getColuna();
        if (linha == outraLinha && Math.abs(coluna - outraColuna) <= alcance) {
            return true;
        } else if (coluna == outraColuna && Math.abs(linha - outraLinha) <= alcance) {
            return true;
        }
        return false;
    }

    public boolean isMoverAtivo() {
        return moverAtual.isAtivo();
    }

    public boolean isCorrerAtivo() {
        return correrAtual.isAtivo();
    }

    public boolean isAtacarAtivo() {
        return atacarAtual.isAtivo();
    }

    public boolean isSobrecarregarAtivo() {
        return sobrecarregarAtual.isAtivo();
    }

    public EnumDirecao direcaoAtualDaMaquina() {
        return direcaoAtual.getDirecaoAtual();
    }

    public String caminhoImagemDirecaoAtual() {
        return direcaoAtual.getCaminhoImagem();
    }

    public int pontosCombateCimaAtual() {
        return direcaoAtual.getResistenciaCima();
    }

    public int pontosCombateBaixoAtual() {
        return direcaoAtual.getResistenciaBaixo();
    }

    public int pontosCombateDireitaAtual() {
        return direcaoAtual.getResistenciaDireita();
    }

    public int pontosCombateEsquerdaAtual() {
        return direcaoAtual.getResistenciaEsquerda();
    }

    public void reativarAcoes() {
        moverAtual = new StateMoverAtivo(this);
        correrAtual = new StateCorrerAtivo(this);
        atacarAtual = new StateAtacarAtivo(this);
    }

    public void resetarMaquina() {
        reativarAcoes();
        sobrecarregarAtual = new StateSobrecarregarInativo(this);
        jaSobrecarregou = false;
    }

    public void desativarAcoes() {
        moverAtual = new StateMoverInativo(this);
        correrAtual = new StateCorrerInativo(this);
        atacarAtual = new StateAtacarInativo(this);
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

    public int getResistenciaTras() {
        return resistenciaTras;
    }

    public void setResistenciaTras(int resistenciaTras) {
        this.resistenciaTras = resistenciaTras;
    }

    public int getResistenciaFrente() {
        return resistenciaFrente;
    }

    public void setResistenciaFrente(int resistenciaFrente) {
        this.resistenciaFrente = resistenciaFrente;
    }

    public int getResistenciaDireita() {
        return resistenciaDireita;
    }

    public void setResistenciaDireita(int resistenciaDireita) {
        this.resistenciaDireita = resistenciaDireita;
    }

    public int getResistenciaEsquerda() {
        return resistenciaEsquerda;
    }

    public void setResistenciaEsquerda(int resistenciaEsquerda) {
        this.resistenciaEsquerda = resistenciaEsquerda;
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

    public StateCorrer getCorrerAtual() {
        return correrAtual;
    }

    public void setCorrerAtual(StateCorrer correrAtual) {
        this.correrAtual = correrAtual;
    }

    public StateAtacar getAtacarAtual() {
        return atacarAtual;
    }

    public void setAtacarAtual(StateAtacar atacarAtual) {
        this.atacarAtual = atacarAtual;
    }

    public StateSobrecarregar getSobrecarregarAtual() {
        return sobrecarregarAtual;
    }

    public void setSobrecarregarAtual(StateSobrecarregar sobrecarregarAtual) {
        this.sobrecarregarAtual = sobrecarregarAtual;
    }

    public boolean getJaSobrecarregou() {
        return jaSobrecarregou;
    }

    public void setJaSobrecarregou(boolean jaSobrecarregou) {
        this.jaSobrecarregou = jaSobrecarregou;
    }
}
