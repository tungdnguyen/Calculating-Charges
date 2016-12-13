public class WrongSin extends Exception
{
	String msg = "The sin calculate is not correct";

	public WrongSin()
	{
		System.out.println(msg);
	}

	public WrongSin(String t)
	{
		super(t);
	}

	public String getMessage()
	{
		return msg;
	}

	public String toString()
	{
		String out;
		out = super.toString()+"The sin calculate exceed limits";
		return out;
	}
}