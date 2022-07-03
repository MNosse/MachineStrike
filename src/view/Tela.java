package view;

//JAVA
import java.awt.*;
import java.util.Vector;
import java.awt.image.BufferedImage;

//JAVAX
import javax.swing.*;

public abstract class Tela {
    private JFrame frmTela = new JFrame();
    private int altura = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.9);
    private int largura = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()*0.9);

    protected ImageIcon criarImagem(String caminho, int altura, int largura) {
        return new ImageIcon(new ImageIcon((caminho)).getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH));
    }

    protected ImageIcon criarImagemVazia(int altura, int largura) {
        return new ImageIcon(new BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB).getScaledInstance(largura, altura, Image.SCALE_SMOOTH));
    }

    protected JButton criarBotao(String texto, int largura, int altura) {
        JButton botao = new JButton(texto);
        botao.setBackground(new Color(217, 217, 217));
        botao.setMinimumSize(new Dimension(largura, altura));
        botao.setPreferredSize(new Dimension(largura, altura));
        return botao;
    }

    protected JComboBox criarComboBox(Vector vector, int largura, int altura) {
        JComboBox jComboBox = new JComboBox(vector);
        jComboBox.setBackground(new Color(217, 217, 217));
        jComboBox.setMinimumSize(new Dimension(largura, altura));
        jComboBox.setPreferredSize(new Dimension(largura, altura));
        return jComboBox;
    }


    public void navegarParaOutraTela(Tela outraTela) {
        frmTela.dispose();
    }

    public int getAltura() {
        return altura;
    }

    public int getLargura() {
        return largura;
    }

    public JFrame getFrmTela() {
        return frmTela;
    }
}
