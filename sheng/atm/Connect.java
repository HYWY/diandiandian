package atm;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Connect {
	public static Connection dbConn = null;
	public Connect(){
		openConn();
		dbConn=getConn();
	}	
	public void openConn(){
		String driveName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=test";
		String userName="sa";
		String userPwd="12345678";
		
		try{
			Class.forName(driveName);//������Ҫ���ӵ����ݿ��������JVM
//			Ҫ�������ݿ⣬��Ҫ��java.sql.DriverManager���󲢻��Connection����   
//		     �ö���ʹ���һ�����ݿ�����ӡ�
			dbConn=DriverManager.getConnection(dbURL, userName, userPwd);
			System.out.println("���ӳɹ�");
		}catch(Exception e1){
			e1.printStackTrace();
			System.out.println("����ʧ��");
		}  
}
	public Connection getConn(){
		return dbConn;
	}
	public static void main(String args[]){
		new Connect();
	}
}
