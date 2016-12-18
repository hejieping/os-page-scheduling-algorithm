package pageManager;

public class Instruction
{
	private int instructionID; // ָ���ID��

	private Page page; // ָ������ҳ��

	public Instruction(int ID)
	{
		this.instructionID = ID;
		page = new Page(ID / 10);
	}

	public int getInstructionID()
	{
		return instructionID;
	}

	public int getPageID()
	{
		return page.getPageID();
	}
}
