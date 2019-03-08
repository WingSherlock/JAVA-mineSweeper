

import java.awt.*;
import java.awt.event.*;
import java.sql.Time;
import java.text.SimpleDateFormat;

import javax.swing.*;
/********锟斤拷*********/
class lei extends JButton {
	int weizhix, weizhiy; // 锟节硷拷
	int leizhouweishu; // 锟斤拷围锟斤拷锟斤拷
	boolean leime; // 锟角凤拷为锟斤拷
	boolean dianji; // 锟角否被碉拷锟�
	boolean Flag; // 锟角凤拷锟斤拷锟�
	int rightci; // 锟揭硷拷锟斤拷锟斤拷

	public lei(int x, int y) {
		weizhix = x;
		weizhiy = y;
		rightci = 0;
		leizhouweishu = 9;
		leime = false;
		dianji = false;
		Flag = false;
	}
}
/******时锟斤拷*********/
class clock extends Thread{
	saolei sl;
	clock(saolei sl){
		this.sl=sl;
	}
	public void run(){
		long i=0;
		Time t=new Time(0);
		while(true){
			SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
			sl.jishi.setForeground(Color.red);
			String s=sdf.format(t);
			sl.jishi.setText(s);
			i+=1000L;
			t.setTime(i);
			try {
				Thread.sleep(1000);						
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class saolei extends JFrame {
	JLabel leishuLable,jishi,shijian;
	int leishu, shenglei, zhaodao; // 锟斤拷前锟斤拷锟斤拷锟斤拷剩锟斤拷锟斤拷锟斤拷锟斤拷锟揭碉拷锟斤拷锟斤拷
	int kuaishu, shengkuai; // 锟杰匡拷锟斤拷锟斤拷锟斤拷前锟斤拷锟斤拷
	JPanel MenuPanel = new JPanel();
	JPanel leiPanel = new JPanel();
	lei[][] leiButton;
	BorderLayout bianjie = new BorderLayout();
	GridLayout wangge = new GridLayout();
	JMenuBar caidanlan=new JMenuBar();
	JMenu youxi=new JMenu("锟斤拷戏锟斤拷G锟斤拷");
	JMenu Help=new JMenu("锟斤拷锟斤拷锟斤拷H锟斤拷");
	JMenuItem newGame=new JMenuItem("锟斤拷锟斤拷戏");
	JMenuItem xuanxiang=new JMenuItem("选锟斤拷");
	JMenuItem tuichu=new JMenuItem("锟剿筹拷");
	clock t = null;
	static String level = "easy";//默锟斤拷值
	
	
	/**
	 * @wbp.parser.constructor
	 */
	public saolei(int c,int r,int kuaishu,int leishu){
		this.kuaishu=kuaishu;
		this.leishu=leishu;
		setVisible(true); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Init();
		setSize(66*c, 66*r);//
			
		juzhong(this);
	}
	
	public saolei(String level){
		if(level.equals("easy")){
			new saolei(9,9,81,10);
			
		}else if(level.equals("soso")){
			new saolei(16,16,256,40);
			
		}else if(level.equals("hard")){
			new saolei(18,20,480,99);
		}
	}
	
	/*****使锟斤拷锟节撅拷锟斤拷********/
	public void juzhong(JFrame f){
		int windowWidth = f.getWidth(); //锟斤拷么锟斤拷诳锟�
		int windowHeight = f.getHeight(); //锟斤拷么锟斤拷诟锟�
		Toolkit kit = Toolkit.getDefaultToolkit(); //锟斤拷锟藉工锟竭帮拷
		Dimension screenSize = kit.getScreenSize(); //锟斤拷取锟斤拷幕锟侥尺达拷
		int screenWidth = screenSize.width; //锟斤拷取锟斤拷幕锟侥匡拷
		int screenHeight = screenSize.height; //锟斤拷取锟斤拷幕锟侥革拷
		f.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//锟斤拷锟矫达拷锟节撅拷锟斤拷锟斤拷示
	}

	/********* 锟斤拷始锟斤拷 *******************/
	private void Init() {
		youxi.add(newGame);
		youxi.add(xuanxiang);
		youxi.addSeparator();
		youxi.add(tuichu);
		caidanlan.add(youxi);
		caidanlan.add(Help);
		newGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				bulei();
				
			}
		});
		xuanxiang.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JFrame xuanxiangkou=new JFrame("选锟斤拷");
				xuanxiangkou.setVisible(true); 
				xuanxiangkou.setSize(500,100);
				juzhong(xuanxiangkou);
				
				xuanxiangkou.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
				JLabel nandu=new JLabel("          锟窖讹拷 :        ");
				JButton easy=new JButton("锟斤拷");
				easy.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						kuaishu = 81;
						leishu = 10;
						setVisible(false); 
						xuanxiangkou.setVisible(false); 
						level = "easy";
						
						new saolei("easy");
						
					}
				});
				JButton soso=new JButton("一锟斤拷");
				soso.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						kuaishu = 256;
						leishu = 40;
						setVisible(false); 
						xuanxiangkou.setVisible(false); 
						level = "ordinary";
						
						new saolei("soso");
				
					}
				});
				JButton hard=new JButton("锟斤拷锟斤拷");
				hard.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						kuaishu = 480;
						leishu = 99;
						setVisible(false); 
						xuanxiangkou.setVisible(false); 
						level = "hard";
						new saolei("hard");
					}
				});
				JButton zidingyi=new JButton("锟皆讹拷锟斤拷");
				zidingyi.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						JFrame ziding=new JFrame("锟皆讹拷锟斤拷");
						ziding.setSize(500,200);
						ziding.setVisible(true);
						juzhong(ziding);
						ziding.getContentPane().setLayout(new GridLayout(4,4));
						
						JLabel leishulabel=new JLabel("锟斤拷锟斤拷:");
						JTextField leitext=new JTextField(2);
						JPanel leishupanel=new JPanel();
						leishupanel.add(leishulabel);
						leishupanel.add(leitext);
						JLabel kuaishulabel=new JLabel("锟斤拷锟斤拷:");
						JTextField kuaitext=new JTextField(2);
						JPanel kuaishupanel=new JPanel();
						kuaishupanel.add(kuaishulabel);
						kuaishupanel.add(kuaitext);
						JLabel changlabel=new JLabel("锟斤拷锟斤拷:");
						JTextField changtext=new JTextField(2);
						JPanel changpanel=new JPanel();
						changpanel.add(changlabel);
						changpanel.add(changtext);
						JLabel kuanlabel=new JLabel("锟斤拷锟�:");
						JTextField kuantext=new JTextField(2);
						JPanel kuanpanel=new JPanel();
						kuanpanel.add(kuanlabel);
						kuanpanel.add(kuantext);
						JButton start=new JButton("Strat");						
						
						ziding.getContentPane().add(leishupanel);
						ziding.getContentPane().add(kuaishupanel);
						ziding.getContentPane().add(changpanel);
						ziding.getContentPane().add(kuanpanel);
						ziding.getContentPane().add(start);
						
						start.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								setVisible(false); 
								xuanxiangkou.setVisible(false); 
								ziding.setVisible(false);
								level = "user-defined";
								new saolei(Integer.parseInt(changtext.getText()),
										Integer.parseInt(kuantext.getText()),
										Integer.parseInt(kuaitext.getText()),
										Integer.parseInt(leitext.getText()));
								
							}
						});

					}
				});
				
				xuanxiangkou.getContentPane().add(nandu);
				xuanxiangkou.getContentPane().add(easy);
				xuanxiangkou.getContentPane().add(soso);
				xuanxiangkou.getContentPane().add(hard);
				xuanxiangkou.getContentPane().add(zidingyi);
				
				
				
				
			}
		});
		tuichu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0); 
			}
		});
		JPanel zong = (JPanel) getContentPane();
		setTitle("扫锟斤拷");
		
		zong.setLayout(bianjie);
		
		leishuLable = new JLabel("      锟斤拷前锟斤拷锟斤拷" + ":" + leishu);
		jishi=new JLabel();
		shijian=new JLabel("Time:");
		
		MenuPanel.add(leishuLable);
		leiPanel.setLayout(wangge);
		wangge.setColumns((int) Math.sqrt(kuaishu));
		wangge.setRows((int) Math.sqrt(kuaishu));
		leiButton = new lei[(int) Math.sqrt(kuaishu)][(int) Math.sqrt(kuaishu)];
		for (int i = 0; i < (int) Math.sqrt(kuaishu); i++) {
			for (int j = 0; j < (int) Math.sqrt(kuaishu); j++) {
				leiButton[i][j] = new lei(i, j);
				leiButton[i][j].setFont(new Font("", Font.PLAIN, 14));// 锟斤拷锟斤拷锟斤拷锟斤拷锟叫�

				leiButton[i][j].setForeground(Color.white);
				leiButton[i][j].addMouseListener(new youjian(this));
				leiButton[i][j].addActionListener(new zuojian(this));
				leiPanel.add(leiButton[i][j]);
			}
		}
		JPanel p=new JPanel();
		p.add(shijian);
		p.add(jishi);
		p.add(MenuPanel);
		zong.add(leiPanel,"Center");
		zong.add(p,"South");
		zong.add(caidanlan,"North");
		bulei();
	}





	/*******锟斤拷锟斤拷***********/
	@SuppressWarnings("deprecation")
	public void bulei() {
		leishuLable.setText("锟斤拷前锟斤拷锟斤拷" + "锟斤拷" + leishu);
		for (int i = 0; i < (int) Math.sqrt(kuaishu); i++) {
			for (int j = 0; j < (int) Math.sqrt(kuaishu); j++) {
				leiButton[i][j].leime = false;
				leiButton[i][j].dianji = false;
				leiButton[i][j].Flag = false;
				leiButton[i][j].rightci = 0;
				leiButton[i][j].leizhouweishu = 9;
				leiButton[i][j].setEnabled(true);
				leiButton[i][j].setText("");
				leiButton[i][j].setFont(new Font("", Font.PLAIN, 14));// 锟斤拷锟斤拷锟斤拷锟斤拷锟叫�
				leiButton[i][j].setForeground(Color.BLUE);
				zhaodao = 0;
				shenglei = leishu;
				shengkuai = kuaishu - leishu;
			}
		}
		for (int i = 0; i < leishu;) {
			int x = (int) (Math.random() * (int) (Math.sqrt(kuaishu) - 1));
			int y = (int) (Math.random() * (int) (Math.sqrt(kuaishu) - 1));
			if (leiButton[x][y].leime != true) {
				leiButton[x][y].leime = true;
				i++;
			}
		}
		for (int i = 0; i < (int) Math.sqrt(kuaishu); i++) {
			for (int j = 0; j < (int) Math.sqrt(kuaishu); j++) {
				int count = 0;
				if (leiButton[i][j].leime != true) {
					for (int x = i - 1; x < i + 2; x++) {
						for (int y = j - 1; y < j + 2; y++) {
							if ((x >= 0) && (y >= 0) && (x < ((int) Math.sqrt(kuaishu)))
									&& (y < ((int) Math.sqrt(kuaishu)))) {
								if (leiButton[x][y].leime == true) {
									count++;
								}
							}
						}
					}
					leiButton[i][j].leizhouweishu = count;
				}
			}
		}
		if(t!=null){
			t.stop();
		}
		t = new clock(this);
		t.start();
	}

	/****** 锟角凤拷赢锟斤拷 ********/
	public void isWin() {
		shengkuai = kuaishu - leishu;
		for (int i = 0; i < (int) Math.sqrt(kuaishu); i++) {
			for (int j = 0; j < (int) Math.sqrt(kuaishu); j++) {
				if (leiButton[i][j].dianji == true) {
					shengkuai--;
				}
			}
		}
		if (zhaodao == leishu || shengkuai == 0) {// 锟斤拷锟斤拷全锟斤拷锟斤拷锟斤拷剩锟铰匡拷锟斤拷为0
			t.stop();
			String ming=JOptionPane.showInputDialog(null,
					"锟斤拷锟斤拷锟斤拷全锟斤拷锟斤拷锟斤拷\n锟斤拷时"+jishi.getText()+"\n锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�","锟斤拷喜锟斤拷锟斤拷锟斤拷",
					JOptionPane.INFORMATION_MESSAGE);
			
			 db dbq=new db();//锟斤拷锟斤拷DBQuery锟斤拷亩锟斤拷锟�
	 		 String dbname = "rank";//锟斤拷锟藉当前锟斤拷锟捷匡拷锟斤拷
	 		String sql = "insert into rank values('"+ming+"','"+jishi.getText()+"','"+level+"')";
	 		String sql1="select * from rank  where level='" + level + "' order by sorce asc limit 1,10";
	 		dbq.InsertRecords(dbname, sql);// 写锟斤拷锟斤拷锟捷匡拷
	 		try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 		dbq.getDB(dbname, sql1);//锟斤拷示前十锟斤拷
	 		dbq.JTableDemo(this,dbname, sql1);
			//bulei();
		}
	}

	/******* 锟斤拷锟斤拷锟阶碉拷时锟斤拷 锟斤拷锟斤拷锟斤拷围锟斤拷锟斤拷 *********/
	public void kong(lei ClickedButton) {
		int i, j;
		i = ClickedButton.weizhix;
		j = ClickedButton.weizhiy;
		for (int x = i - 1; x < i + 2; x++) {
			for (int y = j - 1; y < j + 2; y++) {
				if (((x != i) || (y != j)) && (x >= 0) && (y >= 0) && (x < ((int) Math.sqrt(kuaishu))) && (y < ((int) Math.sqrt(kuaishu)))) {
					if (leiButton[x][y].leime == false && leiButton[x][y].dianji == false && leiButton[x][y].Flag == false) {
						fankai(leiButton[x][y]);
					}
				}
			}
		}
	}

	/****** 锟斤拷锟斤拷 **********/
	public void fankai(lei ClickedButton) {
		ClickedButton.setEnabled(false);// 锟斤拷锟斤拷
		ClickedButton.dianji = true;
		if (ClickedButton.leizhouweishu > 0) {
			ClickedButton.setText(ClickedButton.leizhouweishu + "");
		} else {
			kong(ClickedButton);
		}
	}

	/******* 锟斤拷锟斤拷锟斤拷 **********/
	public static void main(String[] args) {
		 new saolei(9,9,81,10);
		
	}

	/******* 锟斤拷锟� *********/
	public void actionPerformed(ActionEvent e) {
		if (((lei) e.getSource()).dianji == false && ((lei) e.getSource()).Flag == false) {
			if (((lei) e.getSource()).leime == false) {
				fankai(((lei) e.getSource()));
				isWin();
			} else {
				for (int i = 0; i < (int) Math.sqrt(kuaishu); i++) {
					for (int j = 0; j < (int) Math.sqrt(kuaishu); j++) {
						if (leiButton[i][j].leime == true) {
							leiButton[i][j].setForeground(Color.RED);
							leiButton[i][j].setText("L");
						}
					}
				}
				((lei) e.getSource()).setForeground(Color.RED);
				((lei) e.getSource()).setFont(new Font("", Font.BOLD, 20));
				((lei) e.getSource()).setText("X");
				JOptionPane.showMessageDialog(this, "锟饺碉拷锟斤拷锟斤拷锟剿ｏ拷锟斤拷锟斤拷确锟斤拷锟斤拷锟铰匡拷始", "锟斤拷锟脚猴拷锟斤拷锟斤拷", 2);
				bulei();
			}
		}
	}

	/***** 锟揭硷拷 *********/
	public void mouseClicked(MouseEvent e) {
		lei leiSource = (lei) e.getSource();
		if ((SwingUtilities.isRightMouseButton(e) == true) && (leiSource.dianji == false)) {
			leiSource.rightci = (leiSource.rightci + 1) % 3;
			if (leiSource.rightci == 1) {
				if (shenglei > 0) {
					leiSource.setForeground(Color.RED);
					leiSource.setText("F");
					leiSource.Flag = true;
					shenglei--;
					zhaodao++;
				} else {
					leiSource.rightci = 0;
				}
			} else if (leiSource.rightci == 2) {
				shenglei++;
				zhaodao--;
				leiSource.setText("Q");
				leiSource.Flag = false;
			} else {
				leiSource.setText("");
			}
			leishuLable.setText("锟斤拷前锟斤拷锟斤拷" + ":" + shenglei);
			isWin();
		}
	}
	
}

class zuojian implements ActionListener {
	private saolei temp;

	zuojian(saolei temp) {
		this.temp = temp;
	}

	public void actionPerformed(ActionEvent e) {
		temp.actionPerformed(e);
	}
}

class youjian extends MouseAdapter {
	private saolei temp;

	youjian(saolei temp) {
		this.temp = temp;
	}

	public void mouseClicked(MouseEvent e) {
		temp.mouseClicked(e);
	}
}

	
	

