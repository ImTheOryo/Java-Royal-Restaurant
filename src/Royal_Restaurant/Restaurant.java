package Royal_Restaurant;

import panel.RestaurantPanel;
import panel.RestaurantsPanel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class Restaurant extends Restaurants {



        static  String currentDirectory = System.getProperty("user.dir");
        private int id;
        private String name;
        private static String address;


        public Restaurant(String name, String address) {
            Directory directory = new Directory();

            this.id = directory.getBiggestRestaurantDirectory()+ 1;
            this.name = name;
            this.address = address;

            try{
                File directoryFile = new File(currentDirectory + "/src/Royal_Restaurant/",this.id + "");
                boolean directoryExists = directoryFile.mkdir();

                File employeeDirectory = new File(currentDirectory + "/src/Royal_Restaurant/",this.id + "/employees");
                boolean employeeDirectoryExists = employeeDirectory.mkdir();

                File menueDirectory = new File(currentDirectory + "/src/Royal_Restaurant/",this.id + "/menus");
                boolean menueDirectoryExists = menueDirectory.mkdir();

                File commandDirectory = new File(currentDirectory + "/src/Royal_Restaurant/",this.id + "/commands");
                boolean commandDirectoryExists = commandDirectory.mkdir();

                File file = new File( currentDirectory + "/src/Royal_Restaurant/" + this.id ,"info.json");
                System.out.println(file.getAbsoluteFile());
                boolean fileExisting  = file.createNewFile();

                StringBuilder jsonBuilder = new StringBuilder();
                jsonBuilder.append("{\"id\":\"" + this.id + "\",\"name\":\"" + this.name + "\",\"address\":\"" + this.address + "\",}");
                FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
                fileWriter.write(String.valueOf(jsonBuilder));
                fileWriter.close();

            } catch (IOException e){
                e.printStackTrace();
            }
            Restaurants.restaurantList.add(this);
        }

        public int getIdRestaurant() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public static void deleteRestaurant(String idRestaurant) throws IOException {
            Path path = Paths.get(currentDirectory + "/src/Royal_Restaurant/" + idRestaurant);
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        }

        public static void setNewValue(String whatToChange, String id, String valueToChange) {
            RestaurantPanel restaurantPanel = new RestaurantPanel();
            try{
               List<String> lines = Files.readAllLines(Paths.get(currentDirectory + "/src/Royal_Restaurant/" + id + "/info.json"));
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
                   String modifiedAddress = newAddress.substring(0, startValue) + "\"" + valueToChange + "\"" + newAddress.substring(endValue);

                   Files.write(Paths.get(currentDirectory + "/src/Royal_Restaurant/" + id + "/info.json"), modifiedAddress.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
                   restaurantPanel.displayRestaurantPanel(id);
               } else {
                   System.out.println("Une erreur est survenue");
               }
           } catch (IOException e){
               e.printStackTrace();
               restaurantPanel.displayRestaurantPanel(id);
           }
        }

    }
