package cn.smartx.tools.build.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

public class ModifyTemplate extends JDialog {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ModifyTemplate dialog = new ModifyTemplate();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ModifyTemplate() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("\u53D6\u6D88");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
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
			getContentPane().add(scrollPane, BorderLayout.NORTH);
		}
		{
			table = new JTable();
			getContentPane().add(table, BorderLayout.WEST);
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				{
					JLabel label = new JLabel("\u6A21\u677F\u540D\u79F0\uFF1A");
					panel_1.add(label);
				}
				{
					textField = new JTextField();
					textField.setColumns(11);
					panel_1.add(textField);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				{
					JLabel label = new JLabel("Code:");
					panel_1.add(label);
				}
				{
					textField_1 = new JTextField();
					textField_1.setColumns(10);
					panel_1.add(textField_1);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				{
					JLabel label = new JLabel("Title\uFF1A");
					panel_1.add(label);
				}
				{
					textField_2 = new JTextField();
					textField_2.setColumns(10);
					panel_1.add(textField_2);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				{
					JLabel label = new JLabel("\u6A21\u7248URL\uFF1A");
					panel_1.add(label);
				}
				{
					textField_3 = new JTextField();
					textField_3.setColumns(29);
					panel_1.add(textField_3);
				}
			}
		}
	}

}
