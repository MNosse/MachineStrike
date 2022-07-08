package view;

//CONTROLLER
import controller.observer.ObserverTelaJogo;
import controller.controlador.ControladorTelaJogo;

//GLOBAL
import global.EnumTipoTerreno;

//JAVA
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
        btnAtacar = criarBotao("Atacar", ((int)(getLargura()*0.2125)), ((int)(getAltura()*0.056)));
        //btnMover
        btnMover = criarBotao("Mover", ((int)(getLargura()*0.2125)), ((int)(getAltura()*0.056)));
        //btnSobrecarregar
        btnSobrecarregar = criarBotao("Sobrecarregar", ((int)(getLargura()*0.2125)), ((int)(getAltura()*0.056)));
        //btnGirar
        btnGirar = criarBotao("Sobrecarregar", ((int)(getLargura()*0.2125)), ((int)(getAltura()*0.056)));
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
        lblPainelEsquerdo.add(btnAtacar, constraints);
        lblPainelEsquerdo.add(btnMover, constraints);
        lblPainelEsquerdo.add(btnSobrecarregar, constraints);
        lblPainelEsquerdo.add(btnGirar, constraints);
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
        controlador.desenharJogo();
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
    }

    private void iniciarAcoes() {
        //btnMover
        btnMover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.trocarEstadoMover();
            }
        });

        //btnSair
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.navegarParaOutraTela("controller.abstractFactoryTela.ConcretFactoryTelaInicial");
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
                        controlador.selecionarQuadrado(finalLinha+""+finalColuna);
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

    @Override
    public void desenharTabuleiro(HashMap<String, EnumTipoTerreno> terrenos) {
        Set<String> posicoes = terrenos.keySet();
        for (String posicao : posicoes) {
            listaQuadradosTabuleiros.get(posicao).setIcon(imagens.get(EnumTipoTerreno.valueOf(terrenos.get(posicao).toString()).getTipo()));
        }
    }

    @Override
    public void desenharQuadrado(String posicao, String caminhoImagem) {
        listaMaquinasNoTabuleiro.get(posicao).setIcon(criarImagem(caminhoImagem, ((int)(getAltura()*0.11)), ((int)(getAltura()*0.11))));
    }

    @Override
    public void desenharQuadrado(String posicao) {
        listaMaquinasNoTabuleiro.get(posicao).setIcon(imagens.get("Vazio"));
    }

    @Override
    public void desenharQuadrados(HashMap<String, String> maquinas) {
        Set<String> posicoes = maquinas.keySet();
        for (String posicao : posicoes) {
            desenharQuadrado(posicao, maquinas.get(posicao));
        }
    }

    @Override
    public void desenharQuadrados(Set<String> posicoes) {
        for (String posicao : posicoes) {
            desenharQuadrado(posicao);
        }
    }

    public void desenharQuadradosSelecionados(Set<String> antigos, Set<String> novos) {
        if (antigos != null) {
            for (String posicao : antigos) {
                listaMaquinasNoTabuleiro.get(posicao).setIcon(imagens.get("Vazio"));
            }
        }
        if (novos != null) {
            for (String posicao : novos) {
                listaMaquinasNoTabuleiro.get(posicao).setIcon(imagens.get("Selecionado"));
            }
        }
    }

    @Override
    public void atualizarCardMaquinaAtacante(HashMap<String, String> informacoes){
        cardMaquinaAtacante.atualizarConteudo(informacoes);
    }
}
