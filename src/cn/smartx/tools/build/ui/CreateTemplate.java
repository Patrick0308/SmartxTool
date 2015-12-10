package cn.smartx.tools.build.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;

import cn.smartx.tools.build.control.AppManager;
import cn.smartx.tools.build.control.TemplateManager;
import cn.smartx.tools.build.entity.Template;
import cn.smartx.tools.build.util.BaseException;

public class CreateTemplate extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JTextField TempCodeField;
	private JTextField TempNameField;
	private JTextField TempURLField;
	private JTextField TempTitleField;



	/**
	 * Create the dialog.
	 */
	public CreateTemplate(JFrame f) {
		setTitle("\u521B\u5EFA\u6A21\u677F");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		FlowLayout fl_contentPanel = new FlowLayout();
		contentPanel.setLayout(fl_contentPanel);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			{
				JLabel label = new JLabel("\u6A21\u677F\u540D\u79F0\uFF1A");
				panel.add(label);
			}
			{
				TempNameField = new JTextField();
				panel.add(TempNameField);
				TempNameField.setColumns(11);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			{
				JLabel lblNewLabel = new JLabel("Code:");
				panel.add(lblNewLabel);
			}
			{
				TempCodeField = new JTextField();
				panel.add(TempCodeField);
				TempCodeField.setColumns(10);
			}
		}
		{
			JPanel panel_1 = new JPanel();
			contentPanel.add(panel_1);
			FlowLayout fl_panel_1 = (FlowLayout) panel_1.getLayout();
			fl_panel_1.setAlignment(FlowLayout.LEFT);
			{
				JLabel lblTitle = new JLabel("Title\uFF1A");
				panel_1.add(lblTitle);
			}
			{
				TempTitleField = new JTextField();
				panel_1.add(TempTitleField);
				TempTitleField.setColumns(10);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			{
				JLabel lblurl = new JLabel("\u6A21\u7248URL\uFF1A");
				panel.add(lblurl);
			}
			{
				TempURLField = new JTextField();
				panel.add(TempURLField);
				TempURLField.setColumns(29);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u65B0\u5EFA");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Template temp = new Template();
						temp.setTemplateName(TempNameField.getText());
						temp.setTemplateCode(TempCodeField.getText());
						temp.setTemplateFile(TempURLField.getText());
						temp.setTemplateTitle(TempTitleField.getText());
						TemplateManager tmp;
						try {
							tmp = new TemplateManager();
							if(tmp.createTemplate(temp))
								JOptionPane.showMessageDialog(null, "新建成功", "成功", JOptionPane.OK_OPTION);
							else
								JOptionPane.showMessageDialog(null, "新建失败，可能是code重复", "错误", JOptionPane.ERROR_MESSAGE);
							((MainPage)f).reloadTable();
							setVisible(false);
							dispose();
						} catch (BaseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
					}
				});
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
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
