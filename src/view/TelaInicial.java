package view;

import controller.ControladorTelaInicial;
import controller.observer.ObserverTelaInicial;
import view.utils.SingletonImagens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

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
    
    public TelaInicial() {
        layout = new GridBagLayout();
        constraints = new GridBagConstraints();
        controlador = new ControladorTelaInicial();
        controlador.attach(this);
        initialize();
        initializeActions();
    }
    
    private void initialize() {
        //lblLogo
        lblLogo = new JLabel(imagens.get("Logo"));
        //btnJogar
        btnJogar = criarBotao("Jogar", (int) (getLargura() * 0.081), (int) (getAltura() * 0.043));
        //btnTabuleiros
        btnTabuleiros = criarBotao("Tabuleiros", (int) (getLargura() * 0.081), (int) (getAltura() * 0.043));
        //btnRegras
        btnRegras = criarBotao("Regras", (int) (getLargura() * 0.081), (int) (getAltura() * 0.043));
        //lblBotoes
        lblBotoes = new JLabel(criarImagemVazia((int) (getAltura() * 0.043), (int) (getLargura() * 0.406)));
        lblBotoes.setLayout(layout);
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.insets = new Insets(0, (int) (getLargura() * 0.008), 0, (int) (getLargura() * 0.008));
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
        constraints.insets = new Insets((int) (getAltura() * 0.144), 0, 0, 0);
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
    
    private void initializeActions() {
        //btnJogar
        btnJogar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controlador.navegarParaOutraTela("view.abstractFactoryTela.ConcretFactoryTelaConfigurarJogo");
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, "Nao foi possivel localizar essa tela", "Tela nao localizada", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        //btnTabuleiros
        btnTabuleiros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controlador.navegarParaOutraTela("view.abstractFactoryTela.ConcretFactoryTelaTabuleiros");
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, "Nao foi possivel localizar essa tela", "Tela nao localizada", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
