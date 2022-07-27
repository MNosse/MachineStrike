package view;

import controller.ControladorTelaEscolhaTipoArquivo;
import controller.observer.ObserverTelaEscolhaTipoArquivo;
import view.utils.SingletonImagens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaEscolhaTipoArquivo extends Tela implements ObserverTelaEscolhaTipoArquivo {
    private JLabel lblFundo;
    private JButton btnTXT;
    private JLabel lblTitulo;
    private JLabel lblBotoes;
    private JButton btnPROPERTIES;
    private GridBagLayout layout;
    private GridBagConstraints constraints;
    private ControladorTelaEscolhaTipoArquivo controlador;
    
    public TelaEscolhaTipoArquivo() {
        layout = new GridBagLayout();
        constraints = new GridBagConstraints();
        controlador = new ControladorTelaEscolhaTipoArquivo();
        controlador.attach(this);
        initialize();
        initializeActions();
    }
    
    private void initialize() {
        //lblTitulo
        lblTitulo = new JLabel("Qual estrutura de arquivos deseja utilizar?");
        lblTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        lblTitulo.setForeground(Color.BLACK);
        //btnTXT
        btnTXT = criarBotao("TXT", (int) (getLargura() * 0.105), (int) (getAltura() * 0.043));
        //btnPROPERTIES
        btnPROPERTIES = criarBotao("PROPERTIES", (int) (getLargura() * 0.105), (int) (getAltura() * 0.043));
        //lblBotoes
        lblBotoes = new JLabel(criarImagemVazia((int) (getAltura() * 0.043), (int) (getLargura() * 0.244)));
        lblBotoes.setLayout(layout);
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, (int) (getLargura() * 0.008), 0, (int) (getLargura() * 0.008));
        lblBotoes.add(btnTXT, constraints);
        lblBotoes.add(btnPROPERTIES, constraints);
        //lblFundo
        lblFundo = new JLabel(SingletonImagens.getInstancia().getImagens().get("Background"));
        lblFundo.setLayout(layout);
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.insets = new Insets(0, 0, (int) (getAltura() * 0.043), 0);
        lblFundo.add(lblTitulo, constraints);
        constraints.insets = new Insets(0, (int) (getLargura() * 0.008), 0, (int) (getLargura() * 0.008));
        lblFundo.add(lblBotoes, constraints);
        //frmTela
        getFrmTela().setSize(((int) (getLargura() * 0.3)), ((int) (getAltura() * 0.3)));
        getFrmTela().setResizable(false);
        getFrmTela().setTitle("Machine Strike");
        getFrmTela().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getFrmTela().setLocationRelativeTo(null);
        getFrmTela().setContentPane(lblFundo);
        getFrmTela().setVisible(true);
    }
    
    private void initializeActions() {
        //btnTXT
        btnTXT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controlador.setTipoTXT();
                    controlador.navegarParaOutraTela("view.abstractFactoryTela.ConcretFactoryTelaInicial");
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, "Nao foi possivel localizar essa tela", "Tela nao localizada", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        //btnPROPERTIES
        btnPROPERTIES.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controlador.setTipoPROPERTIES();
                    controlador.navegarParaOutraTela("view.abstractFactoryTela.ConcretFactoryTelaInicial");
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, "Nao foi possivel localizar essa tela", "Tela nao localizada", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
