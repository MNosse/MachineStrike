package view;

import controller.ControladorTelaJogo;
import controller.observer.ObserverTelaJogo;
import global.Enum.EnumTipoTerreno;
import global.Exception.*;
import view.components.CardMaquina;
import view.decorator.Imagem;
import view.decorator.JogadorAtivoImagemDecorator;
import view.decorator.JogadorInativoImagemDecorator;
import view.decorator.SelecionarImagemDecorator;
import view.utils.SingletonImagens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TelaJogo extends Tela implements ObserverTelaJogo {
    private JLabel lblFundo;
    private JButton btnSair;
    private JButton btnAtacar;
    private JButton btnMover;
    private JButton btnSobrecarregar;
    private JButton btnGirar;
    private JButton btnCorrer;
    private JButton btnEncerrarTurno;
    private JPanel panJogadorAtivo;
    private JLabel lblJogadorAtivo;
    private GridBagLayout layout;
    private JLabel lblPainelDireito;
    private JLabel lblPainelCentral;
    private JLabel lblPainelEsquerdo;
    private GridBagConstraints constraints;
    private ControladorTelaJogo controlador;
    private CardMaquina cardMaquinaAtacante;
    private CardMaquina cardMaquinaDefensora;
    private Map<String, JLabel> listaQuadradosTabuleiros;
    private Map<String, JLabel> listaMaquinasNoTabuleiro;
    private HashMap<String, ImageIcon> imagens = SingletonImagens.getInstancia().getImagens();
    
    public TelaJogo() {
        controlador = new ControladorTelaJogo();
        controlador.attach(this);
        layout = new GridBagLayout();
        constraints = new GridBagConstraints();
        initialize();
        iniciarAcoes();
    }
    
    private void initialize() {
        iniciarListaQuadradosTabuleiros();
        iniciarListaMaquinasNoTabuleiro();
        lblJogadorAtivo = criarTexto(controlador.getInformacoesHeaderJogadorAtual());
        //panJogadorAtivo
        panJogadorAtivo = new JPanel();
        panJogadorAtivo.setLayout(layout);
        panJogadorAtivo.setBackground(new Color(217, 217, 217));
        panJogadorAtivo.setMinimumSize(new Dimension(((int) (getLargura() * 0.2125)), ((int) (getAltura() * 0.045))));
        panJogadorAtivo.setPreferredSize(new Dimension(((int) (getLargura() * 0.2125)), ((int) (getAltura() * 0.045))));
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        panJogadorAtivo.add(lblJogadorAtivo, constraints);
        //cardMaquinaAtacante
        cardMaquinaAtacante = new CardMaquina(null);
        //btnAtacar
        btnAtacar = criarBotao("Atacar", ((int) (getLargura() * 0.10)), ((int) (getAltura() * 0.056)));
        //btnSobrecarregar
        btnSobrecarregar = criarBotao("Sobrecarregar", ((int) (getLargura() * 0.10)), ((int) (getAltura() * 0.056)));
        //btnMover
        btnMover = criarBotao("Mover", ((int) (getLargura() * 0.10)), ((int) (getAltura() * 0.056)));
        //btnCorrer
        btnCorrer = criarBotao("Correr", ((int) (getLargura() * 0.10)), ((int) (getAltura() * 0.056)));
        //btnGirar
        btnGirar = criarBotao("Girar", ((int) (getLargura() * 0.10)), ((int) (getAltura() * 0.056)));
        //btnEncerrarTurno
        btnEncerrarTurno = criarBotao("Encerrar", ((int) (getLargura() * 0.10)), ((int) (getAltura() * 0.056)));
        //btnSair
        btnSair = criarBotao("Sair", ((int) (getLargura() * 0.2125)), ((int) (getAltura() * 0.056)));
        //lblLeftPallet
        lblPainelEsquerdo = new JLabel(criarImagem("src/images/Filtro.png", ((int) (getAltura() * 0.9)), ((int) (getLargura() * 0.225))));
        lblPainelEsquerdo.setLayout(layout);
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.insets = new Insets(0, 0, (int) (getAltura() * 0.014), 0);
        lblPainelEsquerdo.add(panJogadorAtivo, constraints);
        lblPainelEsquerdo.add(cardMaquinaAtacante, constraints);
        JLabel linha1 = criarLinha(btnAtacar, btnSobrecarregar, ((int) (getLargura() * 0.2125)), ((int) (getAltura() * 0.056)));
        JLabel linha2 = criarLinha(btnMover, btnCorrer, ((int) (getLargura() * 0.2125)), ((int) (getAltura() * 0.056)));
        JLabel linha3 = criarLinha(btnGirar, btnEncerrarTurno, ((int) (getLargura() * 0.2125)), ((int) (getAltura() * 0.056)));
        lblPainelEsquerdo.add(linha1, constraints);
        lblPainelEsquerdo.add(linha2, constraints);
        lblPainelEsquerdo.add(linha3, constraints);
        constraints.insets = new Insets((int) (getAltura() * 0.125), 0, 0, 0);
        lblPainelEsquerdo.add(btnSair, constraints);
        //cardMaquinaDefensora
        cardMaquinaDefensora = new CardMaquina(null);
        //lblPainelDireito
        lblPainelDireito = new JLabel(criarImagem("src/images/Filtro.png", ((int) (getAltura() * 0.9)), ((int) (getLargura() * 0.225))));
        lblPainelDireito.setLayout(layout);
        lblPainelDireito.add(cardMaquinaDefensora);
        //lblPainelCentral
        lblPainelCentral = new JLabel(criarImagem("src/images/Filtro.png", ((int) (getAltura() * 0.9)), ((int) (getAltura() * 0.9))));
        lblPainelCentral.setLayout(layout);
        constraints.insets = new Insets(0, 0, 0, 0);
        Set<String> posicoes = listaQuadradosTabuleiros.keySet();
        for(String posicao : posicoes) {
            constraints.gridy = Integer.parseInt(String.valueOf(posicao.charAt(0)));
            constraints.gridx = Integer.parseInt(String.valueOf(posicao.charAt(1)));
            lblPainelCentral.add(listaMaquinasNoTabuleiro.get(posicao), constraints);
            lblPainelCentral.add(listaQuadradosTabuleiros.get(posicao), constraints);
        }
        controlador.desenharJogoInicial();
        //lblFundo
        lblFundo = new JLabel(imagens.get("Background"));
        lblFundo.setLayout(layout);
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.gridy = 0;
        lblFundo.add(lblPainelEsquerdo);
        constraints.insets = new Insets(0, (int) (getLargura() * 0.008), 0, (int) (getLargura() * 0.008));
        lblFundo.add(lblPainelCentral, constraints);
        constraints.insets = new Insets(0, 0, 0, 0);
        lblFundo.add(lblPainelDireito);
        //frmTela
        getFrmTela().setSize(getLargura(), getAltura());
        getFrmTela().setResizable(false);
        getFrmTela().setTitle("Machine Strike - Jogo");
        getFrmTela().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getFrmTela().setLocationRelativeTo(null);
        getFrmTela().setContentPane(lblFundo);
        getFrmTela().setVisible(true);
        desativarBotoes();
    }
    
    private void iniciarAcoes() {
        //btnMover
        btnMover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controlador.clicarBotaoMover();
                } catch(LimiteDeAcoesException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Limite atingido", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        //btnCorrer
        btnCorrer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controlador.clicarBotaoCorrer();
                } catch(LimiteDeAcoesException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Limite atingido", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        //btnAtacar
        btnAtacar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controlador.clicarBotaoAtacar();
                } catch(SemMaquinaNoCampoAtaqueException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Sem alvos", JOptionPane.INFORMATION_MESSAGE);
                } catch(LimiteDeAcoesException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Limite atingido", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        //btnSobrecarregar
        btnSobrecarregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controlador.clicarBotaoSobrecarregar();
                } catch(LimiteDeAcoesException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Limite atingido", JOptionPane.ERROR_MESSAGE);
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, "Acao bloqueada", "Acao bloqueada", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        //btnGirar
        btnGirar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controlador.clicarBotaoGirar();
                } catch(LimiteDeAcoesException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Limite atingido", JOptionPane.ERROR_MESSAGE);
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, "Acao bloqueada", "Acao bloqueada", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        //btnEncerrarTurno
        btnEncerrarTurno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controlador.clicarBotaoEncerrar();
                } catch(MinimoDeMovimentoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Movimentos necessarios", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        //btnSair
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controlador.navegarParaOutraTela("view.abstractFactoryTela.ConcretFactoryTelaInicial");
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, "Nao foi possivel localizar essa tela", "Tela nao localizada", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
    }
    
    private void iniciarListaMaquinasNoTabuleiro() {
        listaMaquinasNoTabuleiro = new HashMap<>();
        for(int linha = 0; linha < 8; linha++) {
            for(int coluna = 0; coluna < 8; coluna++) {
                JLabel quadrado = new JLabel();
                quadrado.setIcon(imagens.get("Vazio"));
                int finalLinha = linha;
                int finalColuna = coluna;
                quadrado.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
                            controlador.selecionarQuadrado(finalLinha + "" + finalColuna);
                        } catch(ForaDoCampoMovimentoException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Mover invalido", JOptionPane.ERROR_MESSAGE);
                        } catch(ForaDoCampoCorridaException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Correr invalido", JOptionPane.ERROR_MESSAGE);
                        } catch(ForaDoCampoAtaqueException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Ataque invalido", JOptionPane.ERROR_MESSAGE);
                        } catch(JaMovimentouException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Limite Mover atingido", JOptionPane.ERROR_MESSAGE);
                        } catch(JaCorreuException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Limite Correr atingido", JOptionPane.ERROR_MESSAGE);
                        } catch(JaAtacouException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Limite Atacar atingido", JOptionPane.ERROR_MESSAGE);
                        } catch(Exception ex) {
                            JOptionPane.showMessageDialog(null, "Nao foi possivel realizar essa acao", "Acao bloqueada", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
                listaMaquinasNoTabuleiro.put((linha + "" + coluna), quadrado);
            }
        }
    }
    
    private void iniciarListaQuadradosTabuleiros() {
        listaQuadradosTabuleiros = new HashMap<>();
        for(int linha = 0; linha < 8; linha++) {
            for(int coluna = 0; coluna < 8; coluna++) {
                JLabel quadrado = new JLabel();
                listaQuadradosTabuleiros.put((linha + "" + coluna), quadrado);
            }
        }
    }
    
    protected JLabel criarLinha(JButton button1, JButton button2, int largura, int altura) {
        JLabel linha = new JLabel();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        linha.setLayout(layout);
        gridBagConstraints.gridx = GridBagConstraints.RELATIVE;
        gridBagConstraints.gridy = 0;
        linha.setMinimumSize(new Dimension(largura, altura));
        linha.setPreferredSize(new Dimension(largura, altura));
        linha.add(button1, gridBagConstraints);
        gridBagConstraints.insets = new Insets(0, 10, 0, 0);
        linha.add(button2, gridBagConstraints);
        return linha;
    }
    
    @Override
    public void mudarEstadoBtnAtacar(boolean estado) {
        btnAtacar.setEnabled(estado);
    }
    
    @Override
    public void mudarEstadoBtnSobrecarregar(boolean estado) {
        btnSobrecarregar.setEnabled(estado);
    }
    
    @Override
    public void mudarEstadoBtnMover(boolean estado) {
        btnMover.setEnabled(estado);
    }
    
    @Override
    public void mudarEstadoBtnCorrer(boolean estado) {
        btnCorrer.setEnabled(estado);
    }
    
    @Override
    public void mudarEstadoBtnGirar(boolean estado) {
        btnGirar.setEnabled(estado);
    }
    
    @Override
    public void desativarBotoes() {
        btnAtacar.setEnabled(false);
        btnSobrecarregar.setEnabled(false);
        btnMover.setEnabled(false);
        btnCorrer.setEnabled(false);
        btnGirar.setEnabled(false);
    }
    
    @Override
    public void desenharTabuleiro(HashMap<String, EnumTipoTerreno> terrenos) {
        Set<String> posicoes = terrenos.keySet();
        for(String posicao : posicoes) {
            listaQuadradosTabuleiros.get(posicao).setIcon(imagens.get(EnumTipoTerreno.valueOf(terrenos.get(posicao).toString()).getTipo()));
        }
    }
    
    @Override
    public void redesenharMaquinas(HashMap<String, String> maquinasAtivas, HashMap<String, String> maquinasInativas) {
        for(int i = 0; i <= 7; i++) {
            for(int j = 0; j <= 7; j++) {
                listaMaquinasNoTabuleiro.get(i + "" + j).setIcon(imagens.get("Vazio"));
            }
        }
        Set<String> posicoesAtivas = maquinasAtivas.keySet();
        for(String posicao : posicoesAtivas) {
            listaMaquinasNoTabuleiro.get(posicao).setIcon(new JogadorAtivoImagemDecorator(new Imagem(maquinasAtivas.get(posicao), (int) (getAltura() * 0.11), (int) (getAltura() * 0.11))).getImagem());
        }
        Set<String> posicoesInativas = maquinasInativas.keySet();
        for(String posicao : posicoesInativas) {
            listaMaquinasNoTabuleiro.get(posicao).setIcon(new JogadorInativoImagemDecorator(new Imagem(maquinasInativas.get(posicao), (int) (getAltura() * 0.11), (int) (getAltura() * 0.11))).getImagem());
        }
    }
    
    @Override
    public void desenharCamposDeMovimento(Set<String> posicoes) {
        for(String posicao : posicoes) {
            listaMaquinasNoTabuleiro.get(posicao).setIcon(imagens.get("Selecionado"));
        }
    }
    
    @Override
    public void desenharCampoDeAtaque(HashMap<String, String> maquinas) {
        Set<String> posicoes = maquinas.keySet();
        for(String posicao : posicoes) {
            listaMaquinasNoTabuleiro.get(posicao).setIcon(new SelecionarImagemDecorator(new Imagem(maquinas.get(posicao), (int) (getAltura() * 0.11), (int) (getAltura() * 0.11))).getImagem());
        }
    }
    
    @Override
    public void atualizarCardMaquinaAtacante(HashMap<String, String> informacoes) {
        cardMaquinaAtacante.atualizarConteudo(informacoes);
    }
    
    @Override
    public void atualizarCardMaquinaDefensora(HashMap<String, String> informacoes) {
        cardMaquinaDefensora.atualizarConteudo(informacoes);
    }
    
    @Override
    public void atualizarLblJogadorAtivo(String nome) {
        lblJogadorAtivo.setText(nome);
    }
    
    @Override
    public void anunciarGanhador(String nome) {
        JOptionPane.showMessageDialog(null, "O " + nome + " ganhou a partida!!!", "Jogo encerrado", JOptionPane.INFORMATION_MESSAGE);
        try {
            controlador.navegarParaOutraTela("view.abstractFactoryTela.ConcretFactoryTelaInicial");
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null, "Nao foi possivel localizar essa tela", "Tela nao localizada", JOptionPane.ERROR_MESSAGE);
        }
    }
}
