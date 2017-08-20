/**
 * Created by ashah on 8/16/17.
 */
import java.io.*;

class test
{
    public static void main (String[] args) throws java.lang.Exception
    {
        System.out.println("Hello Java");
     printReverse(123);
//         for(int i=0;i<100;i++){
//          printFizzBuzz(i);
//         }
    }

    private static void printReverse(int number){
        int num=number;
        int remainder=0;
        while(num%10>0){
            remainder=num%10;
            num=num/10;
            System.out.println(remainder);
        }
    }

    private static void printFizzBuzz(int i){

        if(i%15==0){
            System.out.println("");
        }else if(i%5==0){
            System.out.println("Buzz");
        }else if(i%3==0){
            System.out.println("Fizz");
        }else{
            System.out.println(i);
        }
    }

}
