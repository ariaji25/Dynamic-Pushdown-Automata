public class Stack{
    class Node{
        String input;
        Node next;
        Node prev;
        Node(String input){
            this.input=input;
        }
    }
    Node head;
    Node tail;
    Stack(){
        head=null;
        tail=null;
    }
    public String peek(){
        return head.input;
    }
    public boolean isEmpty(){
        return head==null && tail==null;
    }
    public void push(String input){
        Node add = new Node(input);
        if(isEmpty()){
            head=add;
            tail=add;
        }
        else{
            add.next=head;
            head.prev=add;
            head=add;
        }
    }
    public void pop(){
        Node tmp=head;
        head=head.next;
        head.prev=null;
        tmp=null;
    }
    public void showS(){
        Node curent = head;
        while(curent!=null){
            System.out.println(" | "+curent.input+" | ");
            curent=curent.next;
        }
    }    
}