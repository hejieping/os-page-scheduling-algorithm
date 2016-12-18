package pageManager;

import javax.swing.JFrame;

public class ThreadLRU extends Thread
{
	private JFrame frame;
	private int[] instructionList;
	private Memory memory;
	public static int lackPageCount; // 缺页数

	public ThreadLRU(JFrame frame)
	{
		this.frame = frame;
	}

	public void run()
	{
		lackPageCount = 0;
		memory = new Memory();
		int InstructionCount = 0;// 已执行的指令条数
		instructionList = new int[320];
		for (int i = 0; i < 320; i++)
		{
			instructionList[i] = 1;
		}

		while (InstructionCount < 320)
		{
			// 产生一条未执行过的指令

			int instructionID = (int) (Math.random() * 320);
			while (!checkInstruction(instructionID))
			{
				instructionID = (int) (Math.random() * 320);
			}
			Instruction instruction = new Instruction(instructionID);
			memory.applyMemory_LRU(instruction);
			InstructionCount++;

		}
		float lackPagePercent = (float) ((float) lackPageCount / 320.0);
		String lackpageString = "缺页率： " + Float.toString(lackPagePercent);
		MainWindow.lackPageJLabel.setText(lackpageString);
	}

	private boolean checkInstruction(int instructionID) // 检查该指令是否已经执行过，没有执行过返回true
	{
		if (instructionList[instructionID] == 1)
		{
			instructionList[instructionID] = 0;
			return true;
		} else
		{
			return false;
		}
	}

}
