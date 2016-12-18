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
		// frame��ʼ��
		frame = new JFrame();
		frame.setSize(600, 450);
		frame.setLocation(new Point(300, 100));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container container = frame.getContentPane();
		container.setLayout(null);
		// �����Ӧ�ؼ�
		JLabel textJLabel = new JLabel("��ѡ���ҳ���㷨");
		textJLabel.setBounds(30, 30, 120, 30);

		lackPageJLabel = new JLabel();
		lackPageJLabel.setBounds(450, 200, 120, 30);
		// ��Ӹ�ѡ��
		CheckboxGroup checkboxGroup = new CheckboxGroup();
		final Checkbox FIFOcheckbox = new Checkbox("FIFO", checkboxGroup, true);
		FIFOcheckbox.setBounds(260, 30, 40, 30);
		final Checkbox LRUcheckbox = new Checkbox("LRU", checkboxGroup, false);
		LRUcheckbox.setBounds(310, 30, 40, 30);

		// ����ı���
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(30, 80, 400, 300);
		// ��Ӱ�ť
		JButton button = new JButton("��ʼģ��");
		button.setBounds(400, 30, 160, 30);
		button.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{
				textArea.setText(""); // ����ϴβ�������
				if (FIFOcheckbox.getState() == true) // ��ѡ����FIFO�㷨�ĸ�ѡ�򣬵���FIFO�㷨�߳�
				{

					ThreadFIFO threadFIFO = new ThreadFIFO(frame);
					threadFIFO.start();
				} else
				// ��ѡ����LRU�㷨�ĸ�ѡ�򣬵���LRU�㷨�߳�
				{
					ThreadLRU threadLRU = new ThreadLRU(frame);
					threadLRU.start();
				}
			}
		});
		// ���ؼ���ӵ����
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
