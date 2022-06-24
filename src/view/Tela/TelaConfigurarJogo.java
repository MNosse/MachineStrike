package view.Tela;

import global.EnumAdicionarRemover;
import global.EnumMaquinas;
import controller.observer.ObserverTelaConfigurarJogo;
import controller.controlador.ControladorTelaConfigurarJogo;
import global.EnumTipoTerreno;
import view.abstractFactoryTela.AbstractFactoryTela;
import view.abstractFactoryTela.ConcretFactoryTelaInicial;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Map;
import java.util.Arrays;
import java.util.Vector;
import java.util.HashMap;

public class TelaConfigurarJogo extends Tela implements ObserverTelaConfigurarJogo {
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
    private HashMap<EnumTipoTerreno, ImageIcon> imagensTerrenos;

    public TelaConfigurarJogo(){
        controlador = new ControladorTelaConfigurarJogo();
        controlador.attach(this);
        layout = new GridBagLayout();
        constraints = new GridBagConstraints();
        initialize();
        iniciarAcoes();
        controlador.desenharTabuleiro(cmbListaTabuleiros.getSelectedItem().toString());
    }

    private void initialize(){
        iniciarListaQuadradosTabuleiros();
        iniciarListaMaquinasNoTabuleiro();
        iniciarImagensTerrenos();
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
        lblMaquinaSelecionada = new JLabel(criarImagem("src/images/Filtro.png", ((int)(getAltura()*0.15)), ((int)(getAltura()*0.15))));
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
        for (int linha = 0; linha < 8; linha++){
            constraints.gridy = linha;
            for (int coluna = 0; coluna < 8; coluna++){
                constraints.gridx = coluna;
                lblPainelCentral.add(listaQuadradosTabuleiros.get(linha+""+coluna), constraints);
                lblPainelCentral.add(listaMaquinasNoTabuleiro.get(linha+""+coluna), constraints);
            }
        }
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

    private void iniciarAcoes(){
        //btnJogar
        btnJogar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        //cmbListaTabuleiros
        cmbListaTabuleiros.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                controlador.desenharTabuleiro(cmbListaTabuleiros.getSelectedItem().toString());
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
//        //cmbJogador
//        cmbJogador.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//            }
//        });
//        //cmbMaquinas
//        cmbMaquinas.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//            }
//        });
        //cmbAdicionarRemover
//        cmbAdicionarRemover.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//            }
//        });
    }

    private void iniciarListaMaquinasNoTabuleiro() {
        listaMaquinasNoTabuleiro = new HashMap<>();
        for (int linha = 0; linha < 8; linha++){
            for (int coluna = 0; coluna < 8; coluna++){
                JLabel quadrado = new JLabel();
                quadrado.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
//                            tile.setIcon(new ImageIcon(new ImageIcon(terrainIcons.get(nameToTerrain.get(cmbTerrain.getSelectedItem()))).getImage().getScaledInstance((int) (height * 0.11), (int) (height * 0.11), Image.SCALE_SMOOTH)));
//                            jLabelTerrain.put(tile, nameToTerrain.get(cmbTerrain.getSelectedItem()));
                    }
                });
                listaMaquinasNoTabuleiro.put((linha+""+coluna), quadrado);
            }
        }
    }

    private void iniciarListaQuadradosTabuleiros() {
        listaQuadradosTabuleiros = new HashMap<>();
        for (int linha = 0; linha < 8; linha++){
            for (int coluna = 0; coluna < 8; coluna++){
                JLabel quadrado = new JLabel();
                quadrado.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
//                            tile.setIcon(new ImageIcon(new ImageIcon(terrainIcons.get(nameToTerrain.get(cmbTerrain.getSelectedItem()))).getImage().getScaledInstance((int) (height * 0.11), (int) (height * 0.11), Image.SCALE_SMOOTH)));
//                            jLabelTerrain.put(tile, nameToTerrain.get(cmbTerrain.getSelectedItem()));
                    }
                });
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

    public void desenharTabuleiro(HashMap<String, EnumTipoTerreno> terrenos){
        for (int linha = 0; linha < 8; linha++){
            for (int coluna = 0; coluna < 8; coluna++){
                listaQuadradosTabuleiros.get(linha+""+coluna).setIcon(imagensTerrenos.get(terrenos.get(linha+""+coluna)));
            }
        }
    }
}
