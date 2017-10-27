import java.util.*;

class Main
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
		int dl=0,seek_time=0,i;
		System.out.println("Enter disk locations: ");
		for(i=0;i<n;i++) disk_location[i] = sc.nextInt();
		System.out.println("Order \t Seek Time");
		dl = ini_head;
		for(i=0;i<n;i++)
		{
			System.out.print(disk_location[i]+" \t ");
			dl = Math.abs(dl - disk_location[i]);
			System.out.println(dl);
			seek_time+=dl;
			dl = disk_location[i];
		}
		System.out.println();
		System.out.println("Total Seek Time :"+seek_time);
	}
}
