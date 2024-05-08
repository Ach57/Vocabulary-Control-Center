//-------------------------------------
// Assignment 03
// Name(s): Achraf Cheniti & Ali Sher
// Student IDs: 40244865 & 
//-------------------------------------
import java.util.ArrayList;
//The purpose of this class is to create a linked list that contains linked list
public class Vocab {

    private OuterList head; // Head of the linked list
    private OuterList tail; // tail of the linked list
    private int size; // Size of the double linked list

    public int getSize() {
        return this.size;
    }


    public Vocab(){ //define the first head of the list
        head = null;
    } // default constructor


    // Add to head
    /**
     * 
     * @param topic String
     * @param innerLinkedList Linked list
     * 
     */
    public void addToHead(String topic, InnerLinkedList innerLinkedList){
        OuterList oldHead = head;
        head = new OuterList(topic, innerLinkedList, null ,head); // insert at position 0
        
        if(tail == null){ // condition when list has no elements and want to add first element
            tail = head;
        }else{

            oldHead.previous = head;
        }
        size++; // increment size
    }

    //add at tail
    /**
     * 
     * @param topic
     * @param innerLinkedList
     */
    public void addAtTail(String topic, InnerLinkedList innerLinkedList){
        if(tail == null){ // in case there is no element in the list
            head = new OuterList(topic, innerLinkedList, null, null);
            tail = head;

        } else{ // in case there are more than one element
            
            OuterList oldTail = tail;
            tail  = new OuterList(topic, innerLinkedList, tail, null);
            oldTail.next = tail;
        }
        size++;
    }

    
    
    // Add after:
    // If the value does not exist, the adding won't happen
    /**
     * 
     * @param currentValue
     * @param newValue
     * @param newInnerLinkedList
     */
    
    public void addAfterTopic(String currentValue, String newValue, InnerLinkedList newInnerLinkedList){
        if(tail == null){
            return;
        }
         else if(tail.topic.equals(currentValue)){ // current position to add is at head
            addAtTail(newValue, newInnerLinkedList); // call method to add after tail incase the value is at tail and increment
        }
        else{ // value is within the doubly linked list

            OuterList position = head;
            while(position!=null && position.topic!= currentValue){
                position = position.next;
            }
            if(position!= null){ // position found
                OuterList n = new OuterList(newValue, newInnerLinkedList, position, position.next);
                position.next.previous = n;
                position.next = n;

                size++;
            }  
        }
        
    }
    // Add Before a topic
    /**
     * 
     * @param currentValue
     * @param newValue
     * @param newInnerLinkedList
     */
    public void addBeforeTopic(String currentValue, String newValue, InnerLinkedList newInnerLinkedList){
        if(head == null){ // means no elements in the list
            return;
        }
        else if(head.topic.equals(currentValue)){ // case the first element is the head 
            addToHead(newValue, newInnerLinkedList); // call add to head
        } else{ // Value exiting within the linked list
            OuterList position = head;
            
            while(position!= null && !position.topic.equals(currentValue)){

                position = position.next; 
            }

            if(position!=null) { //value found
                // null,element 1, element 2, element 3, element 4, null
                OuterList n = new OuterList(newValue, newInnerLinkedList, position.previous, position);
                position.previous.next = n;
                position.previous = n;
                size++; // increment size 

            }

        }

        }


    // remove head
    /**
     * 
     */
    public void removeHead(){
        if(head == null){
            return;
        } else if (head == tail){ // only one element in the list
            System.out.println("topic: "+head.topic +" is removed.");
            head = null;
            tail = null;
            size=0;
            
        } else{
            System.out.println("topic: "+head.topic +" is removed.");
            head  = head.next;
            head.previous = null;
            size--;
            
        }
    }    
    // remove tail
    /**
     * 
     */
    public void removeTail(){
        if(tail == null){
            return;
        } else if(tail == head){
            System.out.println("topic: "+tail.topic +" is remove.");
            head = null;
            tail = null;
            size =0;
        } else{
            System.out.println("topic: "+tail.topic +" is remove.");
            tail = tail.previous;
            tail.next = null;
        }

    }

