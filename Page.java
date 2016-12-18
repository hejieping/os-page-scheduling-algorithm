package pageManager;

//页面类，记录逻辑页面号和在内存中的世界
public class Page
{
	private int PageID;
	private int time; // 页面在内存中待的时间

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
