package pl.coderslab;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {

    public static void main(String[] args) throws IOException {
/*        Scanner sc = new Scanner(new BufferedReader(new FileReader("tasks.csv")));
        int rows = numberoflines();
        int columns = 3;
        String[][] myArray = new String[rows][3];
        while (sc.hasNextLine()) {
            for (int i = 0; i < myArray.length; i++) {
                String[] line = sc.nextLine().split(",");
                for (int j = 0; j < line.length; j++) {
                    myArray[i][j] = line[j];
                }
            }
        }*/

//reading tasks file into 2D array

//        System.out.println(Arrays.deepToString(myArray));
//        wywołanie metody do wyświetlenia menu opcji
        DisplayMenu();


        Scanner scan = new Scanner(System.in);
        switch (scan.nextLine()) {
            case "add":
                addTask();
                break;
/*  *//*          case "remove":
                removeTask();
                break;
            case "list":
                listTask();
                break;
            case "exit":
*//*                exit();*/
            default:
                System.out.println("Please select a correct option.");

        }

    }

    public static void DisplayMenu() {
        //    wyświetlanie dostępnych :
        System.out.println(ConsoleColors.BLUE + "Please select an option" + ConsoleColors.RESET);
        String[] options = {"add", "remove", "list", "exit"};
        System.out.println(options[0]);
        System.out.println(options[1]);
        System.out.println(options[2]);
        System.out.println(options[3]);


    }

    public static String readFile(String fileName) throws IOException {
        FileInputStream file = new FileInputStream(fileName);
        byte[] bytes = file.readAllBytes();
        return new String(bytes);
    }

    public static int numberoflines() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("tasks.csv"));
        int lines = 0;
        while (reader.readLine() != null) lines++;
        reader.close();
        return lines;
    }

    public static void addTask() throws IOException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader("tasks.csv")));
        int rows = numberoflines();
        int columns = 3;
        String[][] myArray = new String[rows][3];
        while (sc.hasNextLine()) {
            for (int i = 0; i < myArray.length; i++) {
                String[] line = sc.nextLine().split(",");
                for (int j = 0; j < line.length; j++) {
                    myArray[i][j] = line[j];
                }
            }
        }
        rows = numberoflines();
        columns = 3;
//        myArray = new String[rows][3];
        System.out.println("Please add task description");
        Scanner scan = new Scanner(System.in);
        String task = scan.nextLine();
        String  added [][] = Arrays.copyOf(myArray, myArray.length+1);
        added[rows-1][0] = task;
        System.out.println("Please add task due date");
        String dueDate = scan.nextLine();
        added[rows-1][1] = dueDate;
        System.out.println("Is your task important: true/ false");
        String importance = scan.nextLine();
        added[rows-1][2] = importance;
        System.out.println(Arrays.deepToString(added));
        BufferedWriter outputWriter = null;
        outputWriter = new BufferedWriter(new FileWriter("tasks.csv"));

//        myArray[rows][1] = scan.nextLine();
    }

}