    //Add element in inner linked list to a topic with a given index
    /**
     * 
     * @param topic Topic name 
     * @param newValue value to add 
     * @return either -1 or 1 ( 1 if the value added, -1 if not)
     * 
     */
    public void addElementToTopic(String topic, String newValue){
        if(head ==null){ // if no element is in the list
            System.out.println("Your list is empty");
        } else {
            OuterList position = head;
            while(position!= null &&position.topic != topic){
                position = position.next;
            }
            if(position== null) // if the topic not found
                System.out.println("Topic was not found");

            position.innerList.addAtEnd(newValue);  // call addAtEnd method to add new value at the end 
            //implemented only for add At end method
        }

    }

    //Remove element
    /**
     * 
     * @param topic
     * @param removedValue
     * @return
     */
    public int removeElementToTopic(String topic, String removedValue){
        if(head ==null){ // if head is null 
            return -1;
        }else{
            OuterList position = head; 

            while(position!= null && position.topic !=topic){
                position = position.next;
            }
            if(position ==null) //if position reached the end and no topic found
                return -1;
            
            position.innerList.removeElement(removedValue); 

            return 1;
        }

        
    }
    
    // display the topic with the linked list
    // Display from head to tail method
    public void displayFromHeadToTail(){
        if(size ==0){
            System.out.println("Your list is empty");
        }
        else{
            System.out.println("Your outter list has "+ size+" element(s): ");
            OuterList position = head;
            while(position!=null){
                System.out.println(position.topic);
                position.innerList.display(); // call innerList.display method
                position = position.next;
            
            }

        }
    }
    // Display from tail to head
    public void displayFromTailToHead(){
        if(size == 0){
            System.out.println("Your list is empty");
        }else{
            System.out.println("Your outter list has "+ size+" element(s): ");
            OuterList position = tail;
            while(position!=null){
                System.out.println(position.topic);
                position.innerList.display();
                position = position.previous;
            }
        }


    }
    // Remove a topic name from the list
    /**
     * 
     * @param topic
     */
    public void removeTopic(String topic){
        if(head == null){
            return; // list is empty
        } else if(head.topic.equals(topic)){
            removeHead();
        } else if(tail.topic.equals(topic)){
            removeTail();
        } else{ // element exiting within the list
            OuterList position = head;
            while(position.next!=null && !position.next.topic.equals(topic)){
                position = position.next;
            }
            if(position.next!=null){
                
                position.next = position.next.next;
                position.next.previous  =position;
                
                size--;
            }

        }


    }


    //display topic list
    //Purpose of this method to display 
    public void displayTopicList(){
        if(head == null){
            System.out.println("You don't have any topics yet");
        } else{
            System.out.println("------------------------------------");
            System.out.println("\t  Pick a topic");
            System.out.println("------------------------------------");
            int counter = 1;
            OuterList position = head;
            while(position!= null){
                System.out.println(counter + " "+ position.topic);
                counter++;
                position= position.next;
            }
            System.out.println("0 Exit");
            System.out.println("------------------------------------");
            System.out.print("Enter Your Choice: ");
            
        }

    }
    //browse a topic:
    /**
     * 
     * @param index
     */
    public void browseTopic(int index){
        // Check if there is no topic or not:
        if(head ==null){
            System.out.println("Your list is empty");
        }
        else{
            int counter = 1;
            OuterList position = head;
            while(position!=null && counter!=index){ //browse through the list and find the position
                
                position= position.next; // browse through the list
                counter++;

            }
            if(position==null) // topic was not found in this case
                System.out.println("Your entered topic does not exit!");
            
            else{ // topic is found 
                System.out.println("Topic: "+position.topic);
                position.innerList.display();

            }
        }

    }
    /**
     * Checks if the topic exists or not
     * @param topic
     * @return True or false
     */
    public boolean doesTopicExist(String topic){
        OuterList position = head;
        while(position!=null && !position.topic.equalsIgnoreCase(topic)){
            position = position.next;
        }
        if(position!=null){ // topic exists
            return true;
        }else{
            return false; // topic does not exist
        }

    }

