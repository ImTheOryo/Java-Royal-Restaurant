package panel;

import Royal_Restaurant.Directory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class RestaurantsPanel {

    Directory directory = new Directory();
    public void allRestaurants(){
        File[] allRestaurant = directory.getAllRestaurantDirectory();

        if(allRestaurant.length != 0){

            for (int i = 0; i < allRestaurant.length; i++) {
                StringBuilder jsonContent = new StringBuilder();
                System.out.println("-------------------------------------");
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(allRestaurant[i] + "/info.json"))) {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        jsonContent.append(line).append("\n");
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
                String json = jsonContent.toString().replace("\"", "").replace("}", "").replace("{", "").replace(",", "").replace("name", "\nNom").replace("address", "\nAdresse").trim();
                System.out.println(json); System.out.println("-------------------------------------");
            }
        }
    }

    public void displayAllRestaurants () {
        allRestaurants();
        System.out.println("Press any key to continue");
        Scanner scanner = new Scanner(System.in);

        scanner.next();
        HomePanel.displayHomePanel();
    }
}
