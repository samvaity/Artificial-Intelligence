//package AI;

class CityNode
{
	String cname;
	int    cost;
	
	CityNode()
	{
		cname = null;
		cost = 0;
	}
	
	public void setName(String name)
	{
		cname = name;
	}
	
	public void setCost(int c)
	{
		cost = c;
	}
	
	public int getCost()
	{
		return cost;
	}
	
	public String getCname()
	{
		return cname;
	}
}

