import java.util.*;
import java.io.*;
public abstract class Charges implements INT,Serializable
{
	double x = 0.0;
	double y = 0.0;
	double charge = 0.0;
	static int id=0;
	int realid =0;

	public Charges()
	{
		x = 0.0;
		y = 0.0;
		charge = 0.0;
		id++;
		realid = id;
	}

	public Charges(double a, double b, double c)
	{
		x = a;
		y =b;
		charge = c;
		id++;
		realid = id;
	}

	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}

	public double getCharge()
	{
		return charge;
	}
	public int getID()
	{
		return realid;
	}

	public void setX(double d)
	{
		x=d;
	}
	public void setY(double e)
	{
		y=e;
	}

	public void setCharge(double f)
	{
		charge =f;
	}

	public String toString()
	{
		String out;
		out = "The charge coordinate is: "+"("+x+", "+y+")"+" "+"The charge is: "+charge;
		return out;
	}

	public abstract double calculateXComponent(Points a);
	public abstract double calculateYComponent(Points a);

}
