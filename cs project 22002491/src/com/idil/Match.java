
package com.idil;


/**
 * @(#)Match.java
 * finds a match for the user using their age, age preference, sex , sex preference , zodiac sign
 * if other than zodiac sign's do not match , program eliminates that person
 * if it is not eliminated, finds the compatibility rate among two people according to their zodiac signs
 * @author İdil Atmaca
 * @version 1.00 2021/05/07
 */

public class Match {

    //variables
    private String name;
    private int age;
    private int ageGap;
    private String sex;
    private String sexPreference;
    private String zodiacSign;

    //arrays
    public static String [] fireSigns = {"Aries","Leo", "Sagittarius"};
    public static String [] earthSigns = {"Taurus","Virgo","Capricorn"};
    public static String [] airSigns = {"Gemini","Libra","Aquarius"};
    public static String [] waterSigns = {"Cancer","Scorpio","Pisces"};
    public static String [][] signGroups = {fireSigns,earthSigns,airSigns,waterSigns};

    //constructor

    /**
     * Constructor
     * Creates a Match object
     * @param name person's name
     * @param age person's age
     * @param ageGap person's preference on age gap for their partner
     * @param sex person's sex
     * @param sexPreference person's sex preference
     * @param zodiacSign person's zodiac sign
     */
    public Match(String name , int age, int ageGap, String sex , String sexPreference, String zodiacSign ){
        setName(name);
        setAge(age);
        setAgeGap(ageGap);
        setSex(sex);
        setSexPreference(sexPreference);
        setZodiacSign(zodiacSign);
    }

    /*
     * ---------------------------------------
     * MUTATORS
     * ---------------------------------------
     */

    /**
     * @param name
     * Mutator method for name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * @param age
     * Mutator method for age
     */
    public void setAge(int age){
        this.age = age;
    }

    /**
     * @param ageGap
     * Mutator method for ageGap
     */
    public void setAgeGap(int ageGap){
        this.ageGap = ageGap;
    }

    /**
     * @param sex
     * Mutator method for sex
     */
    public void setSex(String sex){
        this.sex = sex;
    }

    /**
     * @param preference
     * Mutator method for sex preference
     */
    public void setSexPreference(String preference){
        sexPreference = preference;
    }

    /**
     * @param zodiac
     * Mutator method for zodiac sign
     */
    public void setZodiacSign(String zodiac){
        zodiacSign = zodiac;
    }

    /*
    * ---------------------------------------
    * ACCESSORS
    * ---------------------------------------
    */

    /**
     * Returns the name of the Match object
     * @return String name
     */
    public String getName(){
        return name;
    }

    /**
     * Returns the age of the Match object
     * @return int age
     */
    public int getAge(){
        return age;
    }

    /**
     * Returns the age preference of the Match object
     * @return int ageGap
     */
    public int getAgeGap(){
        return ageGap;
    }

    /**
     * Returns the sex of the Match object
     * @return String sex
     */
    public String getSex(){
        return sex;
    }

    /**
     * Returns the sex preference of the Match object
     * @return String sexPreference
     */
    public String getSexPreference(){
        return sexPreference;
    }

    /**
     * Returns the zodiac sign of the Match object
     * @return String zodiacSign
     */
    public String getZodiacSign(){
        return zodiacSign;
    }

    //methods

    //checks if two people's orientation's match

    /**
     * @param person1 person
     * @param person2 possible match
     * Returns true if both sides preference's match
     * @return boolean
     */
    public static boolean preferenceCheck(Match person1 , Match person2){
        if (person1.getSexPreference().equalsIgnoreCase(person2.getSex())){
            if (person2.getSexPreference().equalsIgnoreCase(person1.getSex())){
                return true;
            }
        }
        return false;
    }

    //checks if two people's age's are in the gap they want

