package controller.command;

import java.util.ArrayList;
import java.util.List;

public class CommandInvoker {
    
    private CommandInvoker() {
    }
    
    private static CommandInvoker instancia;
    
    private List<Command> listaDeComandos = new ArrayList<>();
    
    private List<Command> undo = new ArrayList<>();
    
    public void execute(Command command) throws Exception {
        command.execute();
        listaDeComandos.add(command);
    }
    
    public void undo() {
        if(listaDeComandos.size() > 0) {
            Command command = listaDeComandos.remove(listaDeComandos.size() - 1);
            command.undo();
            undo.add(command);
        }
    }
    
    public void redo() {
        if(undo.size() > 0) {
            Command command = undo.remove(undo.size() - 1);
            command.redo();
            listaDeComandos.add(command);
        }
    }
    
    public static CommandInvoker getInstancia() {
        if(instancia == null)
            instancia = new CommandInvoker();
        
        return instancia;
    }
}
