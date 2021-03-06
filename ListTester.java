import java.io.*;
import java.util.Scanner;

public class ListTester {  //I understand making a separate function for this is not necessary, but I feel it keeps my code cleaner :)
  
  public ListTester(){
  }
  public static ListMan lm1 = new ListMan();
  public void run() {
    // Make the list manager.
    
    final String fileName = "magicitems.txt";
    
    if(loaded==false){
      this.readMagicItemsFromFileToList(fileName, lm1);
      loaded=true;
    }
    
     // Declare an array for the items.
        ListItem[] items = new ListItem[lm1.getLength()];
        readMagicItemsFromFileToArray(fileName, items);
        selectionSort(items);
    
    // Ask player for an item.
    Scanner inputReader = new Scanner(System.in);
    String targetItem = new String();
    targetItem = inputReader.nextLine();
    if (        targetItem.equalsIgnoreCase("north") || targetItem.equalsIgnoreCase("n") ) {
      direction = 3;
    } else if ( targetItem.equalsIgnoreCase("south") || targetItem.equalsIgnoreCase("s") ) {
      direction = 3;
    } else if ( targetItem.equalsIgnoreCase("east")  || targetItem.equalsIgnoreCase("e") ) {
      direction = 0;
    } else if ( targetItem.equalsIgnoreCase("west")  || targetItem.equalsIgnoreCase("w") ) {
      direction = 3;
    }
    System.out.println();
    if(direction<0){
      ListItem li = new ListItem();
      li = sequentialSearch(lm1, targetItem);
      if (li != null) {
        System.out.println(li.toString());
      }
    }
  }
  
  public int getDirection(){
    return this.direction;
  }
  
  public double getCost(){
    return this.cost;
  }
  
  public String getName(){
    return this.name;
  }
  
  // Private
  
  private ListItem sequentialSearch(ListMan lm,
                                    String target) {
    ListItem retVal = null;
    int counter = 0;
    ListItem currentItem = new ListItem();
    currentItem = lm.getHead();
    boolean isFound = false;
    while ( (!isFound) && (currentItem != null) ) {
      counter = counter +1;
      if (currentItem.getName().equalsIgnoreCase(target)) {
        // We found it!
        isFound = true;
        retVal = currentItem;
      } else {
        // Keep looking.
        currentItem = currentItem.getNext();
      }
    }
    if (isFound) {
      this.cost=currentItem.getCost();
      this.name=target;
      return  currentItem;
    } else {
      System.out.println("I apologize.  We do not have that item in stock.");
    }
    
    return retVal;
  }
  
      private static void readMagicItemsFromFileToArray(String fileName,
                                                      ListItem[] items) {
        File myFile = new File(fileName);
        try {
            int itemCount = 0;
            Scanner input = new Scanner(myFile);

            while (input.hasNext() && itemCount < items.length) {
                // Read a line from the file.
                String itemName = input.nextLine();

                // Construct a new list item and set its attributes.
                ListItem fileItem = new ListItem();
                fileItem.setName(itemName);
                fileItem.setCost(Math.random() * 100);
                fileItem.setNext(null); // Still redundant. Still safe.

                // Add the newly constructed item to the array.
                items[itemCount] = fileItem;
                itemCount = itemCount + 1;
            }
            // Close the file.
            input.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found. " + ex.toString());
        }
    }

  
  private void readMagicItemsFromFileToList(String fileName,
                                            ListMan lm) {
    File myFile = new File(fileName);
    try {
      Scanner input = new Scanner(myFile);
      while (input.hasNext()) {
        // Read a line from the file.
        String itemName = input.nextLine();
        
        // Construct a new list item and set its attributes.
        ListItem fileItem = new ListItem();
        fileItem.setName(itemName);
        fileItem.setCost(Math.random() * 30);
        fileItem.setNext(null); // Still redundant. Still safe.
        
        // Add the newly constructed item to the list.
        lm.add(fileItem);
      }
      // Close the file.
      input.close();
    } catch (FileNotFoundException ex) {
      System.out.println("File not found. " + ex.toString());
    }
    
  }
  
  private static void selectionSort(ListItem[] items) {
        for (int pass = 0; pass < items.length-1; pass++) {
            // System.out.println(pass + "-" + items[pass]);
            int indexOfTarget = pass;
            int indexOfSmallest = indexOfTarget;
            for (int j = indexOfTarget+1; j < items.length; j++) {
                if (items[j].getName().compareToIgnoreCase(items[indexOfSmallest].getName()) < 0) {
                    indexOfSmallest = j;
                }
            }
            ListItem temp = items[indexOfTarget];
            items[indexOfTarget] = items[indexOfSmallest];
            items[indexOfSmallest] = temp;
        }
    }
  
  //PrivatesL
  private double cost = 0;
  private String name = null;
  private int direction = -1;
  private boolean loaded = false;
}















