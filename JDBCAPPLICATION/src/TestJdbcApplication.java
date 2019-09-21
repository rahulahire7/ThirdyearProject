import java.sql.*;
import java.util.*;
public class TestJdbcApplication {
	public static void main(String[] args) {
		//load driver...
		int i=0;
		Scanner sc=new Scanner(System.in);
		PreparedStatement ps=null;
		Statement stat;
		ResultSet rs=null;
		try
		{
			int ch;
			//step1-LOAD DRIVER..
			Class.forName("oracle.jdbc.OracleDriver");
			//step2:Connection to DB
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","System","123456789");
			System.out.println("Connection to DB "+con);
			System.out.println("1-Insert record..");
			System.out.println("2-delete\n3-Update\n4-Search\n5-Display All");
			System.out.println("Enter your choice..");
			ch=sc.nextInt();
			switch(ch)
			{
			case 1:
				ps=con.prepareStatement("insert into register values(?,?,?)");
				ps.setInt(1,102);
				ps.setString(2,"Pallavi");
				ps.setFloat(3,88.88f);
				i=ps.executeUpdate();
				if(i>0)
				{
					System.out.println("Record inserted...");
				}
				else
				{
					System.out.println("not inserted..");
				}
				break;
			case 2:
				System.out.println("Enter reg no to delete record");
				int no=sc.nextInt();//102
				ps=con.prepareStatement("delete from register where RNO=?");
				ps.setInt(1,no);
				i=ps.executeUpdate();
				if(i>0)
				{
					System.out.println("Record deleted..");
				}
				break;
			case 3:
				System.out.println("enter no to search record..");
				no=sc.nextInt();//101
				ps=con.prepareStatement("select * from register where rno=?");
				ps.setInt(1,no);//
				rs=ps.executeQuery();//DQL
				rs.next();
				int rno=rs.getInt(1);
				String fnm=rs.getString(2);
				float p=rs.getFloat(3);
				System.out.println(rno+"\t"+fnm+"\t"+p);
				break;
			case 5://display All records..
		stat=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		String str="select * from register";
				ResultSet rs1=stat.executeQuery(str);
				rs1.last();
				System.out.println(rs1.getInt(1)+"\t"+rs1.getString(2)+"\t"+rs1.getFloat(3));
				System.out.println("-------------");
				rs1.previous();
				System.out.println(rs1.getInt(1)+"\t"+rs1.getString(2)+"\t"+rs1.getFloat(3));
				
				ResultSetMetaData rsmd=rs1.getMetaData();
				for(i=1;i<=rsmd.getColumnCount();i++)
				{
					System.out.print(rsmd.getColumnName(i)+"\t");
				}
				System.out.println("\n--------------------------------------------\n");
				while(rs1.next())
				{
					System.out.println(rs1.getInt(1)+"\t"+rs1.getString(2)+"\t"+rs1.getFloat(3));
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();

		}
	}
}
