package controller.command;

//CONTROLLER
import controller.observer.ObserverCommand;

public abstract class Command {

    protected ObserverCommand observer;

    public Command(ObserverCommand observer) {
        this.observer = observer;
    }

    public abstract void execute() throws Exception;

    public abstract void undo();

    public abstract void redo();

}
