import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class TT1 extends JFrame implements ActionListener
{
	JPanel p1;
	JLabel lblid,lblname,lblcity,lblphno;
	JTextField txtid,txtname,txtphno;
	JComboBox cmbcity;
	JButton btnsave ,btnretrive,btndelete,btnupdate,btnfirst,btnlast,btnnext,btnpre;
	
	static ResultSet result;
	static ResultSet result1;
	static Connection con;
	static PreparedStatement stat;
	static Statement stat1;
	
public  TT1()
{
	p1=new JPanel();
	lblid =new JLabel("Roll Number");
	lblname=new JLabel("Student name");
	lblcity=new JLabel("City");
	lblphno=new JLabel("Phone Number");
	
	txtid=new JTextField(10);
	txtname=new JTextField(10);
	txtphno=new JTextField(10);
	
	String s[]={"Mumbai","Pune","Nashik","Nagar"};
	cmbcity=new JComboBox(s);
	
	
	btnsave=new JButton("Save");
	btnretrive=new JButton("Retrive");
	btndelete=new JButton("Delete");
	btnupdate=new JButton("Update");
	btnfirst = new JButton("First");
	btnlast = new JButton("Last");
	btnnext = new JButton("Next");
	btnpre = new JButton("Previous");
	
	setVisible(true);
	setSize(300,300);
	getContentPane().add(p1);
	
	GridLayout g=new GridLayout(8,2);
	p1.setLayout(g);
	
	p1.add(lblid);
	p1.add(txtid);
	p1.add(lblname);
	p1.add(txtname);
	p1.add(lblcity);
	p1.add(cmbcity);
	p1.add(lblphno);
	p1.add(txtphno);

	p1.add(btnsave);
	
	p1.add(btnretrive);
	p1.add(btnupdate);
	p1.add(btndelete);
	p1.add(btnfirst);
	p1.add(btnlast);
	p1.add(btnnext);
	p1.add(btnpre);
	show();

	btnsave.addActionListener(this);
	btnupdate.addActionListener(this);
	btndelete.addActionListener(this);
	btnretrive.addActionListener(this);
	btnfirst.addActionListener(this);
	btnlast.addActionListener(this);
	btnnext.addActionListener(this);
	btnpre.addActionListener(this);		
}
public void actionPerformed(ActionEvent evt)
{
	if(evt.getSource()==btnsave)
	{
		try
		{
		System.out.println("t1");		
stat=con.prepareStatement("insert into student1 values(?,?,?,?)");	
		System.out.println("t2");		
		stat.setInt(1,Integer.parseInt(txtid.getText()));
		System.out.println("t3");		
		stat.setString(2,txtname.getText());
		System.out.println("t4");		
		stat.setString(3,(String)cmbcity.getSelectedItem());
		stat.setInt(4,Integer.parseInt(txtphno.getText()));
		System.out.println("t5");		
		int i=stat.executeUpdate();
		System.out.println("t6");
						
			if(i>=1)
				System.out.println("Record  Saved.....");
			else
				System.out.println("Unable to save record.....");
			JOptionPane.showMessageDialog(this, new String("Record Save Successfully..."));				
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, new String("Cannot insert Duplicate value for Roll Number "));
			System.out.println("Error1......."+e);
		}	

	}
	if(evt.getSource()==btnretrive)
	{
		try
		{
	stat=con.prepareStatement("select * from student1 where rollno = ?");
	stat.setInt(1,Integer.parseInt(txtid.getText()));
	result = stat.executeQuery();
	result.next();
		}
		catch(Exception e)
		{
			System.out.println("Error2......"+e);
		}	
		showRecord(result);		
	}	
	if(evt.getSource()==btndelete)	
	try
	{
stat=con.prepareStatement("delete student1 where rollno = ?");
stat.setInt(1,Integer.parseInt(txtid.getText()));
stat.executeUpdate();
	}
	catch(Exception e)
	{
		System.out.println("Error.3......"+e);
	}			
		if(evt.getSource()==btnupdate)
		{
		try
		{
stat=con.prepareStatement("update student1 set std_name=?, std_city=?,std_phone=? where rollno = ?");			
stat.setInt(4,Integer.parseInt(txtid.getText()));
stat.setString(1,txtname.getText());
stat.setString(2,(String)cmbcity.getSelectedItem());
stat.setInt(3,Integer.parseInt(txtphno.getText()));
stat.executeUpdate();

		}
		catch(Exception e)
		{
			System.out.println("Error.4......"+e);
		}
		JOptionPane.showMessageDialog(this, new String("Record Updated Successfully..."));
	}//if update close
	//action setting for surfing buttons
		if(evt.getSource()== btnfirst)
		{		
				try
				{					  
					result1.first();
					txtid.setText(result1.getString(1));
					txtname.setText(result1.getString(2));
					String str=result1.getString(3).trim();
					cmbcity.setSelectedItem(str);
					txtphno.setText(result1.getString(4));
				}
				  catch(Exception e){}
		
		}
		if(evt.getSource()== btnlast)
		{
		
				try
				{	
				  
					result1.last();
					txtid.setText(result1.getString(1));
					txtname.setText(result1.getString(2));
					String str=result1.getString(3).trim();
					cmbcity.setSelectedItem(str);
					txtphno.setText(result1.getString(4));
				}
				  catch(Exception e){}
		
		}

		if(evt.getActionCommand()== "Next")
		{
		
				try
				{	
				  
					result1.next();
			
					txtid.setText(result1.getString(1));
							
					txtname.setText(result1.getString(2));
					String str=result1.getString(3).trim();
					cmbcity.setSelectedItem(str);
					txtphno.setText(result1.getString(4));
				}
				  catch(Exception e){}		
		}		
		if(evt.getActionCommand()== "Previous")
		{		
				try
				{					  
					result1.previous();
					txtid.setText(result1.getString(1));
					txtname.setText(result1.getString(2));
					String str=result1.getString(3).trim();
					cmbcity.setSelectedItem(str);
					txtphno.setText(result1.getString(4));
				}
				  catch(Exception e){}		
		}
}
public void showRecord(ResultSet result)		
		{
		try
		{
			txtid.setText(result.getString(1));
			txtname.setText(result.getString(2));
		
			String str=result.getString(3).trim();
			cmbcity.setSelectedItem(str);
			txtphno.setText(result.getString(4));
		}
		catch(Exception e3) {};
		
}
public static void main(String args[])
{
	TT1 t=new TT1();
	try
	{
/*Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
con=DriverManager.getConnection("jdbc:odbc:MyData","","");
*/
Class.forName("oracle.jdbc.OracleDriver");
con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","System","123456789");
	  stat1=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
	result1 = stat1.executeQuery("select * from student1");
	}	
		catch(Exception ee){
	System.out.println("In Main Catch "+ee);	
	}
}
}
	