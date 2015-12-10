package cn.smartx.tools.build.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTree;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import cn.smartx.tools.build.control.AppManager;
import cn.smartx.tools.build.control.MenuManager;
import cn.smartx.tools.build.entity.Menu;
import cn.smartx.tools.build.util.BaseException;

public class ModifyMenu extends JDialog {
	private JTextField menuName_text;
	private JTextField menuCode_text;
	private JTextField menuURL_text;
	private MenuManager man = null;

	/**
	 * Create the dialog.
	 */
	public ModifyMenu(JFrame f) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton Cancel = new JButton("\u53D6\u6D88");
				Cancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				{
					JButton modifyButton = new JButton("\u4FEE\u6539");
					modifyButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							Menu newMenu = new Menu();
							newMenu.setMenuTitle(menuName_text.getText());
							newMenu.setMenuURL(menuURL_text.getText());
							newMenu.setMenuCode(menuCode_text.getText());
							MenuManager man;
							try {
								man = new MenuManager();
								man.modifyMenu(((MainPage)f).selectedMenuTitle,((MainPage)f).selectedMenuLevel, newMenu);
							} catch (BaseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							((MainPage)f).refreshTree();
							setVisible(false);
						}
					});
					modifyButton.setActionCommand("OK");
					buttonPane.add(modifyButton);
				}
				Cancel.setActionCommand("Cancel");
				buttonPane.add(Cancel);
				getRootPane().setDefaultButton(Cancel);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				{
					JLabel label = new JLabel("\u83DC\u5355\u540D\uFF1A");
					panel_1.add(label);
				}
				{
					menuName_text = new JTextField();
					menuName_text.setColumns(10);
					
					panel_1.add(menuName_text);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				{
					JLabel label = new JLabel("Code:");
					panel_1.add(label);
				}
				{
					menuCode_text = new JTextField();
					menuCode_text.setColumns(10);
					panel_1.add(menuCode_text);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				{
					JLabel label = new JLabel("URL:");
					panel_1.add(label);
				}
				{
					menuURL_text = new JTextField();
					menuURL_text.setColumns(32);
					panel_1.add(menuURL_text);
				}
			}
			try {
				man= new MenuManager();
			} catch (BaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			menuName_text.setText(((MainPage)f).selectedMenuTitle);
			Menu menu = man.queryMenuByTitle(((MainPage)f).selectedMenuTitle,((MainPage)f).selectedMenuLevel );
			menuCode_text.setText(menu.getMenuCode());
			menuURL_text.setText(menu.getMenuURL());
		}
	}

}
