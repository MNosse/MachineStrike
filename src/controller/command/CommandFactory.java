package controller.command;

//CONTROLLER
import controller.observer.ObserverCommand;

//JAVA
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.lang.reflect.Constructor;

public class CommandFactory {

    private Map<String, Class<? extends Command>> comandos = new HashMap<>();

    private List<ObserverCommand> observers = new ArrayList<>();

    private CommandFactory() {
        comandos.put("mover", MoverCommand.class);
    }

    private static CommandFactory instancia;

    public static CommandFactory getInstancia() {
        if (instancia == null)
            instancia = new CommandFactory();

        return instancia;
    }

    public void attach(ObserverCommand observer) {
        observers.add(observer);
    }

    public Command getComando(String nomeComando, Object[] args) {
        try {
            Class<? extends Command> commandClass = comandos.get(nomeComando);
            Class<?>[] parametros;
            if (args != null) {
                parametros = new Class<?>[]{List.class, Object[].class};
            } else {
                parametros = new Class<?>[]{List.class};
            }
            Constructor<? extends Command> constructor = commandClass.getConstructor(parametros);

            Command command;
            if (args != null) {
                command = constructor.newInstance(observers, args);
            } else {
                command = constructor.newInstance(observers);
            }

            return command;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}