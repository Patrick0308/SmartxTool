package cn.smartx.tools.build.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import cn.smartx.tools.build.control.AppManager;
import cn.smartx.tools.build.control.GroupManager;
import cn.smartx.tools.build.control.RoleManager;
import cn.smartx.tools.build.control.UserManager;
import cn.smartx.tools.build.entity.App;
import cn.smartx.tools.build.entity.Group;
import cn.smartx.tools.build.entity.Role;
import cn.smartx.tools.build.entity.User;
import cn.smartx.tools.build.util.BaseException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JInternalFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreateRole extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField roleName_text;
	private JTextField roleMemo_text;
	private String removeRoleName;
	private JTable RoleTable;
	private Object tblTitle[]={"Name","Memo"};
	private Object tblData[][];
	DefaultTableModel tblModel=new DefaultTableModel(); 

	public void reloadTable() throws BaseException{
		RoleManager tmp = new RoleManager();
		List<Role> roles = tmp.queryRole();
		int rowNum = roles.size();
		tblData = new Object[rowNum][2];
		for(int i= 0; i<rowNum; i++){
			tblData[i][0]=roles.get(i).getRoleName();
			tblData[i][1] = roles.get(i).getRoleMemo();
		}
		tblModel.setDataVector(tblData,tblTitle);
		this.RoleTable.validate();
		this.RoleTable.repaint();
	}

	/**
	 * Create the dialog.
	 */
	public CreateRole() {
		setBounds(100, 100, 475, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			{
				JLabel label = new JLabel("\u89D2\u8272\u540D\uFF1A");
				panel.add(label);
			}
			{
				roleName_text = new JTextField();
				roleName_text.setColumns(10);
				panel.add(roleName_text);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JLabel lblMemo = new JLabel("Memo\uFF1A");
				panel.add(lblMemo);
			}
			{
				roleMemo_text = new JTextField();
				roleMemo_text.setColumns(10);
				panel.add(roleMemo_text);
			}
		}
		
		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane);
		
		RoleTable = new JTable(tblModel);
		RoleTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int i=CreateRole.this.RoleTable.getSelectedRow();
				if(i<0) {
					return;
				}
				removeRoleName=CreateRole.this.tblData[i][0].toString();
				
			}
		});
		try {
			reloadTable();
		} catch (BaseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		scrollPane.setViewportView(RoleTable);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton createButton = new JButton("\u65B0\u5EFA");
				createButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Role role = new Role();
						role.setRoleName(roleName_text.getText());
						role.setRoleMemo(roleMemo_text.getText());
						RoleManager roleMangaer;
						try {
							roleMangaer = new RoleManager();
							if(roleMangaer.createRole(role))
								JOptionPane.showMessageDialog(null, "新建成功", "成功", JOptionPane.OK_OPTION);
							else
								JOptionPane.showMessageDialog(null, "新建失败，可能是code重复", "错误", JOptionPane.ERROR_MESSAGE);
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
							int rowNum  = RoleTable.getRowCount();
							List<Role> rolelist = new ArrayList<Role>();
							for(int i=0; i<rowNum; i++){
								 Role temp = new  Role();
								temp.setRoleName((String) RoleTable.getValueAt(i, 0));
								temp.setRoleMemo((String) RoleTable.getValueAt(i, 1));
								rolelist.add(temp);
							}
							RoleManager roleManager;
							try {
								roleManager = new RoleManager();
								roleManager.modifyRoles(rolelist);
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
						public void actionPerformed(ActionEvent e) {
							Role role = new Role();
							RoleManager man;
							try {
								man = new RoleManager();
								role.setRoleName(removeRoleName);
								man.removeRole(role);
								reloadTable();
							} catch (BaseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
							} 
							
						}
					});
					buttonPane.add(removeButton);
				}
				
			}
			{
				JButton cancelButton = new JButton("\u53D6\u6D88");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
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
