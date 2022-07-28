package view.decorator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SelecionarImagemDecorator extends ImagemDecorator{
    
    public SelecionarImagemDecorator(ImagemBase imagem) {
        super(imagem);
    }
    
    @Override
    public ImageIcon getImagem() {
        BufferedImage imageBase;
        BufferedImage imageSelecao;
        try {
            imageBase = ImageIO.read(new File(imagem.getCaminho()));
            imageSelecao = ImageIO.read(new File("src/images/Selecionado.png"));
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        BufferedImage imagensJuntas = new BufferedImage(imagem.getLargura(), imagem.getAltura(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = imagensJuntas.getGraphics();
        g.drawImage(imageBase.getScaledInstance(imagem.getLargura(), imagem.getAltura(), Image.SCALE_SMOOTH), 0, 0, null);
        g.drawImage(imageSelecao.getScaledInstance(imagem.getLargura(), imagem.getAltura(), Image.SCALE_SMOOTH), 0, 0, null);
        return new ImageIcon(imagensJuntas);
    }
}
