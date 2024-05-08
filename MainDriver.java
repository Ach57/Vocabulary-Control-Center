import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
//------------------------------------
// Assignment 03
// Name(s): Achraf Cheniti & Ali Sher
// Student IDs: 40244865 & 40255236
// Section: U 2234 and S 2234
//------------------------------------
public class MainDriver {

    /*
     * displayMenu(): Method takes no paramter
     * Purpose: Displays the menu whenever it is called
     * Efficiency: Reduce number of repitions within the program
     */
    public static void displayMenu(){
        System.out.println("------------------------------------");
        System.out.println(" Vocabulary Control Center");
        System.out.println("------------------------------------");
        System.out.println("1.  browse a topic");
        System.out.println("2.  insert a new topic before another one");
        System.out.println("3.  insert a new topic after another one");
        System.out.println("4.  remove a topic");
        System.out.println("5.  modify a topic");
        System.out.println("6.  search topics for a word");
        System.out.println("7.  load from file");
        System.out.println("8.  show all words starting with a given letter");
        System.out.println("9.  save to file");
        System.out.println("0.  exit");
        System.out.println("------------------------------------");
        System.out.print("Enter your choice: ");

    }

    /**
     * 
     * @param keyboard: Scanner
     * @param listOfWords : List of words with type Vocab
     * @param innerList: inner list with type InnerLinkedlist
     * 
     *  Purpose: The method loadFromFile takes as parameter the three variables declared
     * it wokrs by reference 
     * by the time it's done, listOfWords should have all the data loaded from the .txt file given
     * 
     */
    public static void loadFromFile(Scanner keyboard, Vocab listOfWords, InnerLinkedList innerList ){
        
        String fileName ; // file name entered by the user
        Scanner fileReader = null;
        System.out.println("Enter the name of the input file:");
        fileName = keyboard.nextLine();
        try{ // handle checked exception FileNotFoundException
            fileReader = new Scanner(new FileInputStream(fileName)); // read .txt file entered by the user 
            
        }catch(FileNotFoundException e){
            System.out.println("Could not find the given file");
            System.out.println("Program will terminate . . . ");
            System.exit(0);

        }catch(Exception e){
            System.out.println("Different exception orccured at loadFromFile(): "+ e.getMessage()); // get type of different exception for debug
            System.exit(0); // exit program
        }

        String topicName="";
        while(fileReader.hasNextLine()){
            String nextline = fileReader.nextLine();
            if(!nextline.equals("") && nextline.charAt(0) =='#'){
                nextline = nextline.substring(1, nextline.length()); // get the name of the topic
                topicName = nextline; // Store topic name in here
            }
            if(!nextline.equals("")&& !nextline.equals(topicName))
                innerList.addAtEnd(nextline);
            if(nextline.equals("") || fileReader.hasNextLine()==false){
                listOfWords.addAtTail(topicName, innerList);
                innerList = new InnerLinkedList();
            }
            
        }


        
        System.out.println("Done reading . . .");
        
    }
    /**
     * The purpose of this method is to handle exception of an int variable and 
     * ensures that the number is within the range
     * @param keyboard
     * @param limit
     * @return the integer
     */
    public static int choiceExceptionHandler(Scanner keyboard, int limit){
        int choice = -1;
        do{
            try{ // handling unchecked exception 
                choice = keyboard.nextInt();
                if(choice>limit || choice<0)
                    System.out.println("Number has to be between 1 and "+ limit);

            }catch(InputMismatchException e){ // case the user enters a string instead of number
                System.out.println("Number has to be between 1 and "+ limit);
                choice =-1;
                keyboard.nextLine();
            }catch(Exception e){ // handle different other type of exception by exiting program 
                System.out.println("different type of exception occured while getting choice "+ e.getMessage());
                System.exit(0);
            }
        }while(choice <0 || choice>limit);
        return choice;
    }
    /**
     * Purpose of this method is to handle case 9 where it saves the new words to a file
     * @param keybord
     * @param listOfWords Vocab list
     */
    public static void saveToFile(Scanner keybord, Vocab listOfWords){
        if(listOfWords.getSize() !=0){
            System.out.println("Please enter the file name you want to save the data to :");
            String fileName = keybord.nextLine();
            PrintWriter fileOutput=null; // ;) 
            try{

                fileOutput = new PrintWriter(new FileOutputStream(fileName)); // Writing to the file


            } catch(FileNotFoundException e){
                System.out.println("Could not open the file.\nSystem will exist. . .");
                System.exit(0);
            
            } catch(Exception e){
                System.out.println("Different kind of exception ocurred at saveToFile "+ e.getMessage()+"\nSystem will exit . . .");
                System.exit(0);
            }

            fileOutput.write(listOfWords.toString());

            fileOutput.close();

            System.out.println("File "+ fileName+ " has been created.");

        } else{
            System.out.println("You don't have any Topics yet to save.");
        }
        
    }
    /**
     * DisplayTopicMenu Method
     */
    public static void displayTopicsMenu(){
        System.out.println("------------------------------------");
        System.out.println("\t   Modify Topics Menu");
        System.out.println("------------------------------------");
        System.out.println("a add a word");
        System.out.println("r remove a word");
        System.out.println("c change a word");
        System.out.println("0 Exit ");
        System.out.println("------------------------------------");
        System.out.print("Enter your choice: ");

    }


    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in); // scanner
        int choice=-1; // choice entered by the user

        Vocab listOfWords = new Vocab(); //Outer linked list
        InnerLinkedList innerList = new InnerLinkedList(); // Innerlinked list object
        ArrayList<String> arrayOfWords = new ArrayList<String>(20);
        do{
            
            displayMenu(); // call to display the menu
            choice = choiceExceptionHandler(keyboard, 9); // handle the choice

            switch(choice){ //handle choice in switch

                case 1: // browse a topic
                listOfWords.displayTopicList(); // number of topics in limit
                int limit = listOfWords.getSize(); // size of the list 
                
                if(limit!=0){
                    choice = choiceExceptionHandler(keyboard, limit);
                    if(choice==0){
                        choice=-1;
                        break;
                    }
                    // choice should have the option entered by the user to browse the topic
                    listOfWords.browseTopic(choice); // Call method browse topic 
                    choice=-1;

                }                
                

                break;

                case 2: // insert a new topic before another one
                    //Check if the topic already exists
                    listOfWords.displayTopicList(); // call method from Vocab Class
                    limit = listOfWords.getSize(); // size of the list 
                    if(limit!=0){ // check if the list is empty or not
                        choice = choiceExceptionHandler(keyboard, limit);
                        if(choice ==0){ // break out 
                            choice =-1;
                            break;
                        }
                        String selectedTopic = listOfWords.getTopicName(choice);

                        keyboard.nextLine();
                        System.out.println("Enter a topic name: ");
                        String topic = keyboard.nextLine();
                        boolean isThere = listOfWords.doesTopicExist(topic); // check if topic exists
                        // In this part, we make sure that the topic is there or not so that the user doesn't enter an existing one
                        while(isThere){
                            System.out.println("This topic already exists");
                            System.out.print("Try again: ");
                            topic = keyboard.nextLine();
                            isThere = listOfWords.doesTopicExist(topic);

                        }
                        if(topic.equals("")){
                            System.out.println("You can't enter a topic with no name");
                            break;
                        }
                        String temp = topic;
                        System.out.println("Enter a word - to quit press Enter: ");
                        innerList = new InnerLinkedList();
                        while(topic!=""){
                            topic = keyboard.nextLine();
                            if(!topic.equals(""))
                                innerList.addAtEnd(topic);
                        }
                        listOfWords.addBeforeTopic(selectedTopic, temp, innerList);
                        System.out.println("New topic "+ topic +"added before "+ selectedTopic);
                        choice =-1;

                    }
                    
                break;

                case 3: //insert a new topic after another one
                    listOfWords.displayTopicList();
                    limit = listOfWords.getSize();

                    if(limit!= 0){

                        choice = choiceExceptionHandler(keyboard, limit);
                        if(choice ==0){
                            choice=-1;
                            break;
                        }
                        String selectedTopic = listOfWords.getTopicName(choice);
                        keyboard.nextLine();
                        System.out.println("Enter a topic name: ");
                        String topic = keyboard.nextLine();
                        boolean isThere = listOfWords.doesTopicExist(topic);
                        while(isThere){
                            System.out.println("This topic already exists");
                            System.out.print("Try again: ");
                            topic = keyboard.nextLine();
                            isThere = listOfWords.doesTopicExist(topic);

                        }
                        String temp = topic;
                        System.out.println("Enter a word - to quit press Enter: ");
                        innerList = new InnerLinkedList();
                        while(topic!=""){
                            topic = keyboard.nextLine();
                            if(!topic.equals(""))
                                innerList.addAtEnd(topic);
                        }
                        listOfWords.addAfterTopic(selectedTopic, temp, innerList);
                        System.out.println("New topic "+ topic +"added after "+ selectedTopic);
                        //choice =-1;
                    }


                break;

                case 4: // remove a topic
                    listOfWords.displayTopicList();
                    limit = listOfWords.getSize(); // get size of the double linked list
                    if(limit!= 0){
                        choice = choiceExceptionHandler(keyboard, limit);
                        if(choice==0){
                            choice=-1;
                            break;
                        }
                            
                        String selectedTopic = listOfWords.getTopicName(choice);
                        keyboard.nextLine();
                        listOfWords.removeTopic(selectedTopic);
                        choice = -1;
                    }
                break;

                case 5: //modify a topic
                    listOfWords.displayTopicList();
                    limit = listOfWords.getSize();// number of elements
                    
                    if(limit!=0){
                        choice = choiceExceptionHandler(keyboard, limit);
                        if(choice==0){
                            choice = -1;
                            break;
                        }
                            
                        String selectedTopic = listOfWords.getTopicName(choice);

                        displayTopicsMenu(); // call method
                        keyboard.nextLine();
                        char input='\0'; // empty char
                        do{
                            String temp = keyboard.nextLine();
                            if(temp.equals("")){
                               break; 
                            }else{
                                input = temp.charAt(0);
                            if(input!=0 && input!='a'&& input!='r' && input!='c')
                                System.out.print("Incorrect option\nAttempt Again: ");
                            switch(input){
                                case 'a': // add a word
                                    System.out.println("Type a word and press Enter, or press Enter to end input");
                                    String newValue = keyboard.nextLine();
                                    if(!newValue.equals("")){
                                        listOfWords.addElementToTopic(selectedTopic, newValue); 
                                    }
                                break;

                                case 'r': // remove a word
                                    System.out.println("Enter a word:");
                                    newValue = keyboard.nextLine();
                                    if(!newValue.equals("")){
                                        listOfWords.removeElementToTopic(selectedTopic, newValue); 
                                    }
                                break;

                                case 'c': // change a word
                                    System.out.println("Enter the word you want to change: ");
                                    newValue = keyboard.nextLine();
                                    if(!newValue.equals("")){
                                        if(listOfWords.isExist(selectedTopic, newValue)){ // true means value found
                                            System.out.println("Current word "+newValue+" - Enter new word:");
                                            String previous = newValue; // Storing newValue in temp
                                            newValue = keyboard.nextLine(); // accept new value
                                            if(!newValue.equals("")){ 
                                                listOfWords.replaceWordinTopic(selectedTopic,previous,newValue);
                                            }
                                        }
                                    }
                                break;

                                case '0':
                                    System.out.println("Back to main menu . . .");
                                break;


                            }
                            
    
                            }
                            }while(input!='0' && input!='a'&& input!='r' && input!='c');

                    }

                break;

                case 6: // search a topic for a word
                    limit = listOfWords.getSize();
                    keyboard.nextLine();
                    if(limit!=0){
                        System.out.println("Enter your word - press enter to return to menu");
                        String word= keyboard.nextLine();
                        if(!word.equals("")){
                            System.out.println(listOfWords.getTopicForWord(word));
                        }


                    }else{
                        System.out.println("Your list is empty");
                    }


                break;

                case 7: //load from a file
                listOfWords = new Vocab();
                keyboard.nextLine();
                loadFromFile(keyboard, listOfWords, innerList); // pass by reference 
                
                break;

                case 8: //show all words starting with a given letter
                    limit = listOfWords.getSize();
                    if(limit!=0){
                        System.out.println("Enter the letter - Press Enter to return back ");
                        keyboard.nextLine();
                        String value = keyboard.nextLine();
                        if(!value.equals("")){
                            char letter = value.charAt(0);
                        
                            listOfWords.showWordsWithGivenLetter(letter, arrayOfWords);
                            System.out.println(arrayOfWords);
                            arrayOfWords.clear();
                        }
                        
                        
                    }else{
                        System.out.println("Your list is empty");
                    }
                break;

                case 9: //save a file 
                keyboard.nextLine();
                saveToFile(keyboard, listOfWords);
                break;

            }
            
            
            
        } while(choice!=0);
        System.out.println("Thank you! Bye now . . . "); // end of message



        keyboard.close(); // closing scanner
    }
}

