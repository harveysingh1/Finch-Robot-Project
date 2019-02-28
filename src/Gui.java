import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Gui extends JFrame
{
	private ForthRobot robot;

	private JPanel contentPane;
	private JTextField cmdString;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Gui()
	{
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0)
			{
				System.out.println("Closing");
			}
		});
		InitRobot();
		
		setResizable(false);
		setTitle("Forth Robot");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 374, 182);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblCommand = new JLabel("Command");
		lblCommand.setBounds(10, 11, 76, 26);
		panel.add(lblCommand);
		
		cmdString = new JTextField();
		cmdString.getDocument().addDocumentListener(new DocumentListener() {
			
			private void update()
			{
				Command cmd = robot.parse(cmdString.getText());
				if(cmd == null)
				{
					cmdString.setForeground(new Color(255, 0, 0));
				}
				else
				{
					cmdString.setForeground(new Color(0, 255, 0));
				}
			}
			
			@Override
			public void removeUpdate(DocumentEvent arg0)
			{
				update();
			}

			@Override
			public void insertUpdate(DocumentEvent arg0)
			{
				update();
			}
			
			@Override
			public void changedUpdate(DocumentEvent arg0)
			{
				update();
			}
		});
		cmdString.setBounds(70, 11, 268, 26);
		panel.add(cmdString);
		cmdString.setColumns(10);
		
		JButton btnExecuteNextCommand = new JButton("Execute Next Command");
		btnExecuteNextCommand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				robot.execute();
			}
		});
		btnExecuteNextCommand.setBounds(70, 48, 268, 23);
		panel.add(btnExecuteNextCommand);
		
		JButton btnDeleteTopCommand = new JButton("Add Command");
		btnDeleteTopCommand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Command cmd = robot.parse(cmdString.getText());
				if(cmd != null)
				{
					robot.add(cmd);
				}
			}
		});
		btnDeleteTopCommand.setBounds(70, 82, 268, 23);
		panel.add(btnDeleteTopCommand);
		
		JButton btnDeleteTopCommand_1 = new JButton("Delete Top Command");
		btnDeleteTopCommand_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				robot.delete();
			}
		});
		btnDeleteTopCommand_1.setBounds(70, 116, 268, 23);
		panel.add(btnDeleteTopCommand_1);
	}

	/**
	 * Create Forth robot
	 */
	private void InitRobot()
	{
		robot = new ForthRobot();
	}
}
