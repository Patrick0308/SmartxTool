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
import cn.smartx.tools.build.entity.Group;
import cn.smartx.tools.build.entity.Role;
import cn.smartx.tools.build.entity.Template;
import cn.smartx.tools.build.entity.User;
import cn.smartx.tools.build.util.BaseException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CreateGroup extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField groupCode_text;
	private JTextField groupName_text;
	private JTextField groupType_text;
	private String removeGroupCode;
	private JTable GroupTable;
	private Object tblTitle[]={"GroupName","Code","Type"};
	private Object tblData[][];
	DefaultTableModel tblModel=new DefaultTableModel(); 
	public void reloadTable() throws BaseException{
		GroupManager tmp = new GroupManager();
		List<Group> groups = tmp.queryGroup();
		int rowNum = groups.size();
		System.out.println(rowNum);
		tblData = new Object[rowNum][3];
		for(int i= 0; i<rowNum; i++){
			tblData[i][0]=groups.get(i).getGroupName();
			tblData[i][1] = groups.get(i).getGroupCode();
			tblData[i][2] = groups.get(i).getGroupType();
		}
		tblModel.setDataVector(tblData,tblTitle);
		this.GroupTable.validate();
		this.GroupTable.repaint();
	}
	/**
	 * Create the dialog.
	 */
	public CreateGroup() {
		setBounds(100, 100, 475, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel panel_1 = new JPanel();
			contentPanel.add(panel_1);
			panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JLabel label = new JLabel("\u7EC4\u540D\uFF1A");
				panel_1.add(label);
			}
			{
				groupName_text = new JTextField();
				groupName_text.setColumns(10);
				panel_1.add(groupName_text);
			}
		}
		{
			JPanel panel_1 = new JPanel();
			contentPanel.add(panel_1);
			{
				JLabel lblcode = new JLabel("\u7EC4Code\uFF1A");
				panel_1.add(lblcode);
			}
			{
				groupCode_text = new JTextField();
				groupCode_text.setColumns(10);
				panel_1.add(groupCode_text);
			}
		}
		{
			JPanel panel_1 = new JPanel();
			contentPanel.add(panel_1);
			{
				JLabel label = new JLabel("\u7EC4\u7C7B\u578B\uFF1A");
				panel_1.add(label);
			}
			{
				groupType_text = new JTextField();
				groupType_text.setColumns(10);
				panel_1.add(groupType_text);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane);
			{
				GroupTable = new JTable(tblModel);
				GroupTable.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						int i=CreateGroup.this.GroupTable.getSelectedRow();
						if(i<0) {
							return;
						}		
						removeGroupCode=CreateGroup.this.tblData[i][1].toString();
						
					}
				});
				try {
					reloadTable();
				} catch (BaseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				scrollPane.setViewportView(GroupTable);
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
						Group group = new Group();
						group.setGroupName(groupName_text.getText());
						group.setGroupCode(groupCode_text.getText());
						group.setGroupType(groupType_text.getText());
						GroupManager groupMangaer;
						try {
							groupMangaer = new GroupManager();
							if(groupMangaer.createGroup(group))
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
							int rowNum  = GroupTable.getRowCount();
							List<Group> grouplist = new ArrayList<Group>();
							for(int i=0; i<rowNum; i++){
								 Group temp = new  Group();
								temp.setGroupName((String) GroupTable.getValueAt(i, 0));
								temp.setGroupCode((String) GroupTable.getValueAt(i, 1));
								temp.setGroupType((String) GroupTable.getValueAt(i, 2));
								grouplist.add(temp);
							}
							GroupManager groupManager;
							try {
								groupManager = new GroupManager();
								groupManager.modifyGroups(grouplist);
								reloadTable();
								JOptionPane.showMessageDialog(null, "修改成功", "成功", JOptionPane.OK_OPTION);
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
							Group group = new Group();
							GroupManager man;
							try {
								man = new GroupManager();
								group.setGroupCode(removeGroupCode);
								man.removeGroup(group);
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
