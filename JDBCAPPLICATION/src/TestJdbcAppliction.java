import java.sql.*;
import java.util.Scanner;
public class TestJdbcAppliction {
	public static void main(String[] args) {
		int ch;
		int eno;
		ResultSet rs;
		Connection con=null;
		PreparedStatement ps=null;
		Statement st;
		try {
			//step1-load driver..
			Class.forName("oracle.jdbc.OracleDriver");
			//step2:Connection to DB
	con=DriverManager
					.getConnection("jdbc:oracle:thin:@localhost:1521:XE","System","123456789");
			System.out.println("Connection :"+con);

			Scanner sc=new Scanner(System.in);
			do{
				System.out.println("1-Save Record\n2-Delete Record\n3-Update Record\n4-Serach Record\n5-Display All");
				System.out.println("Enter Your Choice..");
				ch=sc.nextInt();
				switch(ch)
				{
				case 1:
					ps=con.prepareStatement("insert into mphasis values(?,?,?)");
					System.out.println("enter empno,name,last name");
					ps.setInt(1,sc.nextInt());
					ps.setString(2,sc.next());
					ps.setString(3,sc.next());
					int i=ps.executeUpdate();//DML(insert or update or delete)
					if(i>0)
					{
						System.out.println("Record Save..");
					}
					break;
				case 2:
					System.out.println("Enter Emp no to delete record..");
					eno=sc.nextInt();//1
					ps=con.prepareStatement("delete from mphasis where empid=?");
					ps.setInt(1,eno);
					i=ps.executeUpdate();
					if(i>0){
						System.out.println("Record Deleted..");
					}
					break;
				case 3:
					System.out.println("enter empno and last name to update record..");
					int eno1=sc.nextInt();//1
					String nm=sc.next();//xyz
					ps=con.prepareStatement("update mphasis set lname=? where empid=?");
					ps.setString(1, nm);
					ps.setInt(2,eno1);
					i=ps.executeUpdate();
					if(i>0){
						System.out.println("Record updated..");
					}
				case 4:
					System.out.println("Enter Emp no to serach record..");
					eno=sc.nextInt();//1
					ps=con.prepareStatement("select * from mphasis where empid=?");
					ps.setInt(1,eno);
					 rs=ps.executeQuery();
					rs.next();
					System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
				case 5:
					String str="Select * from mphasis";
					 st=con.createStatement();
					rs=st.executeQuery(str);
					ResultSetMetaData rsmd=rs.getMetaData(); 
					for(i=1;i<=rsmd.getColumnCount();i++)
					{
						System.out.print(rsmd.getColumnName(i)+"\t");
					}
					System.out.println("\n----------------------------------------------\n");
					while(rs.next())
					{
	System.out.println(rs.getInt(1)+"\t"
					+rs.getString(2)+"\t"+rs.getString(3));
					}
				default:
					System.out.println("invalid choice..");
				}
				System.out.println("DO you want to continue press 1");
				ch=sc.nextInt();
			}while(ch==1);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