    // Check if word within a topic exits:
    /**
     * Checks if a word exists within a specific topic or not
     * @param topic
     * @param value
     * @return true if the words exists and false if it doesn't
     */
    public boolean isExist(String topic, String value){
        if(head ==null){ // empty list
            return false;
        }else{
            OuterList position = head;
            while(position!=null && !position.topic.equals(topic)){
                position = position.next;
            }
            if(position!=null){ // topic found
                return position.innerList.isExist(value); // return true or false
            }
            else{ // not found
                System.out.println("The topic "+ topic +" is not found");
                return false;
            }
            
        }
    }


    // Replace a word wtih a word in a list
    /**
     * Replace a word within a topic
     * @param topic
     * @param previousValue
     * @param newValue
     */
    public void replaceWordinTopic(String topic, String previousValue, String newValue){
        if(head==null){
            System.out.println("Your list is empty");
        }else{
            OuterList position = head;

            while(position!= null && !position.topic.equals(topic)){

                position = position.next;
            }
            if(position!=null){

                position.innerList.replaceWord(previousValue, newValue);
            }else{

                System.out.println("Topic was not found");
            }


        }

    }

    /**
     * Gets the topic name based on the index given
     * @param index
     * @return
     */
    public String getTopicName(int index){
        if(head == null){
            return "-1";
        }else{
            OuterList position = head;
            int counter = 1;
            while(position!= null && counter!=index){
                counter ++;
                position = position.next;
            }
            if(position!=null){
                return position.topic;
            }else{
                return "-1";
            }

        }
        
    }

    // Search topics for a word
    /**
     * 
     * @param value
     * @return
     */
    public String getTopicForWord(String value){
        if(head ==null){
            return "Your list is empty";
        }else{
            OuterList position = head;
            while(position!=null){
                if(position.innerList.Exist(value)){ //Value exists in this topic
                    return "Topic: "+position.topic; // return topic name
                    

                }
                position = position.next;
            }
            return "The word "+ value+ " was not found in any topic";
        }

        

    }

    //Show all words starting with a given letter
    /**
     * 
     * @param givenLetter
     * @param list
     */
    public void showWordsWithGivenLetter(char givenLetter, ArrayList<String> list){
        if(head == null){
            System.out.println("Your list is empty");
        }else{
            OuterList position = head;

            while(position!=null){
                
                position.innerList.fillArrayList(givenLetter, list);

                position = position.next;
            }

        }


    }
    
    @Override
    public String toString(){
        if(head == null){
            return "Your list is empty";
        }else{
            String result = "";
            OuterList position = head;
            while(position!=null){
                result+= "#"+position.topic+"\n";
                result+=position.innerList.toString()+"\n";
                position= position.next;
            }
            return result;
        }
        
    }

    
    /*
     *OuterList class with instant attributes String topic and InnerlinkedList 
     *Objective: Obtain a linked list in this format [ [Sport,[Hokey, racing, puck ], [Food, [candy, hot-dog, cheese] ]
     */
    private class OuterList{ // Node class

        private String topic;
        private InnerLinkedList innerList;
        private OuterList next;
        private OuterList previous;

       
        /**
         * 
         * @param topic
         * @param innerList
         * @param previous
         * @param next
         */
        public OuterList(String topic, InnerLinkedList innerList, OuterList previous, OuterList next){
            this.topic  =topic;
            this.innerList = innerList;
            this.next = next;
            this.previous = previous;

        }
    }


   
}
