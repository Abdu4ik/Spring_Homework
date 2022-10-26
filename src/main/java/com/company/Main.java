package com.company;
import com.company.entity.Task2;
import com.company.util.ScannerUtil;
import java.sql.*;

import static com.company.info.Info.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("ismatov shaxzod");


        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement() ) {
            while (true){
            String choice = mainMenu();
            if (choice.equals("0")) break;
                switch (choice){
                    case "1" -> task2("select maker, model, price from product join pc using(model)");
                    case "2" -> task2("select maker, model, price from product join laptop using(model)");
                    case "3" -> task2("select maker, model, price from product join printer using(model)");
                    default -> System.err.println("Invalid option!");
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void task2(String query){

        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){

                Task2 product = new Task2(resultSet.getString("model"), resultSet.getString("maker"), resultSet.getBigDecimal("price"));
                System.out.println(product);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static String mainMenu(){
        System.out.println(" 1 -> PC" +
                "\n 2 -> Laptop" +
                "\n 3 -> Printer" +
                "\n 0 -> Exit");

        System.out.println();
        System.out.print("Choose one of the options: ");

        return ScannerUtil.TEXT.nextLine();
    }

}
