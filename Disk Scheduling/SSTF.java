import java.util.*;

class SSTF
{
	public static void main(String []args)
	{
		int ini_head,n;
		
		System.out.println("Enter initial header location: ");
		Scanner sc = new Scanner(System.in);
		ini_head = sc.nextInt();
		
		System.out.println("Enter number of disk locations: ");
		n = sc.nextInt();
		
		int[] disk_location = new int[n];
		int dl=0,seek_time=0,i,j;
		
		System.out.println("Enter disk locations: ");
		for(i=0;i<n;i++) disk_location[i] = sc.nextInt();
		
		int[] visited = new int[n+1];
		
		System.out.println("Disk Location \t Seek Time");
		
		for(i=0;i<n;i++)
		{
			int min = 100000;
			int pos = 0;
			for(j=0;j<n;j++)
			{
				if(Math.abs(disk_location[j] - ini_head) < min)
				{
					if(visited[j]==0)
					{
						min = Math.abs(disk_location[j] - ini_head);
						pos = j;
					}
				}
			}
			visited[pos]=1;
			seek_time += Math.abs(disk_location[pos] - ini_head);
			System.out.println(disk_location[pos]+"\t\t "+seek_time);
			ini_head = disk_location[pos];
		}
		
		System.out.println();
		System.out.println("Total Seek Time :"+seek_time);
	}
}
