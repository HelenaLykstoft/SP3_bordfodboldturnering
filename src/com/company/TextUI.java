package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class TextUI {


    public ArrayList<String> registerTeam(String message){
        // Creating new ArrayList to create one team at a time
        ArrayList<String> data = new ArrayList<>();
        String input; // The input we write
        Scanner scan = new Scanner(System.in); // Creating scanner so we can read the prompt
        System.out.println("Write the teamname here: ");
        input = scan.nextLine();
        data.add(input);

        while(data.size()<5){ // 5 because theres teamname and up to 5 players
            System.out.println("Write your team members names here or press q to quit: ");
            input=scan.nextLine(); // Gets the next line in terminal
            if(input.equalsIgnoreCase("Q")){
                break;
            }
            data.add(input);
        }
        return data;
    }
    public void displayMessage(String msg){
        System.out.println(msg);
    }
    public String getUserInput(String message){
        displayMessage(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    // Creates a msg where we can write whatever we want to user
    public void writeToUser(String msg){
        System.out.println(msg);
    }
    /*public void showTeams(){

    }
    public void showResults(){

    }
    public void getTeamPlacement(){

    }
    public void showAllMatches(){

    }
    public void showNextMatch(){

    } Skal de bruges eller ej her? et andet sted? aldrig?*/
}
