import java.util.*;

class DINE
{
	
	public static int i;
	public static void main(String[] args)
	{
		
		Fork[] fork = new Fork[5];
		
		for(i=0;i<fork.length;i++)
		{
			fork[i] = new Fork("Fork " +(i+1));
		}
		
		Philosopher[] phil = new Philosopher[5];
		
		phil[0] = new Philosopher("Philosopher 1:- ",fork[0],fork[4]);
		phil[1] = new Philosopher("Philosopher 2:- ",fork[1],fork[0]);
		phil[2] = new Philosopher("Philosopher 3:- ",fork[2],fork[1]);
		phil[3] = new Philosopher("Philosopher 4:- ",fork[3],fork[2]);
		phil[4] = new Philosopher("Philosopher 5:- ",fork[4],fork[3]);
		
		for(i=0;i<phil.length;i++)
		{
			System.out.println("Philosopher "+(i+1)+" enters..");
			Thread thread = new Thread(phil[i]);
			thread.start();
		}
	}
}

class Philosopher extends Thread 
{
	private Fork l_fork,r_fork;
	private String phil_name;
	private int s = 1;
	
	public Philosopher(String s, Fork l_fork, Fork r_fork)
	{
		this.phil_name = s;
		this.l_fork = l_fork;
		this.r_fork = r_fork;
	}
	
	public void consume()
	{
		if(l_fork.f_s == 1)
		{
			if(r_fork.f_s == 1)
			{
				l_fork.wait(phil_name,"Left");
				r_fork.wait(phil_name,"Right");
				System.out.println(phil_name+ " Eating..");
				r_fork.signal(phil_name,"Right");
				l_fork.signal(phil_name,"Left");
				think(phil_name);
			}
			else
			{
				
				//System.out.println("inin");
				System.out.println(phil_name+ " Waiting for right "+r_fork.f_name+"..");
				l_fork.signal(phil_name,"Left");
				think(phil_name);
		
			}
		}
		else
		{
			//System.out.println("in");
			System.out.println(phil_name+ " Waiting for left "+l_fork.f_name+"..");
			think(phil_name);
		}
	}
	
	public void think(String s)
	{
		System.out.println(s+ " Thinking..");
		try
		{
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			System.out.println("Some error");
		}
	}
	
	@Override
	public void run()
	{
		consume();
	}
}

class Fork
{
	public String f_name;
	public int f_s = 1;
	
	public Fork(String s)
	{
		this.f_name = s;
		this.f_s = 1;
	}
	
	public synchronized void wait(String phil_name, String side)
	{
		System.out.println(phil_name+" Using "+f_name+" as "+side+" fork..");
		this.f_s = 0;
	}
	
	public synchronized void signal(String phil_name, String side)
	{
		System.out.println(phil_name+" Releasing "+f_name+" as "+side+" fork..");
		this.f_s = 1;
	}
	
}
