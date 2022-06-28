package view.Tela;

//CONTROLLER
import controller.observer.ObserverTelaTabuleiros;
import controller.controlador.ControladorTelaTabuleiros;

//GLOBAL
import global.EnumTipoTerreno;

//JAVA
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Vector;
import java.util.HashMap;

//JAVAX
import javax.swing.*;

//VIEW
import view.abstractFactoryTela.AbstractFactoryTela;
import view.abstractFactoryTela.ConcretFactoryTelaInicial;


public class TelaTabuleiros extends Tela implements ObserverTelaTabuleiros {
    private JLabel lblFundo;
    private JButton btnVoltar;
    private JButton btnSalvar;
    private JTextField txtNome;
    private boolean edicaoAtiva;
    private JButton btnCancelar;
    private GridBagLayout layout;
    private JLabel lblPainelDireito;
    private JLabel lblPainelCentral;
    private JLabel lblPainelEsquerdo;
    private JComboBox cmbTiposTerreno;
    private JButton btnCriarTabuleiro;
    private JButton btnEditarTabuleiro;
    private JButton btnDeletarTabuleiro;
    private JComboBox cmbListaTabuleiros;
    private JLabel lblTerrenoSelecionado;
    private GridBagConstraints constraints;
    private AbstractFactoryTela factoryTela;
    private ControladorTelaTabuleiros controlador;
    private HashMap<String, JLabel> listaQuadradosTabuleiros;
    private HashMap<EnumTipoTerreno, ImageIcon> imagensTerrenos;
    private HashMap<String, EnumTipoTerreno> terrenosQuadradosTabuleiros;

    public TelaTabuleiros() {
        edicaoAtiva = false;
        controlador = new ControladorTelaTabuleiros();
        controlador.attach(this);
        layout = new GridBagLayout();
        constraints = new GridBagConstraints();
        initialize();
        iniciarAcoes();
        controlador.desenharTabuleiro(cmbListaTabuleiros.getSelectedItem().toString());
    }

    private void initialize() {
        iniciarListaQuadradosTabuleiros();
        iniciarImagensTerrenos();
        //btnCriarTabuleiro
        btnCriarTabuleiro = criarBotao("Criar tabuleiro", ((int)(getLargura()*0.2125)), ((int)(getAltura()*0.056)));
        //cmbListaTabuleiros
        cmbListaTabuleiros = criarComboBox(new Vector<>(controlador.getTabuleiros().keySet()), ((int)(getLargura()*0.2125)), ((int)(getAltura()*0.056)));
        //btnEditarTabuleiro
        btnEditarTabuleiro = criarBotao("Editar tabuleiro", ((int)(getLargura()*0.2125)), ((int)(getAltura()*0.056)));
        //btnDeletarTabuleiro
        btnDeletarTabuleiro = criarBotao("Deletar tabuleiro", ((int)(getLargura()*0.2125)), ((int)(getAltura()*0.056)));
        mudarEstadoEditarDeletar(false);
        //btnVoltar
        btnVoltar = criarBotao("Voltar", ((int)(getLargura()*0.2125)), ((int)(getAltura()*0.056)));
        //lblPainelEsquerdo
        lblPainelEsquerdo = new JLabel(criarImagem("src/images/Filtro.png", ((int)(getAltura()*0.9)), ((int)(getLargura()*0.225))));
        lblPainelEsquerdo.setLayout(layout);
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.insets = new Insets(0, 0, 10, 0);
        lblPainelEsquerdo.add(btnCriarTabuleiro, constraints);
        lblPainelEsquerdo.add(cmbListaTabuleiros, constraints);
        lblPainelEsquerdo.add(btnEditarTabuleiro, constraints);
        lblPainelEsquerdo.add(btnDeletarTabuleiro, constraints);
        constraints.insets = new Insets(375, 0, 0, 0);
        lblPainelEsquerdo.add(btnVoltar, constraints);
        //txtNome
        txtNome = new JTextField();
        txtNome.setBackground(new Color(217, 217, 217));
        txtNome.setMinimumSize(new Dimension((int)(getLargura()*0.2125), (int)(getAltura()*0.056)));
        txtNome.setPreferredSize(new Dimension((int)(getLargura()*0.2125), (int)(getAltura()*0.056)));
        //cmbTiposTerreno
        cmbTiposTerreno = criarComboBox(new Vector<>(Arrays.asList(EnumTipoTerreno.values())), ((int)(getLargura()*0.2125)), ((int)(getAltura()*0.056)));
        //lblTerrenoSelecionado
        lblTerrenoSelecionado = new JLabel(imagensTerrenos.get(cmbTiposTerreno.getSelectedItem()));
        //btnCancelar
        btnCancelar = criarBotao("Cancelar", ((int)(getLargura()*0.2125)), ((int)(getAltura()*0.056)));
        //btnSalvar
        btnSalvar = criarBotao("Salvar", ((int)(getLargura()*0.2125)), ((int)(getAltura()*0.056)));
        //lblPainelDireito
        lblPainelDireito = new JLabel(criarImagem("src/images/Filtro.png", ((int)(getAltura()*0.9)), ((int)(getLargura()*0.225))));
        lblPainelDireito.setLayout(layout);
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.insets = new Insets(0, 0, 10, 0);
        lblPainelDireito.add(txtNome, constraints);
        lblPainelDireito.add(cmbTiposTerreno, constraints);
        lblPainelDireito.add(lblTerrenoSelecionado, constraints);
        constraints.insets = new Insets(335, 0, 10, 0);
        lblPainelDireito.add(btnCancelar, constraints);
        constraints.insets = new Insets(0, 0, 0, 0);
        lblPainelDireito.add(btnSalvar, constraints);
        mudarEstadoPainelDireito(false);
        //lblPainelCentral
        lblPainelCentral = new JLabel(criarImagem("src/images/Filtro.png", ((int)(getAltura()*0.9)), ((int)(getAltura()*0.9))));
        lblPainelCentral.setLayout(layout);
        constraints.insets = new Insets(0, 0, 0, 0);
        for (int linha = 0; linha < 8; linha++) {
            constraints.gridy = linha;
            for (int coluna = 0; coluna < 8; coluna++) {
                constraints.gridx = coluna;
                lblPainelCentral.add(listaQuadradosTabuleiros.get(linha+""+coluna), constraints);
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

    private void iniciarAcoes() {
        //btnCriarTabuleiro
        btnCriarTabuleiro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mudarEstadoPainelDireito(true);
                controlador.iniciarListaTerrenosCriacao(cmbListaTabuleiros.getSelectedItem().toString());
            }
        });
        //cmbListaTabuleiros
        cmbListaTabuleiros.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String selecionado = cmbListaTabuleiros.getSelectedItem().toString();
                controlador.mudarEstadoEditarDeletar(selecionado);
                if (edicaoAtiva) {
                    mudarEstadoPainelDireito(false);
                }
                controlador.desenharTabuleiro(selecionado);
            }
        });
        //btnEditarTabuleiro
        btnEditarTabuleiro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mudarEstadoPainelDireito(true);
                controlador.iniciarListaTerrenosCriacao(cmbListaTabuleiros.getSelectedItem().toString());
            }
        });
        //btnDeletarTabuleiro
        btnDeletarTabuleiro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.deletarArquivoDeTabuleiro(cmbListaTabuleiros.getSelectedItem().toString());
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
        //cmbTiposTerreno
        cmbTiposTerreno.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                lblTerrenoSelecionado.setIcon(imagensTerrenos.get(cmbTiposTerreno.getSelectedItem()));
            }
        });
