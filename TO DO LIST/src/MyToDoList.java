import java.util.Scanner;


public class MyToDoList {
    public static void main(String[]args){

        Scanner scanner = new Scanner(System.in);
        ToDoFunctions function = new ToDoFunctions(); //Instance of ToDoFunctions

        int choice;
        
        //reiterating the to do list options as long as the choice is not == 5
        do{
            System.out.println("**************");
            System.out.println("**TO DO LIST**");
            System.out.println("**************");
            System.out.println("1. Add a task\n2. Mark task as completed\n3. View all tasks\n4. Remove a task\n5. Exit");
            choice = scanner.nextInt();
            scanner.nextLine(); // to consume next line char (\n)

            switch(choice){
                case 1: System.out.println("Enter a task: ");
                        String task = scanner.nextLine();
                        function.newTask(task); //Adding new task
                break;
                case 2: System.out.println("Enter task number: ");
                        int doneIndex = scanner.nextInt();
                        function.markTaskAsDone(doneIndex); //Marking exisitng task as done
                break;
                case 3: System.out.println("**************");
                        System.out.println("***ALL TASK***");
                        System.out.println("**************");
                        function.viewAllTasks(); //Viewing all tasks
                break;
                case 4: System.out.println("Enter task number: ");
                        int removeIndex = scanner.nextInt();
                        function.removeTask(removeIndex); //Removing existing task
                break;
                case 5: System.out.println("Preparing to exit..."); //Exit statement, end of loop
                break;
                default: System.out.println("Invalid number!"); 
                break;
            }

        } while(choice != 5); //end of loop
        scanner.close(); //closing scanner

    }
}
