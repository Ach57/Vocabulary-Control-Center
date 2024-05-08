import java.util.ArrayList;
//------------------------------------
// Assignment 03
// Name(s): Achraf Cheniti & Ali Sher
// Student IDs: 40244865 & 40255236
// Section: U 2234 and S 2234
//------------------------------------
public class InnerLinkedList {
    /* InnerLinkedList()
     * InnerLinked list uses the class InnerList to create the InnerLinked List 
     * Example [Hockey, racing, puck, running, ball]
     */
        private InnerList head; // Head object of type InnerList
        private int size; // size of the inner Linked list

        /*
         * Default Constructor
         */
        public InnerLinkedList(){
            head = null;
        } // Head of the innerLinkedList

        
        /**
         * @param newValue
         * Method adds to head
         */
        public void addAtHead(String newValue){
            head = new InnerList(newValue, head); // Insert at position 0 the new object of inner list
            size++; // increment size

        }

        /**
         * Method adds at end
         * @param newValue
         */
        public void addAtEnd(String newValue){
            if(head ==null){ // check in case there is not element
                addAtHead(newValue);
            }else{ // in case more than one element
                InnerList position = head;
                while(position.next!=null && !position.value.equals(newValue)){

                    position = position.next;
                }
                if(position.next==null){
                    position.next = new InnerList(newValue, null);
                    size++; // increment the size
                }else{
                    System.out.println("Sorry, the word '"+newValue+"' is already listed");
                }
                
                    

                

            }

        }

        // Add after:
        // If the value does not exist, the adding won't happen
        /**
         * Method that adds after 
         * @param value
         * @param newValue
         */
        public void addAfter(String value, String newValue){
            InnerList position = head;
            while(position!=null && !position.value.equals(value)){
                position = position.next;
            }
            if(position != null){
                position.next = new InnerList(newValue, position.next);
                size++;
            }
        }

        //Remove at head
        /**
         * Remove head
         * @return -1 as a string if the list is empty
         */
        public String removeHead(){ // removes the first element of the linked list
            if(head == null) // check if the head is null
                return "-1";
            else{
                InnerList temp = head;
                head = head.next;
                size --; // decrement the size
                return temp.value;
            }

        }

        //Remove at the end
        /**
         * 
         * @return the value that is removed at the end
         */
        public String removeEnd(){
            if(head == null){ // When there is no elements
                return "-1";
            }else if(size ==1){ // when there is only one element
                String value = head.value;
                head = null;
                size --;
                return value;
            } else{ // when there is more than two elements
                InnerList position = head;
                while(position.next.next!=null){
                    position = position.next;
                }
                String value = position.next.value;
                position.next = null;
                size--;
                return value;

            }

        }
        /**
         * 
         * @param value
         * @return the value that is removed after another value
         */
        public String removeAfter(String value){

            if(head == null){
                return "-1";
            }
            else if(size == 1){
                return "-1"; // there is only one value
            }
            else{
                InnerList position  =head;

                while(position!=null && !position.value.equals(value) ){
                    position = position.next;
                }
                if(position == null || position.next ==null){
                    return "-1";
                }

                String tempValue = position.next.value;
                position.next = position.next.next;
                size --;
                return tempValue;
            }
            
        }

        /**
         * Remove a specific element from the list
         * @param value
         */
        public void removeElement(String value){
            if(head == null)
                System.out.println("Your list is empty!");
            else{
                InnerList previous = null;
                InnerList position = head;

                while(position!=null && !position.value.equals(value)){
                    previous = position;
                    position= position.next;
                }
                if(previous == null){ // it means that i'm at intial position
                    removeHead(); // call remove at head
                    
                }else if(position==null){
                    System.out.println("Sorry, there is no word: "+ value);
                }else{

                    previous.next = previous.next.next;

                    size--;
                }
                    
            }
            

        }
        


        /**
         * Display method of the list
         */
        public void display(){
            int counter=0;
            if(size ==0){
                System.out.println("Your Topic has no elements");
            }else{
                System.out.println("Your innerlist has "+ size+" element(s): ");
                InnerList position = head; // Store the head in a variable position
                
                while(position != null){
                    counter++;
                    System.out.println(position.value+"\t");
                    
                    if(counter%4==0){
                        System.out.println("\n");
                    }
                    position = position.next;// mov
                }
                System.out.println("\n");
                
                

            }

            
        }

        /**
         * Checks if the value exists or not
         * @param value
         * @return
         */
        public boolean isExist(String value){
            if(head == null){
                return false; // value can not be found since there is no element
            }else{
                InnerList position = head;

                while(position!=null && !position.value.equals(value)){
                    position = position.next;

                }
                if(position!=null){ // value found
                    return true;
                }else{
                    System.out.println("The word "+ value+ " is not found");
                    return false; // value not found
                }

                
            }
            
        }
        /**
         * This method is a copy of the isExist except that it doesn't print a value
         * @param value
         * @return
         */
        public boolean Exist(String value){
            if(head == null){
                return false; // value can not be found since there is no element
            }else{
                InnerList position = head;

                while(position!=null && !position.value.equals(value)){
                    position = position.next;

                }
                if(position!=null){ // value found
                    return true;
                }else{
                    return false; // value not found
                }   
            }
        }

        
        /**
         *  Method that replaces a word within a list with a new one
         * @param previousWord
         * @param newWord
         */
        public void replaceWord(String previousWord, String newWord){
            if(head == null){
                return;
            }else{

                InnerList position = head;

                while(position!= null && !position.value.equals(previousWord)){

                    position = position.next;
                }
                if(position!= null){
                    position.value = newWord;
                    System.out.println("Word "+ previousWord+" has been replace with "+ newWord);

                }
            }


        }
        /**
         * Method that filles the arrayList using the char 
         * @param startingLetter
         * @param list
         */
        public void fillArrayList(char startingLetter, ArrayList<String> list){
            if (head==null){
                return ;
            }
            else{
                InnerList position=head;
                while(position!=null ){
                    if (position.value.charAt(0)==startingLetter){
                        list.add(position.value);

                    }
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
                InnerList position = head;
                while(position!= null){
                    result+=position.value+"\n";
                    position = position.next;
                }
                return result;
            }
        }

        /*
        *Class InnerList has two private instant variables String and Node
        * Purpose will be the linked list within the element of the Vocab linked list
        * Example: [ [Sport,[Hokey, racing, puck ], [Food, [candy, hot-dog, cheese] ]
        * 
        *  */ 
        private class InnerList{ // Node Class

        private String value; // private attribute String
        private InnerList next; // private attribute


        /**
         *  Paramaterized Constructor
         * @param value takes a String as Value
         * @param next next node
         */
        public InnerList(String value, InnerList next){

            this.value = value;
            this.next = next;
            
        }

    }
}
