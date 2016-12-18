package pageManager;

public class Memory
{
	// 内存页面数组，一共有四个页面
	private Page[] memoryList;

	public Memory()
	{
		memoryList = new Page[4];
		for (int i = 0; i < 4; i++)
		{
			memoryList[i] = new Page();
		}
	}

	private int check(int pageID) // 检查内存中是否存在该指令
	{
		for (int i = 0; i < 4; i++)
		{
			if (memoryList[i].getPageID() == pageID)
			{
				return i;
			}
		}
		return -1;
	}

	private int getsuitablePage()// 找出内存中待最久的页面的ID
	{
		int maxTimeID = 0;
		int maxTime = memoryList[0].getTime();
		for (int i = 0; i < 4; i++)
		{
			if (memoryList[i].getPageID() == -1)
			{
				return i;
			}
			if (maxTime < memoryList[i].getTime())
			{
				maxTime = memoryList[i].getTime();
				maxTimeID = i;
			}
		}
		return maxTimeID;
	}

	private void addTime()
	{
		for (int i = 0; i < 4; i++)
		{
			if (memoryList[i].getPageID() != -1)
			{
				memoryList[i].setTime(memoryList[i].getTime() + 1);
			}
		}
	}

	public void applyMemory_FIFO(Instruction instruction)// FIFO页面置换算法
	{
		// 指令已经在内存
		if (check(instruction.getPageID()) != -1)
		{
			MainWindow.textArea.append("指令" + instruction.getInstructionID()
					+ "已经在页面" + check(instruction.getPageID()) + "\n");

		} else
		// 指令不在内存中
		{
			// 找出空页面，当页面满时找出进来最久的页面
			int memoryID = getsuitablePage();
			ThreadFIFO.lackPageCount++;// 缺页数+1
			// 内存页面需要置换
			if (memoryList[memoryID].getPageID() != -1)
			{
				MainWindow.textArea.append("指令"
						+ instruction.getInstructionID() + "不在内存中" + "将逻辑页面"
						+ memoryList[memoryID].getPageID() + "置换出，并将指令放入内存物理页"
						+ memoryID + "\n");

			}
			// 有空内存进行页面导入
			else
			{
				MainWindow.textArea
						.append("指令" + instruction.getInstructionID() + "不在内存中"
								+ "将逻辑页面" + instruction.getPageID() + "调入内存物理页"
								+ memoryID + "\n");
			}
			memoryList[memoryID] = new Page(instruction.getPageID());
		}
		addTime();// 页面存在时间+1
	}

	public void applyMemory_LRU(Instruction instruction)
	{
		// 指令已经在内存
		if (check(instruction.getPageID()) != -1)
		{
			MainWindow.textArea.append("指令" + instruction.getInstructionID()
					+ "已经在页面" + check(instruction.getPageID()) + "\n");
			memoryList[check(instruction.getPageID())].setTime(0);
			// 将已经存在于内存中，且刚被调用的页面时间设为0，这也是FIFO和LRU算法的差别

		} else
		// 指令不在内存中
		{
			// 找出空页面，当页面满时找出进来最久的页面
			int memoryID = getsuitablePage();
			ThreadLRU.lackPageCount++;// 缺页数+1
			// 内存页面需要置换
			if (memoryList[memoryID].getPageID() != -1)
			{
				MainWindow.textArea.append("指令"
						+ instruction.getInstructionID() + "不在内存中" + "将逻辑页面"
						+ memoryList[memoryID].getPageID() + "置换出，并将指令放入内存物理页"
						+ memoryID + "\n");

			}
			// 有空内存进行页面导入
			else
			{
				MainWindow.textArea
						.append("指令" + instruction.getInstructionID() + "不在内存中"
								+ "将逻辑页面" + instruction.getPageID() + "调入内存物理页"
								+ memoryID + "\n");
			}
			memoryList[memoryID] = new Page(instruction.getPageID());
		}
		addTime();// 页面存在时间+1
	}
}
