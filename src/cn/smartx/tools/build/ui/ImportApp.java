package cn.smartx.tools.build.ui;

import java.awt.BorderLayout;
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

import cn.smartx.tools.build.control.AppListManager;
import cn.smartx.tools.build.util.BaseException;

public class ImportApp extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField appURLTextField;

	/**
	 * Create the dialog.
	 */
	public ImportApp(JDialog j) {
		setBounds(100, 100, 450, 177);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel label = new JLabel("\u8F6F\u4EF6\u8DEF\u5F84\uFF1A");
			contentPanel.add(label);
		}
		{
			appURLTextField = new JTextField();
			appURLTextField.setText((String) null);
			appURLTextField.setColumns(30);
			contentPanel.add(appURLTextField);
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
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			{
				JButton okButton = new JButton("\u786E\u5B9A");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							new AppListManager().importOrCreateApp(appURLTextField.getText());
						} catch (BaseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, e1.getMessage(), "´íÎó", JOptionPane.ERROR_MESSAGE);
						}
						((ChoiceApp)j).reloadTable();
						setVisible(false);
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
