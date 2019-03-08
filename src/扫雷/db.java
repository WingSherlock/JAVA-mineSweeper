package 扫雷;

import java.sql.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


public class db extends JFrame{
	// �������Ӷ���
	private Connection con;
	// �����ݿ���Ϊ�����������Ӷ���
	public Connection getConnection(String dbname) {
		//SQL Server���ݿ����ز�������
 /* 
      //���ݿ�����������ÿ�����ݿⶼ�ǹ̶���
     String drivername = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
      // ���ݿ�URL
		String url = "jdbc:sqlserver://127.0.0.1:1433;" + "DatabaseName="+dbname;
		String userName = "javase";// ��¼���ݿ��û���
		String password = "javase";// �û�����
*/
		//My SQL���ݿ��������
		String drivername = "com.mysql.jdbc.Driver";
		// ���ݿ�URL����ʽ��jdbc:mysql://���ݿ����ڵ���������IP��ַ:�˿ں�/���ݿ���
		String url = "jdbc:mysql://localhost:3306/"+ dbname;// ���ݿ�URL
		String userName = "cwr";// ��¼���ݿ��û���
		String password = "cwr";// �û�����
	
		try {
			// ����My SQL��������
			Class.forName(drivername);
			//��ȡ��ǰ���ݿ�����Ӷ���
			con =(Connection) DriverManager.getConnection(url, userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;//�������Ӷ���
	}
	
	// ��¼
	public void getDB(String dbname, String sql) {
		try {
			con = getConnection(dbname);//��ȡ��ǰ���ݿ�dbname���ڵ�����
			Statement st = (Statement) con.createStatement();
			//ͨ��stִ�в�ѯ���sql�ַ���������ý����rs����
			ResultSet rs = st.executeQuery(sql);
			System.out.println("����");
			while (rs.next()) {
				String name = rs.getString("name");
				String sorce = rs.getString("sorce");
				String level = rs.getString("level");
				System.out.println(""+name + "\t\t" + sorce + "\t\t" + level );
			}
			rs.close();//�رս����
			st.close();//�رյ�Statement����st
			con.close();//�ر����ݿ������
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void InsertRecords(String dbname, String sql) {// �޸ļ�¼
		try {
			con = getConnection(dbname);
			Statement st = con.createStatement();
			st.executeUpdate(sql);// �õ���Ӱ��ļ�¼��
			
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
		// �����������
		private JScrollPane spTable;

		private JButton bthC;
		// ����Ĭ�ϱ��ģʽ
		private DefaultTableModel model;
		// �������
		private JTable table;
		JFrame jiemian=new JFrame("Winner!");
		void JTableDemo(saolei sl,String dbname, String sql) {
			

			// ����Ĭ�ϱ��ģʽ
			model = new DefaultTableModel();
			// �������
			table = new JTable(model);
			// ���ñ��ѡ��ģʽΪ��һѡ��
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			table.setEnabled(false);

			// ����һ��������壬�������
			spTable = new JScrollPane(table);
			// �����������ӵ���������
			jiemian.add(spTable, BorderLayout.CENTER);

			// ������ť
			bthC = new JButton("����");
			bthC.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					
					sl.bulei();
					jiemian.setVisible(false);
					
				}
			});

			// ��ʢ�Ű�ť�������ӵ�������ϲ������棩
			jiemian.add(bthC, BorderLayout.SOUTH);

			// ��ʼ����ʾ�������
			try {
				showData(dbname,sql);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// �趨���ڴ�С
			jiemian.setSize(500, 400);
			int windowWidth = jiemian.getWidth(); //��ô��ڿ�
			int windowHeight = jiemian.getHeight(); //��ô��ڸ�
			Toolkit kit = Toolkit.getDefaultToolkit(); //���幤�߰�
			Dimension screenSize = kit.getScreenSize(); //��ȡ��Ļ�ĳߴ�
			int screenWidth = screenSize.width; //��ȡ��Ļ�Ŀ�
			int screenHeight = screenSize.height; //��ȡ��Ļ�ĸ�
			jiemian.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//���ô��ھ�����ʾ
			// ���ô��ڿ��ӣ���ʾ��
			jiemian.setVisible(true);
		}

		// ��ʾ�������
		private void showData(String dbname, String sql) throws SQLException {
			con = getConnection(dbname);//��ȡ��ǰ���ݿ�dbname���ڵ�����
			Statement st = (Statement) con.createStatement();
			//ͨ��stִ�в�ѯ���sql�ַ���������ý����rs����
			ResultSet rs = st.executeQuery(sql);
			
			try {
				ResultSetMetaData rsmd = rs.getMetaData();
				// ��ȡ����
				int colCount = rsmd.getColumnCount();
				// �������
				Vector<String> title = new Vector<String>();
				// ����
				title.add("Rank");
				for (int i = 1; i <= colCount; i++) {
					title.add(rsmd.getColumnLabel(i));
				}
				// �������
				Vector<Vector<String>> data = new Vector<Vector<String>>();
				int rowCount = 0;
				while (rs.next()) {
					rowCount++;
					// ������
					Vector<String> rowdata = new Vector<String>();
					rowdata.add(String.valueOf(rowCount));
					for (int i = 1; i <= colCount; i++) {
						rowdata.add(rs.getString(i));
					}
					data.add(rowdata);
				}
				if (rowCount == 0) {
					model.setDataVector(null, title);
				} else {
					model.setDataVector(data, title);
				}
			} catch (Exception ee) {
				System.out.println(ee.toString());
				JOptionPane.showMessageDialog(this, "ϵͳ�����쳣�����������ݿ⡣ϵͳ�����˳�������",
						"����", 0);
			} finally {
				rs.close();//�رս����
				st.close();//�رյ�Statement����st
				con.close();//�ر����ݿ������
			}
		}
	 }
	