    /**
     * @param person1 person
     * @param person2 possible match
     * Returns true if both sides age preferences match
     * @return boolean
     */
    public static boolean ageCheck(Match person1 , Match person2){

        //using the first person's age and age gap preference, checks if the other persons age is in that range
        if ( person1.age - person1.getAgeGap() <= person2.getAge()
                && person2.getAge() <= person1.getAge() + person1.getAgeGap() ){

            //does the same thing for the other person
            if ( person2.getAge() - person2.getAgeGap() <= person1.getAge()
                    && person1.getAge() <= person2.getAge() + person2.getAgeGap() ){

                return true;
            }

        }

        return false;
    }

    //find the person's zodiac group using their zodiac sign
    /**
     * @param zodiac
     * Returns to the array that zodiac sign belongs
     * @return String[]
     */
    public static String[] findGroup(String zodiac){

        String[] isEmpty = {};
        int index = 0;
        //search the zodiac sign in the signGroups two dimensional array
        for(int i = 0 ; i < signGroups.length ; i++){

            //find the subgroup the zodiac sign belongs
            for(int j = 0; j < fireSigns.length ; j++ ){
                if(zodiac.equalsIgnoreCase(signGroups[i][j])){
                    index = i;

                    //after finding the zodiac sign's group -> return to that group's  array
                    return signGroups[index];
                }
            }
        }
        //if zodiac group is not found ->
        // return to an empty array to check if the inputted zodiac sign from the user is valid or not
        return isEmpty;
    }

    //see if two arrays are the same
    /**
     * @param arr1 first array
     * @param arr2 second array
     * Returns true if arrays are identical
     * @return boolean
     */
    public static boolean equals(String[] arr1 , String [] arr2){

        //checks if the lengths of the arrays are the same
        if(arr1.length == arr2.length){

            if(arr1.length == 0){
                return true;
            }

            //checks if the every element is the same
            for( int a = 0 ; a < arr1.length && arr1[a].equals(arr2[a]) ; a++){
                if (a == arr1.length -1 && arr1[a].equals(arr2[a] )){
                    return true;
                }
            }

        }
        return false;

    }


    /**
     * @param person1 person
     * @param person2 possible match
     * Returns compatibility rate among two people using zodiac signs
     * @return int rate
     */
    public static int zodiacCompatibility(Match person1 , Match person2){
       int rate = 0;

       //find the zodiac group of the people and initialize it
       String[] group1 = findGroup(person1.getZodiacSign());
       String[] group2 = findGroup(person2.getZodiacSign());

       //if one is air & the other is fire group = %100 compatibility
       if( (equals(airSigns,group1) && equals(fireSigns,group2))
            || (equals(airSigns,group2) && equals(fireSigns,group1))){
           rate =  100;
       }

       //earth & water group = %80 compatibility
       else if( (equals(earthSigns,group1) && equals(waterSigns,group2))
                || (equals(earthSigns,group2) && equals(waterSigns,group1))){
            rate = 80;
        }

       // same group = %60 compatibility
       else if( (equals(group2,group1))){
            rate = 60;
        }

       // air & earth group = %40 compatibility
       else if( (equals(airSigns,group1) && equals(earthSigns,group2))
               || (equals(airSigns,group2) && equals(earthSigns,group1))){
           rate = 40;
       }

       // air & water group = %20 compatibility
       else if( (equals(fireSigns,group1) && equals(waterSigns,group2))
               || (equals(fireSigns,group2) && equals(waterSigns,group1))){
           rate = 20;
       }

       // air & water group = %10 compatibility
       else if( (equals(airSigns,group1) && equals(waterSigns,group2))
               || (equals(airSigns,group2) && equals(waterSigns,group1))){
           rate = 10;
       }

       // earth & fire group = no compatibility
       else if( (equals(earthSigns,group1) && equals(fireSigns,group2))
               || (equals(earthSigns,group2) && equals(fireSigns,group1))){
            rate = 0;
       }


        return rate;

    }

    /**
     * Returns the String representation of the Card object's name
     * @return String name
     */
    public String toString(){
        return getName() + "\n";
    }





}
