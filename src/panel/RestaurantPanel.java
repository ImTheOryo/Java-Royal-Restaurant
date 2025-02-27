package panel;

import Royal_Restaurant.Directory;
import Royal_Restaurant.Restaurant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

    public class RestaurantPanel {
            Directory directory = new Directory();
            private File restaurantInfo;
            public String restaurantId;
            Scanner scanner = new Scanner(System.in);


            public void displayRestaurantPanel(String restaurantId) {
                this.restaurantId = restaurantId;
                displayRestaurantInfo(restaurantId);
                System.out.println("Que souhaitez vous modifier ?");
                System.out.println("1. Les informations générales du restaurant");
                System.out.println("2. Les employés");
                System.out.println("3. Le menu");
                String userChoice = scanner.nextLine();
                choiceRestaurant(restaurantId, userChoice);
            }

            public  void choiceRestaurant( String restaurantId, String choice) {

                switch (choice) {
                    case "1":
                        getRestaurantinfo();
                        System.out.println("Que souhaitez vous modifier ?");
                        System.out.println("1. Le nom ?");
                        System.out.println("2. L'adresse ?");
                        String userchoice = scanner.nextLine();
                        changeRestaurantInfo(restaurantId, userchoice);
                    case "2":
                        EmployeesPanel.displayEmployeesPanel(restaurantId);
                        break;
                    case "3":
                        MenusPanel.displayMenusPanel(restaurantId);

                    default:
                        displayRestaurantPanel(restaurantId);
                }
            }

            public void getRestaurantinfo() {
                this.restaurantInfo = directory.getSpecificRestaurantDirectory(this.restaurantId);
            }

            public void displayRestaurantInfo(String restaurantId) {
                this.restaurantId = restaurantId;

                int largestDirectoryNumber = directory.getBiggestRestaurantDirectory();
                if (Integer.parseInt(restaurantId) > largestDirectoryNumber) {
                    HomePanel.displayHomePanel();
                }
                getRestaurantinfo();

                StringBuilder jsonContent = new StringBuilder();

                System.out.println("-------------------------------------");

                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(this.restaurantInfo + "/info.json"))) {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        jsonContent.append(line).append("\n");
                    }
                } catch (IOException e){
                    System.out.println("Le fichier n'existe pas !");
                    HomePanel.displayHomePanel();
                }
                String json = jsonContent.toString().replace("\"", "").replace("}", "").replace("{", "").replace(",", "").replace("name", "\nNom").replace("address", "\nAdresse").trim();
                System.out.println(json);
                System.out.println("-------------------------------------");
            }

            public void changeRestaurantInfo(String restaurantId, String userChoice) {
                switch (userChoice) {
                    case "1":
                        System.out.println("Veuillez entrer le nouveau nom :");
                        String newName = scanner.nextLine();
                        Restaurant.setNewValue("name", restaurantId, newName);
                        break;
                    case "2":
                        System.out.println("Veuillez entrer la nouvelle adresse :");
                        String newAddress = scanner.nextLine();
                        Restaurant.setNewValue("address", restaurantId, newAddress);
                        break;
                    default:
                        displayRestaurantInfo(restaurantId);
                        break;
                }

            }

            public String getRestaurantId() {
                return this.restaurantId;
            }
    }
