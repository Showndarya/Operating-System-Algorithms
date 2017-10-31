import java.util.*;

class CLOOK
{
	public static void main(String []args)
	{
		int ini_head,n,prev_head,max_loc=0,min_loc=10000;
		
		System.out.println("Enter initial header location: ");
		Scanner sc = new Scanner(System.in);
		ini_head = sc.nextInt();
		
		System.out.println("Enter previous header location: ");
		prev_head = sc.nextInt();
		
		System.out.println("Enter number of disk locations: ");
		n = sc.nextInt();
		
		int[] disk_location = new int[n];
		int dl=0,seek_time=0,i,j,f=0,d=0;
		
		System.out.println("Enter disk locations: ");
		for(i=0;i<n;i++) 
		{
			disk_location[i] = sc.nextInt();
			if(disk_location[i]<min_loc) min_loc = disk_location[i];
			if(disk_location[i]>max_loc) max_loc = disk_location[i];
		}
		
		//Arrays.sort(disk_location);
		
		int[] visited = new int[n+1];
		
		System.out.println("Disk Location \t Seek Time");
		
		if(prev_head<=ini_head) f=0;
		else f=1;
		
		for(i=0;i<n;i++)
		{
			int pos = -1;
			int min = 10000;
			for(j=0;j<n;j++)
			{
				if(d==1)
				{
					if(disk_location[j] == ini_head) 
					{
						pos=j;
						break;
					}
				}
				else
				{
					if(f==0)
					{
						if(disk_location[j]>ini_head && min>disk_location[j]-ini_head && visited[j]==0)
						{
							min = disk_location[j] - ini_head;
							pos = j;
						}
					}
					
					else if(f==1)
					{
						if(disk_location[j]<=ini_head && min>ini_head-disk_location[j] && visited[j]==0)
						{
							pos = j;
							min = ini_head-disk_location[j];
						}
					}
				}
			}
			//System.out.println(pos);
			if(pos==-1)
			{
				if(f==0)
				{
					ini_head = min_loc;
					//System.out.println(ini_head);
					d=1;
				}
				else 
				{
					ini_head = max_loc;
					//System.out.println(ini_head);
					d=1;
				}
				System.out.println("-------------------------------------------------");
				System.out.println("Changing Directions");
				System.out.println("-------------------------------------------------");
				System.out.println("Disk Location \t Seek Time");
				i--;
				continue;
			}
			visited[pos] = 1;
			if(d==0) seek_time += Math.abs(disk_location[pos] - ini_head);
			else if(d==1) d=0;
			System.out.println(disk_location[pos]+"\t\t "+seek_time);
			ini_head = disk_location[pos];
		}
		
		System.out.println();
		System.out.println("Total Seek Time :"+seek_time);
	}
}
