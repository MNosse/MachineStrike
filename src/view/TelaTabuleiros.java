package view;

import controller.ControladorTelaTabuleiros;
import controller.observer.ObserverTelaTabuleiros;
import global.Enum.EnumTipoTerreno;
import view.utils.SingletonImagens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

public class TelaTabuleiros extends Tela implements ObserverTelaTabuleiros {
    private JLabel lblFundo;
    private JButton btnVoltar;
    private JButton btnSalvar;
    private JTextField txtNome;
    private boolean edicaoAtiva;
    private boolean clicouEditar;
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
    private ControladorTelaTabuleiros controlador;
    private HashMap<String, JLabel> listaQuadradosTabuleiros;
    private HashMap<String, EnumTipoTerreno> terrenosQuadradosTabuleiros;
    private HashMap<String, ImageIcon> imagens = SingletonImagens.getInstancia().getImagens();
    
    public TelaTabuleiros() {
        edicaoAtiva = false;
        clicouEditar = false;
        try {
            controlador = new ControladorTelaTabuleiros();
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
        //btnCriarTabuleiro
        btnCriarTabuleiro = criarBotao("Criar tabuleiro", ((int) (getLargura() * 0.2125)), ((int) (getAltura() * 0.056)));
        //cmbListaTabuleiros
        cmbListaTabuleiros = criarComboBox(new Vector<>(controlador.getTabuleiros().keySet()), ((int) (getLargura() * 0.2125)), ((int) (getAltura() * 0.056)));
        //btnEditarTabuleiro
        btnEditarTabuleiro = criarBotao("Editar tabuleiro", ((int) (getLargura() * 0.2125)), ((int) (getAltura() * 0.056)));
        //btnDeletarTabuleiro
        btnDeletarTabuleiro = criarBotao("Deletar tabuleiro", ((int) (getLargura() * 0.2125)), ((int) (getAltura() * 0.056)));
        mudarEstadoEditarDeletar(false);
        //btnVoltar
        btnVoltar = criarBotao("Voltar", ((int) (getLargura() * 0.2125)), ((int) (getAltura() * 0.056)));
        //lblPainelEsquerdo
        lblPainelEsquerdo = new JLabel(criarImagem("src/images/Filtro.png", ((int) (getAltura() * 0.9)), ((int) (getLargura() * 0.225))));
        lblPainelEsquerdo.setLayout(layout);
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.insets = new Insets(0, 0, (int) (getAltura() * 0.014), 0);
        lblPainelEsquerdo.add(btnCriarTabuleiro, constraints);
        lblPainelEsquerdo.add(cmbListaTabuleiros, constraints);
        lblPainelEsquerdo.add(btnEditarTabuleiro, constraints);
        lblPainelEsquerdo.add(btnDeletarTabuleiro, constraints);
        constraints.insets = new Insets((int) (getAltura() * 0.542), 0, 0, 0);
        lblPainelEsquerdo.add(btnVoltar, constraints);
        //txtNome
        txtNome = new JTextField();
        txtNome.setBackground(new Color(217, 217, 217));
        txtNome.setMinimumSize(new Dimension((int) (getLargura() * 0.2125), (int) (getAltura() * 0.056)));
        txtNome.setPreferredSize(new Dimension((int) (getLargura() * 0.2125), (int) (getAltura() * 0.056)));
        //cmbTiposTerreno
        cmbTiposTerreno = criarComboBox(new Vector<>(Arrays.asList(EnumTipoTerreno.values())), ((int) (getLargura() * 0.2125)), ((int) (getAltura() * 0.056)));
        //lblTerrenoSelecionado
        lblTerrenoSelecionado = new JLabel(imagens.get(EnumTipoTerreno.valueOf(cmbTiposTerreno.getSelectedItem().toString()).getTipo()));
        //btnCancelar
        btnCancelar = criarBotao("Cancelar", ((int) (getLargura() * 0.2125)), ((int) (getAltura() * 0.056)));
        //btnSalvar
        btnSalvar = criarBotao("Salvar", ((int) (getLargura() * 0.2125)), ((int) (getAltura() * 0.056)));
        //lblPainelDireito
        lblPainelDireito = new JLabel(criarImagem("src/images/Filtro.png", ((int) (getAltura() * 0.9)), ((int) (getLargura() * 0.225))));
        lblPainelDireito.setLayout(layout);
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.insets = new Insets(0, 0, (int) (getAltura() * 0.014), 0);
        lblPainelDireito.add(txtNome, constraints);
        lblPainelDireito.add(cmbTiposTerreno, constraints);
        lblPainelDireito.add(lblTerrenoSelecionado, constraints);
        constraints.insets = new Insets((int) (getAltura() * 0.484), 0, (int) (getAltura() * 0.014), 0);
        lblPainelDireito.add(btnCancelar, constraints);
        constraints.insets = new Insets(0, 0, 0, 0);
        lblPainelDireito.add(btnSalvar, constraints);
        mudarEstadoPainelDireito(false);
        //lblPainelCentral
        lblPainelCentral = new JLabel(criarImagem("src/images/Filtro.png", ((int) (getAltura() * 0.9)), ((int) (getAltura() * 0.9))));
        lblPainelCentral.setLayout(layout);
        constraints.insets = new Insets(0, 0, 0, 0);
        Set<String> quadrados = listaQuadradosTabuleiros.keySet();
        for(String posicao : quadrados) {
            constraints.gridy = Integer.parseInt(String.valueOf(posicao.charAt(0)));
            constraints.gridx = Integer.parseInt(String.valueOf(posicao.charAt(1)));
            lblPainelCentral.add(listaQuadradosTabuleiros.get(posicao), constraints);
        }
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
                clicouEditar = false;
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
                if(edicaoAtiva) {
                    mudarEstadoPainelDireito(false);
                }
                controlador.desenharTabuleiro(selecionado);
            }
        });
        //btnEditarTabuleiro
        btnEditarTabuleiro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clicouEditar = true;
                mudarEstadoPainelDireito(true);
                txtNome.setEnabled(false);
                controlador.iniciarListaTerrenosCriacao(cmbListaTabuleiros.getSelectedItem().toString());
            }
        });
        //btnDeletarTabuleiro
        btnDeletarTabuleiro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clicouEditar = false;
                try {
                    controlador.deletarArquivoDeTabuleiro(cmbListaTabuleiros.getSelectedItem().toString());
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao manusear arquivos", "Erro de arquvios", JOptionPane.ERROR_MESSAGE);
                }
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
        //cmbTiposTerreno
        cmbTiposTerreno.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                lblTerrenoSelecionado.setIcon(imagens.get(EnumTipoTerreno.valueOf(cmbTiposTerreno.getSelectedItem().toString()).getTipo()));
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
                if(clicouEditar && controlador.getTipoTabuleiro(cmbListaTabuleiros.getSelectedItem().toString()).getTipo().equals("criado")) {
                    try {
                        controlador.atualizarArquivoDeTabuleiro(cmbListaTabuleiros.getSelectedItem().toString(), terrenosQuadradosTabuleiros);
                        JOptionPane.showMessageDialog(null, "Tabuleiro atualizado", "Operacao concluida", JOptionPane.INFORMATION_MESSAGE);
                    } catch(Exception ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao manusear arquivos", "Erro de arquvios", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    try {
                        if(txtNome.getText().isBlank()) {
                            JOptionPane.showMessageDialog(null, "O nome nao pode ser vazio", "Nome vazio", JOptionPane.ERROR_MESSAGE);
                        } else if(controlador.getTipoTabuleiro(txtNome.getText()) == null) {
                            controlador.criarArquivoDeTabuleiro(txtNome.getText(), terrenosQuadradosTabuleiros);
                            JOptionPane.showMessageDialog(null, "Tabuleiro criado", "Operacao concluida", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Ja existe um tabuleiro com esse nome", "Tabuleiro existente", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch(Exception ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao manusear arquivos", "Erro de arquvios", JOptionPane.ERROR_MESSAGE);
                    }
                }
                resetarDesenhoTabuleiros();
                mudarEstadoPainelDireito(false);
            }
        });
        
        
    }
    
    private void iniciarListaQuadradosTabuleiros() {
        listaQuadradosTabuleiros = new HashMap<>();
        for(int linha = 0; linha < 8; linha++) {
            for(int coluna = 0; coluna < 8; coluna++) {
                JLabel quadrado = new JLabel();
                int finalLinha = linha;
                int finalColuna = coluna;
                quadrado.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(edicaoAtiva) {
                            quadrado.setIcon(imagens.get(EnumTipoTerreno.valueOf(cmbTiposTerreno.getSelectedItem().toString()).getTipo()));
                            terrenosQuadradosTabuleiros.put((finalLinha + "" + finalColuna), EnumTipoTerreno.valueOf(cmbTiposTerreno.getSelectedItem().toString()));
                        }
                    }
                });
                listaQuadradosTabuleiros.put((linha + "" + coluna), quadrado);
            }
        }
    }
    
    public void iniciarListaTerrenosCriacao(HashMap<String, EnumTipoTerreno> terrenos) {
        terrenosQuadradosTabuleiros = terrenos;
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
        Set<String> posicoes = terrenos.keySet();
        for(String posicao : posicoes) {
            listaQuadradosTabuleiros.get(posicao).setIcon(imagens.get(terrenos.get(posicao).getTipo()));
        }
    }
    
    @Override
    public void atualizarListaDeTabuleiros(Vector<String> vector) {
        cmbListaTabuleiros.setModel(new DefaultComboBoxModel(vector));
        resetarDesenhoTabuleiros();
    }
    
    public void resetarDesenhoTabuleiros() {
        cmbListaTabuleiros.setSelectedIndex(0);
        controlador.desenharTabuleiro(cmbListaTabuleiros.getSelectedItem().toString());
        controlador.mudarEstadoEditarDeletar(cmbListaTabuleiros.getSelectedItem().toString());
    }
}
