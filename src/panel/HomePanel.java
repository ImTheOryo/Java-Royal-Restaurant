package panel;
import Royal_Restaurant.Restaurant;

import java.util.Scanner;


    public class HomePanel {

        static RestaurantsPanel restaurantsPanel = new RestaurantsPanel();
        static RestaurantPanel restaurantPanel = new RestaurantPanel();

        public static void displayHomePanel() {
            System.out.println("1) Montrer tout les restaurants");
            System.out.println("2) Ajouter un restaurant");
            System.out.println("3) Modifier un restaurant");
            System.out.println("4) Supprimer un restaurant");
            System.out.println("-------------------------------------");
            System.out.println("Enter your choice: ");

            Scanner scanner = new Scanner(System.in);

            String userResponse = scanner.next();
            HomePanel.userHomePanel(userResponse);
        }

        public static void  userHomePanel(String userResponse){
            Scanner scanner = new Scanner(System.in);
            switch (userResponse) {
                case "1":
                    restaurantsPanel.displayAllRestaurants();
                case "2":
                    System.out.println("Entrer le nom du restaurant :");
                    String restaurantName = scanner.nextLine();

                    System.out.println("Entrer l'adresse du restaurant :");
                    String restaurantAddress = scanner.nextLine();

                    Restaurant restaurant = new Restaurant(restaurantName, restaurantAddress);
                    displayHomePanel();
                case "3":
                    restaurantsPanel.allRestaurants();
                    System.out.println("Quel restaurant souhaitez vous modifier ?");
                    String editedRestaurantId = scanner.next();
                    restaurantPanel.displayRestaurantPanel(editedRestaurantId);
                case "4":
                    restaurantsPanel.allRestaurants();
                    System.out.println("Quel restaurant souhaitez vous supprimer ?");
                    String deletedRestaurantId = scanner.next();
                    try{
                        Restaurant.deleteRestaurant(deletedRestaurantId);
                        displayHomePanel();
                    } catch (Exception e){
                        System.out.println("Une erreur est survenue !");
                    }

                default:
                    break;
            }
        }






    }
