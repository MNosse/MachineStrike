package view.Tela;

import controller.controlador.ControladorTelaInicial;
import controller.observer.ObserverTelaInicial;
import view.abstractFactoryTela.AbstractFactoryTela;
import view.abstractFactoryTela.ConcretFactoryTelaConfigurarJogo;
import view.abstractFactoryTela.ConcretFactoryTelaTabuleiros;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInicial extends Tela implements ObserverTelaInicial {
    private JLabel lblLogo;
    private JLabel lblFundo;
    private JLabel lblBotoes;
    private JButton btnJogar;
    private JButton btnRegras;
    private JButton btnTabuleiros;
    private GridBagLayout layout;
    private GridBagConstraints constraints;
    private AbstractFactoryTela factoryTela;
    private ControladorTelaInicial controlador;

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
        lblLogo = new JLabel(criarImagem("src/images/Logo.png", ((int)(getAltura()*0.375)), ((int)(getLargura()*0.36402))));
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
        lblFundo = new JLabel(criarImagem("src/images/Background.png", getAltura(), getLargura()));
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
                factoryTela = new ConcretFactoryTelaConfigurarJogo();
                navegarParaOutraTela(factoryTela.construirTela());
            }
        });
        //btnTabuleiros
        btnTabuleiros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                factoryTela = new ConcretFactoryTelaTabuleiros();
                navegarParaOutraTela(factoryTela.construirTela());
            }
        });
    }
}