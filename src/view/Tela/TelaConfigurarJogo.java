package view.Tela;

//CONTROLLER
import controller.observer.ObserverCommand;
import controller.observer.ObserverTelaConfigurarJogo;
import controller.controlador.ControladorTelaConfigurarJogo;

//GLOBAL
import global.EnumMaquinas;
import global.EnumTipoTerreno;
import global.EnumAdicionarRemover;

//JAVA
import java.awt.*;
import java.util.*;
import java.awt.event.*;

//JAVAX
import javax.swing.*;

//VIEW
import view.abstractFactoryTela.AbstractFactoryTela;
import view.abstractFactoryTela.ConcretFactoryTelaInicial;


public class TelaConfigurarJogo extends Tela implements ObserverTelaConfigurarJogo, ObserverCommand {
    private JLabel lblFundo;
    private JButton btnJogar;
    private JButton btnVoltar;
    private JComboBox cmbJogador;
    private GridBagLayout layout;
    private JComboBox cmbMaquinas;
    private JLabel lblPainelDireito;
    private JLabel lblPainelCentral;
    private JLabel lblPainelEsquerdo;
    private JComboBox cmbListaTabuleiros;
    private JLabel lblMaquinaSelecionada;
    private JComboBox cmbAdicionarRemover;
    private GridBagConstraints constraints;
    private AbstractFactoryTela factoryTela;
    private ControladorTelaConfigurarJogo controlador;
    private Map<String, JLabel> listaQuadradosTabuleiros;
    private Map<String, JLabel> listaMaquinasNoTabuleiro;
    private HashMap<EnumMaquinas, ImageIcon> imagensMaquinas;
    private HashMap<EnumTipoTerreno, ImageIcon> imagensTerrenos;
    private HashMap<String, ImageIcon> imagensOutrosTiposQuadrados;

    public TelaConfigurarJogo() {
        controlador = new ControladorTelaConfigurarJogo();
        controlador.attach(this);
        layout = new GridBagLayout();
        constraints = new GridBagConstraints();
        initialize();
        iniciarAcoes();
        controlador.desenharTabuleiro(cmbListaTabuleiros.getSelectedItem().toString());
    }

