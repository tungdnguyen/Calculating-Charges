import java.util.*;
import java.io.*;
public class Points
{
	String name =" ";
	double x =0.0;
	double y =0.0;
	static int id = 0;
	int realid =0;

	public Points()
	{
		name = "P0";
		x =0.0;
		y = 0.0;
	}

	public Points(String a, double b, double c)
	{
		name =a;
		x = b;
		y = c;

	}

	public String getName()
	{
		return name;
	}

	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}


	public void setX(double d)
	{
		x = d;
	}

	public void setY(double e)
	{
		y=e;
	}

	public void setName(String f)
	{
		name =f;
	}

	public String toString()
	{
		String out;
		out = "The point: "+name+"."+" "+"The coordinate is: ("+x+","+" "+y+")";
		return out;
	}

}