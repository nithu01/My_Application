 package com.app.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.ListIterator;

public class MainActivity3 extends AppCompatActivity {

    int a=10,b=15;
    int [] arra= {5,-2,23,7,87,-42,509};
    String array="123";
    char[] ch;
    String str="Hello world";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
//        Arrays.sort(arra);
//        for(int num :arra){
//            Log.d("TAG","vALUE"+num);
//        }
        /*sort array*/

        for(int i=0;i<arra.length;i++){
            for(int j=i+1;j<arra.length;j++){
                if(arra[i]>arra[j]){
                    int temp=0;
                    temp=arra[i];
                    arra[i]=arra[j];
                    arra[j]=temp;
                }
            }

        }
        for(int num:arra){
            Log.d("TAG","vALUE"+num);
        }

        /*reverse string*/

        ch=str.toCharArray();
        for(int i=ch.length-1;i>=0;i--)
        {
            Log.d("TAG","STRINGDATA"+ch[i]);
        }

        /*Fibonicci series*/
        int a=0,b=1,c;
        Log.d("TAG","fibonnici series"+a+"\n"+b);
        for(int i=0;i<5;i++){
            c=a+b;
            Log.d("TAG","fibonnici series"+c);
            b=a;
            a=c;
        }

        /*Factorial*/
        int f=1;
        for(int i=5;i>0;i--){
            f=f*i;
            Log.d("TAG","factorial"+f);
        }

        /*Swap two numbers*/
        int one=10,two=7;

        one=one+two;
        two=one-two;
        one=one-two;

        Log.d("TAG","swap two numbers"+one+"\n"+two);

        /*Prime number*/
        int pri=5;
        if(pri%2==0){
            Log.d("TAG","prime number");
        }else{
            Log.d("TAG","not a prime number");
        }

        /*Reverse a number*/
        int rev=0,num=878,temp;
        temp=num;
        while(num!=0){
            int rem=num%10;
            rev=rev*10+rem;
            num=num/10;
        }
        Log.d("TAG","REVERSE NUMBER"+rev);
                if(temp==rev){
                    Log.d("TAG","Palindrome");
                }else{
                    Log.d("TAG","Not a palindrome");
                }


         /*Check Vowel*/
         String val="Neethu";
         int vow=0,cont=0,digit=0;
         char ch;
          val =val.toLowerCase();
          for(int i =0 ;i<val.length();i++){
            ch= val.charAt(i);
            if(ch=='i'||ch=='o'||ch=='u'||ch=='a'||ch=='e'){
                vow++;

            }else if(ch>='a' && ch<='z'){
                cont++;
            }
            Log.d("TAG","VALUE OF VOWELS"+vow+"\n"+cont);
          }

          /*check list contains a number odd number of times*/
        int arr[]={1,2,3,4,5,1,2,3,5,5};

          for(int i=0;i<arr.length;i++){
              int count=0;
              for(int j=0;j<arr.length;j++)
              {
                  if(arr[i]==arr[j]){
                      Log.d("TAG","count"+count);
                      count++;
                  }
              }
                  if(count%2!=0){
                      Log.d("TAG","oddnumbe"+arr[i]+"\n"+count);
                  }

          }

          /*Check list conatins odd number*/
        int value[]={2,4,5,6,8};
        for(int i:value){
            if(i%2!=0){
                Log.d("TAG","ODD NUMBER"+i);
            }
        }


        /*Check if an arra conatin element*/
//        int ele=12;
//        int arrr[]={2,3,5,12,10};
//        for(int i=0;i<arra.length;i++){
//            if(arrr[i]==ele){
//                Log.d("TAG","conatinselelemet"+arrr[i]);
//            }
//        }

        /*sum of elemets of array*/
        int data[]={1,2,3,4,5,6,10,8,9,7},sum=0;
        for(int i=0;i<data.length;i++){
            sum=sum+data[i];
        }
        Log.d("TAG","sumofnumbes"+sum);

        /*largest element in an array*/
        int lar=data[0],second;
        for(int i=0;i<data.length;i++){
            if(data[i]>lar){
                lar=data[i];
            }
        }
        Log.d("TAG","largestelement"+lar);

        /*second last digit of a number*/
        int secondlarger=12345%1000/100;
        Log.d("TAG","secondlarger"+secondlarger);

        /*first digit in a number*/
        int digits=123456,first=0;
        while(digits!=0){
            first=digits%10;
            digits=digits/10;
        }
        Log.d("TAG","first"+first);

        /*check string is present in a text*/
        String str="My Name";
        if(str.contains("N")){
            Log.d("TAG","containsstring"+"t");
        }else{
            Log.d("TAG","containsstring"+"no string");
        }

        /*remove trailing white spaces*/
        String s = "  abc  def\t";
        Log.d("TAG","whitespaces"+s.trim());

        /*Print Date*/
        Calendar calendar=Calendar.getInstance();


        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String formatdate=simpleDateFormat.format(calendar.getTime());
        Log.d("TAG","DATETIME"+formatdate);

        /*sort*/

        ArrayList<String> arrayList=new ArrayList<>(Arrays.asList("Z","B","A","D"));
        Collections.sort(arrayList);

        ListIterator<String> listIterator=arrayList.listIterator();
        while (listIterator.hasNext()){
            Log.d("TAG","VALUEOFLIST"+listIterator.next());
        }

        ArrayList<String> namesList = new ArrayList<String>(Arrays.asList( "alex", "brian", "charles", "alex") );

        System.out.println(namesList);

        

        System.out.println(namesList);

    }

    private static void printString(String str) {
        System.out.println(str.toLowerCase());
    }



}