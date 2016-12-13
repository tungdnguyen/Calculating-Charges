import java.util.*;
import java.io.*;
public class NegativeCharge extends Charges implements INT,Serializable
{
	public NegativeCharge()
	{
		super();
	}

	public NegativeCharge(double a, double b, double c)
	{
		super(a,b,c);
	}

	public double calculateSin(Points a) throws WrongSin
	{
		double x1 = this.getX();
		double y1 = this.getY();
		double xminus = Math.pow(x1-a.getX(),2);
		double yminus = Math.pow(y1-a.getY(),2);

		double distance = Math.sqrt(xminus+yminus);
		double side = y1-a.getY();

		double sin = side/distance;
		try
		{
		if(sin>1||sin<-1)
		{
			throw new WrongSin();
		}
		}

		catch(WrongSin ws)
		{
			System.out.println(ws.getMessage());
			sin = 10000;
		}
		finally
		{
			return sin;
		}

	}

		public double calculateCos(Points a) throws WrongCos
	{
		double x1 = this.getX();
		double y1 = this.getY();
		double xminus = Math.pow(x1-a.getX(),2);
		double yminus = Math.pow(y1-a.getY(),2);

		double distance = Math.sqrt(xminus+yminus);
		double side = a.getX()-x1;
		double cos = side/distance;

		try
		{
		if(cos>1||cos<-1)
		{
			throw new WrongCos();
		}
		}

		catch(WrongCos wc)
		{
			System.out.println(wc.getMessage());
			cos = 10000;
		}
		finally
		{
			return cos;
		}

	}

	public double calculateMagnitude(Points a)
	{
		double x1 = this.getX();
		double y1 = this.getY();
		double xminus = Math.pow(x1-a.getX(),2);
		double yminus = Math.pow(y1-a.getY(),2);

		double distance = Math.sqrt(xminus+yminus);
		double k = Math.pow(10,9)*8.99;
		double c = this.getCharge();

		double e = (k*c)/Math.pow(distance,2);

		return e;
	}

	public double calculateXComponent(Points a)
	{
		double comp;
		try{
		comp = this.calculateMagnitude(a)*this.calculateCos(a);
		}
		catch(WrongCos wc)
		{
			System.out.println("cos exceed limit");
			comp = 100000;
		}
		return comp;
	}

	public double calculateYComponent(Points a)
	{
		double comp;
		try{
		comp = Math.abs(this.calculateMagnitude(a)*this.calculateSin(a));
	}
	catch(WrongSin ws)
	{
		System.out.println("sin exceed limit");
		comp = 10000;
	}

	return comp;

	}

		public String toString()
	{
		String out;
		out = "a negative charge with"+super.toString();
		return out;
	}

	
}