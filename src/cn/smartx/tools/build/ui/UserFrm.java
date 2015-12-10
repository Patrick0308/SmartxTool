package cn.smartx.tools.build.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import cn.smartx.tools.build.control.AppManager;
import cn.smartx.tools.build.control.GroupManager;
import cn.smartx.tools.build.control.UserManager;
import cn.smartx.tools.build.entity.Group;
import cn.smartx.tools.build.entity.User;
import cn.smartx.tools.build.util.BaseException;

import javax.swing.JPopupMenu;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;

public class UserFrm extends JDialog {
	private JTable userTbl;
	private String[] tblTitle={"用户名","登录名","用户密码"};
	private Object tblData[][];
	DefaultTableModel tblModel=new DefaultTableModel(); 

	public void reloadTable() throws BaseException{
		UserManager tmp = new UserManager();
		List<User> appGroups = tmp.queryUser();
		int rowNum = appGroups.size();
		tblData = new Object[rowNum][4];
		for(int i= 0; i<rowNum; i++){
			tblData[i][0]=appGroups.get(i).getUserName();
			tblData[i][1]=appGroups.get(i).getUserLoginName();
			tblData[i][2]=appGroups.get(i).getPassword();
		}
		tblModel.setDataVector(tblData,tblTitle);
		this.userTbl.validate();
		this.userTbl.repaint();
	}

	/**
	 * Create the dialog.
	 */
	public UserFrm() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton createUserButton = new JButton("\u65B0\u5EFA\u7528\u6237");
				createUserButton.setActionCommand("Cancel");
				buttonPane.add(createUserButton);
			}
			{
				JButton okButton = new JButton("\u786E\u5B9A");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			{
				JPopupMenu popupMenu = new JPopupMenu();
				addPopup(scrollPane, popupMenu);
				{
					JMenuItem menuItem = new JMenuItem("\u5220\u9664");
					popupMenu.add(menuItem);
				}
			}
			{
				userTbl = new JTable();
				scrollPane.setViewportView(userTbl);
				try {
					reloadTable();
				} catch (BaseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
