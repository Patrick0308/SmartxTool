package cn.smartx.tools.build.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;

import cn.smartx.tools.build.control.AppListManager;
import cn.smartx.tools.build.control.AppManager;
import cn.smartx.tools.build.entity.App;
import cn.smartx.tools.build.entity.AppList;
import cn.smartx.tools.build.util.BaseException;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class ChoiceApp extends JDialog {

	private JPanel contentPane;
	private final Action action = new SwingAction();
	private JTable appsTable;
	private Object tblTitle[]={"APPÃû³Æ","FilePath"};
	private Object tblData[][];
	DefaultTableModel tblModel=new DefaultTableModel(); 
	/**
	 * Launch the application.
	 */

	
	public void reloadTable(){
		AppListManager listMan = new AppListManager();
		AppList applist = null;
		List<String> apps = new ArrayList<String>();
		try {
			applist = listMan.getAppsList();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,e.getMessage(),"´íÎó",JOptionPane.ERROR_MESSAGE );
		}
		if(applist!=null){
			apps = applist.getAppsURL();
		}
		int rowNum = apps.size();
		tblData = new Object[rowNum][2];
		for(int i= 0; i<rowNum; i++){
			tblData[i][0]=(new File(apps.get(i))).getName();
			tblData[i][1]=apps.get(i);
		}
		tblModel.setDataVector(tblData,tblTitle);
		this.appsTable.validate();
		this.appsTable.repaint();
	}
	/**
	 * Create the frame.
	 */
	public ChoiceApp() {
		setTitle("\u9009\u62E9\u8F6F\u4EF6");
		setBounds(100, 100, 382, 319);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		appsTable = new JTable(tblModel);
		appsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int i=ChoiceApp.this.appsTable.getSelectedRow();
				if(i<0) {
					return;
				}
				String url =ChoiceApp.this.tblData[i][1].toString();
				AppManager.nowAppURL = url;
			}
		});
		reloadTable();
		scrollPane.setViewportView(appsTable);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton CreateButton = new JButton("\u521B\u5EFA\u8F6F\u4EF6");
		CreateButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				JDialog createApp = new CreateApp(ChoiceApp.this);
				createApp.setVisible(true);
			}
		});
		
		JButton ImportAppButton = new JButton("\u5BFC\u5165\u8F6F\u4EF6");
		ImportAppButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ImportApp(ChoiceApp.this).setVisible(true);
			}
		});
		
		JButton RemoveButton = new JButton("\u5220\u9664\u8F6F\u4EF6");
		RemoveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(AppManager.nowAppURL != null){
					try {
						new AppListManager().removeAppURL(AppManager.nowAppURL);
						AppManager.nowAppURL = null;
					} catch (BaseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, e1.getMessage(), "´íÎó", JOptionPane.ERROR_MESSAGE);
					}
					reloadTable();
				}else{
					JOptionPane.showMessageDialog(null, "ÇëÑ¡ÔñÈí¼þ", "´íÎó", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(RemoveButton);
		panel.add(ImportAppButton);
		panel.add(CreateButton);
		
		JButton OKButton = new JButton("\u786E\u5B9A");
		OKButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(AppManager.nowAppURL != null){
					setVisible(false);
					MainPage main = new MainPage(ChoiceApp.this);
					main.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "ÇëÑ¡ÔñÈí¼þ", "´íÎó", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(OKButton);
		this.validate();
		setVisible(true);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
