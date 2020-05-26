package tools;
 
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * @author Binver131
 *
 * @Description 计算字符串显示长度的工具
 */
public class FontTool {
 
    
 
    public static int getWidth(String t, Font font) {
    	String string = t+"  ";
    	int width = 0;
        Shell shell = new Shell();
        Label label = new Label(shell, SWT.NONE);
        label.setFont(font);
        GC gc = new GC(label);
        for(int i=0;i<string.length();i++){
              char c = string.charAt(i);
              width += gc.getAdvanceWidth(c);
        }
        gc.dispose();
        shell.dispose();
        return width;
    }
 
}