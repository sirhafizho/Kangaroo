package jumpygrof.github;

public class LinkedList<T extends Comparable <T>> {
    private ListNode head;
    private int length = 0;

    public LinkedList(){
        this.head = null;
    }
    public LinkedList(ListNode head) {
        this.head = head;
    }

    public boolean isEmpty(){
        if(head == null)
        return true;
        return false;
    }
    
    public void addNode(T newdata){
        ListNode newNode = new ListNode(newdata,null);
        if(head==null){
            head = newNode;
        }else{
            ListNode currentNode = head;
            while(currentNode!=null){
                if(currentNode.getLink()!=null)
                currentNode = currentNode.getLink();
                else break;
            }
            currentNode.setLink(newNode);
        }
        length++;
    }

    public void addfrontNode(T newdata){
        if(isEmpty()) addNode(newdata);
        else {
            ListNode newnode = new ListNode(newdata,head);
            head = newnode;
            length++;
        }
    }
    
    public void addbackNode(T newdata){
        addNode(newdata);
    }
    
    public void addsortNode(T newdata) {
        ListNode currentNode = head;
        ListNode newnode;
        if (isEmpty()) {
            head = new ListNode(newdata, null);
            length++;
        } else if (((T) currentNode.getData()).compareTo(newdata) >= 0) {
            if (currentNode == head)
                addfrontNode(newdata);
        } else {
            while (currentNode != null) {
                if (currentNode.getLink() == null) {
                    addbackNode(newdata);
                    break;
                } else if (((T) currentNode.getLink().getData()).compareTo(newdata) >= 0) {
                    newnode = new ListNode(newdata, currentNode.getLink());
                    currentNode.setLink(newnode);
                    length++;
                    break;
                }
                currentNode = currentNode.getLink();
            }
        }
    }
    
    public T atindex(int indexwanted){
        int index = 0;
        ListNode currentNode = head;
        if(indexwanted>=length||indexwanted<0){
            System.out.println("Index number is invalid");
            return null;
        }
        else while(index<=indexwanted){
            if(index!=indexwanted){
                currentNode = currentNode.getLink();
                //System.out.println("masuk");
            }
            else break;
            index++;
        }
        return (T)currentNode.getData();

    }
    
    public void deleteFrontNode(){
        if(head!=null){
            head=head.getLink();
            length--;
        }
        else
            System.out.println("The list is empty.");
    }
    
    public void deleteNode(){
        ListNode currentNode = head;
        ListNode previousNode = head;
        if(head != null){
            if (currentNode.getLink() == null)
                head = null;
            else{
                while (currentNode.getLink() != null){
                    previousNode = currentNode;
                    currentNode = currentNode.getLink();
                }
                previousNode.setLink(null);
            }
            length--;
        }else
            System.out.println("The list is empty");
    }
    
    public void deleteNodeByPosition(int index){
        
        if(index==0)
            deleteFrontNode();
        else if(index==(length-1))
            deleteNode();
        else if(index>=length)
            System.out.println("Invalid index. No node deleted");
        else{
            ListNode currentNode = head;
            for (int i = 1; i < index; i++) {
                currentNode = currentNode.getLink();
            }
            ListNode tempNode = currentNode.getLink();
            currentNode.setLink(tempNode.getLink());
            tempNode=null;
            length--;
        }
    }
    public int length(){
        return length;
    }
    public void showlist(){
        ListNode currentNode = head;
        while(currentNode!=null){
            System.out.println(currentNode.toString());
            if(currentNode.getLink()!=null)
            currentNode = currentNode.getLink();
            else break;
        }
    }
    
    public int searchinlist(T searchdata){
        ListNode currentNode = head;
        //System.out.println(searchdata.toString());
        int amount = 0;
        while(currentNode!=null){
            if(searchdata.compareTo((T)currentNode.getData())==0){
                amount++;
            }
            if(currentNode.getLink()!=null)
            currentNode = currentNode.getLink();
            else break;
        }
        return amount;
    }
}