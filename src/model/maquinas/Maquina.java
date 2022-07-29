package model.maquinas;

import global.Enum.EnumDirecao;
import global.Enum.EnumTipoMaquinas;
import global.Enum.EnumTipoTerreno;
import global.Exception.JaAtacouException;
import global.Exception.JaCorreuException;
import global.Exception.JaMovimentouException;
import global.Exception.JaSobrecarregouException;
import model.Jogador;
import model.Tabuleiro;
import model.Terreno;
import model.state.StateSobrecarregar.StateSobrecarregar;
import model.state.StateSobrecarregar.StateSobrecarregarInativo;
import model.state.stateAtacar.StateAtacar;
import model.state.stateAtacar.StateAtacarAtivo;
import model.state.stateAtacar.StateAtacarInativo;
import model.state.stateCorrer.StateCorrer;
import model.state.stateCorrer.StateCorrerAtivo;
import model.state.stateCorrer.StateCorrerInativo;
import model.state.stateDirecao.StateDirecao;
import model.state.stateMover.StateMover;
import model.state.stateMover.StateMoverAtivo;
import model.state.stateMover.StateMoverInativo;

import java.util.List;

public abstract class Maquina {
    protected int vida;
    protected int linha;
    protected int coluna;
    protected int ataque;
    protected int alcance;
    protected String nome;
    protected int movimento;
    protected Jogador jogador;
    protected int pontosVitoria;
    protected int resistenciaTras;
    protected StateMover moverAtual;
    protected EnumTipoMaquinas tipo;
    protected int resistenciaFrente;
    protected int resistenciaDireita;
    protected int resistenciaEsquerda;
    protected StateCorrer correrAtual;
    protected StateAtacar atacarAtual;
    protected boolean jaSobrecarregou;
    protected StateDirecao direcaoAtual;
    protected StateSobrecarregar sobrecarregarAtual;
    
    public Maquina(int vida, int linha, int coluna, int ataque, int alcance, String nome, int movimento, Jogador jogador, int pontosVitoria, int resistenciaTras, EnumTipoMaquinas tipo, int resistenciaFrente, int resistenciaDireita, int resistenciaEsquerda) {
        this.vida = vida;
        this.linha = linha;
        this.coluna = coluna;
        this.ataque = ataque;
        this.alcance = alcance;
        this.nome = nome;
        this.movimento = movimento;
        this.jogador = jogador;
        this.pontosVitoria = pontosVitoria;
        this.resistenciaTras = resistenciaTras;
        this.tipo = tipo;
        this.resistenciaFrente = resistenciaFrente;
        this.resistenciaDireita = resistenciaDireita;
        this.resistenciaEsquerda = resistenciaEsquerda;
        moverAtual = new StateMoverAtivo(this);
        correrAtual = new StateCorrerAtivo(this);
        atacarAtual = new StateAtacarAtivo(this);
        sobrecarregarAtual = new StateSobrecarregarInativo(this);
        jaSobrecarregou = false;
    }
    
    public void girar() {
        direcaoAtual.girar();
    }
    
    public void mover(int novaLinha, int novaColuna, Terreno terrenoNaPosicao, List<Maquina> maquinasEmJogo) throws JaMovimentouException {
        moverAtual.mover(novaLinha, novaColuna, terrenoNaPosicao, maquinasEmJogo);
    }
    
    public void acaoMover(int novaLinha, int novaColuna, Terreno terrenoNaPosicao) {
        linha = novaLinha;
        coluna = novaColuna;
        moverAtual = new StateMoverInativo(this);
        correrAtual = new StateCorrerInativo(this);
        if(terrenoNaPosicao.getTipo().equals(EnumTipoTerreno.PANTANO)) {
            atacarAtual = new StateAtacarInativo(this);
        }
    }
    
    public void correr(int novaLinha, int novaColuna, Terreno terrenoNaPosicao, List<Maquina> maquinasEmJogo) throws JaCorreuException {
        correrAtual.correr(novaLinha, novaColuna, terrenoNaPosicao, maquinasEmJogo);
    }
    
    public void acaoCorrer(int novaLinha, int novaColuna) {
        linha = novaLinha;
        coluna = novaColuna;
        moverAtual = new StateMoverInativo(this);
        correrAtual = new StateCorrerInativo(this);
        atacarAtual = new StateAtacarInativo(this);
    }
    
    public void atacar(Maquina outraMaquina, Tabuleiro tabuleiro) throws JaAtacouException {
        atacarAtual.atacar(outraMaquina, tabuleiro);
    }
    
