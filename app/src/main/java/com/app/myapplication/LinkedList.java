package com.app.myapplication;

import android.content.Context;
import android.widget.Toast;

public class LinkedList {

    Node head;
     Context context;

    class Node{

        int data;
        Node next;
        Node(int d,Context ctx){
            data=d;
            next=null;
            context=ctx;
        }
    }

    public void push(int val,Context context){
        Node new_node=new Node(val,context);
        new_node.next=head;
        head=new_node;
    }

    public void print_list(){
        LinkedList.Node node=head;
        while (node!=null){
            Toast.makeText(context,""+node.data,Toast.LENGTH_SHORT).show();
            node=node.next;
        }

    }
}
