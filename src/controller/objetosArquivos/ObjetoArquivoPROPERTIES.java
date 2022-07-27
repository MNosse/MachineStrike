package controller.objetosArquivos;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Properties;

public class ObjetoArquivoPROPERTIES {
    private String nome;
    private String tipo;
    private String linha1;
    private String linha2;
    private String linha3;
    private String linha4;
    private String linha5;
    private String linha6;
    private String linha7;
    private String linha8;
    
    public ObjetoArquivoPROPERTIES(File file) throws IOException {
        Properties properties = new Properties();
        properties.load(new InputStreamReader(new FileInputStream(file)));
        this.nome = properties.getProperty("nome", "erro");
        this.tipo = properties.getProperty("tipo", "erro");
        this.linha1 = properties.getProperty("linha1", "erro");
        this.linha2 = properties.getProperty("linha2", "erro");
        this.linha3 = properties.getProperty("linha3", "erro");
        this.linha4 = properties.getProperty("linha4", "erro");
        this.linha5 = properties.getProperty("linha5", "erro");
        this.linha6 = properties.getProperty("linha6", "erro");
        this.linha7 = properties.getProperty("linha7", "erro");
        this.linha8 = properties.getProperty("linha8", "erro");
        
        Field[] propriedades = this.getClass().getDeclaredFields();
        for(Field field : propriedades) {
            if(field.toString().equals("erro")) {
                throw new IOException();
            }
        }
        
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public String getLinha1() {
        return linha1;
    }
    
    public String getLinha2() {
        return linha2;
    }
    
    public String getLinha3() {
        return linha3;
    }
    
    public String getLinha4() {
        return linha4;
    }
    
    public String getLinha5() {
        return linha5;
    }
    
    public String getLinha6() {
        return linha6;
    }
    
    public String getLinha7() {
        return linha7;
    }
    
    public String getLinha8() {
        return linha8;
    }
}
