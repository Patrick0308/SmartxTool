package cn.smartx.tools.build.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JPopupMenu;

import cn.smartx.tools.build.control.AppManager;
import cn.smartx.tools.build.control.MenuManager;
import cn.smartx.tools.build.control.TemplateManager;
import cn.smartx.tools.build.entity.App;
import cn.smartx.tools.build.entity.Menu;
import cn.smartx.tools.build.entity.Template;
import cn.smartx.tools.build.util.BaseException;
import cn.smartx.tools.build.velocity.buildVelocity;

import java.awt.Component;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;

public class MainPage extends JFrame {

	private JPanel contentPane;
	private String[] tblTitle={"Code","name","templatefile","title"};
	private Object tblData[][];
	DefaultTableModel tblModel=new DefaultTableModel(); 
	private JTable TemplateTable;
	DefaultMutableTreeNode selectedMenu;
	int selectedMenuLevel;
	String selectedMenuTitle;
	private JTree MenuTree;
	private App  nowApp;
	private AppManager appman;
	public void reloadTable() throws BaseException{
		TemplateManager tmp = new TemplateManager();
		List<Template> appTemplates = new ArrayList<Template>() ;
		if(tmp.queryTemplate() != null ){
			appTemplates = tmp.queryTemplate();
		}
		int rowNum = appTemplates.size();
		tblData = new Object[rowNum][4];
		for(int i= 0; i<rowNum; i++){
			tblData[i][0]=appTemplates.get(i).getTemplateCode();
			tblData[i][1]=appTemplates.get(i).getTemplateName();
			tblData[i][2]=appTemplates.get(i).getTemplateFile();
			tblData[i][3]=appTemplates.get(i).getTemplateTitle();
		}
		tblModel.setDataVector(tblData,tblTitle);
		this.TemplateTable.validate();
		this.TemplateTable.repaint();
	}
	
	public static void initTree(List<Menu> menus,String menuPM,DefaultMutableTreeNode parent) {
		if(menus != null){
			for(Menu tmp: menus){
				if(tmp.getMenuPM().equals(menuPM)){
					DefaultMutableTreeNode other = new DefaultMutableTreeNode(tmp.getMenuTitle());
					parent.add(other);
					initTree(menus,tmp.getMenuTitle(),other);
				}
			}
		}
	}
	public void addNode(DefaultMutableTreeNode node){
		DefaultTreeModel model = (DefaultTreeModel)this.MenuTree.getModel();
		 model.insertNodeInto(node, selectedMenu, 0);
	}
	
