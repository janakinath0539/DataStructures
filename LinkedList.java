class Node<N>{
    N data;
    Node<N> next=null;

    Node(N data){
        this.data=data;
    }
}

class LinkedList<N>{

    Node<N> head=null;
    Node<N> tail=null;

    void insertAtFront(N d){

        Node<N> n=new Node<>(d);
        if(head==null)
            head=tail=n;
        else{
            n.next=head;
            head=n;
        }
    }

    void insertAtPosition(N d,int p){

        Node<N> n=new Node<>(d);
        Node<N> current=head;
        Node<N> pre=head;
        int count=1;
        while(current!=null&&count!=p){
                pre = current;
            current = current.next;
            count++;
        }
        n.next=pre.next;
        pre.next=n;
    }

    void insertAtEnd(N d){
        Node<N> n=new Node<>(d);
        if(head==null) {
            head = n;
            tail=n;
        }
        else{
            tail.next=n;
            tail=tail.next;
        }
    }


    boolean deletion(N d){

        Node<N> current=head;
        Node<N> pre=null;
        while(current!=null){
            if(current.data.equals(d)) {
                if(current==head){
                    head=head.next;
                    current.next=null;
                }
                else {
                    pre.next = current.next;
                    current.next = null;
                }
                return true;
            }
            else {
                pre=current;
                current=current.next;
            }
        }
        return false;
    }

    void traverse(){

        Node<N> temp=head;
        while(temp!=null){
            System.out.print(temp.data+"-->");
            temp=temp.next;
        }
        System.out.println();
    }

    int size(){

        Node<N> temp=head;
        int count=0;
        while(temp!=null){
            temp=temp.next;
            count++;
        }
        return count;
    }

    boolean search(N d){

        Node<N> temp=head;
        while(temp!=null){

            if(temp.data.equals(d))
                return true;
            else
                temp=temp.next;
        }
        return false;
    }
}
