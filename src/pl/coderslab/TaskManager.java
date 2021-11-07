package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class TaskManager {
    static String[][] myArray;
    Scanner sc = new Scanner(new BufferedReader(new FileReader("tasks.csv")));
    int rows = numberoflines();
    int columns = 3;

    public TaskManager() throws IOException {
    }


    public static void main(String[] args) throws IOException {
        myArray = readFileToTab("tasks.csv");
//        System.out.println(Arrays.deepToString(myArray));
//        wywołanie metody do wyświetlenia menu opcji
        DisplayMenu();
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            String choice = scan.nextLine();

            switch (choice) {
                case "add":
                    addTask();
                    break;
                case "remove":
                    removeTask();
                    break;
                case "list":
                    listTask();
                    break;
                case "exit":
                    exit("tasks.csv", myArray);
                    System.out.println(ConsoleColors.RED + "Bye, bye.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please select a correct option.");

            }
            DisplayMenu();
        }
    }

    public static void DisplayMenu() {
        //    wyświetlanie dostępnych opcji
        System.out.println(ConsoleColors.BLUE + "Please select an option" + ConsoleColors.RESET);
        String[] options = {"add", "remove", "list", "exit"};
        System.out.println(options[0]);
        System.out.println(options[1]);
        System.out.println(options[2]);
        System.out.println(options[3]);


    }



    public static int numberoflines() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("tasks.csv"));
        int lines = 0;
        while (reader.readLine() != null) lines++;
        reader.close();
        return lines;
    }

    private static void addTask() throws IOException {

        int rows = numberoflines();
        int columns = 3;
        System.out.println("Please add task description");
        Scanner scan = new Scanner(System.in);
        String task = scan.nextLine();
        myArray = Arrays.copyOf(myArray, myArray.length + 1);
        myArray[myArray.length - 1] = new String[3];
        myArray[rows][0] = task;
        System.out.println("Please add task due date");
        String dueDate = scan.nextLine();
        myArray[rows][1] = dueDate;
        System.out.println("Is your task important: true/ false");
        String importance = scan.nextLine();
        myArray[rows][2] = importance;
        System.out.println(Arrays.deepToString(myArray));

    }

    private static void removeTask() {
        System.out.println("Please select number to remove");
        Scanner scan = new Scanner(System.in);
        int numberToRemove = scan.nextInt();

        for (int i = 0; i < myArray.length; i++) {
            if (i == numberToRemove) {
                myArray = ArrayUtils.remove(myArray, i);

            }
        }
        System.out.println(Arrays.deepToString(myArray));

    }

    public static void exit(String fileName, String[][] table) throws IOException {
        Path dir = Paths.get(fileName);

        String[] lines = new String[myArray.length];
        for (int i = 0; i < table.length; i++) {
            lines[i] = String.join(",", table[i]);
        }
        Files.write(dir, Arrays.asList(lines));
        System.out.println(Arrays.deepToString(myArray));
    }

    public static String[][] readFileToTab(String fileName) throws IOException {
        Path dir = Paths.get("tasks.csv");
        String[][] table = null;
        List<String> strings = Files.readAllLines(dir);
        table = new String[strings.size()][strings.get(0).split(",").length];
        for (int i = 0; i < strings.size(); i++) {
            String[] split = strings.get(i).split(",");
            for (int j = 0; j < split.length; j++) {
                table[i][j] = split[j];
            }
        }
        return table;
    }

    public static void listTask() {
        for (int k = 0; k < myArray.length; k++) {
            System.out.print(k + ": ");
            for (int l = 0; l < myArray[k].length; l++) {

                System.out.print(myArray[k][l] + " ");

            }
            System.out.println();
        }

    }
}
