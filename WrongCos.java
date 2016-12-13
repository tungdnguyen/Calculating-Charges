public class WrongCos extends Exception
{
	String msg = "The cos calculate is not correct";

	public WrongCos()
	{
		System.out.println(msg);
	}

	public WrongCos(String t)
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
		out = super.toString()+"The cos calculate exceed limits";
		return out;
	}
}