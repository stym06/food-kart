package org.foodkart;

import org.foodkart.controllers.RestaurantController;
import org.foodkart.controllers.UserController;
import org.foodkart.models.FoodItem;
import org.foodkart.models.Restaurant;
import org.foodkart.models.User;
import org.foodkart.services.RestaurantService;
import org.foodkart.services.UserService;
import org.foodkart.services.UserSessionService;

public class Driver {
    public static void main(String[] args) {

        //init services
        UserSessionService userSessionService = new UserSessionService();
        RestaurantService restaurantService = new RestaurantService(userSessionService);
        UserService userService = new UserService(userSessionService);

        //init controllers
        UserController userController = new UserController(userService, restaurantService);
        RestaurantController restaurantController = new RestaurantController(restaurantService);

        //register users
        User user1 = userController.registerUser(new User("Pralove", "M", "phoneNumber-1", "HSR"));
        User user2 = userController.registerUser(new User("Nitesh", "M", "phoneNumber-2", "BTM"));
        User user3 = userController.registerUser(new User("Vatsal", "M", "phoneNumber-3", "BTM"));

        //login
        userController.login(user1.getId());

        //register restaurant
        restaurantController.registerRestaurant(new Restaurant("Food Court-1", "BTM/HSR", new FoodItem("NI Thali", 100.0 ,5)));
        restaurantController.registerRestaurant(new Restaurant("Food Court-2", "BTM", new FoodItem("Burger", 120.0 ,3)));

        //login
        userController.login(user2.getId());

        //register restaurant
        restaurantController.registerRestaurant(new Restaurant("Food Court-3", "HSR", new FoodItem("SI Thali", 150.0 ,1)));

        //login
        userController.login(user3.getId());

        //show restaurant by price
        restaurantController.showRestaurantsSortedByPrice().forEach(System.out::println);

        //place order
        restaurantController.placeOrder("Food Court-1", 2);
        restaurantController.placeOrder("Food Court-2", 7);

        //create review
        restaurantController.rateRestaurant("Food Court-2", 3, "Good Food");
        restaurantController.rateRestaurant("Food Court-1", 5, "Nice Food");

        //show restaurant by rating
        restaurantController.showRestaurantsSortedByRating().forEach(System.out::println);

        //login user
        userController.login(user1.getId());

        //update quantity
        restaurantController.updateQuantity("Food Court-2", 5);

        //update location
        System.out.println(restaurantController.updateLocation("Food Court-2", "BTM/HSR"));

    }
}
