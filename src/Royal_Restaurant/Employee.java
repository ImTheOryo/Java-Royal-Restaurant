package Royal_Restaurant;

import panel.EmployeesPanel;
import panel.RestaurantPanel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import static Royal_Restaurant.Restaurant.currentDirectory;

    public class Employee extends Employees {
            Directory directory = new Directory();
            RestaurantPanel restaurantPanel = new RestaurantPanel();
            static int curentId = 0;
            private int id;
            private String firstName;
            private String lastName;
            private double salary;
            private String position;
            private String dateEmployment;

            public Employee(String firstName, String lastName, String position, double salary, String dateEmployment, String directoryRestaurant) {
                this.id = directory.getBiggestEmployeeID(directoryRestaurant) + 1;
                this.firstName = firstName;
                this.lastName = lastName;
                this.position = position;
                this.salary = salary;
                this.dateEmployment = dateEmployment;

                Employees.employeeList.add(this);

                try{
                    File file = new File( currentDirectory + "/src/Royal_Restaurant/" + directoryRestaurant + "/employees/", this.id + ".json");
                    boolean fileExisting  = file.createNewFile();

                    String jsonBuilder = "{\"id\":\"" + this.id + "\"," +
                            "\"firstName\":\"" + this.firstName+ "\"," +
                            "\"lastName\":\"" + this.lastName + "\"," +
                            "\"position\":\"" + this.position + "\"," +
                            "\"salary\":\"" + this.salary +"\"," +
                            "\"dateEmployment\":\"" + this.dateEmployment +"\",}";

                    FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
                    fileWriter.write(jsonBuilder);
                    fileWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public static void deleteEmployee(String directoryEmployee, String id ) {
                Path path = Path.of(currentDirectory + "/src/Royal_Restaurant/" + directoryEmployee + "/employees/" + id + ".json");
                try {
                    boolean deleted = Files.deleteIfExists(path);
                } catch (IOException e) {
                    System.out.println("Une erreur est survenue");
                }
            }

            public int getEmployeeId() {
                return id;
            }

            public String getFirstName() {
                return firstName;
            }

            public String getLastName() {
                return lastName;
            }

            public double getSalary() {
                return salary;
            }

            public String getPosition() {
                return position;
            }

            public static void setNewEmployeeChange(String whatToChange, String currentRestaurant, String currentEmployee, String newChange) {
                try{
                    List<String> lines = Files.readAllLines(Paths.get(currentDirectory + "/src/Royal_Restaurant/" + currentRestaurant + "/employees/" + currentEmployee + ".json"));
                    StringBuilder content = new StringBuilder();
                    for (String line : lines) {
                        content.append(line);
                    }

                    String newAddress = content.toString();
                    String target = "\"" + whatToChange + "\"";
                    int targetIndex = newAddress.indexOf(target);

                    if(targetIndex != -1) {
                        int startValue = newAddress.indexOf(":", targetIndex) + 1;
                        int endValue = newAddress.indexOf(",", startValue);
                        if(endValue == -1) {
                            int endvalue = newAddress.indexOf("}", startValue);
                        }
                        System.out.println(newAddress); //TEST
                        String modifiedAddress = newAddress.substring(0, startValue) + "\"" + newChange + "\"" + newAddress.substring(endValue);

                        Files.write(Paths.get(currentDirectory + "/src/Royal_Restaurant/" + currentRestaurant + "/employees/" + currentEmployee + ".json"), modifiedAddress.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
                        EmployeesPanel.displayEmployeesPanel(currentRestaurant);
                    } else {
                        System.out.println("Une erreur est survenue");
                        EmployeesPanel.displayEmployeesPanel(currentRestaurant);
                    }
                } catch (IOException e){
                    System.out.println("L'employé que vous voulez modifier n'existe pas");
                    EmployeesPanel.displayEmployeesPanel(currentRestaurant);

                }
            }
    }
