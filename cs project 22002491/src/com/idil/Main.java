
package com.idil;
/**
 * @(#)Main.java
 * gets personal information from the user and finds the best possible match among the people created in the main
 * checks the inputs whether they are valid or not , if not asks for another input
 * @author İdil Atmaca
 * @version 1.00 07/05/2021
 */
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {

        // variables
        String name;
        int age;
        int ageGap;
        String sex;
        String sexPreference;
        String zodiacSign;
        int bestRate = 0;


        Match best = new Match("default", 0, 0, "default", "default", "default");

        Match person1 = new Match("İdil Atmaca", 19, 4, "female", "male", "pisces");
        Match person2 = new Match("Burak Yılmaz", 21, 5, "male", "male", "cancer");
        Match person3 = new Match("Yusuf Bayrak", 22, 6, "male", "male", "capricorn");
        Match person4 = new Match("Defne Bilgili", 20, 10, "female", "male", "taurus");
        Match person5 = new Match("Ahmet Yıldırım", 23, 4, "male", "female", "aquarius");
        Match person6 = new Match("Berke Gök", 20, 2, "male", "female", "virgo");
        Match person7 = new Match("Sude Kılıç", 38, 12, "female", "female", "gemini");
        Match person8 = new Match("Mehmet Ali Erbil",64,40,"male","female","sagittarius");
        Match person9 = new Match("Mira Beyaz",19,3,"female","male","leo");
        Match person10 = new Match("Pelin Ateşoğlu",28,11,"female","female","libra");
        Match person11 = new Match("Ahmet Celil Atlı",22,30,"male","female","scorpio");
        Match person12 = new Match("Tuna Metin",21,4,"male","female","capricorn");


        //creating arrays

        //this array is created for comparison (to determine if the inputted zodiac sign is valid)
        String [] empty = {};

        //array that contains possible matches for the user
        Match[] people = {person1, person2, person3, person4, person5, person6, person7,person8,person9,person10,person11,person12};


        Scanner input = new Scanner(System.in);

        System.out.print("Enter your name: ");
        name = input.nextLine();

        //getting an input from the user about their personal information but if the input is invalid -> ask again


        //asks user for their age
        // if the age is less than 18, exits the program
        System.out.print("Enter your age: ");
        while(!input.hasNextInt()){

            System.out.println("INVALID age - try again");
            System.out.print("Enter your age: ");
            input.next();
        }
        age = input.nextInt();

        if (age < 18) {
            System.out.println("You have to be 18 or older to find a match");
        }
        
        else {

            //ask the user their preference on age gap for their partner
            System.out.print("Enter your preference on age gap w/ your partner: ");
            while(!input.hasNextInt()){

                System.out.println("INVALID age - try again");
                System.out.print("Enter your preference on age gap: ");
                input.next();
            }

            ageGap = input.nextInt();

            System.out.print("Enter your sex (female/male): ");


            sex = input.next();

            //ask the user their sex
            while (!sex.equalsIgnoreCase("female") && !sex.equalsIgnoreCase("male")){
                System.out.println("INVALID sex - try again");
                System.out.print("Enter your sex (female/male): ");
                sex = input.next();
            }

            System.out.print("Enter your sex preference (female/male): ");
            sexPreference = input.next();

            //ask the user their sex preference
            while (!sexPreference.equalsIgnoreCase("female")&&!sexPreference.equalsIgnoreCase("male")){
                System.out.println("INVALID sex - try again");
                System.out.print("Enter your sex preference (female/male): ");
                sexPreference = input.next();
            }

            //ask the user their zodiac sign
            System.out.print("Enter your zodiac sign: ");
            zodiacSign = input.next();

            //using the findGroup() functionality -> program checks if the input is valid or not
            while (Match.equals(Match.findGroup(zodiacSign),empty)){
                System.out.println("INVALID zodiac sign - try again");
                System.out.print("Enter your zodiac sign: ");
                zodiacSign = input.next();
            }

            //creating the candidate using their information
            Match candidate = new Match(name, age, ageGap, sex, sexPreference, zodiacSign);

            //comparing every people in the system with the candidate to fins a possible match
            for (int i = 0; i < people.length; i++) {

                //if age and sex preferences do not match, eliminate that person directly
                while (i < people.length && Match.preferenceCheck(candidate, people[i]) && Match.ageCheck(candidate, people[i])) {

                    //if a better match is found -> update the best match and their rate
                    if (Match.zodiacCompatibility(candidate, people[i]) > bestRate) {

                        bestRate = Match.zodiacCompatibility(candidate, people[i]);
                        best = people[i];

                    }
                    i++;
                }
            }

            //if no match is found -> display a message to the user
            if(best.getName().equals("default")){
                System.out.println("Sorry, we weren't able to find you a match :(");
            }

            //if there is a possible match -> display the name of the match to the user with their compatibility rate
            else{
                System.out.println("Best candidate for you with %" + bestRate + " compatibility is: " + best);

            }

        }
    }
}
