package view;

//CONTROLLER
import controller.ControladorTelaJogo;
import controller.observer.ObserverTelaJogo;

//GLOBAL
import global.Enum.EnumTipoTerreno;

//JAVA
import java.awt.*;
import java.util.Map;
import java.util.Set;
import java.awt.event.*;
import java.util.HashMap;

//JAVAX
import javax.swing.*;

//VIEW
import view.components.CardMaquina;
import view.utils.SingletonImagens;

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
        lblJogadorAtivo = criarTexto(controlador.getNomeJogadorAtual());//mudar
        //panJogadorAtivo
        panJogadorAtivo = new JPanel();
        panJogadorAtivo.setLayout(layout);
        panJogadorAtivo.setBackground(new Color(217, 217, 217));
        panJogadorAtivo.setMinimumSize(new Dimension(((int)(getLargura()*0.2125)), ((int)(getAltura()*0.045))));
        panJogadorAtivo.setPreferredSize(new Dimension(((int)(getLargura()*0.2125)), ((int)(getAltura()*0.045))));
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        panJogadorAtivo.add(lblJogadorAtivo, constraints);
        //cardMaquinaAtacante
        cardMaquinaAtacante = new CardMaquina(null);
        //btnAtacar
        btnAtacar = criarBotao("Atacar", ((int)(getLargura()*0.10)), ((int)(getAltura()*0.056)));
        //btnSobrecarregar
        btnSobrecarregar = criarBotao("Sobrecarregar", ((int)(getLargura()*0.10)), ((int)(getAltura()*0.056)));
        //btnMover
        btnMover = criarBotao("Mover", ((int)(getLargura()*0.10)), ((int)(getAltura()*0.056)));
        //btnCorrer
        btnCorrer = criarBotao("Correr", ((int)(getLargura()*0.10)), ((int)(getAltura()*0.056)));
        //btnGirar
        btnGirar = criarBotao("Girar", ((int)(getLargura()*0.10)), ((int)(getAltura()*0.056)));
        //btnEncerrarTurno
        btnEncerrarTurno = criarBotao("Encerrar", ((int)(getLargura()*0.10)), ((int)(getAltura()*0.056)));
        //btnSair
        btnSair = criarBotao("Sair", ((int)(getLargura()*0.2125)), ((int)(getAltura()*0.056)));
        //lblLeftPallet
        lblPainelEsquerdo = new JLabel(criarImagem("src/images/Filtro.png", ((int)(getAltura()*0.9)), ((int)(getLargura()*0.225))));
        lblPainelEsquerdo.setLayout(layout);
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.insets = new Insets(0, 0, 10, 0);
        lblPainelEsquerdo.add(panJogadorAtivo, constraints);
        lblPainelEsquerdo.add(cardMaquinaAtacante, constraints);
        JLabel linha1 = criarLinha(btnAtacar, btnSobrecarregar, ((int)(getLargura()*0.2125)), ((int)(getAltura()*0.056)));
        JLabel linha2 = criarLinha(btnMover, btnCorrer, ((int)(getLargura()*0.2125)), ((int)(getAltura()*0.056)));
        JLabel linha3 = criarLinha(btnGirar, btnEncerrarTurno, ((int)(getLargura()*0.2125)), ((int)(getAltura()*0.056)));
        lblPainelEsquerdo.add(linha1, constraints);
        lblPainelEsquerdo.add(linha2, constraints);
        lblPainelEsquerdo.add(linha3, constraints);
        constraints.insets = new Insets(30, 0, 0, 0);
        lblPainelEsquerdo.add(btnSair, constraints);
        //lblPainelDireito
        lblPainelDireito = new JLabel(criarImagem("src/images/Filtro.png", ((int)(getAltura()*0.9)), ((int)(getLargura()*0.225))));
        lblPainelDireito.setLayout(layout);
        //lblPainelCentral
        lblPainelCentral = new JLabel(criarImagem("src/images/Filtro.png", ((int)(getAltura()*0.9)), ((int)(getAltura()*0.9))));
        lblPainelCentral.setLayout(layout);
        constraints.insets = new Insets(0, 0, 0, 0);
        Set<String> posicoes = listaQuadradosTabuleiros.keySet();
        for (String posicao : posicoes) {
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
        constraints.insets = new Insets(0, 10, 0, 10);
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
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Limite de acoes com maquinas distintas atingido",
                            "Acao bloqueada", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //btnCorrer
        btnCorrer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controlador.clicarBotaoCorrer();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Limite de acoes com maquinas distintas atingido",
                            "Acao bloqueada", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //btnAtacar
        btnAtacar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controlador.clicarBotaoAtacar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Limite de acoes com maquinas distintas atingido",
                            "Acao bloqueada", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //btnSobrecarregar
        btnSobrecarregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controlador.clicarBotaoSobrecarregar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Limite de acoes com maquinas distintas atingido",
                            "Acao bloqueada", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //btnGirar
        btnGirar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controlador.clicarBotaoGirar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Limite de acoes com maquinas distintas atingido",
                            "Acao bloqueada", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //btnEncerrarTurno
        btnEncerrarTurno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.clicarBotaoEncerrar();
            }
        });

        //btnSair
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controlador.navegarParaOutraTela("view.abstractFactoryTela.ConcretFactoryTelaInicial");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Nao foi possivel localizar essa tela",
                            "Tela nao localizada", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    private void iniciarListaMaquinasNoTabuleiro() {
        listaMaquinasNoTabuleiro = new HashMap<>();
        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                JLabel quadrado = new JLabel();
                quadrado.setIcon(imagens.get("Vazio"));
                int finalLinha = linha;
                int finalColuna = coluna;
                quadrado.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
                            controlador.selecionarQuadrado(finalLinha+""+finalColuna);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Nao foi possivel realizar essa acao",
                                    "Acao bloqueada", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
                listaMaquinasNoTabuleiro.put((linha+""+coluna), quadrado);
            }
        }
    }

    private void iniciarListaQuadradosTabuleiros() {
        listaQuadradosTabuleiros = new HashMap<>();
        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                JLabel quadrado = new JLabel();
                listaQuadradosTabuleiros.put((linha+""+coluna), quadrado);
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
        for (String posicao : posicoes) {
            listaQuadradosTabuleiros.get(posicao).setIcon(imagens.get(EnumTipoTerreno.valueOf(terrenos.get(posicao).toString()).getTipo()));
        }
    }

    @Override
    public void apagarCampoDeMovimento(Set<String> posicoes) {
        for (String posicao : posicoes) {
            listaMaquinasNoTabuleiro.get(posicao).setIcon(imagens.get("Vazio"));
        }
    }

    @Override
    public void desenharCampoDeMovimento(Set<String> posicoes) {
        for (String posicao : posicoes) {
            listaMaquinasNoTabuleiro.get(posicao).setIcon(imagens.get("Selecionado"));
        }
    }

    @Override
    public void desenharQuadrado(String posicao) {
        listaMaquinasNoTabuleiro.get(posicao).setIcon(imagens.get("Vazio"));
    }

    @Override
    public void desenharQuadrado(String posicao, String caminhoImagem) {
        listaMaquinasNoTabuleiro.get(posicao).setIcon(criarImagem(caminhoImagem, ((int)(getAltura()*0.11)), ((int)(getAltura()*0.11))));
    }

    @Override
    public void desenharQuadrados(HashMap<String, String> maquinas) {
        Set<String> posicoes = maquinas.keySet();
        for (String posicao : posicoes) {
            desenharQuadrado(posicao, maquinas.get(posicao));
        }
    }

    @Override
    public void atualizarCardMaquinaAtacante(HashMap<String, String> informacoes){
        cardMaquinaAtacante.atualizarConteudo(informacoes);
    }

    @Override
    public void atualizarLblJogadorAtivo(String nome) {
        lblJogadorAtivo.setText(nome);
    }
}
