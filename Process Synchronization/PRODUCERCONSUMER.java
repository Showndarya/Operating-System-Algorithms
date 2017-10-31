import java.util.*;

class PRODUCERCONSUMER
{
	public static int bc,p,v=0,c=0,n,s=1;
	public static LinkedList<Integer> q = new LinkedList<>();
    public static void main(String args[])
    {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Enter buffer capacity:");
    	bc = sc.nextInt();
    	System.out.println("Enter number of processes:");
    	p=sc.nextInt();
    	n=p;
        Thread thread1 = new Thread(new Producer("Producer produced: "));
        Thread thread2 = new Thread(new Consumer("Consumer consumed: "));
        thread1.start();
        thread2.start();
        boolean thread1IsAlive = true;
        boolean thread2IsAlive = true;
        do {
           if (thread1IsAlive && !thread1.isAlive()) {
               thread1IsAlive = false;
               System.out.println("All processes produced");
           }
           if (thread2IsAlive && !thread2.isAlive()) {
               thread2IsAlive = false;
               System.out.println("All processes consumed");
           }
        } while(thread1IsAlive);
        //System.out.println("All processes consumed");
    }
}
 
 
class Producer implements Runnable
{
    String name;

    public Producer(String id)
    {
        name = id;
    }
 
    public void run()
    {
    	while(Main.p>0){
	        if(Main.q.size() == Main.bc || Main.s==0) 
	        	{
	        		System.out.println("Buffer is full..");
	        		randomWait();
	        	}

	        	else
	        	{
	        		Main.s=0;
	        		Main.q.add(++Main.v);
	        		System.out.println(name+Main.v);
	        		Main.p--;
	        		randomWait();
	        		Main.s=1;
	        	}
	        }
	       
    }
    void randomWait()
    {
        try 
        {
        	Main.s=1;
        	System.out.println("Producer is waiting..");
        	Thread.currentThread().sleep((long)(3000*Math.random()));
        } 
        catch (InterruptedException x)
        {
           System.out.println("Some error");
        }
    }
	
}
class Consumer implements Runnable
{
    String name;
    public Consumer(String id)
    {
        name = id;
    }
 
    public void run()
    {
    	while(Main.c<Main.n){
	        	if(Main.q.size() == 0 || Main.s==0) 
	        	{
	        		System.out.println("Buffer is empty..");
	        		randomWait();
	        	}

	        	else
	        	{
	        		Main.s=0;
	        		int t=Main.q.removeFirst();
	        		System.out.println(name+t);
	        		Main.c++;
	        		randomWait();
	        		Main.s=1;
	        	}
    	}
    }
 
    void randomWait()
    {
        try 
        {
        	Main.s=1;
        	System.out.println("Consumer is waiting..");
        	Thread.currentThread().sleep((long)(3000*Math.random()));
        } 
        catch (InterruptedException x) 
        {
           System.out.println("Some error");
        }
    }
}

