/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Management_System_Package;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author d00159804
 */
public class Management_System_App {

    Scanner scan = new Scanner(System.in);
    public static Management_System[] Games = new Management_System[10];
    int count;

    //Menu system for choice of what to do
    public static void menuSystem() {
        System.out.println("Type 1 to Load Games");
        System.out.println("Type 2 to Get a Game by it's I.D.");
        System.out.println("Type 3 to Get a Game by it's Title");
        System.out.println("Type 4 to Sort the Games by I.D. (Insertion sort)");
        System.out.println("Type 5 to Sort the Games by Title (Selection sort)");
        System.out.println("Type 6 to Sort the Games by Rating (Bubble sort)");
        System.out.println("Type 7 to Sell a Game");
        System.out.println("Type 8 to Arrange the Games By Quantity");
        System.out.println("Type 9 to Print all games in the System");
        System.out.println("To Exit Type -1");
    }

    //Prints the Array on screen
    public void print() {
        for (int i = 0; i < Games.length; i++) {
            System.out.println(Games[i].toString());
        }
    }

    //Adds Games to the Array 
    public void add(Management_System Game) {
        if (count < Games.length) {
            Games[count] = Game;
            count++;
        } else {
            Management_System[] tempArray = new Management_System[Games.length + 1];
            for (int i = 0; i < count; i++) {
                tempArray[i] = Games[i];
            }
            tempArray[count] = Game;
            count++;
            Games = tempArray;
        }
    }

    //Part 1.0: Load and read in details from text file when program is loaded
    public void loadGamesAtStart() throws IOException {
        File text = new File("E:\\CA2\\src\\Management_System_Package\\GamesStart.txt");
        Scanner scnr = new Scanner(text);

        String GameLine;

        while (scnr.hasNextLine()) {

            Management_System Game = new Management_System("", "", 0, 0, 0);
            GameLine = scnr.nextLine();
            String[] tags = GameLine.split(",");

            Game.setTitle(tags[0]);
            Game.setGenre(tags[1]);
            Game.setID(Integer.parseInt(tags[2]));
            Game.setRating(Integer.parseInt(tags[3]));
            Game.setQuantity(Integer.parseInt(tags[4]));

            add(Game);
        }
    }

    //Part 1.1: Load and read in details from text file through menu
    public void loadGamesFromMenu() throws IOException {

        File text = new File("E:\\CA2\\src\\Management_System_Package\\GamesLoad.txt");
        Scanner scnr = new Scanner(text);

        String GameLine;

        while (scnr.hasNextLine()) {

            boolean addAmount = false;
            Management_System Game = new Management_System("", "", 0, 0, 0);
            GameLine = scnr.nextLine();

            String[] tags = GameLine.split(",");

            Game.setTitle(tags[0]);
            Game.setGenre(tags[1]);
            Game.setID(Integer.parseInt(tags[2]));
            Game.setRating(Integer.parseInt(tags[3]));
            Game.setQuantity(Integer.parseInt(tags[4]));

            for (int i = 0; i < Games.length; i++) {

                if (Games[i].getID() == Game.getID() && addAmount == false) {
                    int AddQuantity = Games[i].getQuantity() + Game.getQuantity();
                    Games[i].setQuantity(AddQuantity);
                    addAmount = true;
                    add(Game);
                }
            }

            if (addAmount == false) {
                add(Game);
            }
        }
    }

