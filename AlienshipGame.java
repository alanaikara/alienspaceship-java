package com.company;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import java.util.regex.*;


public class AlienshipGame {

    int numGuess = 0;
    private ArrayList<Alienship> ship = new ArrayList<Alienship>();
    public static void main(String[] args) {
	// write your code here
        AlienshipGame game = new AlienshipGame();
        game.setup();

    }
     private void setup(){
         ship.add(new Alienship("submarine",3));
         ship.add(new Alienship("marine",3));
         ship.add(new Alienship("sub",3));
         setLocations();

         System.out.println("lets start the game \n good luck");
         play();
     }

     private void play(){
        String guess, result;
        Scanner input = new Scanner(System.in);
        while(!ship.isEmpty()) {
            result = "miss!";

            numGuess++;
            System.out.println("Enter a guess:");
            guess = input.nextLine();
            guess = guess.toUpperCase();
            boolean guesstrue = Pattern.matches("[A-H][1-8]",guess);
            if(guesstrue){
                for (int i = 0; i < ship.size(); i++) {
                    result = ship.get(i).check(guess);
                    System.out.println(ship.get(i));
                    System.out.println("hii");
                    if (result.equals("kill")) {
                        result = ("It's a kill! you sunk " + (4 - ship.size()) + " out of 3 Alienships!");
                        //result = ("you sunk"+ i +"out of 3 alien ships" + ship.get(i).getName());
                        ship.remove(i);
                        break;
                    } else if (result.equals("hit!")) {
                        break;
                    }

                }
                System.out.println(result);
        }else{
             System.out.println("invalid input");
         }}
        input.close();
        finish();
    }

    private void finish(){
        System.out.println("You took a total of  "+ numGuess +" guesses.");

    }

    private void setLocations(){
        Random rand = new Random();
        ArrayList<String> locationToSet = new ArrayList<String>();
        ArrayList<String> temp = null;
        int let, num, incl, incn;
        String alpha = "ABCDEFGH";
        boolean worked;
        for(int i=0;i<ship.size();i++){
            worked = false;
            start:
                    while (!worked){
                        locationToSet.clear();
                        worked = true;
                        let = rand.nextInt(6);// 6 since we have 8by8 grid
                        num = 1+rand.nextInt(6);
                        //vertical or horizontal
                        if(num%2==0){
                            incl=1;
                            incn =0;
                        }else{
                            incl=0;
                            incn=1;
                        }

                        for(int j=0;j<ship.get(i).getSize();j++){
                            String loc = "" + alpha.charAt(let)+num;
                            let+=incl;
                            num+=incn;

                            for(int t=0;t<ship.size();t++){
                                if(t!= i){
                                    temp=ship.get(t).getLocations();
                                    if(temp.contains(loc)){
                                        worked = false;
                                        continue start; //we failed star over
                                    }
                                }
                            }
                            locationToSet.add(loc);
                            System.out.println(Arrays.deepToString(locationToSet.toArray()));
                        }
                        ship.get(i).setLocation(locationToSet);
                        System.out.println(Arrays.deepToString(ship.toArray()));
                    }
        }
    }
}

