package com.example.peeyushrai.healthscanner;

import android.text.Editable;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by shreyasrai on 4/18/18.
 */

public class User {

    private ArrayList<Condition> userConditions = new ArrayList<Condition>();

    private static String userName;
    private static String firstName;
    private static String lastName;
    private static int yearOfBirth;
    private static String password;
    private static String Email;
    private static String login_userName;
    private static String login_passWord;
    private static int age;


    public User(String name, String fName, String lName, String email, int Yob, String pass)
    {
        userName = name;
        firstName = fName;
        lastName = lName;
        yearOfBirth = Yob;
        password = pass;
        Email = email;
    }


    public static String getUserName()
    {
        return userName;
    }
    public static void setUserName(String e)
    {
        userName = e.toString();
    }
    public static String getfirstName()
    {
        return firstName;
    }
    public static void setfirstName(String e)
    {
        firstName = e.toString();
    }
    public static String getLastName()
    {
        return lastName;
    }
    public static void setLastName(String e)
    {
        lastName = e.toString();
    }
    public static String getEmail() {return Email;}
    public static void setEmail(String e) {Email = e;}
    private static void calculateAge()
    {
        age = Calendar.getInstance().get(Calendar.YEAR) - yearOfBirth;
    }

    public static int getYOB()
    {
        return yearOfBirth;
    }
    public static void setYOB(String e)
    {
        yearOfBirth = Integer.parseInt(e.toString());
    }
    public static void setPassword(String str){password = str;}
    public static String getPassword(){return password;}
    public static void setLogin_userName(String s){login_userName = s;}
    public static void setLogin_passWord(String strng){login_passWord = strng;}
    public static String getLogin_userName(){return login_userName;}
    public static String getLogin_password(){return login_passWord;}
    public void addCondition (Condition c) { userConditions.add(c); }
    public void removeCondition (Condition c) { userConditions.remove(c); }
}
