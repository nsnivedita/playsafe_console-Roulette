package com.game;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;

public class Main {

    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        String x;
        System.out.println("*******************************************************************************************************************************");
        System.out.println("*****************************************************WELCOME TO Roulette Game**************************************************");
        System.out.println("*******************************************************************************************************************************");
        do
        {
            System.out.println("\nPress 1 to Start the Game");
            System.out.println("Press 2 Exit");
            try{
                x = in.nextLine();
                int intValue = Integer.parseInt(x);
                switch(intValue)
                {
                    case 1:
                        Game();
                        break;
                    case 2: System.out.println("***********************************************************");
                        System.out.println("***********************************************************");
                        System.out.println("Thank You");
                        System.out.println("***********************************************************");
                        System.out.println("***********************************************************");
                        System.exit(0);
                        break;
                    default:System.out.println("You have entered a wrong choice");
                }
            }
            catch (NumberFormatException ex) {
                System.out.println("You haven't entered an Integer");
            }
        }while(true);

    }

    public static void Game()
    {
        List<Player> person = new ArrayList<Player>();
        String even = "EVEN";
        String odd = "ODD";

        System.out.println("Welcome to the Game");

        boolean flag = true;
        do {
            String x,details,name,betNum;
            double betAmount;
            System.out.println("Press 1 Enter the Bet details");
            System.out.println("Press 2 to Spin the wheel");
            try{
                x = in.nextLine();
                int intValue = Integer.parseInt(x);
                switch(intValue)
                {
                    case 1: try {
                        System.out.println("Please enter the Player Name followed by bet number(from 1-36, EVEN or ODD) and bet amount");
                        details = in.nextLine();
                        String[] var = details.split(" ");
                        name = var[0];
                        if(var[1].equals(even) || var[1].equals(odd) || isInteger(var[1])){
                            betNum = var[1];
                        }
                        else{
                            System.out.println("You have entered the wrong bet number");
                            break;
                        }

                        betAmount = Double.parseDouble(var[2]);
                        Player p = new Player(name,betNum,betAmount);
                        person.add(p);
                    }
                    catch(ArrayIndexOutOfBoundsException e) {
                        System.out.println("Please input the correct data");
                    }

                        break;
                    case 2: Random generator = new Random();
                        int rouletteNum = generator.nextInt(37);
                        for(Player P: person){
                            if(isInteger(P.getBetNum())){
                                if(Integer.parseInt(P.getBetNum()) == rouletteNum) {
                                    P.setstatus("WIN");
                                    P.setWinAmount(P.getBetAmount() * 36);
                                }
                                else {
                                    P.setstatus("LOSE");
                                    P.setWinAmount(0);
                                }
                            }
                            else {
                                if(rouletteNum % 2 == 0 && P.getBetNum().equals(even)) {
                                    P.setstatus("WIN");
                                    P.setWinAmount(P.getBetAmount() * 2);
                                }
                                else if(rouletteNum % 2 != 0 && P.getBetNum().equals(odd)) {
                                    P.setstatus("WIN");
                                    P.setWinAmount(P.getBetAmount() * 2);
                                }
                                else {
                                    P.setstatus("LOSE");
                                    P.setWinAmount(0);
                                }
                            }
                        }
                        showOutput(person,rouletteNum);
                        fileOp(person);
                        break;
                    default:System.out.println("You have entered a wrong choice");
                }
            }
            catch (NumberFormatException ex) {
                System.out.println("You haven't entered right values");
            }


        }while(flag );
    }

    public static boolean isInteger(String s) {
        boolean isValidInteger = false;
        try
        {
            Integer.parseInt(s);
            isValidInteger = true;
        }
        catch (NumberFormatException ex)
        {
            // String is not an integer
        }

        return isValidInteger;
    }

    public static void showOutput(List<Player> person, int num )
    {
        System.out.println();
        System.out.println("                              OUTPUT 1                                      ");
        System.out.println("Number :" +num);
        System.out.printf("%-10s %10s %10s %10s", "Player", "Bet", "Outcome", "Winnings");
        System.out.println();
        for(Player P: person){
            System.out.format("%-10s %10s %10s %10f",P.getPlayerName(),P.getBetNum(),P.getStatus(),P.getWinAmount());
            System.out.println();
        }
    }

    public static void fileOp(List<Player> person)
    {
        try {
            File F = new File("temp.txt");
            F.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            File file = new File("Players_name.txt");
            Scanner sc = new Scanner(file);
            String line,name;
            double totalBet, totalWin;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String[] var = line.split(",");
                name = var[0];
                totalWin = Double.parseDouble(var[1]);
                totalBet = Double.parseDouble(var[2]);
                String temp ="";

                for(Player P: person){
                    if(P.getPlayerName().equals(name)){
                        totalBet = totalBet + P.getBetAmount();
                        totalWin = totalWin + P.getWinAmount();
                        temp = name+","+totalWin+","+totalBet+"\r\n";
                    }
                }
                try{
                    FileOutputStream outputStream = new FileOutputStream("temp.txt", true);
                    byte[] strToBytes = temp.getBytes();
                    outputStream.write(strToBytes);
                    outputStream.close();
                }
                catch(Exception e){
                    System.out.println(e);
                }

            }
            try {
                FileInputStream fis = new FileInputStream("temp.txt");
                FileOutputStream fos = new FileOutputStream("Players_name.txt");
                int b;
                while  ((b=fis.read()) != -1)
                    fos.write(b);
                fis.close();
                fos.close();
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        File Obj = new File("temp.txt");
        Obj.delete();
        output2();
    }

    public static void output2()
    {
        try {
            File file = new File("Players_name.txt");
            Scanner sc = new Scanner(file);
            String line;
            System.out.println();
            System.out.println("                              OUTPUT 2                                     ");
            System.out.printf("%-10s %10s %10s", "Player", "Total Win", "Total Bet");
            System.out.println();
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String[] var = line.split(",");
                System.out.format("%-10s %2f %2f",var[0],Double.parseDouble(var[1]),Double.parseDouble(var[2]));
                System.out.println();
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
