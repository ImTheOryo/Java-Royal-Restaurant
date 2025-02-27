package panel;

import Royal_Restaurant.Directory;
import Royal_Restaurant.Employee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class EmployeesPanel {

        public static void displayEmployeesPanel(String currentDirectory) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Que souhaitez vous faire ?");
            System.out.println("1. Afficher tout les employées");
            System.out.println("2. Ajouter un employé");
            System.out.println("3. Modifier un employé");
            System.out.println("4. Supprimer un employé");
            System.out.println("5. Retourner en arrière");
            String choice = scanner.nextLine();
            userEmployeeChoicePanel(choice,currentDirectory);
        }

        public static void userEmployeeChoicePanel(String choice, String directoryRestaurant) {
            Scanner scanner = new Scanner(System.in);

            switch (choice){
                case "1":
                    displayAllEmployees(directoryRestaurant);
                    System.out.println("Appuyez sur une touche pour sortir");
                    scanner.nextLine();
                    displayEmployeesPanel(directoryRestaurant);
                    break;
                case "2":
                    EmployeePanel.addEmployee(directoryRestaurant);
                    break;
                case "3":
                    displayAllEmployees(directoryRestaurant);
                    System.out.println("Quel employé voulez-vous modifier ?");
                    String userToModify = scanner.nextLine();
                    EmployeePanel.displayEmployeeToModify(directoryRestaurant,userToModify);
                case "4":
                    displayAllEmployees(directoryRestaurant);
                    System.out.println("Quel employé voulez-vous supprimer ?");
                    String userDelete = scanner.nextLine();
                    Employee.deleteEmployee(directoryRestaurant, userDelete);
                case "5":

                default: displayEmployeesPanel(directoryRestaurant);
                    break;
            }
        }


        public static void displayAllEmployees(String directoryRestaurant) {
            Directory directory = new Directory();
            File [] allEmployees = directory.getAllEmployee(directoryRestaurant);

            if(allEmployees.length != 0){
                System.out.println("-------------------------------------");
                for (int i = 0; i < allEmployees.length; i++) {
                    StringBuilder jsonContent = new StringBuilder();

                    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(allEmployees[i]))) {
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            jsonContent.append(line).append("\n");
                        }
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                    String json = jsonContent.toString().replace("\"", "").replace("}", "").replace("{", "").replace(":", ": ").replace(",", "").replace("firstName", "\nPrénom").replace("lastName", "\nNom de famille").replace("position", "\nPosition").replace("salary", "\nSalaire mensuel").replace("dateEmployment", "€\nDate d'embauche").trim();
                    System.out.println(json); System.out.println("-------------------------------------");
                }
            }
        }
    }
