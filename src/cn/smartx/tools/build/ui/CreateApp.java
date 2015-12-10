package cn.smartx.tools.build.ui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;

import cn.smartx.tools.build.control.AppManager;
import cn.smartx.tools.build.entity.App;
import cn.smartx.tools.build.util.BaseException;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreateApp extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField appName_text;
	private JTextField appCode_text;
	private JTextField appInitpage_text;


	/**
	 * Create the dialog.
	 */
	public CreateApp(JDialog d) {
		setTitle("´´½¨APP");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			{
				JLabel lblNewLabel = new JLabel("\u65B0\u7684\u8F6F\u4EF6\u540D\uFF1A");
				panel.add(lblNewLabel);
			}
			{
				appName_text = new JTextField();
				panel.add(appName_text);
				appName_text.setColumns(10);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JLabel lblcode = new JLabel("\u8F6F\u4EF6Code:");
				panel.add(lblcode);
			}
			{
				appCode_text = new JTextField();
				appCode_text.setColumns(10);
				panel.add(appCode_text);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JLabel label = new JLabel("\u8F6F\u4EF6\u521D\u59CB\u9875\uFF1A");
				panel.add(label);
			}
			{
				appInitpage_text = new JTextField();
				appInitpage_text.setColumns(20);
				panel.add(appInitpage_text);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("\u53D6\u6D88");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			{
				JButton okButton = new JButton("\u786E\u5B9A");
				okButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						AppManager appMan = new AppManager();
						App app = new App();
						app.setAppName(appName_text.getText());
						app.setAppCode(appCode_text.getText());
						app.setInitPage(appInitpage_text.getText());
						try {
							appMan.createApp(app);
						} catch (BaseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, e1.getMessage(),"´íÎó",JOptionPane.ERROR_MESSAGE);
						}
						setVisible(false);
						((ChoiceApp)d).reloadTable();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
