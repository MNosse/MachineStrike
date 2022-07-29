package view;

import controller.ControladorTelaConfigurarJogo;
import controller.observer.ObserverTelaConfigurarJogo;
import global.Enum.EnumAdicionarRemover;
import global.Enum.EnumJogador;
import global.Enum.EnumMaquinas;
import global.Enum.EnumTipoTerreno;
import global.Exception.LimiteDeMaquinasException;
import global.Exception.MaquinaEmTerrenoInvalidoException;
import global.Exception.MinimoMaquinasException;
import global.Exception.SubstituicaoInvalidaException;
import view.components.CardMaquina;
import view.decorator.*;
import view.utils.SingletonImagens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class TelaConfigurarJogo extends Tela implements ObserverTelaConfigurarJogo {
    private JLabel lblFundo;
    private JButton btnJogar;
    private JButton btnVoltar;
    private JComboBox cmbJogador;
    private GridBagLayout layout;
    private JComboBox cmbMaquinas;
    private JLabel lblPainelDireito;
    private JLabel lblPainelCentral;
    private CardMaquina cardMaquina;
    private JLabel lblPainelEsquerdo;
    private JComboBox cmbListaTabuleiros;
    private JComboBox cmbAdicionarRemover;
    private GridBagConstraints constraints;
    private ControladorTelaConfigurarJogo controlador;
    private Map<String, JLabel> listaQuadradosTabuleiros;
    private Map<String, JLabel> listaMaquinasNoTabuleiro;
    private HashMap<String, ImageIcon> imagens = SingletonImagens.getInstancia().getImagens();
    
    public TelaConfigurarJogo() {
        try {
            controlador = new ControladorTelaConfigurarJogo();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao manusear arquivos", "Erro de arquvios", JOptionPane.ERROR_MESSAGE);
        }
        controlador.attach(this);
        layout = new GridBagLayout();
        constraints = new GridBagConstraints();
        initialize();
        iniciarAcoes();
        controlador.desenharTabuleiro(cmbListaTabuleiros.getSelectedItem().toString());
    }
    
    private void initialize() {
        iniciarListaQuadradosTabuleiros();
        iniciarListaMaquinasNoTabuleiro();
        //btnJogar
        btnJogar = criarBotao("Jogar", ((int) (getLargura() * 0.2125)), ((int) (getAltura() * 0.056)));
        //cmbListaTabuleiros
        cmbListaTabuleiros = criarComboBox(new Vector<>(controlador.getTabuleiros().keySet()), ((int) (getLargura() * 0.2125)), ((int) (getAltura() * 0.056)));
        //btnVoltar
        btnVoltar = criarBotao("Voltar", ((int) (getLargura() * 0.2125)), ((int) (getAltura() * 0.056)));
        //lblLeftPallet
        lblPainelEsquerdo = new JLabel(criarImagem("src/images/Filtro.png", ((int) (getAltura() * 0.9)), ((int) (getLargura() * 0.225))));
        lblPainelEsquerdo.setLayout(layout);
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.insets = new Insets(0, 0, (int) (getAltura() * 0.014), 0);
        lblPainelEsquerdo.add(btnJogar, constraints);
        lblPainelEsquerdo.add(cmbListaTabuleiros, constraints);
        constraints.insets = new Insets((int) (getAltura() * 0.68), 0, 0, 0);
        lblPainelEsquerdo.add(btnVoltar, constraints);
        //cmbJogador
        cmbJogador = criarComboBox(new Vector<>(controlador.getJogadores().keySet()), ((int) (getLargura() * 0.2125)), ((int) (getAltura() * 0.056)));
        //cmbAdicionarRemover
        cmbAdicionarRemover = criarComboBox(new Vector<>(Arrays.asList(EnumAdicionarRemover.values())), ((int) (getLargura() * 0.2125)), ((int) (getAltura() * 0.056)));
        //cmbMaquinas
        cmbMaquinas = criarComboBox(new Vector<>(Arrays.asList(EnumMaquinas.values())), ((int) (getLargura() * 0.2125)), ((int) (getAltura() * 0.056)));
        //cardMaquina
        try {
            cardMaquina = new CardMaquina(controlador.getInformacoesMaquina((EnumMaquinas) cmbMaquinas.getSelectedItem()));
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Nao foi possivel localizar essa maquina", "Maquina inexistente", JOptionPane.ERROR_MESSAGE);
        }
        //lblPainelDireito
        lblPainelDireito = new JLabel(criarImagem("src/images/Filtro.png", ((int) (getAltura() * 0.9)), ((int) (getLargura() * 0.225))));
        lblPainelDireito.setLayout(layout);
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.insets = new Insets(0, 0, (int) (getAltura() * 0.014), 0);
        lblPainelDireito.add(cmbJogador, constraints);
        lblPainelDireito.add(cmbAdicionarRemover, constraints);
        lblPainelDireito.add(cmbMaquinas, constraints);
        constraints.insets = new Insets(0, 0, (int) (getAltura() * 0.243), 0);
        lblPainelDireito.add(cardMaquina, constraints);
        //lblPainelCentral
        lblPainelCentral = new JLabel(criarImagem("src/images/Filtro.png", ((int) (getAltura() * 0.9)), ((int) (getAltura() * 0.9))));
        lblPainelCentral.setLayout(layout);
        constraints.insets = new Insets(0, 0, 0, 0);
        Set<String> posicoes = listaQuadradosTabuleiros.keySet();
        for(String posicao : posicoes) {
            constraints.gridy = Integer.parseInt(String.valueOf(posicao.charAt(0)));
            constraints.gridx = Integer.parseInt(String.valueOf(posicao.charAt(1)));
            JLabel maquinaLabel = listaMaquinasNoTabuleiro.get(posicao);
            if(constraints.gridy > 1 && constraints.gridy < 6) {
                maquinaLabel.setIcon(imagens.get("Bloqueado"));
            }
            lblPainelCentral.add(maquinaLabel, constraints);
            lblPainelCentral.add(listaQuadradosTabuleiros.get(posicao), constraints);
        }
        controlador.alterouComboboxJogadores((EnumJogador) cmbJogador.getSelectedItem());
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
        getFrmTela().setTitle("Machine Strike - Configurar Jogo");
        getFrmTela().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getFrmTela().setLocationRelativeTo(null);
        getFrmTela().setContentPane(lblFundo);
        getFrmTela().setVisible(true);
    }
    
    private void iniciarAcoes() {
        //btnJogar
        btnJogar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controlador.navegarParaTelaJogo(cmbListaTabuleiros.getSelectedItem().toString());
                } catch(MinimoMaquinasException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Selecionar maquinas", JOptionPane.ERROR_MESSAGE);
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, "Nao foi possivel localizar essa tela", "Tela nao localizada", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        //cmbListaTabuleiros
        cmbListaTabuleiros.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                controlador.desenharTabuleiro(cmbListaTabuleiros.getSelectedItem().toString());
                mudarEstadoPainelDireito();
                controlador.alterouComboboxJogadores((EnumJogador) cmbJogador.getSelectedItem());
                controlador.removerMaquinasDosJogadores();
            }
        });
        //btnVoltar
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controlador.navegarParaOutraTela("view.abstractFactoryTela.ConcretFactoryTelaInicial");
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, "Nao foi possivel localizar essa tela", "Tela nao localizada", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        //cmbJogador
        cmbJogador.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                controlador.alterouComboboxJogadores((EnumJogador) cmbJogador.getSelectedItem());
            }
        });
        //cmbMaquinas
        cmbMaquinas.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    cardMaquina.atualizarConteudo(controlador.getInformacoesMaquina((EnumMaquinas) cmbMaquinas.getSelectedItem()));
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, "Nao foi possivel localizar essa maquina", "Maquina inexistente", JOptionPane.ERROR_MESSAGE);
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
                        if((cmbJogador.getSelectedItem().equals(EnumJogador.JOGADOR1) && finalLinha > 5) || cmbJogador.getSelectedItem().equals(EnumJogador.JOGADOR2) && finalLinha < 2) {
                            if(cmbAdicionarRemover.getSelectedItem().equals(EnumAdicionarRemover.ADICIONAR)) {
                                try {
                                    controlador.adicionarMaquinaAoJogador((EnumJogador) cmbJogador.getSelectedItem(), finalLinha, finalColuna, EnumMaquinas.valueOf(cmbMaquinas.getSelectedItem().toString()), cmbListaTabuleiros.getSelectedItem().toString());
                                } catch(LimiteDeMaquinasException ex) {
                                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Limite atingido", JOptionPane.ERROR_MESSAGE);
                                } catch(SubstituicaoInvalidaException ex) {
                                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Substituicao invalida", JOptionPane.ERROR_MESSAGE);
                                } catch(MaquinaEmTerrenoInvalidoException ex) {
                                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Posicao invalida", JOptionPane.ERROR_MESSAGE);
                                } catch(Exception ex) {
                                    JOptionPane.showMessageDialog(null, "Nao foi possivel localizar essa maquina", "Maquina inexistente", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                controlador.removerMaquinaDoJogador((EnumJogador) cmbJogador.getSelectedItem(), finalLinha, finalColuna);
                            }
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
    
    public void mudarEstadoPainelDireito() {
        cmbJogador.setSelectedIndex(0);
        cmbAdicionarRemover.setSelectedIndex(0);
        cmbMaquinas.setSelectedIndex(0);
    }
    
    public void desenharTabuleiro(HashMap<String, EnumTipoTerreno> terrenos) {
        for(int linha = 0; linha < 8; linha++) {
            for(int coluna = 0; coluna < 8; coluna++) {
                listaQuadradosTabuleiros.get(linha + "" + coluna).setIcon(imagens.get(EnumTipoTerreno.valueOf(terrenos.get(linha + "" + coluna).toString()).getTipo()));
            }
        }
    }
    
    public void desenharMaquina(String caminhoImagem, String posicao) {
        listaMaquinasNoTabuleiro.get(posicao).setIcon(new Imagem(caminhoImagem, (int)(getAltura()*0.11), (int)(getAltura()*0.11)).getImagem());
    }
    
    public void desenharBloqueadosOuVazios(HashMap<String, String> valores) {
        Set<String> posicoes = valores.keySet();
        for(String posicao : posicoes) {
            listaMaquinasNoTabuleiro.get(posicao).setIcon(imagens.get(valores.get(posicao)));
        }
    }
}
