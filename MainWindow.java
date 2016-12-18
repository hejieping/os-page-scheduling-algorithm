package pageManager;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MainWindow
{

	/**
	 * @param args
	 */

	private JFrame frame;
	public static JTextArea textArea;
	public static JLabel lackPageJLabel;

	public MainWindow()
	{
		// frame初始化
		frame = new JFrame();
		frame.setSize(600, 450);
		frame.setLocation(new Point(300, 100));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container container = frame.getContentPane();
		container.setLayout(null);
		// 添加相应控件
		JLabel textJLabel = new JLabel("请选择调页的算法");
		textJLabel.setBounds(30, 30, 120, 30);

		lackPageJLabel = new JLabel();
		lackPageJLabel.setBounds(450, 200, 120, 30);
		// 添加复选框
		CheckboxGroup checkboxGroup = new CheckboxGroup();
		final Checkbox FIFOcheckbox = new Checkbox("FIFO", checkboxGroup, true);
		FIFOcheckbox.setBounds(260, 30, 40, 30);
		final Checkbox LRUcheckbox = new Checkbox("LRU", checkboxGroup, false);
		LRUcheckbox.setBounds(310, 30, 40, 30);

		// 添加文本区
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(30, 80, 400, 300);
		// 添加按钮
		JButton button = new JButton("开始模拟");
		button.setBounds(400, 30, 160, 30);
		button.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{
				textArea.setText(""); // 清除上次操作内容
				if (FIFOcheckbox.getState() == true) // 当选中了FIFO算法的复选框，调用FIFO算法线程
				{

					ThreadFIFO threadFIFO = new ThreadFIFO(frame);
					threadFIFO.start();
				} else
				// 当选中了LRU算法的复选框，调用LRU算法线程
				{
					ThreadLRU threadLRU = new ThreadLRU(frame);
					threadLRU.start();
				}
			}
		});
		// 将控件添加到面板
		container.add(scrollPane);
		container.add(textJLabel);
		container.add(FIFOcheckbox);
		container.add(LRUcheckbox);
		container.add(button);
		container.add(lackPageJLabel);

		frame.setVisible(true);
	}

	public static void main(String[] args)
	{
		MainWindow startMainWindow = new MainWindow();
	}

}
