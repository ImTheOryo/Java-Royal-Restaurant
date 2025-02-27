package panel;

import java.time.LocalDate;
import java.util.Scanner;
import Royal_Restaurant.Menu;
public class MenusPanel {

        public static void displayMenusPanel(String restaurantId) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Que souhaitez vous faire ?");
            System.out.println("1. Afficher tous les menus");
            System.out.println("2. Ajouter un menu");
            System.out.println("3. Modifier un menu");
            System.out.println("4. Quitter");
            String choice = scanner.nextLine();
            menusChoicePanel(restaurantId, choice);
        }
        public static void menusChoicePanel(String restaurantId, String choice) {
            Scanner scanner = new Scanner(System.in);

            switch (choice) {
                case "1":
                    System.out.println("Appuyez sur une touche pour sortir");
                    choice = scanner.nextLine();
                    displayMenusPanel(restaurantId);
                case "2":
                    System.out.println("Nom du menu");
                    String menuName = scanner.nextLine();
                    System.out.println("Type de menu");
                    String menuType = scanner.nextLine();
                    String date = LocalDate.now().toString();
                    Menu menu = new Menu(restaurantId, menuName, menuType, date);
                    displayMenusPanel(restaurantId);
                    break;
                case "4":

            }
        }

        public void displayAllMenusFromRestaurant(String restaurantId) {

        }
    }
