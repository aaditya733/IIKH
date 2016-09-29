/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iikh;

import java.util.*;
import java.lang.*;
import java.io.*;
import java.io.FileReader;
import java.io.IOException; 

/**
 *
 * @author Aditya
 */
public class Meal {
    String text;
    ArrayList<String> meals=new ArrayList<String>();
    public Meal(){
        try{
            BufferedReader buf = new BufferedReader(new FileReader("test/meals.txt"));
            String lineJustFetched = null;
            String[] wordsArray;
            while(true){
                lineJustFetched = buf.readLine();
                if(lineJustFetched == null){  
                    break; 
                }else{
                    meals.add(lineJustFetched);
                }
            }
            UpdateText();
            buf.close();
        }catch(Exception e){
            e.printStackTrace();}
    }
    
    public void PrintMeals(){
        int size = meals.size();
        if(size >0){
            for(int i=0;i<meals.size();i++)
            {
                System.out.print((i+1)+" "+meals.get(i)+"\n");
            }
        }else{
            System.out.println("No meals in database.");
        }
    }
    
    public void UpdateText(){
        text="";
        for(int i=0; i < meals.size();i++){
                if(text==null){
                    text=meals.get(i)+"\n";}
                else if(i==meals.size()-1){
                    text=text+meals.get(i);}
                else{
                    text=text+meals.get(i)+"\n";}
            }
        File fold=new File("test/meals.txt");
        //fold.delete();
        //File fnew=new File("test/recipe.txt");
        try {
            FileWriter f2 = new FileWriter(fold, false);
            f2.write(text);
            f2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void EditMeals(){
        PrintMeals();
        System.out.println("Enter the Meal No. you wish to edit.");
        Scanner in = new Scanner(System.in);
        BufferedReader inp = new BufferedReader (new InputStreamReader(System.in));
        ArrayList<String> words = new ArrayList<>();
        int meal_no = in.nextInt();
        if(meal_no < meals.size()+1){
        ArrayList<String> recipe=new ArrayList<String>();
        ArrayList<String> quantity=new ArrayList<String>();
        String[] wordsArray;
        wordsArray = meals.get(meal_no-1).split("\t");
            for(String each : wordsArray){
                if(!"".equals(each)){
                    words.add(each);
            }
        }
        //System.out.print(words);
        for(int i=0;i <words.size();i++){
            if(i%2==0){
                recipe.add(words.get(i));
                //quantity.add(words.get(i));
            }
            else{
                quantity.add(words.get(i));}
                //quantity.add(words.get(i));}
        }
        for(int i=0;i<quantity.size();i++){
            Recipe r = new Recipe();
            String temp;
            System.out.print("Enter a recipe name for "+i+" instead. If you don't wish to change press -1.");
            temp="";
            try{
            temp = inp.readLine();}
            catch(Exception e){
            e.printStackTrace();}
            if(!("-1".equals(temp))){
                if(r.CheckRecipe(temp)){
                    recipe.set(i,temp);}}
            System.out.print("Enter a new quantity for "+i+" instead. If you don't wish to change press -1.");
            temp="";
            try{
            temp = inp.readLine();}
            catch(Exception e){
            e.printStackTrace();}
            if(!("-1".equals(temp))){
                quantity.set(i,temp);}
            }
        String addmeal;
        addmeal="";
        for(int j=0;j<recipe.size();j++){
            if(addmeal==null){
            addmeal=recipe.get(j)+"\t"+quantity.get(j)+"\t";}
            else if(j==recipe.size()-1){
            addmeal=addmeal+recipe.get(j)+"\t"+quantity.get(j);}
            else{
            addmeal=addmeal+recipe.get(j)+"\t"+quantity.get(j)+"\t";}}
        meals.set(meal_no-1, addmeal);
        }
        else{
            System.out.println("The entered recipe number doesn't exist.");
        }
        UpdateText();
    }
    
    public void AddMeal()
    {
        ArrayList<String> recipe=new ArrayList<String>();
        ArrayList<String> quantity=new ArrayList<String>();
        BufferedReader inp = new BufferedReader (new InputStreamReader(System.in));
        System.out.print("Enter the number of recipes to be included in the meal.");
        int n=0;
        try{
        n = Integer.parseInt(inp.readLine());}
        catch(Exception e){
            e.printStackTrace();}
        for(int i=0;i<n;i++){
            Recipe r = new Recipe();
            String temp;
            System.out.print("Enter a recipe name.");
            temp="";
            try{
            temp = inp.readLine();}
            catch(Exception e){
            e.printStackTrace();}
            if(r.CheckRecipe(temp)){
                    recipe.add(temp);}
            else{
                System.out.print("Recipe doesn't exist.");
                break;
            }
            System.out.print("Enter quantity for the recipe.");
            try{
            temp = inp.readLine();}
            catch(Exception e){
            e.printStackTrace();}
            quantity.add(temp);
            if(i==(n-1)){
                String addmeal;
                addmeal="";
                for(int j=0;j<n;j++){
                    if(addmeal==null){
                    addmeal=recipe.get(j)+"\t"+quantity.get(j)+"\t";}
                    else if(j==n-1){
                    addmeal=addmeal+recipe.get(j)+"\t"+quantity.get(j);}
                    else{
                    addmeal=addmeal+recipe.get(j)+"\t"+quantity.get(j)+"\t";}
                }
                meals.add(addmeal);
            }
        }
        UpdateText();
    }
    
    public boolean CheckMeal(int temp){
        boolean a = false;
        if(temp>=1&temp<meals.size()+1){
            a = true;
        }
        return a;
    }
}