    private void initialize() {
        iniciarImagensTerrenos();
        iniciarImagensMaquinas();
        iniciarImagensOutrosTiposQuadrados();
        iniciarListaQuadradosTabuleiros();
        iniciarListaMaquinasNoTabuleiro();
        //btnJogar
        btnJogar = criarBotao("Jogar", ((int)(getLargura()*0.2125)), ((int)(getAltura()*0.056)));
        //cmbListaTabuleiros
        cmbListaTabuleiros = criarComboBox(new Vector<>(controlador.getTabuleiros().keySet()), ((int)(getLargura()*0.2125)), ((int)(getAltura()*0.056)));
        //btnVoltar
        btnVoltar = criarBotao("Voltar", ((int)(getLargura()*0.2125)), ((int)(getAltura()*0.056)));
        //lblLeftPallet
        lblPainelEsquerdo = new JLabel(criarImagem("src/images/Filtro.png", ((int)(getAltura()*0.9)), ((int)(getLargura()*0.225))));
        lblPainelEsquerdo.setLayout(layout);
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.insets = new Insets(0, 0, 10, 0);
        lblPainelEsquerdo.add(btnJogar, constraints);
        lblPainelEsquerdo.add(cmbListaTabuleiros, constraints);
        constraints.insets = new Insets(470, 0, 0, 0);
        lblPainelEsquerdo.add(btnVoltar, constraints);
        //cmbJogador
        cmbJogador = criarComboBox(new Vector<>(controlador.getJogadores().keySet()), ((int)(getLargura()*0.2125)), ((int)(getAltura()*0.056)));
        //cmbAdicionarRemover
        cmbAdicionarRemover = criarComboBox(new Vector<>(Arrays.asList(EnumAdicionarRemover.values())), ((int)(getLargura()*0.2125)), ((int)(getAltura()*0.056)));
        //cmbMaquinas
        cmbMaquinas = criarComboBox(new Vector<>(Arrays.asList(EnumMaquinas.values())), ((int)(getLargura()*0.2125)), ((int)(getAltura()*0.056)));
        //lblMonstroSelecionado
        lblMaquinaSelecionada = new JLabel(imagensMaquinas.get(cmbMaquinas.getSelectedItem()));
        //lblPainelDireito
        lblPainelDireito = new JLabel(criarImagem("src/images/Filtro.png", ((int)(getAltura()*0.9)), ((int)(getLargura()*0.225))));
        lblPainelDireito.setLayout(layout);
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.insets = new Insets(0, 0, 10, 0);
        lblPainelDireito.add(cmbJogador, constraints);
        lblPainelDireito.add(cmbAdicionarRemover, constraints);
        lblPainelDireito.add(cmbMaquinas, constraints);
        constraints.insets = new Insets(0, 0, 355, 0);
        lblPainelDireito.add(lblMaquinaSelecionada, constraints);
        //lblPainelCentral
        lblPainelCentral = new JLabel(criarImagem("src/images/Filtro.png", ((int)(getAltura()*0.9)), ((int)(getAltura()*0.9))));
        lblPainelCentral.setLayout(layout);
        constraints.insets = new Insets(0, 0, 0, 0);
        for (int linha = 0; linha < 8; linha++) {
            constraints.gridy = linha;
            for (int coluna = 0; coluna < 8; coluna++) {
                constraints.gridx = coluna;
                JLabel maquinaLabel = listaMaquinasNoTabuleiro.get(linha+""+coluna);
                JLabel terrenoLabel = listaQuadradosTabuleiros.get(linha+""+coluna);
                if (linha > 1 && linha < 6) {
                    maquinaLabel.setIcon(imagensOutrosTiposQuadrados.get("Bloqueado"));
                }
                lblPainelCentral.add(maquinaLabel, constraints);
                lblPainelCentral.add(terrenoLabel, constraints);
            }
        }
        mudarJogadores();
        //lblFundo
        lblFundo = new JLabel(criarImagem("src/images/Background.png", getAltura(), getLargura()));
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
        getFrmTela().setTitle("Machine Strike - Tabuleiros");
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
                //fazer algo
            }
        });
        //cmbListaTabuleiros
        cmbListaTabuleiros.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                controlador.desenharTabuleiro(cmbListaTabuleiros.getSelectedItem().toString());
                mudarEstadoPainelDireito();
                mudarJogadores();
                controlador.removerMaquinasDosJogadores();
            }
        });
        //btnVoltar
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                factoryTela = new ConcretFactoryTelaInicial();
                navegarParaOutraTela(factoryTela.construirTela());
            }
        });
        //cmbJogador
        cmbJogador.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                mudarJogadores();
            }
        });
        //cmbMaquinas
        cmbMaquinas.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                lblMaquinaSelecionada.setIcon(imagensMaquinas.get(cmbMaquinas.getSelectedItem()));
            }
        });
    }

    private void iniciarListaMaquinasNoTabuleiro() {
        listaMaquinasNoTabuleiro = new HashMap<>();
        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                JLabel quadrado = new JLabel();
                quadrado.setIcon(imagensOutrosTiposQuadrados.get("Vazio"));
                int finalLinha = linha;
                int finalColuna = coluna;
                quadrado.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (cmbJogador.getSelectedItem().toString().equals("Jogador 1")) {
                            if (finalLinha > 5) {
                                if (cmbAdicionarRemover.getSelectedItem().equals(EnumAdicionarRemover.ADICIONAR)) {
                                    controlador.adicionarMaquinaAoJogador("Jogador 1", finalLinha+""+finalColuna, EnumMaquinas.valueOf(cmbMaquinas.getSelectedItem().toString()));
                                    quadrado.setIcon(criarImagem(controlador.imagemAtualMaquina("Jogador 1", finalLinha+""+finalColuna), ((int)(getAltura()*0.11)), ((int)(getAltura()*0.11))));
                                } else {
                                    controlador.removerMaquinaDoJogador("Jogador 1", finalLinha+""+finalColuna);
                                    quadrado.setIcon(imagensOutrosTiposQuadrados.get("Vazio"));
                                }

                            }
                        } else {
                            if (finalLinha < 2) {
                                if (cmbAdicionarRemover.getSelectedItem().equals(EnumAdicionarRemover.ADICIONAR)) {
                                    controlador.adicionarMaquinaAoJogador("Jogador 2", finalLinha+""+finalColuna, EnumMaquinas.valueOf(cmbMaquinas.getSelectedItem().toString()));
                                    quadrado.setIcon(criarImagem(controlador.imagemAtualMaquina("Jogador 2", finalLinha+""+finalColuna), ((int)(getAltura()*0.11)), ((int)(getAltura()*0.11))));
                                } else {
                                    controlador.removerMaquinaDoJogador("Jogador 2", finalLinha+""+finalColuna);
                                    quadrado.setIcon(imagensOutrosTiposQuadrados.get("Vazio"));
                                }
                            }
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

    private void iniciarImagensTerrenos() {
        imagensTerrenos = new HashMap<>();
        imagensTerrenos.put(EnumTipoTerreno.ABISMO, criarImagem("src/images/"+EnumTipoTerreno.ABISMO.getTipo()+".png", ((int)(getAltura()*0.11)), ((int)(getAltura()*0.11))));
        imagensTerrenos.put(EnumTipoTerreno.PANTANO, criarImagem("src/images/"+EnumTipoTerreno.PANTANO.getTipo()+".png", ((int)(getAltura()*0.11)), ((int)(getAltura()*0.11))));
        imagensTerrenos.put(EnumTipoTerreno.PASTO, criarImagem("src/images/"+EnumTipoTerreno.PASTO.getTipo()+".png", ((int)(getAltura()*0.11)), ((int)(getAltura()*0.11))));
        imagensTerrenos.put(EnumTipoTerreno.FLORESTA, criarImagem("src/images/"+EnumTipoTerreno.FLORESTA.getTipo()+".png", ((int)(getAltura()*0.11)), ((int)(getAltura()*0.11))));
        imagensTerrenos.put(EnumTipoTerreno.ELEVACAO, criarImagem("src/images/"+EnumTipoTerreno.ELEVACAO.getTipo()+".png", ((int)(getAltura()*0.11)), ((int)(getAltura()*0.11))));
        imagensTerrenos.put(EnumTipoTerreno.MONTANHA, criarImagem("src/images/"+EnumTipoTerreno.MONTANHA.getTipo()+".png", ((int)(getAltura()*0.11)), ((int)(getAltura()*0.11))));
    }

    private void iniciarImagensMaquinas() {
        imagensMaquinas = new HashMap<>();
        imagensMaquinas.put(EnumMaquinas.ARIETE1, criarImagem("src/images/Ariete1-Norte.png", ((int)(getAltura()*0.11)), ((int)(getAltura()*0.11))));
        imagensMaquinas.put(EnumMaquinas.ARIETE2, criarImagem("src/images/Ariete2-Norte.png", ((int)(getAltura()*0.11)), ((int)(getAltura()*0.11))));
        imagensMaquinas.put(EnumMaquinas.ARRANCADA, criarImagem("src/images/Arrancada-Norte.png", ((int)(getAltura()*0.11)), ((int)(getAltura()*0.11))));
        imagensMaquinas.put(EnumMaquinas.ATIRADOR1, criarImagem("src/images/Atirador1-Norte.png", ((int)(getAltura()*0.11)), ((int)(getAltura()*0.11))));
        imagensMaquinas.put(EnumMaquinas.ATIRADOR2, criarImagem("src/images/Atirador2-Norte.png", ((int)(getAltura()*0.11)), ((int)(getAltura()*0.11))));
        imagensMaquinas.put(EnumMaquinas.CORPO_A_CORPO1, criarImagem("src/images/CorpoACorpo1-Norte.png", ((int)(getAltura()*0.11)), ((int)(getAltura()*0.11))));
        imagensMaquinas.put(EnumMaquinas.CORPO_A_CORPO2, criarImagem("src/images/CorpoACorpo2-Norte.png", ((int)(getAltura()*0.11)), ((int)(getAltura()*0.11))));
        imagensMaquinas.put(EnumMaquinas.MERGULHO1, criarImagem("src/images/Mergulho1-Norte.png", ((int)(getAltura()*0.11)), ((int)(getAltura()*0.11))));
        imagensMaquinas.put(EnumMaquinas.MERGULHO2, criarImagem("src/images/Mergulho2-Norte.png", ((int)(getAltura()*0.11)), ((int)(getAltura()*0.11))));
        imagensMaquinas.put(EnumMaquinas.PUXAO, criarImagem("src/images/Puxao-Norte.png", ((int)(getAltura()*0.11)), ((int)(getAltura()*0.11))));
    }

    private void iniciarImagensOutrosTiposQuadrados() {
        imagensOutrosTiposQuadrados = new HashMap<>();
        imagensOutrosTiposQuadrados.put("Filtro", criarImagem("src/images/Filtro.png", ((int)(getAltura()*0.11)), ((int)(getAltura()*0.11))));
        imagensOutrosTiposQuadrados.put("Vazio", criarImagem("src/images/Vazio.png", ((int)(getAltura()*0.11)), ((int)(getAltura()*0.11))));
        imagensOutrosTiposQuadrados.put("Selecionado", criarImagem("src/images/Selecionado.png", ((int)(getAltura()*0.11)), ((int)(getAltura()*0.11))));
        imagensOutrosTiposQuadrados.put("Bloqueado", criarImagem("src/images/Bloqueado.png", ((int)(getAltura()*0.11)), ((int)(getAltura()*0.11))));
    }

    public void mudarEstadoPainelDireito() {
        cmbJogador.setSelectedIndex(0);
        cmbAdicionarRemover.setSelectedIndex(0);
        cmbMaquinas.setSelectedIndex(0);
    }

    public void desenharTabuleiro(HashMap<String, EnumTipoTerreno> terrenos) {
        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                listaQuadradosTabuleiros.get(linha+""+coluna).setIcon(imagensTerrenos.get(terrenos.get(linha+""+coluna)));
            }
        }
    }

    public void mudarJogadores() {
        String jogador = cmbJogador.getSelectedItem().toString();
        Set<String> posicoesMaquinasJogador1 = controlador.getMaquinasJogadorKeySet("Jogador 1");
        Set<String> posicoesMaquinasJogador2 = controlador.getMaquinasJogadorKeySet("Jogador 2");
        if (jogador.equals("Jogador 1")) {
            for (int linha = 0; linha < 2; linha++) {
                constraints.gridy = linha;
                for (int coluna = 0; coluna < 8; coluna++) {
                    constraints.gridx = coluna;
                    if (!posicoesMaquinasJogador2.contains(linha+""+coluna)) {
                        listaMaquinasNoTabuleiro.get(linha+""+coluna).setIcon(imagensOutrosTiposQuadrados.get("Bloqueado"));
                    }
                }
            }
            for (int linha = 6; linha < 8; linha++) {
                constraints.gridy = linha;
                for (int coluna = 0; coluna < 8; coluna++) {
                    constraints.gridx = coluna;
                    if (!posicoesMaquinasJogador1.contains(linha+""+coluna)) {
                        listaMaquinasNoTabuleiro.get(linha+""+coluna).setIcon(imagensOutrosTiposQuadrados.get("Vazio"));
                    }
                }
            }
        } else {
            for (int linha = 0; linha < 2; linha++) {
                constraints.gridy = linha;
                for (int coluna = 0; coluna < 8; coluna++) {
                    constraints.gridx = coluna;
                    if (!posicoesMaquinasJogador2.contains(linha+""+coluna)) {
                        listaMaquinasNoTabuleiro.get(linha+""+coluna).setIcon(imagensOutrosTiposQuadrados.get("Vazio"));
                    }
                }
            }
            for (int linha = 6; linha < 8; linha++) {
                constraints.gridy = linha;
                for (int coluna = 0; coluna < 8; coluna++) {
                    constraints.gridx = coluna;
                    if (!posicoesMaquinasJogador1.contains(linha+""+coluna)) {
                        listaMaquinasNoTabuleiro.get(linha+""+coluna).setIcon(imagensOutrosTiposQuadrados.get("Bloqueado"));
                    }
                }
            }
        }
    }
}
