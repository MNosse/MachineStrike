package controller.command;

import controller.observer.ObserverCommand;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    
    private Map<String, Class<? extends Command>> comandos = new HashMap<>();
    
    private ObserverCommand observer;
    
    private CommandFactory() {
        comandos.put("mover", MoverCommand.class);
        comandos.put("girar", GirarCommand.class);
        comandos.put("correr", CorrerCommand.class);
        comandos.put("atacar", AtacarCommand.class);
        comandos.put("sobrecarregar", SobrecarregarCommand.class);
    }
    
    private static CommandFactory instancia;
    
    public static CommandFactory getInstancia() {
        if(instancia == null)
            instancia = new CommandFactory();
        
        return instancia;
    }
    
    public void setObserver(ObserverCommand observer) {
        this.observer = observer;
    }
    
    public Command getComando(String nomeComando, Object[] args) {
        try {
            Class<? extends Command> commandClass = comandos.get(nomeComando);
            Class<?>[] parametros;
            if(args != null) {
                parametros = new Class<?>[]{ObserverCommand.class, Object[].class};
            } else {
                parametros = new Class<?>[]{ObserverCommand.class};
            }
            Constructor<? extends Command> constructor = commandClass.getConstructor(parametros);
            
            Command command;
            if(args != null) {
                command = constructor.newInstance(observer, args);
            } else {
                command = constructor.newInstance(observer);
            }
            
            return command;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}