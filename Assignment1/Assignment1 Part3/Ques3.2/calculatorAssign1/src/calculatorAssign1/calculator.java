package calculatorAssign1;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.math.MathContext;

import javax.swing.SwingConstants;
import javax.swing.JLabel;
//main calculator class
//implements standard java keylistner interface
public class calculator implements KeyListener{
	//main window frame
	private JFrame frame;
	//state of input and operation selected
	private Integer state = 0, op;
	//textfield object
	private JTextField textField;
	//number Buttons
	public JButton[] numberButton = new JButton[10];
	//function buttons
	public JButton[] functionButton = new JButton[5];
	//number highlight object
	private objectHighlight numberHighlight = new objectHighlight(numberButton, 0);
	//function highlight
	private objectHighlight functionHighlight = new objectHighlight(functionButton, 1);
	//labels for instructions
	private JLabel lblNewLabel_1;
	private JLabel lblPressSpace;
	private JLabel lblPressC;
	private JLabel lblPressSpace_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//create a new object on start
					calculator window = new calculator();
					window.frame.setVisible(true);
					window.frame.setFocusable(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public calculator() {
		initialize();
		//keylistner is added on the main frame
		frame.addKeyListener(this);
		//start the number highlight thread
		numberHighlight.execute();
	}

	private void initialize() {
		//////////render all the graphics at appropriate places and properties ////////////////////////
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 210, 469);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Gisha", Font.PLAIN, 14));
		textField.setEditable(false);
		textField.setBounds(10, 11, 184, 46);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Instrctions:");
		lblNewLabel.setBounds(10, 364, 135, 14);
		frame.getContentPane().add(lblNewLabel);

		lblNewLabel_1 = new JLabel("1. Press Enter to select number");
		lblNewLabel_1.setBounds(10, 377, 184, 14);
		frame.getContentPane().add(lblNewLabel_1);

		lblPressSpace = new JLabel("2. Press Space to select function");
		lblPressSpace.setBounds(10, 391, 194, 14);
		frame.getContentPane().add(lblPressSpace);

		lblPressC = new JLabel("3. Press C to clear");
		lblPressC.setBounds(10, 406, 135, 14);
		frame.getContentPane().add(lblPressC);

		lblPressSpace_1 = new JLabel("4. Press Space to evaluate");
		lblPressSpace_1.setBounds(10, 419, 184, 14);
		frame.getContentPane().add(lblPressSpace_1);


		for(int i = 0;i<10;i++){
			numberButton[i] = new JButton(String.valueOf(i));
			numberButton[i].setFont(new Font("Tahoma", Font.BOLD, 18));
			numberButton[i].setBackground(new Color(224, 223, 219));
			frame.getContentPane().add(numberButton[i]);
		}
		numberButton[7].setBounds(20, 68, 50, 50);
		numberButton[8].setBounds(80, 68, 50, 50);
		numberButton[9].setBounds(140, 68, 50, 50);
		numberButton[4].setBounds(20, 129, 50, 50);
		numberButton[5].setBounds(80, 129, 50, 50);
		numberButton[6].setBounds(140, 129, 50, 50);
		numberButton[1].setBounds(20, 190, 50, 50);
		numberButton[2].setBounds(80, 190, 50, 50);
		numberButton[3].setBounds(140, 190, 50, 50);
		numberButton[0].setBounds(80, 251, 50, 50);

		for(int i = 0;i<5;i++){
			functionButton[i] = new JButton(getFunc(i));
			functionButton[i].setFont(new Font("Tahoma", Font.BOLD, 18));
			functionButton[i].setBackground(new Color(224, 223, 219));
			frame.getContentPane().add(functionButton[i]);
		}
		functionButton[0].setBounds(20, 251, 50, 50);
		functionButton[3].setBounds(140, 305, 50, 50);
		functionButton[1].setBounds(20, 305, 50, 50);
		functionButton[2].setBounds(80, 305, 50, 50);
		functionButton[4].setBounds(140, 251, 50, 50);
		////////////////////////////////////////////////////////////////////////
	}
	//method the get the function binding corresponding to input number
	private String getFunc(Integer num){
		switch(num){
		case 0:
			return "+";
		case 1:
			return "-";
		case 2:
			return "*";
		case 3:
			return "/";
		case 4:
			return "C";
		}
		return null;
	}

	///keypress events
	///actions are implemented in states
	//state 0: is no input
	//:1 one number entered start function thread
	//:2 function selected
	//:3 atleast one number selected
	//:4 result displayed
	@Override
	public void keyPressed(KeyEvent e) {
		//enter number
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			if(state != 4){
				if(state == 0){
					//start function thread
					functionHighlight.execute();
					state = 1;
				}
				else if(state == 2)
					state = 3;
				//update value of text field
				textField.setText(textField.getText() + String.valueOf(numberHighlight.i));
			}
		}
		//select function
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			if(state == 1){
				//add function to textfield
				op = functionHighlight.i;
				textField.setText(textField.getText() + getFunc(op));
				state = 2;
				functionHighlight.cancel(true);
			}
			else if(state == 3){
				numberButton[numberHighlight.i].setBackground(new Color(224, 223, 219));
				functionButton[op].setBackground(new Color(224, 223, 219));
				numberHighlight.cancel(true);
				state = 4;
				textField.setText(evaluateExpr(textField.getText()));
			}
		}
		//clear input cases
		if(e.getKeyCode() == KeyEvent.VK_C){
			if(state == 1){
				op = functionHighlight.i;
				functionHighlight.cancel(true);
				functionButton[op].setBackground(new Color(224, 223, 219));
			}
			else if(state == 2 || state == 3){
				functionButton[op].setBackground(new Color(224, 223, 219));
			}
			else if(state == 4){
				numberHighlight = new objectHighlight(numberButton, 0);
				numberHighlight.execute();
			}
			functionHighlight = new objectHighlight(functionButton, 1);;
			textField.setText("");
			state = 0;
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {

	}
	@Override
	public void keyTyped(KeyEvent arg0) {
	}
	///method evaluated the input expr
	//each expr contains two numbers and one function
	private String evaluateExpr(String expr){
		//bigdecimal used for long user inputs
		BigDecimal num1, num2, ans, temp = new BigDecimal("0");
		int check = 1;
		//find the position of function
		while(Character.isDigit(expr.charAt(check)) || expr.charAt(check) == '.')
			check++;
		//extract numbers from expression
		num1 = new BigDecimal(expr.substring(0,check));
		num2 = new BigDecimal(expr.substring(check + 1,expr.length()));
		//division by zero error
		if(num2.compareTo(temp) == 0 && expr.charAt(check) == '/')
			return "Invalid";
		//evaluate functions
		if(expr.charAt(check) == '*')
			ans = num1.multiply(num2);
		else if(expr.charAt(check) == '+')
			ans = num1.add(num2);
		else if(expr.charAt(check) == '/')
			ans = num1.divide(num2, MathContext.DECIMAL32);
		else
			ans = num1.subtract(num2);
		return ans.toString();
	}
}
