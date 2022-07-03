package view;

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
import view.components.CardMaquina;


public class TelaConfigurarJogo extends Tela implements ObserverTelaConfigurarJogo, ObserverCommand {
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
        //cardMaquina
        cardMaquina = new CardMaquina(controlador.getInformacoesMaquina((EnumMaquinas)cmbMaquinas.getSelectedItem()));
        //lblPainelDireito
        lblPainelDireito = new JLabel(criarImagem("src/images/Filtro.png", ((int)(getAltura()*0.9)), ((int)(getLargura()*0.225))));
        lblPainelDireito.setLayout(layout);
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.insets = new Insets(0, 0, 10, 0);
        lblPainelDireito.add(cmbJogador, constraints);
        lblPainelDireito.add(cmbAdicionarRemover, constraints);
        lblPainelDireito.add(cmbMaquinas, constraints);
        constraints.insets = new Insets(0, 0, 60, 0);
        lblPainelDireito.add(cardMaquina, constraints);
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
        controlador.alterouComboboxJogadores(cmbJogador.getSelectedItem().toString());
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
                controlador.alterouComboboxJogadores(cmbJogador.getSelectedItem().toString());
                controlador.removerMaquinasDosJogadores();
            }
        });
        //btnVoltar
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.navegarParaTelaInicial();
            }
        });
        //cmbJogador
        cmbJogador.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                controlador.alterouComboboxJogadores(cmbJogador.getSelectedItem().toString());
            }
        });
        //cmbMaquinas
        cmbMaquinas.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                cardMaquina.atualizarConteudo(controlador.getInformacoesMaquina((EnumMaquinas)cmbMaquinas.getSelectedItem()));

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
                        if (cmbAdicionarRemover.getSelectedItem().equals(EnumAdicionarRemover.ADICIONAR)) {
                            controlador.adicionarMaquinaAoJogador(cmbJogador.getSelectedItem().toString(), finalLinha+""+finalColuna, EnumMaquinas.valueOf(cmbMaquinas.getSelectedItem().toString()), cmbListaTabuleiros.getSelectedItem().toString());
                        } else {
                            controlador.removerMaquinaDoJogador(cmbJogador.getSelectedItem().toString(), finalLinha+""+finalColuna);
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
        imagensOutrosTiposQuadrados.put("Vazio", criarImagem("src/images/Vazio.png", ((int)(getAltura()*0.11)), ((int)(getAltura()*0.11))));
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

    public void desenharMaquina(String caminhoImagem, String posicao) {
        listaMaquinasNoTabuleiro.get(posicao).setIcon(criarImagem(caminhoImagem, ((int)(getAltura()*0.11)), ((int)(getAltura()*0.11))));
    }

    public void desenharBloquadoOuVazio(String nomeImagem, String posicao) {
        listaMaquinasNoTabuleiro.get(posicao).setIcon(imagensOutrosTiposQuadrados.get(nomeImagem));
    }
}
