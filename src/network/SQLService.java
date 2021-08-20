package network;


import java.sql.*;
import network.response.*;
public class SQLService {
	Connection connection=null;
	Statement st=null;
	private static String url="jdbc:mysql://localhost:3307/warehouse?characterEncoding=utf8";
	private static String name="root";
	private static String pass="password";
	public void getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection(url,name,pass);
			System.out.println("connection success");
			st=connection.createStatement();
//			String query="select * from student";
//			ResultSet rs=st.executeQuery(query);
//			while(rs.next()) {
//				System.out.println("id : "+rs.getInt("id")+" name: "+rs.getString("name"));
//			}
			}catch(Exception e) {
				System.out.println("error while creating connection"+e);
			}
	}
	
	public ResultSet executeQuery( String query) {
		
		if(connection==null) {
			return null;
		}else {
			try {
			ResultSet rs=st.executeQuery(query);
			return rs;
			}catch(Exception e) {
				System.out.println("Error occured while execution query "+e);
				return null;
			}
		}
	}
public QueryResponse executeUpdate( String query) {
		QueryResponse response=new QueryResponse();
		if(connection==null) {
			response.statusCode=0;
			response.message="connection not created";
			return response;
		}else {
			try {
			int rs=st.executeUpdate(query);
			response.statusCode=rs;
			response.message="";
			return response;
			}catch(Exception e) {
				response.statusCode=0;
				response.message=e.getMessage();
				System.out.println("Error occured while execution query "+e);
				return response;
			}
		}
	}
}