    public void acaoAtacar(Maquina outraMaquina, Tabuleiro tabuleiro) {
        int linhaOutraMaquina = outraMaquina.getLinha();
        int colunaOutraMaquina = outraMaquina.getColuna();
        Terreno terrenoOutraMaquina = tabuleiro.getTerrenoPorPosicao(linhaOutraMaquina + "" + colunaOutraMaquina);
        Terreno terrenoDessaMaquina = tabuleiro.getTerrenoPorPosicao(linha + "" + coluna);
        int pontoDeAtaque = ataque + terrenoDessaMaquina.getPontosDeCombate();
        int pontoDeDefesa = terrenoOutraMaquina.getPontosDeCombate() + outraMaquina.getPontosEscudo(linha, coluna);
        if(linha == linhaOutraMaquina) {
            if(coluna < colunaOutraMaquina && direcaoAtual.getDirecaoAtual().equals(EnumDirecao.LESTE)) {
                if(pontoDeAtaque > pontoDeDefesa) {
                    outraMaquina.setVida(outraMaquina.getVida() - (pontoDeAtaque - pontoDeDefesa));
                } else {
                    vida--;
                    outraMaquina.setVida(outraMaquina.getVida() - 1);
                    if(tabuleiro.getMaquinaPorPosicao(linha, colunaOutraMaquina + 1) != null) {
                        encadearAtaque(tabuleiro.getMaquinaPorPosicao(linha, colunaOutraMaquina + 1), tabuleiro);
                    } else if((colunaOutraMaquina + 1) <= 7) {
                        outraMaquina.setColuna(colunaOutraMaquina + 1);
                    } else {
                        outraMaquina.setVida(outraMaquina.getVida() - 1);
                    }
                }
            } else if(coluna > colunaOutraMaquina && direcaoAtual.getDirecaoAtual().equals(EnumDirecao.OESTE)) {
                if(pontoDeAtaque > pontoDeDefesa) {
                    outraMaquina.setVida(outraMaquina.getVida() - (pontoDeAtaque - pontoDeDefesa));
                } else {
                    vida--;
                    outraMaquina.setVida(outraMaquina.getVida() - 1);
                    if(tabuleiro.getMaquinaPorPosicao(linha, colunaOutraMaquina - 1) != null) {
                        encadearAtaque(tabuleiro.getMaquinaPorPosicao(linha, colunaOutraMaquina - 1), tabuleiro);
                    } else if((colunaOutraMaquina - 1) >= 0) {
                        outraMaquina.setColuna(colunaOutraMaquina - 1);
                    } else {
                        outraMaquina.setVida(outraMaquina.getVida() - 1);
                    }
                }
            }
        } else if(coluna == colunaOutraMaquina) {
            if(linha < linhaOutraMaquina && direcaoAtual.getDirecaoAtual().equals((EnumDirecao.SUL))) {
                if(pontoDeAtaque > pontoDeDefesa) {
                    outraMaquina.setVida(outraMaquina.getVida() - (pontoDeAtaque - pontoDeDefesa));
                } else {
                    vida--;
                    outraMaquina.setVida(outraMaquina.getVida() - 1);
                    if(tabuleiro.getMaquinaPorPosicao(linhaOutraMaquina + 1, coluna) != null) {
                        encadearAtaque(tabuleiro.getMaquinaPorPosicao(linhaOutraMaquina + 1, coluna), tabuleiro);
                    } else if((linhaOutraMaquina + 1) <= 7) {
                        outraMaquina.setLinha(linhaOutraMaquina + 1);
                    } else {
                        outraMaquina.setVida(outraMaquina.getVida() - 1);
                    }
                }
            } else if(linha > linhaOutraMaquina && direcaoAtual.getDirecaoAtual().equals(EnumDirecao.NORTE)) {
                if(pontoDeAtaque > pontoDeDefesa) {
                    outraMaquina.setVida(outraMaquina.getVida() - (pontoDeAtaque - pontoDeDefesa));
                } else {
                    vida--;
                    outraMaquina.setVida(outraMaquina.getVida() - 1);
                    if(tabuleiro.getMaquinaPorPosicao(linhaOutraMaquina - 1, coluna) != null) {
                        encadearAtaque(tabuleiro.getMaquinaPorPosicao(linhaOutraMaquina - 1, coluna), tabuleiro);
                    } else if((linhaOutraMaquina - 1) >= 0) {
                        outraMaquina.setLinha(linhaOutraMaquina - 1);
                    } else {
                        outraMaquina.setVida(outraMaquina.getVida() - 1);
                    }
                }
            }
        }
    }
    
