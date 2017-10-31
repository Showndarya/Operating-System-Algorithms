import java.util.*;
import java.io.*;
import java.lang.*;

class BANKER
{
	public static void main(String[] args)
	{
		int np,nr,r,c,e;
		System.out.println("Enter number of processes and number of resources available: ");
		Scanner sc = new Scanner(System.in);
		np = sc.nextInt();
		nr = sc.nextInt();
		
		int[][] allocated = new int[np][nr];
		int[][] maximum = new int[np][nr];
		int[][] need = new int[np][nr];
		int[] available = new int[nr];
		int[] alloc_avail = new int[nr];
		
		System.out.println("Enter the allocated matrix: ");
		for(r=0;r<np;r++) for(c=0;c<nr;c++) allocated[r][c] = sc.nextInt();
		
		System.out.println("Enter the maximum resources availability matrix: ");
		for(r=0;r<np;r++) for(c=0;c<nr;c++) maximum[r][c] = sc.nextInt();	
		
		System.out.println("Enter the availability matrix of resources: ");
		for(r=0;r<nr;r++) available[r] = sc.nextInt();
		
		System.out.println("The need matrix is: ");
		for(r=0;r<np;r++)
		{
			for(c=0;c<nr;c++)
			{
				need[r][c] = maximum[r][c] - allocated[r][c];
				System.out.print(need[r][c]+" ");
			}
			System.out.println();
		}
		
		for(r=0;r<nr;r++)
		{
			for(c=0;c<np;c++)
			{
				alloc_avail[r]+=allocated[c][r];
			}
			alloc_avail[r]+=available[r];
		}
		
		int[] process_complete = new int[np];
		int[] unsafe_chk = new int[np];
		int tnp = np,ip=0,f=0;
		
		for(r=0;r<np;r++)
		{
			for(c=0;c<nr;c++)
			{
				if(alloc_avail[c]<need[r][c]) 
				{
					f=1;
					System.out.println("Unsafe");
				}
			}
		}
		if(f==0){
		while(true)
		{
			f=0;
			if(ip==np) ip=0;
			if(unsafe_chk[ip]==1 && tnp<=1)
			{
				f=1;
				break;
			}
			if(process_complete[ip] == 1) 
			{
				ip++;
				continue;
			}
			else
			{
				for(c=0;c<nr;c++)
				{
					if(available[c]<need[ip][c]) f=1;
				}
				if(f==1) 
				{
					for(c=0;c<nr;c++)
					{
						if(alloc_avail[c]<need[ip][c]) unsafe_chk[ip]=1;
					}
					ip++;
					continue;
				}
				else
				{
					process_complete[ip]=1;
					System.out.println("Process "+ip+" can be allocated the resources..");
					System.out.println("Process Terminated. Releasing resources..");
					System.out.println("The resources available after release are:");
					for(c=0;c<nr;c++)
					{
						available[c] += allocated[ip][c];
						System.out.print(available[c]+" ");
					}
					System.out.println();
					ip++;
					tnp--;
					if(tnp==0) break;
				}
			}
		}
		}
		if(tnp==0) System.out.println("Safe");
		//else if(f==1) System.out.println("Unsafe");
	}
}
