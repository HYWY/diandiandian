//ת��ҳ��2
package Pace;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

import Pace.Pro1_4.MyThread;
public class Pro1_5 extends JFrame implements ActionListener{
	int time=60;
	JLabel lab2=new JLabel();
	String a1="2_1_����.png";
	String a2="1_1_����.png";
	String a3="1_1_ȷ��.png";
	String b1="2_2_����.png";
	String b2="1_2_����.png";
	String b3="1_2_ȷ��.png";
	String c1="2_3_����.png";
	String c2="1_3_����.png";
	String c3="1_3_ȷ��.png";
	JFrame jf=new JFrame("ת�˽���");
	MyButton btn1=new MyButton(a1,b1,c1);
	MyButton btn2=new MyButton(a2,b2,c2);
	MyButton btn3=new MyButton(a3,b3,c3);
	JLabel lab1=new JLabel("������ת�˽��:");
	JTextField txf1=new JTextField();
	String s=null;
	String x=null;
	Connect h=null;
	boolean kg=true;
	public Pro1_5(String c,String d,Connect k){
		s=c;
		x=d;
		h=k;
		Container cp=jf.getContentPane();
		jf.setSize(1024,640);
		jf.setLocation(180,50);
		String path="��ҳ��2.jpg";
		Image backGround=new ImageIcon(path).getImage();
		JLabel label = new aLabel(backGround);
		label.setBounds(0, 0, jf.getWidth(), jf.getHeight());
		JPanel imagePanel = (JPanel) jf.getContentPane();  
        imagePanel.setOpaque(false);  
        // �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����  
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        lab2.setFont(new   java.awt.Font("Dialog",   1,   40));
		lab1.setFont(new   java.awt.Font("Dialog",   1,   25));
		txf1.setFont(new   java.awt.Font("Dialog",   1,   25));
		lab2.setForeground(Color.green);
		lab2.setOpaque(true);
		lab2.setBackground(Color.black);
		cp.setLayout(null);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		lab2.setBounds(500,80,70,40);
		btn1.setBounds(1,480,198,47);
		btn2.setBounds(810,420,198,47);
		btn3.setBounds(810,480,198,47);
		lab1.setBounds(420,150,350,40);
		txf1.setBounds(400,220,220,40);
		cp.add(btn1);
		cp.add(btn2);
		cp.add(btn3);
		cp.add(lab1);
		cp.add(txf1);
		cp.add(lab2);
		cp.add(label);
		jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jf.setVisible(true);
		new Thread(new MyThread()).start();
	}
	class MyThread implements Runnable{
		public void run(){
			while(time>0){
				if(kg==false)break;
				time--;
				lab2.setText(time+"");
				try{
					Thread.sleep(1000);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			kg=false;
			jf.setVisible(false);
			if(time==0)
			new Pro1_1();
		}
	}
	String sqlconnc(String w){
		String p = null;
		  try{
			  Connection dbConn=DriverManager.getConnection(h.dbURL,h.userName,h.userPwd);
			  Statement sql=dbConn.createStatement();
			  String str="SELECT*FROM �˻� WHERE �˻���='"+w+"'";
			  ResultSet rs=sql.executeQuery(str);
			  while(rs.next()){
			  p=rs.getString(3);
			  }
			  dbConn.close();
		  }catch(SQLException e1){
				e1.printStackTrace();
			}
		  return p;
	}
	void sqlconns(){  
		  try{
			  Connection dbConn=DriverManager.getConnection(h.dbURL,h.userName,h.userPwd);
			  Statement sql1=dbConn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			  String z=sqlconnc(s);
			  String str="SELECT*FROM "+z+"";
			  ResultSet rs=sql1.executeQuery(str);
			  rs.last();
			  String a=txf1.getText();
			  float b=Float.parseFloat(a);
			  float f=0;
			  f=b+f;
			  int g;
			  float d=0;
			  float l=0;
			  float y=0;
			  float fl1=rs.getFloat(7);
			  if(f>fl1)
			  {
				  new Pro2_9();
				  txf1.setText(null);
			  }else
			  {
				  fl1=fl1-f;
				  GetTime t=new GetTime();
				  String c=t.mDateTime;
				  g=rs.getInt(1)+1;
				  String Gengstr="('"+g+"','"+c+"','"+d+"','"+f+"','"+y+"','"+l+"','"+fl1+"')";
					String xinstr="INSERT INTO "+z+" VALUES"+Gengstr;
					sql1.executeUpdate(xinstr);
				  sqlconnx();
				  new Pro1_2(s,h);
				  kg=false;
				  jf.setVisible(false);
				  new Pro2_17();
			  }
		  }catch(SQLException e1){
				e1.printStackTrace();
			}
	}
	void sqlconnx(){ 
		  try{
			  Connection dbConn=DriverManager.getConnection(h.dbURL,h.userName,h.userPwd);
			  Statement sql1=dbConn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			  String y=sqlconnc(x);
			  String str="SELECT*FROM "+y+"";
			  ResultSet rs=sql1.executeQuery(str);
			  rs.last();
			  String a=txf1.getText();
			  float b=Float.parseFloat(a);
			  float f=0;
			  f=b+f;
			  int g;
			  float d=0;
			  float z=0;
			  float k=0;
			  float fl1=rs.getFloat(7);
			  fl1=fl1+f;
			  GetTime t=new GetTime();
			   String c=t.mDateTime;
				  g=rs.getInt(1)+1;
				  String Gengstr="('"+g+"','"+c+"','"+f+"','"+d+"','"+k+"','"+z+"','"+fl1+"')";
					String xinstr="INSERT INTO "+y+" VALUES"+Gengstr;
					sql1.executeUpdate(xinstr);
		  }catch(SQLException e1){
				e1.printStackTrace();
			}
	}
	void sqlconnd(){
		String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";

		  String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=����ATMϵͳ";

		  String userName="sa";

		  String userPwd="123456";


		  try{
		   Class.forName(driverName);
		  }
		  catch(Exception e){
		   e.printStackTrace();
		  }   
		  try{
			  Connection dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
			  Statement sql=dbConn.createStatement();
			  String str="SELECT*FROM ����� WHERE �˻���='"+s+"'";
			  ResultSet rs=sql.executeQuery(str);
			  while(rs.next()){
				  boolean b=rs.getBoolean(4);
				  if(b){
					  new Pro1_2(s,h);
					  kg=false;
					  jf.setVisible(false);
					  new Pro2_22();
				  }else
				  {
					  sqlconns();
				  }
			  }
			  dbConn.close();
		  }catch(SQLException e1){
				e1.printStackTrace();
			}
	}
public void actionPerformed(ActionEvent e){
	JButton btn=(JButton)e.getSource();
	if(btn==btn1)
		{new Pro1_4(s,h);
		kg=false;
	     jf.setVisible(false);
		}
	if(btn==btn2)
		{
		txf1.setText(null);
		time=60;
		}
	if(btn==btn3)
	{
		sqlconnd();
	}
	}
}