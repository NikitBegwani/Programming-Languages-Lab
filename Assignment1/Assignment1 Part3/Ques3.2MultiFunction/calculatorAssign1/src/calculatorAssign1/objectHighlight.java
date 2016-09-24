package calculatorAssign1;
import java.awt.Color;
import java.util.List;

import javax.swing.SwingWorker;
import javax.swing.JButton;

public class objectHighlight extends SwingWorker<Integer, Integer> {

  /*private JTextField messagesTextArea;*/
  private JButton[] objectButton;
  public Integer type, i = 0;
  
  public objectHighlight(JButton[] numberButton, Integer type) {
    /*this.messagesTextArea = messagesTextArea;*/
    this.objectButton = numberButton;
    this.type = type;
  }

  @Override
  protected Integer doInBackground() throws Exception {
	  while(true){
		  if(type == 0)
			  i = (i+1)%10;
		  else
			  i = (i+1)%4;
		  publish(i);
		  Thread.sleep(1500);
	  }
  }

  protected void process(final List<Integer> chunks) {
    for (Integer temp : chunks) {
    	if(type == 0){
    		objectButton[temp].setBackground(new Color(0, 240, 240));
    		objectButton[temp>0?((temp-1)):9].setBackground(new Color(224, 223, 219));
    	}
    	else{
    		objectButton[temp].setBackground(new Color(240, 0, 240));
    		objectButton[temp>0?((temp-1)):3].setBackground(new Color(224, 223, 219));
    	}
    }
  }
}