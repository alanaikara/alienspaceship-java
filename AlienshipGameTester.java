package com.company;

import java.util.ArrayList;
import java.lang.String;
import java.util.Arrays;
import java.util.Scanner;

public class AlienshipGameTester {
    int numGuess = 0;
//    String[] userGuess = { "a2","a3","a4", "a2","a3","a4", "a2","a3","a4", "a2","a3","a4", "a2","a3","a4"};
//    String[] expectedResult = { "miss!","miss!","hit!","kill","miss!","miss!","hit!","kill"};
    private ArrayList<Alienship> ship = new ArrayList<Alienship>();

    public static void main(String[] args) {
        // write your code here
        AlienshipGameTester game = new AlienshipGameTester();
        game.setup();

       // game.gettester(userGuess, expectedResult, ship1);


    }

    private void setup(){
        ship.add(new Alienship("submarine",3));
        ship.add(new Alienship("marine",3));
        ship.add(new Alienship("sub",3));
       // setLocations();
        setLocationsUser();
        System.out.println("lets test the game");
        String[] userGuess = { "b4","b5","b6", "f5","e5","d5", "g4","g5","g6"};
        String[] expectedResult = { "hit!","hit!","kill1","kill","miss!","miss!","hit!","hit!","kill3"};
        //gettester(userGuess, expectedResult,ship);
        test();
    }

    private void setLocationsUser(){
        Scanner input = new Scanner(System.in);
        int t=0;
        ArrayList<String> locationToSet = new ArrayList<String>();
        while(t<3) {
            System.out.println("enter the cells of "+(t+1)+" ship");
            for(int i=0;i<3;i++) {
                String loc = input.nextLine();
                loc=loc.toUpperCase();
                locationToSet.add(loc);
            }
            ship.get(t).setLocation(locationToSet);
            t++;
        }
    }


    private void setLocations(){
        ArrayList<String> ship1 = new ArrayList<String>();
        ship1.add("B4");
        ship1.add("B5");
        ship1.add("B6");
        ship.get(0).setLocation(ship1);

        ArrayList<String> ship2 = new ArrayList<String>();
        ship2.add("F5");
        ship2.add("E5");
        ship2.add("D5");
        ship.get(1).setLocation(ship2);

        ArrayList<String> ship3 = new ArrayList<String>();
        ship3.add("G4");
        ship3.add("G5");
        ship3.add("G6");
        ship.get(2).setLocation(ship3);
        //System.out.println(Arrays.deepToString(ship.toArray()));

    }

    private void test() {
        String guess, result;
        Scanner input = new Scanner(System.in);
        while(!ship.isEmpty()) {

            System.out.println("Enter a guess:");
            guess = input.nextLine();

            System.out.println("Enter the expected result:");
            result = input.nextLine();

            gettester1(guess,result,ship);
        }
            input.close();


    }

    private void gettester1(String userGuess, String expectedResult, ArrayList<Alienship> ship) {
        String result;
            result = "miss!";
            userGuess = userGuess.toUpperCase();
            for (int i = 0; i < ship.size(); i++) {
                result = ship.get(i).check(userGuess);
                if (result.equals("kill")) {
                    result = ("kill"+(4 - ship.size()));
                    //result = ("It's a kill! you sunk " + (4 - ship.size()) + " out of 3 Alienships!");
                    //result = ("you sunk"+ i +"out of 3 alien ships" + ship.get(i).getName());
                    ship.remove(i);
                    break;
                } else if (result.equals("hit!")) {
                    break;
                }
                //System.out.println(result+ ""+ expectedResult[i]);
            }
            String pass;

            if(result==expectedResult){ pass = "pass";}
            else{ pass = "fail";}
            System.out.println("actual result: "+result+" expected result: "+expectedResult+" ");
        }


    private void gettester(String[] userguess, String[] expectedResult, ArrayList<Alienship> ship) {
        String guess, result;
        for (int j = 0; j < userguess.length; j++) {
                guess = userguess[j];
                result = "miss!";
                numGuess++;
                guess = guess.toUpperCase();
                for (int i = 0; i < ship.size(); i++) {
                    result = ship.get(i).check(guess);
                    if (result.equals("kill")) {
                        result = ("kill"+(4 - ship.size()));
                        //result = ("It's a kill! you sunk " + (4 - ship.size()) + " out of 3 Alienships!");
                        //result = ("you sunk"+ i +"out of 3 alien ships" + ship.get(i).getName());
                        ship.remove(i);
                        break;
                    } else if (result.equals("hit!")) {
                        break;
                    }
                    //System.out.println(result+ ""+ expectedResult[i]);
                }
                String pass;
                if(result==expectedResult[j]){
                    pass="pass";

                }
                else{
                    pass="fail";
                }
            System.out.println(result+" "+expectedResult[j]+" "+pass);
            }
        }



    private void finish(){
        System.out.println("number of guess= "+ numGuess);

    }


}
