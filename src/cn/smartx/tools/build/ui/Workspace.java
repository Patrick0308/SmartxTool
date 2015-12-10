package cn.smartx.tools.build.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import cn.smartx.tools.build.control.AppListManager;
import cn.smartx.tools.build.control.WorkspaceManager;
import cn.smartx.tools.build.util.FileException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Workspace extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField workspaceTextview;
	private WorkspaceManager spaceMan;

	/**
	 * Create the dialog.
	 */
	public Workspace() {
		try {
			spaceMan = new WorkspaceManager();
		} catch (FileException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, e.getMessage(), "´íÎó", JOptionPane.ERROR_MESSAGE);
		}
		setTitle("\u5DE5\u4F5C\u8DEF\u5F84\u8BBE\u7F6E");
		setBounds(100, 100, 450, 177);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel label = new JLabel("\u5DE5\u4F5C\u8DEF\u5F84\uFF1A");
			contentPanel.add(label);
		}
		{
			workspaceTextview = new JTextField();
				workspaceTextview.setText(spaceMan.workspace);
			contentPanel.add(workspaceTextview);
			workspaceTextview.setColumns(30);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton workspaceOkButton = new JButton("\u786E\u5B9A");
				workspaceOkButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String spaceurl = workspaceTextview.getText();
						if(spaceurl == null)
							JOptionPane.showInputDialog("ÇëÊäÈë¹¤×÷Ä¿Â¼");
						else{
							AppListManager.appspace = spaceurl;
							WorkspaceManager workMan;
							try {
								workMan = new WorkspaceManager();
								workMan.setSpace(spaceurl);
							} catch (FileException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								JOptionPane.showMessageDialog(null, e.getMessage(), "´íÎó", JOptionPane.ERROR_MESSAGE);
							}
							setVisible(false);
							new ChoiceApp().setVisible(true);
						}
					}
				});
				workspaceOkButton.setActionCommand("OK");
				buttonPane.add(workspaceOkButton);
				getRootPane().setDefaultButton(workspaceOkButton);
			}
		}
	}

}
