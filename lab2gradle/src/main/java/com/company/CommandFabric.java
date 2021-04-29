package com.company;


import com.company.commands.Command;
import org.apache.log4j.Logger;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;


public class CommandFabric {

    private final static Logger logger = Logger.getLogger(CommandFabric.class);
    CommandDictionary CD = new CommandDictionary();
    private final GameDraw wrld;
    public Map<String,Command> commands = new HashMap<String, Command>();

    CommandFabric(GameDraw w) throws IOException {
        CD.ParseCommands();
        wrld = w;
    }

    /**
     *
     * @param name - name of function like INIT/TELEPORT/DRAW/WARD
     * creation of new class for new type of command which haven't used yet
     */
    public void FirstCreateCommand(String name){
        try {
            Class<?> cls = Class.forName(CD.dictionary.get(name));
            Constructor<?> cons = cls.getConstructor(GameDraw.class);
            commands.put(name, (Command) cons.newInstance(wrld));
            logger.info(name+" class was added");
        }catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param comm - command string
     * function try to find command class in map and start command, if it can't, it use function of creating new command
     * class and then start command
     */
    public boolean CreateCommand(String comm){
        String[] sub_str = comm.split(" ");
        Command command;
        boolean checker = false;
        try {
            if(commands.get(sub_str[0]) == null){
                FirstCreateCommand(sub_str[0]);
            }

            command = commands.get(sub_str[0]);
            command.SetCommandLine(sub_str);
            checker = command.CheckCommand();
            if(checker) {
                command.DoCommand();
            }
            logger.info(sub_str[0]+" class was taken");
            return checker;
        }catch (IOException e){
            e.printStackTrace();
        }
        return true;
    }

}
