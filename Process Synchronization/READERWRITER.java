import java.util.*;

class READERWRITER
{
	private static RWthread[] rthread,wthread;
	private static int readers=0,writers=0,r_l,w_l,f_w=0,f_r=0;
	public static void main(String args[])
	{
		int rt,wt,i;
		System.out.println("Enter number of readers:");
		Scanner sc = new Scanner(System.in);
		rt = sc.nextInt();
		System.out.println("Enter number of writers:");
		wt = sc.nextInt();
		
		rthread = new RWthread[rt];
		wthread = new RWthread[wt];
		
		for(i=0;i<rthread.length;i++)
		{
			rthread[i] = new RWthread();
			rthread[i].rorw(0);
		}
		for(i=0;i<wthread.length;i++)
		{
			wthread[i] = new RWthread();
			wthread[i].rorw(1);
		}
		
		r_l = rthread.length;
		w_l = wthread.length;
		//System.out.println(rthread.length);
		while(f_w!=1 && f_r!=1) driver();
	}
	
	public static void driver()
	{
		Random rand  =  new Random();
		int rorw = rand.nextInt(2)+0;
		if(rorw==0)
		{
			//System.out.println(readers);
			if(readers>=r_l) 
			{
				//readers--;
				//System.out.println("Readers are exhausted..");
				f_r=1;
			}
			else 
			{
				readers++;
				if(readers>=r_l) 
				{
					//readers--;
					//System.out.println("Readers are exhausted..");
					f_r=1;
				}
				else rthread[readers].start();
			}
		}
		else if(rorw==1)
		{
			if(writers>=w_l) 
			{
				//writers--;
				//System.out.println("Writers are exhausted..");
				f_w=1;
			}
			else 
			{
				writers++;
				if(writers>=w_l) 
				{
					//writers--;
					//System.out.println("Writers are exhausted..");
					f_w=1;
				}
				else wthread[writers].start();
			}
		}
	}
}

class RWthread extends Thread
{
	private int rorw = 0,s_w=1,s_r=1,rc=0,wait=0;
	private long w_wait=3000,r_wait=2000;
	
	public void rorw(int what)
	{
		rorw = what;
	}
	
	public boolean isFree(int s)
	{
		if(s!=1 && wait==0)
		{
			wait=1;
			System.out.println("Thread " +(getId()-10) +" is in wait state");
		}
		return s!=1;
	}
	
	public void lockW()
	{
		s_w=0;
		System.out.println("Writer " + (getId()-10) +" is locked");
	}
	
	public void lockR()
	{
		s_r=0;
		System.out.println("Reader " + (getId()-10) +" is locked");
	}
	
	public void dataWriter()
	{
		wait=0;
		System.out.println("Writer " + (getId()-10) +" is writing");
		try
		{
			sleep(w_wait);
		}
		catch(Exception e)
		{
			System.out.println("Some error");
		}
		System.out.println("Writer " +(getId()-10) +" has finished writing");
	}
	
	public void releaseR()
	{
		s_r=1;
		System.out.println("Reader " + (getId()-10) +" is now unlocked");
	}
	
	public void releaseW()
	{
		s_w=1;
		System.out.println("Writer " + (getId()-10) +" is now unlocked");
	}
	
	public void dataReader()
	{
		System.out.println("Reader " +(getId()-10) +" is reading");
		try
		{
			sleep(w_wait);
		}
		catch(Exception e)
		{
			System.out.println("Some error");
		}
		System.out.println("Reader " +(getId()-10) +" has finished reading");
		
		if(--rc==0) releaseW();
	}
	
	@Override
	public void run()
	{
		super.run();
		if(rorw==0)
		{
			while(isFree(s_r))
			{
				try
				{
					sleep(r_wait);
				}
				catch(Exception e)
				{
					System.out.println("Some error");
				}
			}
			if(rc==0) releaseW();
			lockR();
			rc++;
			dataReader();
			releaseR();
		}
		else if(rorw==1)
		{
			while(isFree(s_w))
			{
				try
				{
					sleep(w_wait);
				}
				catch(Exception e)
				{
					System.out.println("Some error");
				}
			}
			lockR();
			lockW();
			dataWriter();
			releaseR();
			releaseW();
		}
	}
}

