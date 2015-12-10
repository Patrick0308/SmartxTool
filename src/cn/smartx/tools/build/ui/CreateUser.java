package cn.smartx.tools.build.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;

import cn.smartx.tools.build.control.AppManager;
import cn.smartx.tools.build.control.GroupManager;
import cn.smartx.tools.build.control.UserManager;
import cn.smartx.tools.build.entity.Group;
import cn.smartx.tools.build.entity.User;
import cn.smartx.tools.build.util.BaseException;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CreateUser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField password_text;
	private JTextField userName_text;
	private JTextField userLogin_text;
	private JTable UserTable;
	private String removeUserLoginName;

	private Object tblTitle[]={"UserName","LoginName","Password"};
	private Object tblData[][];
	DefaultTableModel tblModel=new DefaultTableModel(); 

	public void reloadTable() throws BaseException{
		UserManager tmp = new UserManager();
		List<User> users = tmp.queryUser();
		int rowNum = users.size();
		tblData = new Object[rowNum][3];
		for(int i= 0; i<rowNum; i++){
			tblData[i][0]=users.get(i).getUserName();
			tblData[i][1] = users.get(i).getUserLoginName();
			tblData[i][2] = users.get(i).getPassword();
		}
		tblModel.setDataVector(tblData,tblTitle);
		this.UserTable.validate();
		this.UserTable.repaint();
	}

	/**
	 * Create the dialog.
	 */
	public CreateUser() {
		setBounds(100, 100, 475, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			{
				JLabel label = new JLabel("\u7528\u6237\u540D\uFF1A");
				panel.add(label);
			}
			{
				userName_text = new JTextField();
				userName_text.setColumns(10);
				panel.add(userName_text);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JLabel label = new JLabel("\u767B\u5F55\u540D\uFF1A");
				panel.add(label);
			}
			{
				userLogin_text = new JTextField();
				userLogin_text.setColumns(10);
				panel.add(userLogin_text);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			{
				JLabel lbll = new JLabel("\u5BC6\u7801\uFF1A");
				panel.add(lbll);
			}
			{
				password_text = new JTextField();
				panel.add(password_text);
				password_text.setColumns(32);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane);
			{
				UserTable = new JTable(tblModel);
				UserTable.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						int i=CreateUser.this.UserTable.getSelectedRow();
						if(i<0) {
							return;
						}		
						removeUserLoginName=CreateUser.this.tblData[i][1].toString();
						
					}
				});
				try {
					reloadTable();
				} catch (BaseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				scrollPane.setViewportView(UserTable);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton createButton = new JButton("\u65B0\u5EFA");
				createButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						User user = new User();
						user.setUserName(userName_text.getText());
						user.setUserLoginName(userLogin_text.getText());
						user.setPassword(password_text.getText());
						UserManager userMangaer;
						try {
							userMangaer = new UserManager();
							if(userMangaer.createUser(user))
								JOptionPane.showMessageDialog(null, "新建成功", "成功", JOptionPane.OK_OPTION);
							else
								JOptionPane.showMessageDialog(null, "新建失败 ,可能是code重复", "错误", JOptionPane.ERROR_MESSAGE);
							reloadTable();
						} catch (BaseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
						}
					
					}
				});
				createButton.setActionCommand("OK");
				buttonPane.add(createButton);
				getRootPane().setDefaultButton(createButton);
				{
					JButton modifyButton = new JButton("\u4FEE\u6539");
					modifyButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int rowNum  = UserTable.getRowCount();
							List<User> userlist = new ArrayList<User>();
							for(int i=0; i<rowNum; i++){
								 User temp = new  User();
								temp.setUserName((String) UserTable.getValueAt(i, 0));
								temp.setUserLoginName((String) UserTable.getValueAt(i, 1));
								temp.setPassword((String) UserTable.getValueAt(i, 2));
								userlist.add(temp);
							}
							UserManager userManager;
							try {
								userManager = new UserManager();
								userManager.modifyUsers(userlist);
								JOptionPane.showMessageDialog(null, "修改成功", "成功", JOptionPane.OK_OPTION);
								reloadTable();
							} catch (BaseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
							}
						}
					});
					buttonPane.add(modifyButton);
				}
				{
					JButton removeButton = new JButton("\u5220\u9664");
					removeButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							User user = new User();
							UserManager man;
							try {
								man = new UserManager();
								user.setUserLoginName(removeUserLoginName);
								man.removeUser(user);
								reloadTable();
							} catch (BaseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
							} 
						}
					});
					buttonPane.add(removeButton);
				}
			}
			{
				JButton cancelButton = new JButton("\u53D6\u6D88");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
