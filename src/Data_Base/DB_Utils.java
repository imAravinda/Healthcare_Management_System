package Data_Base;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
public class DB_Utils {
	public static void changeScene(ActionEvent event, String fxmlFile, String title, String username){
        Parent root=null;

        if(username!=null){
            try{
                FXMLLoader loader= new FXMLLoader(DB_Utils.class.getResource(fxmlFile));
                root= loader.load();

            }catch(IOException e){
                e.printStackTrace();
            }
        }else{
            try{
                root=FXMLLoader.load(DB_Utils.class.getResource(fxmlFile));
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root,1000,600));
        stage.show();
    }

    public static void signUserUp(ActionEvent event, String username, String password, String firstname, String lastname){
        Connection connection=null;
        PreparedStatement psInsert=null;
        PreparedStatement psCheckUserExists=null;

        ResultSet resultSet=null;

        try{
            connection= DriverManager.getConnection("jdbc:mysql://localhost/code_hub","root","");
            psCheckUserExists=connection.prepareStatement("SELECT * FROM user_acc WHERE username=?");
            psCheckUserExists.setString(1,username);
            resultSet= psCheckUserExists.executeQuery();


            if(resultSet.isBeforeFirst()){
                System.out.println("USer already exists !");
                Alert alert= new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username");
                alert.show();
            }else{
                psInsert=connection.prepareStatement("INSERT INTO user_acc values (?, ?, ? ,?)");
                psInsert.setString(1,firstname);
                psInsert.setString(2,lastname);
                psInsert.setString(3,username);
                psInsert.setString(4,password);

                System.out.println(psInsert);

                psInsert.executeUpdate();

                changeScene(event,"CoursesView.fxml","Courses",username);
            }


        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet!=null){
                try{
                    resultSet.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }

            if(psCheckUserExists!=null){
                try{
                    psCheckUserExists.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }

            if(psInsert!=null){
                try{
                    psInsert.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }

            if(connection!=null){
                try{
                    connection.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }

    }

    public static void logInUser(ActionEvent event,String username,String password){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;

        try{
            connection= DriverManager.getConnection("jdbc:mysql://localhost/code_hub","root","");
            preparedStatement= connection.prepareStatement("SELECT password,firstName FROM user_acc WHERE username= ?");
            preparedStatement.setString(1,username);
            resultSet=preparedStatement.executeQuery();

            if(!resultSet.isBeforeFirst()){
                System.out.println("User not found in the database !");
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Incorrect username or password");
                alert.show();
            }else{
                while(resultSet.next()){
                    String retrivedPassword=resultSet.getString("password");
                    String retrievedFistName=resultSet.getString("firstName");

                    System.out.println("password is correct");

                    if(retrivedPassword.equals(password)){
                        changeScene(event,"CoursesView.fxml","Courses",username);
                    }else{
                        System.out.println("Passwords did not match");
                        Alert alert=new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Invalid username or password");
                        alert.show();
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet!=null){
                try{
                    resultSet.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }

            if(preparedStatement!=null){
                try{
                    preparedStatement.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }

            if(connection!=null){
                try{
                    connection.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
