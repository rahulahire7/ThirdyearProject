import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class TestGui implements ActionListener{
	
	JFrame f1;
	JPanel p1;
	JLabel lunm,lpass;
	JTextField tunm,tpass;
	JButton b1;
	Button b2;
	public TestGui(){
		f1=new JFrame("Login");
		f1.setSize(400,500);
		f1.setVisible(true);
		
		p1=new JPanel();
		f1.add(p1);
		p1.setBackground(Color.yellow);
		
		lunm=new JLabel("User Name");
		lpass=new JLabel("Password");
		
		tunm=new JTextField(10);
		tpass=new JTextField(10);
		b1=new JButton("login");
		b2=new Button("Reset");
		
		p1.add(lunm);
		
		p1.add(tunm);
		p1.add(lpass);
		p1.add(tpass);
		p1.add(b1);
		p1.add(b2);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
	}
	public static void main(String[] args) {
		TestGui t=new TestGui();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1){
			p1.setBackground(Color.red);
		}
		if(e.getSource()==b2){
			p1.setBackground(Color.yellow);
		}
	}
}








