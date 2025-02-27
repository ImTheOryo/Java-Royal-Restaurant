package Royal_Restaurant;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static Royal_Restaurant.Restaurant.currentDirectory;

    public class Menu extends Menus {
        private int id;
        private String menuName;
        private String menuType;
        private String menuDateCreation;
        Directory directory = new Directory();

        public Menu(String restaurantId, String menuName, String menuType, String menuDateCreation) {
            this.id = directory.getBiggestMenuID(restaurantId) + 1;
            this.menuName = menuName;
            this.menuType = menuType;
            this.menuDateCreation = menuDateCreation;
            Menus.menusList.add(this);

            try {
                File directory = new File( "./src/Royal_Restaurant/" + restaurantId + "/menus/" + this.id);
                boolean directoryExisting  = directory.mkdir();


                String jsonBuilder = "{\"id\":\"" + this.id +"\"," +
                        "\"name\":\"" + this.menuName + "\"," +
                        "\"type\":\"" + this.menuType + "\"," +
                        "\"dateCreation\":\"" + this.menuDateCreation + "\",}";

                File file = new File(  "./src/Royal_Restaurant/" + restaurantId + "/menus/" + this.id + "/", "info.json");
                boolean fileExisting  = file.createNewFile();

                FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
                fileWriter.write(jsonBuilder);
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
