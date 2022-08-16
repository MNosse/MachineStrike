package view.decorator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JogadorAtivoImagemDecorator extends ImagemDecorator {
    
    public JogadorAtivoImagemDecorator(ImagemBase imagem) {
        super(imagem);
    }
    
    @Override
    public ImageIcon getImagem() {
        BufferedImage imageBase;
        try {
            imageBase = ImageIO.read(new File(imagem.getCaminho()));
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        BufferedImage imagensJuntas = new BufferedImage(imagem.getLargura(), imagem.getAltura(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = imagensJuntas.getGraphics();
        g.setColor(Color.BLUE);
        g.fillRect((int) (imagem.getLargura() * 0.1), (int) (imagem.getAltura() * 0.1), (int) (imagem.getLargura() * 0.8), (int) (imagem.getAltura() * 0.8));
        g.drawImage(imageBase.getScaledInstance(imagem.getLargura(), imagem.getAltura(), Image.SCALE_SMOOTH), 0, 0, null);
        return new ImageIcon(imagensJuntas);
    }
}
