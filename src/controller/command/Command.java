package controller.command;

//CONTROLLER
import controller.observer.ObserverCommand;

import java.util.List;

public abstract class Command {

    protected List<ObserverCommand> observers;

    public Command(List<ObserverCommand> observers) {
        this.observers = observers;
    }

    public abstract void execute();

    public abstract void undo();

    public abstract void redo();

}
