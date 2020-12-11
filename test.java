import javax.swing.*;
class test
{
	public static void main(String[] args)
	{
		int count=0,n,i,j,sum=2;
		java.util.Scanner sc=new java.util.Scanner(System.in);
		System.out.print("enter value of n:");
		n=sc.nextInt();
		for(i=2;i<n;i++)
		{
			for(j=2;j<i;j++)
			{
				if(i%j==0)//means that number is prime
				{
					System.out.println("not:"+i);
				}
				else
				{
					System.out.println(i);
					break;
				}
			}
		}
		
	}
}	

/*
 
javac CompilerWindow.java
javac InbuiltKeywords.java
javac RunCompiler.java
java RunCompiler

*/

