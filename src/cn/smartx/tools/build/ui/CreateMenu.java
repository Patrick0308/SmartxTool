package cn.smartx.tools.build.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.JSplitPane;
import javax.swing.JInternalFrame;

import java.awt.Dimension;

import javax.swing.JTree;

import java.awt.Cursor;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;

import cn.smartx.tools.build.control.AppManager;
import cn.smartx.tools.build.control.MenuManager;
import cn.smartx.tools.build.entity.Menu;
import cn.smartx.tools.build.util.BaseException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.awt.Font;

public class CreateMenu extends JDialog {
	private JTextField MenuTitleField;
	private JTextField MenuCodeField;
	private JTextField MenuURLField;
	private String MenuPM ;
	private int FaMenuLevel;
	
	/**
	 * Create the dialog.
	 */
	public CreateMenu(JFrame f) {
		setTitle("\u521B\u5EFA\u83DC\u5355");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel contentPanel = new JPanel();
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			{
				JPanel panel = new JPanel();
				FlowLayout flowLayout = (FlowLayout) panel.getLayout();
				contentPanel.add(panel);
				{
					JLabel label = new JLabel("\u83DC\u5355\u540D\uFF1A");
					panel.add(label);
				}
				{
					MenuTitleField = new JTextField();
					panel.add(MenuTitleField);
					MenuTitleField.setColumns(10);
				}
			}
			{
				JPanel panel = new JPanel();
				contentPanel.add(panel);
				panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				{
					JLabel lblCode = new JLabel("Code:");
					panel.add(lblCode);
				}
				{
					MenuCodeField = new JTextField();
					panel.add(MenuCodeField);
					MenuCodeField.setColumns(10);
				}
			}
			{
				JPanel panel = new JPanel();
				contentPanel.add(panel);
				{
					JLabel lblUrl = new JLabel("URL:");
					panel.add(lblUrl);
				}
				{
					MenuURLField = new JTextField();
					panel.add(MenuURLField);
					MenuURLField.setColumns(32);
				}
			}
		}
		{
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			getContentPane().add(panel, BorderLayout.SOUTH);
			{
				JButton Cancel = new JButton("\u53D6\u6D88");
				Cancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}
				});
				panel.add(Cancel);
			}
			{
				JButton OKButton = new JButton("\u521B\u5EFA");
				OKButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						Menu menu = new Menu();
						menu.setMenuCode(MenuCodeField.getText());
						menu.setMenuTitle(MenuTitleField.getText());
						menu.setMenuURL(MenuURLField.getText());
						MenuPM = ((MainPage)f).selectedMenuTitle; 
							menu.setMenuPM(MenuPM);
						menu.setMenuLevel(String.valueOf(FaMenuLevel+1));
						MenuManager tmp;
						try {
							tmp = new MenuManager();
							tmp.createMenu(menu);
						} catch (BaseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, e1.getMessage(), "´íÎó", JOptionPane.ERROR_MESSAGE);
						}
					
						setVisible(false);
						DefaultMutableTreeNode menuNode = (DefaultMutableTreeNode) new DefaultMutableTreeNode(menu.getMenuTitle());
						((MainPage) f).addNode(menuNode);
					}
				});
				panel.add(OKButton);
			}
		}
	}

}
