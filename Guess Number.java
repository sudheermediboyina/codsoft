import java.io.*;
import java.util.*;
class HelloWorld {
    int i,j,guess,n,g,w;
    Scanner s=new Scanner(System.in);
    public static void main(String[] args) {
        HelloWorld h=new HelloWorld();
        h.func();
    }
    public void func(){
        System.out.println("[ Game Description: You Have to find a Magic Number which is from 1 to 100 in range ]");
        System.out.print("How many times you will Play the Game???:");
        n=s.nextInt();
        for(i=0;i<n;i++)
        {
            Random r=new Random();
            guess=r.nextInt(100)+1;
            System.out.printf("Game %d is Started!!!\n",i+1);
            System.out.println("You will have 7 Chances to Guess Magic Number!!!");
            for(j=0;j<7;j++)
            {
                System.out.printf("Enter Your %d(st/nd/rd/th) Guess:",j+1);
                g=s.nextInt();
                if(g==guess)
                {
                    System.out.println("Your Guess is Correct!!!");
                    j=0;
                    break;
                }
                else if(g<guess)
                    System.out.println("Your Guess is too Low!!!");
                else
                    System.out.println("Your Guess is too High!!!");
            }
            if(j!=0)
            System.out.printf("You have Failed!!!\nThe Magic Number is %d\n",guess);
            System.out.print("Do You want to continue??(1/0):");
            w=s.nextInt();
            if(w!=1)
            break;
        }
        System.out.print("Thank You For Playing!!!");
    }
}