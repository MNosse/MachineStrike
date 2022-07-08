package view.components;

//JAVA
import java.awt.*;
import java.util.HashMap;

//JAVAX
import javax.swing.*;

public class CardMaquina extends JLabel {

    private int altura = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.40);
    private int largura = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()*0.19125);

    private JLabel lblTextoCabecalho;
    private JLabel lblTextoVida;
    private JPanel panCabecalho;
    private JPanel panInformacoes;
    private JLabel lblTextoAtaque;
    private JLabel lblTextoAlcance;
    private JLabel lblImagemMaquina;
    private JLabel lblTextoMovimento;

    public CardMaquina(HashMap<String, String> informacoesMaquina) {
        super(new ImageIcon(new ImageIcon("src/images/Carta.png").getImage().getScaledInstance((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()*0.19125), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.38), Image.SCALE_SMOOTH)));
        if (informacoesMaquina != null) {
            initialize(informacoesMaquina.get("CaminhoImagem"), informacoesMaquina.get("Nome"), informacoesMaquina.get("PV"), informacoesMaquina.get("Vida"), informacoesMaquina.get("Ataque"), informacoesMaquina.get("Alcance"), informacoesMaquina.get("Movimento"));
        } else {
            initialize("src/images/Bloqueado.png", "", "", "", "", "", "");
        }
    }

    private void initialize(String caminhoImagem, String nome, String pv, String vida, String ataque, String alcance, String movimento) {
        GridBagConstraints constraints = new GridBagConstraints();
        //lblTextoCabecalho
        lblTextoCabecalho = criarTexto(nome+" PV: "+pv);
        //panCabecalho
        panCabecalho = new JPanel();
        panCabecalho.setLayout(new GridBagLayout());
        panCabecalho.setBackground(new Color(217, 217, 217));
        panCabecalho.setMinimumSize(new Dimension((int)(largura*0.93), (int)(altura*0.075)));
        panCabecalho.setPreferredSize(new Dimension((int)(largura*0.93), (int)(altura*0.075)));
        panCabecalho.add(lblTextoCabecalho, constraints);
        //lblImagemMaquina
        lblImagemMaquina = new JLabel(criarImagem(caminhoImagem, ((int)(largura*0.55)), ((int)(largura*0.55))));
        //lblTextoVida
        lblTextoVida = criarTexto("Vida: "+vida);
        //lblTextoAtaque
        lblTextoAtaque = criarTexto("Ataque: "+ataque);
        //lblTextoAlcance
        lblTextoAlcance = criarTexto("Alcance: "+alcance);
        //lblTextoMovimento
        lblTextoMovimento = criarTexto("Movimento: "+alcance);
        //panInformacoes
        panInformacoes = new JPanel();
        panInformacoes.setLayout(new GridBagLayout());
        panInformacoes.setBackground(new Color(217, 217, 217));
        panInformacoes.setMinimumSize(new Dimension((int)(largura*0.93), (int)(altura*0.25)));
        panInformacoes.setPreferredSize(new Dimension((int)(largura*0.93), (int)(altura*0.25)));
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.insets = new Insets(0, 0, 0, (int)(largura*0.48));
        panInformacoes.add(lblTextoVida, constraints);
        panInformacoes.add(lblTextoAtaque, constraints);
        panInformacoes.add(lblTextoAlcance, constraints);
        panInformacoes.add(lblTextoMovimento, constraints);
        constraints.anchor = GridBagConstraints.CENTER;
        //Card
        this.setLayout(new GridBagLayout());
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.insets = new Insets(0, 0, (int)(altura*0.05), 0);
        this.add(panCabecalho, constraints);
        this.add(lblImagemMaquina, constraints);
        constraints.insets = new Insets(0, 0, (int)(altura*0.010), 0);
        this.add(panInformacoes, constraints);
    }

    private ImageIcon criarImagem(String caminho, int altura, int largura) {
        return new ImageIcon(new ImageIcon((caminho)).getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH));
    }

    private JLabel criarTexto(String texto) {
        JLabel lbl = new JLabel();
        lbl.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        lbl.setBackground(new Color(217, 217, 217));
        lbl.setForeground(Color.BLACK);
        lbl.setText(texto);
        return lbl;
    }

    public void atualizarConteudo(HashMap<String, String> informacoesMaquina){
        lblImagemMaquina.setIcon(criarImagem(informacoesMaquina.get("CaminhoImagem"), ((int)(largura*0.55)), ((int)(largura*0.55))));
        lblTextoCabecalho.setText(informacoesMaquina.get("Nome")+" PV: "+informacoesMaquina.get("PV"));
        lblTextoVida.setText("Vida: "+informacoesMaquina.get("Vida"));
        lblTextoAtaque.setText("Ataque: "+informacoesMaquina.get("Ataque"));
        lblTextoAlcance.setText("Alcance: "+informacoesMaquina.get("Alcance"));
        lblTextoMovimento.setText("Movimento: "+informacoesMaquina.get("Movimento"));
    }
}
