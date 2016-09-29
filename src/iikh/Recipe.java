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
public class Recipe {
    String text;
    ArrayList<String> name=new ArrayList<String>();
    ArrayList<String> indegrients=new ArrayList<String>();
    ArrayList<String> procedure=new ArrayList<String>();
    public Recipe()
    {
        try{
            BufferedReader buf = new BufferedReader(new FileReader("test/recipe.txt"));
            ArrayList<String> words = new ArrayList<>();
            String lineJustFetched = null;
            String[] wordsArray;
            while(true){
                lineJustFetched = buf.readLine();
                if(lineJustFetched == null){  
                    break; 
                }else{
                    wordsArray = lineJustFetched.split("\t");
                    for(String each : wordsArray){
                        if(!"".equals(each)){
                            words.add(each);
                        }
                    }
                }
            }
            for(int i=0;i <words.size();i++){
                if(i%3==0){
                    name.add(words.get(i));
                }
                else if((i-1)%3==0){
                    indegrients.add(words.get(i));
                }
                else if((i-2)%3==0){
                    procedure.add(words.get(i));
                }
            }
            UpdateText();
            buf.close();
        }catch(Exception e){
            e.printStackTrace();}
    }
    
    public void UpdateText()
    {
        text="";
        for(int i=0; i < name.size();i++){
                if(text==null){
                    text=name.get(i)+"\t"+indegrients.get(i)+"\t"+procedure.get(i)+"\n";}
                else if(i==name.size()-1){
                    text=text+name.get(i)+"\t"+indegrients.get(i)+"\t"+procedure.get(i);}
                else{
                    text=text+name.get(i)+"\t"+indegrients.get(i)+"\t"+procedure.get(i)+"\n";}
            }
        File fold=new File("test/recipe.txt");
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

    public void PrintRecipes(){
        int size = name.size();
        if(size >0){
            for(int i=0;i<name.size();i++)
            {
                System.out.print((i+1)+"\t"+name.get(i)+"\t"+indegrients.get(i)+"\t"+procedure.get(i)+"\n");
            }
        }else{
            System.out.println("No recipes in database.");
        }
    }
    
    public void EditRecipe(){
        PrintRecipes();
        System.out.println("Enter the Recipe No. you wish to edit.");
        Scanner in = new Scanner(System.in);
        int recipe_no = in.nextInt();
        if(recipe_no < name.size()+1){
            String temp;
            in.nextLine();
            System.out.println("Enter a new name. If you don't wish to change press -1.");
            temp= in.nextLine();
            if(!("-1".equals(temp))){
                name.set(recipe_no-1,temp);}
            
            System.out.print("Enter a new set of indegrients. If you don't wish to change press -1.");
            temp = in.nextLine();
            if(!("-1".equals(temp))){
                indegrients.set(recipe_no-1,temp);}
            
            System.out.print("Enter a new procedure. If you don't wish to change press -1.");
            temp = in.nextLine();
            if(!("-1".equals(temp))){
                procedure.set(recipe_no-1,temp);}
        }else{
            System.out.println("The entered recipe number doesn't exist.");
        }
        UpdateText();
    }
    
    public void AddRecipe()
    {
        System.out.print("Enter the name of recipe.");
        String temp;
        Scanner in = new Scanner(System.in);
        temp = in.nextLine();
        name.add(temp);
        System.out.print("Enter the indegreints of recipe.");
        temp = in.nextLine();
        indegrients.add(temp);
        System.out.print("Enter the procedure of recipe.");
        temp = in.nextLine();
        procedure.add(temp);
        UpdateText();
    }
    
    public boolean CheckRecipe(String sample){
        boolean a = false;
        for(int i=0;i<name.size();i++){
            if(name.get(i).equals(sample)){
                a=true;
            }
        }
        return a;
    }
}