    //Part 2: Binary search to get item by I.D.
    public Management_System getById(int id) {
        int lo = 0;
        int hi = Games.length - 1;
        sortById();
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (id < Games[mid].getID()) {
                hi = mid - 1;
            } else if (id > Games[mid].getID()) {
                lo = mid + 1;
            } else {
                return Games[mid];
            }
        }
        return null;
    }

    //Part 3: Linear search get the item by the Title
    public Management_System getByTitle(String title) {
        for (int i = 0; i < Games.length; i++) {
            if (Games[i].getTitle().equalsIgnoreCase(title)) {
                return Games[i];
            }
        }
        return null;
    }

    //Part 4: Sort by ID Insertion sort
    public void sortById() {
        int i;
        for (int j = 1; j < Games.length; j++) {
            Management_System tempArray = Games[j];
            i = j;
            while (i > 0 && Games[i - 1].getID() > tempArray.getID()) {
                Games[i] = Games[i - 1];
                i--;
            }
            Games[i] = tempArray;
        }
    }

    //Part 5: Sort by Title Selection sort
    public void sortByTitle() {
        for (int i = 0; i < Games.length; i++) {
            int min = i;
            for (int j = i + 1; j < Games.length; j++) {
                if (Games[j].getTitle().compareToIgnoreCase(Games[min].getTitle()) < 0) {
                    min = j;
                }
            }
            Management_System tempArray = Games[i];
            Games[i] = Games[min];
            Games[min] = tempArray;
        }
    }

    // Part 6: Sort by Rating Bubble sort
    public void sortByRating() {
        for (int i = 1; i < Games.length; i++) {
            for (int j = 0; j < Games.length - i; j++) {
                if (Games[j].getRating() < Games[j + 1].getRating()) {
                    Management_System tempArray = Games[j];
                    Games[j] = Games[j + 1];
                    Games[j + 1] = tempArray;
                }
            }
        }
    }

    //Part 7: Selling and Reducing Item quantity and remove the item if the quantity is zero
    //This part of the code removes item the reducing of the item is done in a different part
    public static void remove(int index) {
        for (int i = 0; i < Games.length; i++) {
            if (Games[i].getQuantity() == 0) {
                Management_System[] tempArray = new Management_System[Games.length - 1];
                System.arraycopy(Games, 0, tempArray, 0, index);
                System.arraycopy(Games, index + 1, tempArray, index, Games.length - index - 1);
                Games = tempArray;
            }
        }
    }

    // Part 8: Arranging by quantity Partition sort
    public void partition() {
        int pivot = 3;
        int i = 0;
        int j = Games.length - 1;
        while (i <= j) {
            while (i < Games.length && Games[i].getQuantity() < pivot) {
                i++;
            }
            while (j >= 0 && Games[j].Quantity > pivot) {
                j--;
            }
            if (i <= j) {
                Management_System tempArray = Games[i];
                Games[i] = Games[j];
                Games[j] = tempArray;
                i++;
                j--;
            }
        }
    }

    //Runs the menu System and all the methods 
    public void runTests() throws IOException {

        String SearchID = scan.nextLine();
        int numChoice = Integer.parseInt(SearchID);

        while (numChoice != -1) {

            if (numChoice == 1) {

                System.out.println("");
                System.out.println("Stock Has now Been Loaded!!");
                System.out.println("");

                loadGamesFromMenu();
                print();

                menuSystem();

            } else if (numChoice == 2) {

                System.out.println("");
                System.out.println("Enter I.D. to Search for");
                System.out.println("");

                SearchID = scan.nextLine();
                int id = Integer.parseInt(SearchID);
                Management_System Search = getById(id);

                if (Search != null) {
                    System.out.println("");
                    System.out.println(Search);
                    menuSystem();
                } else {
                    System.out.println("");
                    System.out.println("Could not find game with I.D: " + id);
                    System.out.println("");
                    menuSystem();
                }

            } else if (numChoice == 3) {

                System.out.println("");
                System.out.println("Enter Title to Search For");
                System.out.println("");

                String title = scan.nextLine();
                Management_System Search2 = getByTitle(title);

                if (Search2 != null) {
                    System.out.println("");
                    System.out.println(Search2);
                    menuSystem();
                } else {
                    System.out.println("");
                    System.out.println("Could not find Game with Title: " + title);
                    System.out.println("");
                    menuSystem();
                }

            } else if (numChoice == 4) {

                sortById();

                System.out.println("");
                System.out.println("Games now sorted by I.D. as a Insertion sort");
                System.out.println("");

                print();
                menuSystem();

            } else if (numChoice == 5) {

                sortByTitle();

                System.out.println("");
                System.out.println("Games now sorted by Title as a Selection sort");
                System.out.println("");

                print();
                menuSystem();

            } else if (numChoice == 6) {

                sortByRating();

                System.out.println("");
                System.out.println("Games now sorted by Rating as a Bubble sort");
                System.out.println("");

                print();
                menuSystem();

            } else if (numChoice == 7) {

                //Part 7.2: Selling an Item
                System.out.println("");
                System.out.println("Enter I.D. to Sell");
                SearchID = scan.nextLine();
                int id = Integer.parseInt(SearchID);
                Management_System Search3 = getById(id);

                System.out.println("");
                System.out.println("Enter amount of I.D. " + id + " to sell");
                SearchID = scan.nextLine();
                int amountToRemove = Integer.parseInt(SearchID);


                if (Search3 != null) {

                    //Reduce Quantity By Amount entered
                    for (int i = 0; i < Games.length; i++) {
                        if (Games[i].getID() == Search3.getID()) {
                            Games[i].setQuantity(Games[i].getQuantity() - amountToRemove);

                            if (Games[i].getQuantity() != 0) {

                                System.out.println("");
                                System.out.println("Game Still In Stock!!");
                                System.out.println("");

                                System.out.print(Search3);
                                System.out.println("");

                                menuSystem();

                            }
                            if (Games[i].getQuantity() <= 0) {

                                System.out.println("");
                                System.out.println("Game is no longer in Stock and has been removed!!");
                                System.out.println("");

                                //Remove the item if the quantity is zero
                                remove(i);
                                menuSystem();
                            }
                        }
                    }
                }

            } else if (numChoice == 8) {

                partition();
                print();
                menuSystem();

                System.out.println("");
                System.out.println("Games with a quantity of 3 or less is");
                System.out.println("");
                for (int i = 0; i < Games.length; i++) {
                    if (Games[i].getQuantity() <= 3) {
                        System.out.print(Games[i].toString());
                    }

                }

                System.out.println("");
                System.out.println("Games with a quantity of 3 or more");
                System.out.println("");
                for (int i = 0; i < Games.length; i++) {
                    if (Games[i].getQuantity() > 3) {
                        System.out.print(Games[i].toString());
                    }

                }



            } else if (numChoice == 9) {

                System.out.println("");
                print();
                System.out.println("");
                menuSystem();

            } else if (numChoice == -1) {

                System.exit(0);

            } else if (numChoice < -1 && numChoice >= 10) {

                System.out.println("Sorry Invalid Number");
                menuSystem();
            }

            SearchID = scan.nextLine();
            numChoice = Integer.parseInt(SearchID);

        }
    }

    public static void main(String[] s) throws IOException {

        Management_System_App GamesToAdd = new Management_System_App();
        GamesToAdd.loadGamesAtStart();
        menuSystem();
        GamesToAdd.runTests();

    }
}
