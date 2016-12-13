import java.util.*;
import java.io.*;
import java.text.*;
public class MainClass
{
	public static void main (String [] args)
	{

		DecimalFormat dec = new DecimalFormat("#,###,###,##0.00");
		Scanner scan1 = new Scanner(System.in);
		System.out.println("what is the name of the data file? including the .txt");
		String li = scan1.nextLine();
		//read the text file 
		ArrayList<Points> point = new ArrayList<Points>();
		ArrayList<Charges> charge = new ArrayList<Charges>();
		FileInputStream filestream;
		BufferedReader buff;

		try
		{
			filestream = new FileInputStream(li);
			buff = new BufferedReader(new InputStreamReader(filestream));
			String read = " ";
				while((read=buff.readLine())!=null)
			{
				StringTokenizer stok = new StringTokenizer(read,":");
				String check = stok.nextToken();
				if(check.equals("P"))
				{
					String info1 = stok.nextToken();
					StringTokenizer stok2 = new StringTokenizer(info1,",");
					String pointname = stok2.nextToken();
					String xstring = stok2.nextToken();
					double xreal = Double.parseDouble(xstring);
					String ystring = stok2.nextToken();
					double yreal = Double.parseDouble(ystring);
					Points p1 = new Points(pointname,xreal,yreal);
					point.add(p1);
				}

				if(check.equals("Q"))
				{
					String info2 = stok.nextToken();
					StringTokenizer stok3 = new StringTokenizer(info2,",");
					String xstr = stok3.nextToken();
					double xre = Double.parseDouble(xstr);
					String ystr = stok3.nextToken();
					double yre = Double.parseDouble(ystr);
					String cha = stok3.nextToken();
					double charg = Double.parseDouble(cha);
					if(charg>=0)
					{
						PositiveCharge pos = new PositiveCharge(xre,yre,charg);
						charge.add(pos);

					}
					if(charg<0)
					{
						NegativeCharge neg = new NegativeCharge(xre,yre,charg);
						charge.add(neg);
					}
				}

			}

			filestream.close();
			buff.close();			

		}	
		catch(IOException ioe)
		{
			System.out.println("something wrong with the file");
		}

		//calculate the charges for each point 

		for(Points a: point)
		{
			double xtotal =0.0;
			double ytotal =0.0;
			//iterate through all charge to calculate all the impact
			for(Charges b: charge)
			{ 
				System.out.println("------Calculating point: "+a.toString()+" "+"With charge: #"+b.getID());
				double mag1 = b.calculateMagnitude(a);
				String mag = dec.format(mag1);
				System.out.println("The magnitude at point"+ a.getName()+" due to charge point "+b.getID() + " "+"is: "+mag);
				double co1=10000;
				double si1=10000;
				try{

					co1 = b.calculateCos(a);
					String co = dec.format(co1);
					System.out.println("Cos of the angle is: "+co);
					double xtemp1 = b.calculateXComponent(a);
					String xtemp = dec.format(xtemp1);
					System.out.println("The x component is: " + xtemp);
					xtotal= xtotal+ xtemp1;

				}
				catch(WrongCos wc)
				{
					System.out.println(wc.getMessage());
				}
				try{

					si1 = b.calculateSin(a);
					String si = dec.format(si1);
					System.out.println("Sine of the angle is: "+si);
					double ytemp1 = b.calculateYComponent(a);
					String ytemp = dec.format(ytemp1);
					System.out.println("The y component is: "+ytemp);
					ytotal =ytotal+ytemp1;
				}
				catch(WrongSin ws)
				{
					System.out.println(ws.getMessage());
				}
				System.out.println("--------------------------------------------");

			}
				double totalcharge1 = Math.sqrt(Math.pow(xtotal,2)+Math.pow(ytotal,2));
				String totalcharge = dec.format(totalcharge1);
				System.out.println("The total charge of point: "+a.getName()+" is: "+totalcharge);
				System.out.println("--------------------------------------------");
		}

			//output to file charges
			String filename="charges.ser";
			FileOutputStream fos=null;
			ObjectOutputStream out=null;
			try
			{

				fos=new FileOutputStream(filename,false);
				out=new ObjectOutputStream(fos);
				for(Charges ar: charge)
				{
					out.writeObject(ar);
				}
				out.close();
				System.out.println("-----------charges.ser ready to read----------------------");
			}
			catch(IOException ioe)
			{
				ioe.printStackTrace();
			}

			//read file

			Scanner scan = new Scanner(System.in);
			System.out.println("Do you want to have the charges.ser binary coded file read?");
			String check2 = scan.nextLine();
			if(check2.equals("yes"))
			{
				ObjectInputStream ob = null;
				FileInputStream fis = null;
				Charges cha = null;
				ArrayList<Charges> charge2 = new ArrayList<Charges>();
			try
			{

				fis = new FileInputStream("charges.ser");
				ob = new ObjectInputStream(fis);
				try
				{
					while(true)
					{
						cha =(Charges)ob.readObject();
						charge2.add(cha);
					}
				}
				catch(EOFException eof)
				{
					System.out.println("end of file");
				}

				ob.close();
				fis.close();
			}
			catch(IOException ioe)
			{
				ioe.printStackTrace();	
			}
			catch(ClassNotFoundException cnf)
			{
				cnf.printStackTrace();
			}
		

		for(Charges f: charge2)
			{
				System.out.println(f.toString());
			}
		}

		else
		{
			System.out.println("Terminating program");
			System.exit(0);
		}
}
}