import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class Function1 {
    private static String url = "jdbc:mysql://localhost:3306/my_inventory"; //JDBC URL connection
    private static String username = "root"; //default username in MySQL
    private static String password = ""; //default password in MySQL
    //Method to add an item to inventory table
    void addItem(String item_code, String description, int quantity, String name, int cost){
        String query = "INSERT INTO inventory (item_code, description, quantity, name, Used, cost, total_cost) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try(Connection connection = DriverManager.getConnection(url,username,password);
        PreparedStatement statement = connection.prepareStatement(query)){

            statement.setString(1, item_code); //Converting parameter item_code to SQL item_code VARCHAR
            statement.setString(2, description); //Converting parameter description to SQL description VARCHAR
            statement.setInt(3, quantity); //Converting parameter quantity to SQL quantity INT
            statement.setString(4, name); //Converting parameter name to SQL name VARCHAR
            statement.setInt(5, 0); //Assigning SQL Used to 0 as initial value
            statement.setInt(6, cost); //Converting parameter cost to SQL cost INT
            int total = quantity * cost; //computing the total cost of the item given
            statement.setInt(7, total); //Converting total to SQL total_cost INT
            statement.executeUpdate(); //execution of SQL Query
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    //Method to view inventory
    void viewInventory(){
        String query = "SELECT * FROM inventory";
        try(Connection connection = DriverManager.getConnection(url,username,password);
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery()){
            //if the result set is empty or row is empty
            if(!resultSet.next()){
                System.out.println("NO ITEM IN INVENTORY!!!");
            }
            //if the table has atleast a row existing
            else{
                do{ 
                    //retrieving exisiting data of every column of each row
                    String itemCode = resultSet.getString("item_code"); 
                    String description = resultSet.getString("description"); 
                    int quantity = resultSet.getInt("quantity"); 
                    String name = resultSet.getString("name");
                    int used = resultSet.getInt("Used");
                    int cost = resultSet.getInt("cost");
                    int totalCost = resultSet.getInt("total_cost");
                    //String format(padding) for readability
                    System.out.printf("%-10s %-15s %-5s %-10s %-6s %-5s %s\n", itemCode, description, quantity, name, used, cost, totalCost);
                } while(resultSet.next());
            }
            

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    //Method to dynamically update the tables if an item is used
    void getItem(String name, String itemCode, int quantity){
        //SQL query for inserting data to used_items table
        String insertQuery = "INSERT INTO used_items (used_name, used_item_code, used_quantity) VALUES (?, ?, ?)";
        //SQL query for updating data to inventory table
        String updateQuery = "UPDATE inventory SET Used = ?, quantity = quantity - ? WHERE item_code = ?";
        try(Connection connection = DriverManager.getConnection(url,username,password);
        //creating two statements for each query
        PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
        PreparedStatement updateStatement = connection.prepareStatement(updateQuery)){

            //insering data to used_items table
            insertStatement.setString(1, name); 
            insertStatement.setString(2, itemCode);
            insertStatement.setInt(3, quantity);
            insertStatement.executeUpdate();

            //updating data to inventory table
            updateStatement.setInt(1, quantity);
            updateStatement.setInt(2, quantity);
            updateStatement.setString(3, itemCode);
            updateStatement.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    //Method to view used_items table
    void viewUsedInventory(){
        String query = "SELECT * FROM used_items";
        try(Connection connection = DriverManager.getConnection(url,username,password);
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery()){

            //if the result set is empty or row is empty
            if(!resultSet.next()){
                System.out.println("NO USED ITEMS IN INVENTORY!!!");
            }
            //if the table has atleast a row existing
            else{
                do{
                    //retrieving exisiting data of every column of each row
                    String itemCode = resultSet.getString("used_item_code");
                    int quantity = resultSet.getInt("used_quantity");
                    String name = resultSet.getString("used_name");
                    //String format(padding) for readability
                    System.out.printf("%-10s %-8s %s\n", itemCode, quantity, name);
                } while(resultSet.next());
            }

        } catch(SQLException e){
            e.printStackTrace();
        }

    }
}
