package pageManager;

//ҳ���࣬��¼�߼�ҳ��ź����ڴ��е�����
public class Page
{
	private int PageID;
	private int time; // ҳ�����ڴ��д���ʱ��

	public Page()
	{
		PageID = -1;
	}

	public Page(int PageID)
	{
		this.PageID = PageID;
	}

	public int getPageID()
	{
		return PageID;
	}

	public int getTime()
	{
		return time;
	}

	public void setTime(int time)
	{
		this.time = time;
	}
}
