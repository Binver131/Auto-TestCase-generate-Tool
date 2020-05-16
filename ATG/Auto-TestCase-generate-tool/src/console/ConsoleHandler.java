package console;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.MessageConsoleStream;

public class ConsoleHandler{  
    private static MessageConsoleStream consoleStream;  
    
    public static void info(final String _message){  
        Display.getDefault().asyncExec(new Runnable(){  
            @Override  
            public void run() {  
                consoleStream = ConsoleFactory.getConsole().newMessageStream();  
                consoleStream.println(new SimpleDateFormat("HH:mm:ss").format(new Date())+ "(INFO)" +   
                         " " + _message);  
            }  
        });  
    }  
    public static void error(final String _message){  
        Display.getDefault().asyncExec(new Runnable(){  
            @Override  
            public void run() {  
                consoleStream = ConsoleFactory.getConsole().newMessageStream();  
                consoleStream.setColor(new Color(null,255,0,0));  
                consoleStream.println(new SimpleDateFormat("HH:mm:ss").format(new Date())+ "(ERROR)" +   
                         " " + _message);  
            }         
        });  
    }        
}  
