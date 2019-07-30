/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 42519630833
 */
public class Invoker {

    private List<Command> imediatos = new ArrayList<>();

    private List<Command> todos = new ArrayList<>();

    private List<Command> undo = new ArrayList<>();

    public void addCommand(Command command) {
        imediatos.add(command);
    }

    public boolean execute() throws Exception{
        boolean retorno = false;
        for (Command commandImediato : imediatos) {
            retorno = commandImediato.execute();
            todos.add(commandImediato);
        }

        imediatos.clear();
        return retorno;
    }
    
    public void undo() throws Exception{
        if(!todos.isEmpty()){
            Command ultimoCommand = todos.remove(todos.size()-1);
            ultimoCommand.undo();
            undo.add(ultimoCommand);
            
        }else{
            throw new Exception("Não há rodadas anteriores");
    }
        
            
    }
    
    public void execute(Command command){
        command.execute();
        todos.add(command);
    }
    
}