	public void refreshTree(){
		MenuManager man;
		String appName = null;
		List<Menu> menus  = null;
		try {
			man = new MenuManager();
			menus = man.queryMenu();
			appName = nowApp.getAppName();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(appName);
		DefaultTreeModel model = new DefaultTreeModel(root);
		initTree(menus,appName,root);
		MenuTree = new JTree(model);
	}
	/**
	 * Create the frame.
	 */
	public MainPage(JDialog j) {
		super();
		appman = new AppManager();
		setTitle(AppManager.nowAppURL);
		try {
			nowApp = appman.getApp(AppManager.nowAppURL);
		} catch (BaseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 538, 429);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu FileMenu = new JMenu("\u6587\u4EF6");
		menuBar.add(FileMenu);
		
		JMenuItem MNT_OpenAPP = new JMenuItem("\u5207\u6362\u8F6F\u4EF6");
		MNT_OpenAPP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				j.setVisible(true);
			}
		});
		FileMenu.add(MNT_OpenAPP);
		
		JMenuItem MNT_DeleteAPP = new JMenuItem("\u5F7B\u5E95\u5220\u9664\u8F6F\u4EF6");
		MNT_DeleteAPP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AppManager appMan  = new AppManager();
				try {
					appMan.removeApp(AppManager.nowAppURL);
				} catch (BaseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
				}
				setVisible(false);
				((ChoiceApp)j).reloadTable();
				j.setVisible(true);
				MainPage.this.dispose();
			}
		});
		FileMenu.add(MNT_DeleteAPP);
		
		JMenuItem MNT_Exit = new JMenuItem("\u9000\u51FA");
		MNT_Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		FileMenu.add(MNT_Exit);
		
		JMenu EditMenu = new JMenu("\u8BBE\u7F6E");
		menuBar.add(EditMenu);
		
		JMenuItem groupMenuItem = new JMenuItem("\u7EC4");
		groupMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new CreateGroup().setVisible(true);
			}
		});
		EditMenu.add(groupMenuItem);
		
		JMenuItem userMenuItem = new JMenuItem("\u7528\u6237");
		userMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateUser().setVisible(true);
			}
		});
		EditMenu.add(userMenuItem);
		
		JMenuItem menuItem = new JMenuItem("\u89D2\u8272");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateRole().setVisible(true);
			}
		});
		EditMenu.add(menuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(10, 0));
		setContentPane(contentPane);
		
		JScrollPane MenuScrollPane = new JScrollPane();
		contentPane.add(MenuScrollPane, BorderLayout.WEST);
		
		
		
		
		refreshTree();
		MenuTree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent arg0) {
				DefaultMutableTreeNode note=(DefaultMutableTreeNode) MenuTree.getLastSelectedPathComponent(); 
				selectedMenu=note;//获得这个结点的名称
				if(note != null){
					selectedMenuTitle  = note.toString();
					selectedMenuLevel = note.getLevel();
				}
			}
		});
		MenuTree.setPreferredSize(new Dimension(120, 16));
		MenuScrollPane.setViewportView(MenuTree);
		
		JPopupMenu TreePopMenu = new JPopupMenu();
		addPopup(MenuTree, TreePopMenu);
		
		JMenuItem menuItem_4 = new JMenuItem("\u521B\u5EFA\u5B50\u83DC\u5355");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateMenu(MainPage.this).setVisible(true);
			}
		});
		TreePopMenu.add(menuItem_4);
		
		JMenuItem menuItem_5 = new JMenuItem("\u5220\u9664");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTreeModel model = (DefaultTreeModel)MenuTree.getModel();
				model.removeNodeFromParent(selectedMenu);
				MenuManager man;
				try {
					man = new MenuManager();
					Menu removeMenu = new Menu();
					removeMenu.setMenuTitle(selectedMenuTitle);
					man.removeMenu(selectedMenuTitle,selectedMenuLevel);
				} catch (BaseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		TreePopMenu.add(menuItem_5);
		
		JMenuItem menuItem_6 = new JMenuItem("\u4FEE\u6539");
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ModifyMenu(MainPage.this).setVisible(true);
			}
		});
		TreePopMenu.add(menuItem_6);
		
		JLabel label = new JLabel("\u83DC\u5355\u6811\u7ED3\u6784\u663E\u793A\uFF1A");
		MenuScrollPane.setColumnHeaderView(label);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		TemplateTable = new JTable(tblModel);
		try {
			reloadTable();
		} catch (BaseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(scrollPane, popupMenu);
		
		JMenuItem create_Item = new JMenuItem("\u521B\u5EFA\u6A21\u677F");
		create_Item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new CreateTemplate(MainPage.this).setVisible(true);
			}
		});
		popupMenu.add(create_Item);
		
		JMenuItem remove_item = new JMenuItem("\u5220\u9664");
		remove_item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedRow = TemplateTable.getSelectedRow();
				Template temp = new Template();
				temp.setTemplateCode((String)TemplateTable.getValueAt(selectedRow, 0));
				TemplateManager man;
				try {
					man = new TemplateManager();
					man.removeTemplate(temp);
					reloadTable();
				} catch (BaseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		popupMenu.add(remove_item);
		
		JMenuItem modify_item = new JMenuItem("\u4FEE\u6539");
		modify_item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowNum  = TemplateTable.getRowCount();
				List<Template> list = new ArrayList<Template>();
				for(int i=0; i<rowNum; i++){
					Template temp = new Template();
					temp.setTemplateCode((String)TemplateTable.getValueAt(i, 0));
					temp.setTemplateFile((String)TemplateTable.getValueAt(i, 2));
					temp.setTemplateTitle((String)TemplateTable.getValueAt(i, 3));
					temp.setTemplateName((String)TemplateTable.getValueAt(i, 1));
					list.add(temp);
				}
				TemplateManager man;
				try {
					man = new TemplateManager();
					man.replaceTemplate(list);
					reloadTable();
				} catch (BaseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		popupMenu.add(modify_item);
		scrollPane.setViewportView(TemplateTable);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnNewButton_1 = new JButton("\u8FD0\u884C\u7A0B\u5E8F");
		panel.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("\u751F\u6210\u4EE3\u7801");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buildVelocity.create();
			}
		});
		panel.add(btnNewButton);
		
		this.addWindowListener(new WindowAdapter(){   
	    	public void windowClosing(WindowEvent e){ 
	    		System.exit(0);
             }
        });
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
