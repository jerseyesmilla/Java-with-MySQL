import java.util.Scanner;

public class Main{
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);
        Function1 func1 = new Function1(); //Instance of Function1

        int choice;
        
        //Main choices that will loop as long as the choice is not equal to 5
        do{
            System.out.println();
            System.out.println("INVENTORY");
            System.out.println();
            System.out.println("1. Add an item\n2. View inventory\n3. Get an item\n4. View used items\n5. Exit");
            choice = scanner.nextInt();
            scanner.nextLine(); //Consumes next line character (\n)
            System.out.println(); //Creates new line for readability
            switch(choice){
                case 1:
                System.out.println("Enter your name: ");
                String name = scanner.nextLine();
                System.out.println("Enter item code: ");
                String itemCode = scanner.nextLine();
                System.out.println("Enter description: ");
                String description = scanner.nextLine();
                System.out.println("Enter quantity: ");
                int quantity = scanner.nextInt();
                System.out.println("Enter cost: ");
                int cost = scanner.nextInt();
                func1.addItem(itemCode, description, quantity, name, cost); //Method to add an item
                System.out.println("Item added successfully!");
                break;
                case 2:
                //String format (padding) for readability when displaying the inventory
                System.out.printf("%-10s %-15s %-5s %-10s %-6s %-5s %s\n", "Item Code", "Description", "Qty", "Name", "Used", "Cost", "Total Cost");
                func1.viewInventory(); //Method to view the inventory
                break;
                case 3:
                System.out.println("Enter your name: ");
                String usedName = scanner.nextLine();
                System.out.println("Enter item code: ");
                String usedItemCode = scanner.nextLine();
                System.out.println("Enter quantity: ");
                int usedQuantity = scanner.nextInt();
                System.out.println("Inventory updated!");
                //Method to use an item in the inventory, this will create another table for used items
                func1.getItem(usedName, usedItemCode, usedQuantity); 
                break;
                ////String format (padding) for readability when displaying the inventory
                case 4: System.out.printf("%-10s %-8s %s\n", "Item Code", "Quantity", "Name");
                //Method to view the used items inventory 
                func1.viewUsedInventory();
                break;
                case 5: System.out.println("Exiting...");
                break;
                default: System.out.println("Invalid input!");
                break;
            }

        } while (choice != 5);
        scanner.close(); //closing the scanner
    }
}