//      btnCancelar
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mudarEstadoPainelDireito(false);
                cmbListaTabuleiros.setSelectedIndex(0);
            }
        });
        //btnSalvar
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtNome.getText().equals("")){
                    controlador.atualizarArquivoDeTabuleiro(cmbListaTabuleiros.getSelectedItem().toString(),  terrenosQuadradosTabuleiros);
                }else{
                    controlador.criarArquivoDeTabuleiro(txtNome.getText(), terrenosQuadradosTabuleiros);
                }
                mudarEstadoPainelDireito(false);
            }
        });


    }

    private void iniciarListaQuadradosTabuleiros() {
        listaQuadradosTabuleiros = new HashMap<>();
        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                JLabel quadrado = new JLabel();
                int finalLinha = linha;
                int finalColuna = coluna;
                quadrado.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(edicaoAtiva) {
                            quadrado.setIcon(imagensTerrenos.get(cmbTiposTerreno.getSelectedItem()));
                            terrenosQuadradosTabuleiros.put((finalLinha +""+finalColuna), EnumTipoTerreno.valueOf(cmbTiposTerreno.getSelectedItem().toString()));
                        }
                    }
                });
                listaQuadradosTabuleiros.put((linha+""+coluna), quadrado);
            }
        }
    }

    public void iniciarListaTerrenosCriacao(HashMap<String, EnumTipoTerreno> terrenos) {
        terrenosQuadradosTabuleiros = terrenos;
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

    public void mudarEstadoPainelDireito(boolean estado) {
        edicaoAtiva = estado;
        txtNome.setText("");
        txtNome.setEnabled(estado);
        cmbTiposTerreno.setSelectedIndex(0);
        cmbTiposTerreno.setEnabled(estado);
        lblTerrenoSelecionado.setEnabled(estado);
        btnCancelar.setEnabled(estado);
        btnSalvar.setEnabled(estado);
    }

    public void mudarEstadoEditarDeletar(boolean estado) {
        btnEditarTabuleiro.setEnabled(estado);
        btnDeletarTabuleiro.setEnabled(estado);
    }

    public void desenharTabuleiro(HashMap<String, EnumTipoTerreno> terrenos) {
        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                listaQuadradosTabuleiros.get(linha+""+coluna).setIcon(imagensTerrenos.get(terrenos.get(linha+""+coluna)));
            }
        }
    }

    @Override
    public void atualizarListaDeTabuleiros(Vector<String> vector) {
        cmbListaTabuleiros.setModel(new DefaultComboBoxModel(vector));
        cmbListaTabuleiros.setSelectedIndex(0);
        controlador.desenharTabuleiro(cmbListaTabuleiros.getSelectedItem().toString());
    }
}
