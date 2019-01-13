
public class Graph{
    class Node{
        String value;
        Edge next;
        Node prev;
        Node(String value){
            this.value=value;
        }
    }
    class Edge{
        String terminal, topS, pushS;
        Node prev;
        Edge next;
        Edge(String t, String tS, String pS){
            terminal=t;
             topS=tS;
            pushS=pS;
        }
        public void addDoStack(String tS, String pS){
            topS=tS;
            pushS=pS;
        }
    }
    Node head;
    Node tail;
    Edge first;
    Edge last;
    public static Stack pda;
    public static boolean valid;
    public static Node onState;
    Graph(){
        head=null;
        tail=null;
        first=null;
        last=null;
    }
    public void insertState(String name){
        Node add = new Node(name);
        if(head==null){
            head=add;
            tail=add;
            Graph.onState=head;
            tail.prev=null;
        }
        else{
            tail.prev=add;
            tail=add;
        }
    }
    public Node getState(String key){
            Node tmp = head;
            while((!tmp.value.equals(key) )&& tmp.prev!=null){
                tmp=tmp.prev;
            }
            return tmp;
    }
    public void addTransitioin(String old, String t, String top, String nextS, String pops){
        Edge add = new Edge(t,top,pops);
        Node cur1=getState(old);
        Node cur2=getState(nextS);
        Linked(cur1, add, cur2);
    }
    public void Linked(Node key1, Edge e , Node key2){
         if(key1.next==null){
            key1.next=e;
            e.prev=key2;
        }
        else{
            Edge curent2=key1.next;
            while(curent2.next!=null){
                curent2=curent2.next;
            }
            curent2.next=e;
            e.prev=key2;
        }
    }
    public void showTrantition(){
      Node cur=head;
        while(cur!=null){
            Edge tmp = cur.next;
            while(tmp!=null){
                System.out.println("=> d("+cur.value+", "+tmp.terminal+", "+tmp.topS+")= ("+tmp.prev.value+", "+tmp.pushS+")");
                tmp=tmp.next;
            }
            cur=cur.prev;
        }
    }
    public static void setOnState(Node state){
      Graph.onState=state;
    }
    public boolean searchTransition(String charakter, Node state){
      Edge transition = state.next;
      boolean check=false;
      System.out.println("=> State : "+state.value);
      System.out.println("=> Read : "+charakter);
      while(transition!=null){
        if(transition.terminal.equals(charakter)&&Graph.pda.peek().equals(transition.topS)){
          if(!transition.pushS.equals("E")){
            Graph.pda.push(String.valueOf(transition.pushS.charAt(0)));
            Graph.pda.showS();
            if(!transition.prev.value.equals(state.value)){
                setOnState(transition.prev);
            }
            check = true;
            break;
          }
          else if(transition.pushS.equals("E")){
            if(!Graph.pda.peek().equals("Z")){
              Graph.pda.pop();
            }
            Graph.pda.showS();
            if(!transition.prev.value.equals(state.value)){
                setOnState(transition.prev);
            }
            check = true;
            break;
          }
        }
        transition=transition.next;
      }
      if(check==true){
        return true;
      }
      else{
        return false;
      }
    }
    public void testWordPDA(String test){
        Graph.pda=new Stack();
        Graph.pda.push("Z");
        Graph.valid=true;
        int i =0;
        Node state;
        while(i<test.length()&&Graph.valid==true){
            state=Graph.onState;
            Graph.valid=searchTransition(String.valueOf(test.charAt(i)),state);
            if(Graph.valid!=true){
              System.out.println("Transtion not found");
              break;
            }
              i++;
        }
        System.out.println("==============================< Result >==================================== ");
        if(Graph.pda.peek().equals("Z")&&Graph.valid==true){
          System.out.println("=> Word accepted ");
        }
        else{
          System.out.println("=> Word unaccepted ");
        }
    }
}
