package calculatorAssign1;
import java.awt.Color;
import java.util.List;

import javax.swing.SwingWorker;
import javax.swing.JButton;

//This class objects are generate events to highlight different object on UI
public class objectHighlight extends SwingWorker<Integer, Integer> {

//there are two types of object highlight one for functions and other for numbers
//JButton takes the set of buttons to be highlighted
  private JButton[] objectButton;
  public Integer type, i = 0;
 //constructor sets the global variables
  public objectHighlight(JButton[] numberButton, Integer type) {
    this.objectButton = numberButton;
    this.type = type;
  }

  @Override
  //the thread increments the value of i, and them sleeps for 1.5sec
  protected Integer doInBackground() throws Exception {
	  while(true){
		  //type 0 is number and there are 10 numbers [0-9]
		  if(type == 0)
			  i = (i+1)%10;
		//type 1 is functions and there are 4 functions [+,-,*,/]
		  else
			  i = (i+1)%4;
		  //value is published for UI update from EHT
		  publish(i);
		  Thread.sleep(1500);
	  }
  }
//process is run by the EHT when a value is published from doInBackground
//it updates the color of current object and changes back the color of previous object
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
