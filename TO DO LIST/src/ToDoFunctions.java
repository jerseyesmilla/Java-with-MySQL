import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

public class ToDoFunctions {
    private static final String url = "jdbc:mysql://localhost:3306/todo_list";
    private static final String username = "root"; //default username in MySQL
    private static final String password = ""; //default password in MySQL

    //Method to add a task
    void newTask(String task){
        //SQL query
        String query = "INSERT INTO tasks (task, is_done) VALUES (?, ?)";
        //Trying to connect the driver and to throw an error if any occurs.
        try (Connection conn = DriverManager.getConnection(url, username, password);
        PreparedStatement statement = conn.prepareStatement(query)){

            statement.setString(1, task); //Converting the Java String task to an SQL VARCHAR 
            statement.setBoolean(2, false); //Assigning is_done value to false as a default
            statement.executeUpdate();
            System.out.println("Task Successfully Added!!");

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    //Method to mark an existing task as done
    void markTaskAsDone(int index){
        //SQL query
        String query = "UPDATE tasks SET is_done = TRUE WHERE id = ?";
        //Trying to connect the driver and to throw an error if any occurs.
        try(Connection conn = DriverManager.getConnection(url,username,password);
        PreparedStatement statement = conn.prepareStatement(query)){

            //Setting the designated Java int index equivalent to SQL id
            //and converting its is_done to TRUE.
            statement.setInt(1, index); 
            int row = statement.executeUpdate();

            //Display message after execution
            System.out.println(row <=0 ? "Invalid Task Number!" : "Task marked as done!"); //shorthand if-else

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    //Method to view all the tasks
    void viewAllTasks(){
        //SQL query
        String query = "SELECT * FROM tasks";
        //Trying to connect the driver and to throw an error if any occurs.
        try (Connection conn = DriverManager.getConnection(url,username,password);
        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery()){

            if(!resultSet.next()){ //if the task is empty or no row existing
                System.out.println("No task available!");
            }
            else{
                //Loop to show all the existing tasks in the database
                do{
                    int id = resultSet.getInt("id");
                    String task = resultSet.getString("task");
                    boolean isDone = resultSet.getBoolean("is_done");
                    System.out.println(id + ". " + (isDone ? "[DONE] " : "[PENDING] ") + task); //Using shorthand if-else to display the tasks
                } while (resultSet.next());
            }
            System.out.println();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    //Method to remove a task
    void removeTask(int index){
        //SQL query
        String query = "DELETE FROM tasks WHERE id = ?";
        //Trying to connect the driver and to throw an error if any occurs.
        try(Connection conn = DriverManager.getConnection(url,username,password);
        PreparedStatement statement = conn.prepareStatement(query)){

            statement.setInt(1, index); //Setting index to SQL id which will delete its row
            int row = statement.executeUpdate();
            
            //Display message after execution
            System.out.println(row > 0 ? "Task " + index + " removed successfully!" : "Invalid task number!"); //shorthand if-else


        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
