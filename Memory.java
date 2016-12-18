package pageManager;

public class Memory
{
	// �ڴ�ҳ�����飬һ�����ĸ�ҳ��
	private Page[] memoryList;

	public Memory()
	{
		memoryList = new Page[4];
		for (int i = 0; i < 4; i++)
		{
			memoryList[i] = new Page();
		}
	}

	private int check(int pageID) // ����ڴ����Ƿ���ڸ�ָ��
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

	private int getsuitablePage()// �ҳ��ڴ��д���õ�ҳ���ID
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

	public void applyMemory_FIFO(Instruction instruction)// FIFOҳ���û��㷨
	{
		// ָ���Ѿ����ڴ�
		if (check(instruction.getPageID()) != -1)
		{
			MainWindow.textArea.append("ָ��" + instruction.getInstructionID()
					+ "�Ѿ���ҳ��" + check(instruction.getPageID()) + "\n");

		} else
		// ָ����ڴ���
		{
			// �ҳ���ҳ�棬��ҳ����ʱ�ҳ�������õ�ҳ��
			int memoryID = getsuitablePage();
			ThreadFIFO.lackPageCount++;// ȱҳ��+1
			// �ڴ�ҳ����Ҫ�û�
			if (memoryList[memoryID].getPageID() != -1)
			{
				MainWindow.textArea.append("ָ��"
						+ instruction.getInstructionID() + "�����ڴ���" + "���߼�ҳ��"
						+ memoryList[memoryID].getPageID() + "�û���������ָ������ڴ�����ҳ"
						+ memoryID + "\n");

			}
			// �п��ڴ����ҳ�浼��
			else
			{
				MainWindow.textArea
						.append("ָ��" + instruction.getInstructionID() + "�����ڴ���"
								+ "���߼�ҳ��" + instruction.getPageID() + "�����ڴ�����ҳ"
								+ memoryID + "\n");
			}
			memoryList[memoryID] = new Page(instruction.getPageID());
		}
		addTime();// ҳ�����ʱ��+1
	}

	public void applyMemory_LRU(Instruction instruction)
	{
		// ָ���Ѿ����ڴ�
		if (check(instruction.getPageID()) != -1)
		{
			MainWindow.textArea.append("ָ��" + instruction.getInstructionID()
					+ "�Ѿ���ҳ��" + check(instruction.getPageID()) + "\n");
			memoryList[check(instruction.getPageID())].setTime(0);
			// ���Ѿ��������ڴ��У��Ҹձ����õ�ҳ��ʱ����Ϊ0����Ҳ��FIFO��LRU�㷨�Ĳ��

		} else
		// ָ����ڴ���
		{
			// �ҳ���ҳ�棬��ҳ����ʱ�ҳ�������õ�ҳ��
			int memoryID = getsuitablePage();
			ThreadLRU.lackPageCount++;// ȱҳ��+1
			// �ڴ�ҳ����Ҫ�û�
			if (memoryList[memoryID].getPageID() != -1)
			{
				MainWindow.textArea.append("ָ��"
						+ instruction.getInstructionID() + "�����ڴ���" + "���߼�ҳ��"
						+ memoryList[memoryID].getPageID() + "�û���������ָ������ڴ�����ҳ"
						+ memoryID + "\n");

			}
			// �п��ڴ����ҳ�浼��
			else
			{
				MainWindow.textArea
						.append("ָ��" + instruction.getInstructionID() + "�����ڴ���"
								+ "���߼�ҳ��" + instruction.getPageID() + "�����ڴ�����ҳ"
								+ memoryID + "\n");
			}
			memoryList[memoryID] = new Page(instruction.getPageID());
		}
		addTime();// ҳ�����ʱ��+1
	}
}
