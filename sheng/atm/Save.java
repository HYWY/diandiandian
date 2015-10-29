package atm;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Save extends JFrame implements ActionListener{

	Container cp;
	private JLabel showchecklab;
	private JLabel tipslab;
	private JTextField iputtf;
	private JButton cancelbtn;
	private JButton ackbtn;
	static double savemoney;
	public Save(){
		super("SaveInterface");
		cp =getContentPane();
		cp.setLayout(new GridBagLayout());
		
		showchecklab = new JLabel("����볮Ʊ");
		showchecklab.setFont(new Font("����",Font.BOLD,25));
		
		tipslab = new JLabel("<html>������볮Ʊ�������Ʒ<br>�˹�Ա��ֻ������������Ϊ100��ֽ��<br>���δ���㻹�ɷ���100�ų�Ʊ<br>����"
				+ "��������ѣ�������ؽ��װ����й����׼�շ�</html>");
		tipslab.setFont(new Font("����",Font.BOLD,15));
		
		iputtf = new JTextField();
		cancelbtn = new JButton("ȡ��");
		cancelbtn.addActionListener(this);
		ackbtn = new JButton("ȷ��");
		ackbtn.addActionListener(this);
		cp.add(showchecklab,new GridBagConstraints(0,0,3,1,1,1,
				GridBagConstraints.SOUTH,GridBagConstraints.NONE,new Insets(130, 0, 0, 0),0,0));

		cp.add(iputtf,new GridBagConstraints(0,2,3,1,1,1,
				GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(10, 0, -10, 0),100,10));//�±߾���Ϊ-5������

		cp.add(tipslab,new GridBagConstraints(0,5,2,1,1,1,
				GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0, 40, 10, 0),0,0));
		cp.add(cancelbtn,new GridBagConstraints(2,6,1,1,1,1,
				GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(0, 0, 10, 0),0,0));
		cp.add(ackbtn,new GridBagConstraints(2,5,1,1,1,1,
				GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(0, 0, 10, 0),0,0));
		ImageIcon ii = new ImageIcon("src/background.jpg");
		JLabel bdlab = new JLabel(ii);
		bdlab.setBounds(0,0,ii.getIconWidth(),ii.getIconHeight());
		this.getLayeredPane().add(bdlab,new Integer(Integer.MIN_VALUE));
		JPanel jp = (JPanel)cp;
		jp.setOpaque(false);
        
		setBounds(300, 100, ii.getIconWidth(), ii.getIconHeight());
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
	
		new Save();
	}

	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		if(btn == cancelbtn){
			new UserInterface();
			this.dispose();
		}
		if(btn == ackbtn){
			if((int)Double.parseDouble(iputtf.getText())%100==0){
				try {
					Statement state = Connect.dbConn.createStatement();
					String sql2="select balance from userMessage where account = '"+LoadInterface.account+"'";
					ResultSet rs=state.executeQuery(sql2);
					rs.next();
					double balance=rs.getDouble(1);
					double balance1=balance+Double.parseDouble(iputtf.getText());
					String sql = "update userMessage set balance ='"+balance1+"' where account ='"+LoadInterface.account+"'";
	                int a= state.executeUpdate(sql);
	                Date now = new Date();
	                SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd");
	                if(a>0){
	                	Statement state1 = Connect.dbConn.createStatement();
						String sql1 = "insert into trade (tradeDate,tradeMoney,remark,account)values('"+matter.format(now)+"','"+Double.parseDouble(iputtf.getText())+"','����','"+LoadInterface.account+"')";
		                int a1= state1.executeUpdate(sql1);
		                if(a1>0){
		                	savemoney=Double.parseDouble(iputtf.getText());
		                	new ShowSaveDetail2();
		        			this.dispose();
		                }
		                else{
		                	JOptionPane.showMessageDialog(this, "���ʧ��");
		                }
		                state1.close();
	                }
	                else{
	                	JOptionPane.showMessageDialog(this, "���ʧ��");
	                }
	                state.close();
	              
				} catch (SQLException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
				
			}
			else{
				JOptionPane.showMessageDialog(this, "������100��������");
			}
			
		}
		
	}

}
