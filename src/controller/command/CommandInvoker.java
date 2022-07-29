package controller.command;

import java.util.ArrayList;
import java.util.List;

public class CommandInvoker {
    
    private CommandInvoker() {
    }
    
    private static CommandInvoker instancia;
    
    private List<Command> listaDeComandos = new ArrayList<>();
    
    public void execute(Command command) throws Exception {
        command.execute();
        listaDeComandos.add(command);
    }
    
    public static CommandInvoker getInstancia() {
        if(instancia == null)
            instancia = new CommandInvoker();
        
        return instancia;
    }
}
