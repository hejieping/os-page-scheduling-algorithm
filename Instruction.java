package pageManager;

public class Instruction
{
	private int instructionID; // 指令的ID号

	private Page page; // 指令所属页面

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
