package view;

//CONTROLLER
import controller.observer.ObserverTelaInicial;
import controller.ControladorTelaInicial;

//JAVA
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

//JAVAX
import javax.swing.*;

//VIEW
import view.utils.SingletonImagens;

public class TelaInicial extends Tela implements ObserverTelaInicial {
    private JLabel lblLogo;
    private JLabel lblFundo;
    private JLabel lblBotoes;
    private JButton btnJogar;
    private JButton btnRegras;
    private JButton btnTabuleiros;
    private GridBagLayout layout;
    private GridBagConstraints constraints;
    private ControladorTelaInicial controlador;
    private HashMap<String, ImageIcon> imagens = SingletonImagens.getInstancia().getImagens();

    public TelaInicial(){
        layout = new GridBagLayout();
        constraints = new GridBagConstraints();
        controlador = new ControladorTelaInicial();
        controlador.attach(this);
        initialize();
        initializeActions();
    }

    private void initialize(){
        //lblLogo
        lblLogo = new JLabel(imagens.get("Logo"));
        //btnJogar
        btnJogar = criarBotao("Jogar", 100, 30);
        //btnTabuleiros
        btnTabuleiros = criarBotao("Tabuleiros", 100, 30);
        //btnRegras
        btnRegras = criarBotao("Regras", 100, 30);
        //lblBotoes
        lblBotoes = new JLabel(criarImagemVazia(30, 500));
        lblBotoes.setLayout(layout);
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.insets = new Insets(0, 10, 0, 10);
        lblBotoes.add(btnJogar, constraints);
        lblBotoes.add(btnTabuleiros, constraints);
        lblBotoes.add(btnRegras, constraints);
        //lblFundo
        lblFundo = new JLabel(imagens.get("Background"));
        lblFundo.setLayout(layout);
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        lblFundo.add(lblLogo, constraints);
        constraints.insets = new Insets(100, 0, 0, 0);
        lblFundo.add(lblBotoes, constraints);
        //frmTela
        getFrmTela().setSize(getLargura(), getAltura());
        getFrmTela().setResizable(false);
        getFrmTela().setTitle("Machine Strike");
        getFrmTela().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getFrmTela().setLocationRelativeTo(null);
        getFrmTela().setContentPane(lblFundo);
        getFrmTela().setVisible(true);
    }

    private void initializeActions(){
        //btnJogar
        btnJogar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.navegarParaOutraTela("view.abstractFactoryTela.ConcretFactoryTelaConfigurarJogo");
            }
        });
        //btnTabuleiros
        btnTabuleiros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.navegarParaOutraTela("view.abstractFactoryTela.ConcretFactoryTelaTabuleiros");
            }
        });
    }
}