    public void encadearAtaque(Maquina outraMaquina, Tabuleiro tabuleiro) {
        int linhaOutraMaquina = outraMaquina.getLinha();
        int colunaOutraMaquina = outraMaquina.getColuna();
        outraMaquina.setVida(outraMaquina.getVida() - 1);
        if(linha == linhaOutraMaquina) {
            if(coluna < colunaOutraMaquina) {
                if(tabuleiro.getMaquinaPorPosicao(linhaOutraMaquina, colunaOutraMaquina + 1) != null) {
                    encadearAtaque(tabuleiro.getMaquinaPorPosicao(linhaOutraMaquina, colunaOutraMaquina + 1), tabuleiro);
                } else if(colunaOutraMaquina < 7) {
                    outraMaquina.setColuna(colunaOutraMaquina + 1);
                } else {
                    outraMaquina.setVida(outraMaquina.getVida() - 1);
                }
            } else if(coluna > colunaOutraMaquina) {
                if(tabuleiro.getMaquinaPorPosicao(linhaOutraMaquina, colunaOutraMaquina - 1) != null) {
                    encadearAtaque(tabuleiro.getMaquinaPorPosicao(linhaOutraMaquina, colunaOutraMaquina - 1), tabuleiro);
                } else if(colunaOutraMaquina > 0) {
                    outraMaquina.setColuna(colunaOutraMaquina - 1);
                } else {
                    outraMaquina.setVida(outraMaquina.getVida() - 1);
                }
            }
        } else if(coluna == colunaOutraMaquina) {
            if(linha < linhaOutraMaquina) {
                if(tabuleiro.getMaquinaPorPosicao(linhaOutraMaquina + 1, colunaOutraMaquina) != null) {
                    encadearAtaque(tabuleiro.getMaquinaPorPosicao(linhaOutraMaquina + 1, colunaOutraMaquina), tabuleiro);
                } else if(linhaOutraMaquina < 7) {
                    outraMaquina.setLinha(linhaOutraMaquina + 1);
                } else {
                    outraMaquina.setVida(outraMaquina.getVida() - 1);
                }
            } else if(linha > linhaOutraMaquina) {
                if(tabuleiro.getMaquinaPorPosicao(linhaOutraMaquina - 1, colunaOutraMaquina) != null) {
                    encadearAtaque(tabuleiro.getMaquinaPorPosicao(linhaOutraMaquina - 1, colunaOutraMaquina), tabuleiro);
                } else if(linhaOutraMaquina > 0) {
                    outraMaquina.setLinha(linhaOutraMaquina - 1);
                } else {
                    outraMaquina.setVida(outraMaquina.getVida() - 1);
                }
            }
        }
        
        
    }
    
    public void sobrecarregar() throws JaSobrecarregouException {
        sobrecarregarAtual.sobrecarregar();
    }
    
    public boolean podeMover(int novaLinha, int novaColuna, Terreno terrenoNaPosicao, List<Maquina> maquinasEmJogo) {
        if(!terrenoNaPosicao.getTipo().equals(EnumTipoTerreno.ABISMO)) {
            boolean continua = true;
            for(Maquina maquina : maquinasEmJogo) {
                if(maquina.getLinha() == novaLinha && maquina.getColuna() == novaColuna) {
                    continua = false;
                }
            }
            if(continua) {
                int diferencaLinha = Math.abs(linha - novaLinha);
                int diferencaColuna = Math.abs(coluna - novaColuna);
                if((diferencaLinha + diferencaColuna) != 0 && (diferencaLinha + diferencaColuna) <= movimento) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean podeCorrer(int novaLinha, int novaColuna, Terreno terrenoNaPosicao, List<Maquina> maquinasEmJogo) {
        if(!terrenoNaPosicao.getTipo().equals(EnumTipoTerreno.ABISMO)) {
            boolean continua = true;
            for(Maquina maquina : maquinasEmJogo) {
                if(maquina.getLinha() == novaLinha && maquina.getColuna() == novaColuna) {
                    continua = false;
                }
            }
            if(continua) {
                int diferencaLinha = Math.abs(linha - novaLinha);
                int diferencaColuna = Math.abs(coluna - novaColuna);
                if((diferencaLinha + diferencaColuna) != 0 && (diferencaLinha + diferencaColuna) <= movimento + 1) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean podeAtacar(Maquina outraMaquina, Tabuleiro tabuleiro) {
        if(!outraMaquina.getJogador().equals(jogador)) {
            int outraLinha = outraMaquina.getLinha();
            int outraColuna = outraMaquina.getColuna();
            if(linha == outraLinha && Math.abs(coluna - outraColuna) <= alcance) {
                int inicio = Math.min(coluna, outraColuna);
                int fim = Math.max(coluna, outraColuna);
                for(int i = (inicio + 1); i < fim; i++) {
                    if(tabuleiro.getMaquinaPorPosicao(linha, i) != null) {
                        return false;
                    }
                }
                return true;
            } else if(coluna == outraColuna && Math.abs(linha - outraLinha) <= alcance) {
                int inicio = Math.min(linha, outraLinha);
                int fim = Math.max(linha, outraLinha);
                for(int i = (inicio + 1); i < fim; i++) {
                    if(tabuleiro.getMaquinaPorPosicao(i, coluna) != null) {
                        return false;
                    }
                }
                return true;
            }
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
    
    public String caminhoImagemDirecaoAtual() {
        return direcaoAtual.getCaminhoImagem();
    }
    
    public abstract String caminhoImagemDirecaoFixa();
    
    public int getPontosEscudo(int linhaOutra, int colunaOutra) {
        if(linha == linhaOutra) {
            if(coluna > colunaOutra) {
                return pontosCombateEsquerdaAtual();
            } else if(coluna < colunaOutra) {
                return pontosCombateDireitaAtual();
            }
        } else if(coluna == colunaOutra) {
            if(linha > linhaOutra) {
                return pontosCombateCimaAtual();
            } else if(linha < linhaOutra) {
                return pontosCombateBaixoAtual();
            }
        }
        return 0;
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
