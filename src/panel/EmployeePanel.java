package panel;

import Royal_Restaurant.Directory;
import Royal_Restaurant.Employee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class EmployeePanel {

        public static void addEmployee(String currentDirectory){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            Scanner scanner = new Scanner(System.in);

            double salary = 1;

            System.out.println("Prénom de l'employé :");
            String firstName = scanner.nextLine();

            System.out.println("Nom de l'employé :");
            String lastName = scanner.nextLine();

            System.out.println("Position de l'employé :");
            String position = scanner.nextLine();

            System.out.println("Salaire :");
            try{
                salary = scanner.nextDouble();
            } catch (Exception e){
                System.out.println("Veuillez saisir un salaire valide !");
                addEmployee(currentDirectory);
            }
            scanner.nextLine();
            System.out.println("Date d'embauche :");
            String date = scanner.nextLine();

            try{
               date = String.valueOf(LocalDate.parse(date, formatter));
            } catch (Exception e){
                System.out.println("Erreur le date");
                addEmployee(currentDirectory);
            }
            Employee employee = new Employee(firstName, lastName, position, salary, date, currentDirectory);
            EmployeesPanel.displayEmployeesPanel(currentDirectory);

        }

        public static void displayEmployeeToModify (String currentDirectory, String idEmployee) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Que voulez-vous modifier ?");
            System.out.println("1) Le nom");
            System.out.println("2) Le prénom");
            System.out.println("3) La position");
            System.out.println("4) Le salaire");
            System.out.println("5) Le date d'embauche");
            String choice = scanner.nextLine();
            userChoiceToModify(currentDirectory, idEmployee, choice);
        }

        public static void userChoiceToModify(String currentdirectory, String idEmployee, String userChoice ) {
            Scanner scanner = new Scanner(System.in);

            switch (userChoice) {
                case "1":
                    System.out.println("Nouveau nom :");
                    String newLastName = scanner.nextLine();
                    Employee.setNewEmployeeChange("lastName", currentdirectory, idEmployee, newLastName);
                case "2":
                    System.out.println("Nouveau prénom :");
                    String newFirstName = scanner.nextLine();
                    Employee.setNewEmployeeChange("firstName", currentdirectory, idEmployee, newFirstName);
                case "3":
                    System.out.println("Nouvelle position :");
                    String newPosition = scanner.nextLine();
                    Employee.setNewEmployeeChange("position", currentdirectory, idEmployee, newPosition);
                case "4":
                    System.out.println("Nouveau salaire :");
                    String newSalary = scanner.nextLine();
                    Employee.setNewEmployeeChange("salary", currentdirectory, idEmployee, newSalary);
                case "5":
                    System.out.println("Nouvelle date de embauche :");
                    String newDate = scanner.nextLine();
                    Employee.setNewEmployeeChange("dateEmployment", currentdirectory, idEmployee, newDate);
                default:
                    EmployeesPanel.displayEmployeesPanel(currentdirectory);

            }
        }
    }