/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iikh;
import java.util.*;
import java.lang.*;
import java.io.*;
/**
 *
 * @author Aditya
 */
public class Plan {
    String text;
    ArrayList<String> meal=new ArrayList<String>();
    public Plan(){
        try{
            BufferedReader buf = new BufferedReader(new FileReader("test/database.txt"));
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
                            meal.add(each);
                        }
                    }
                }
            }
            UpdateText();
            buf.close();
        }catch(Exception e){
            e.printStackTrace();}
    }
    
    public void PrintPlan(){
        String to_print="";
        for(int i=0;i < meal.size();i++){
            if(i%3==0){
                to_print=to_print+"\n"+"Day "+((i/3)+1)+": Breakfast: Meal "+meal.get(i)+" ";
            }
            if((i-1)%3==0){
                to_print=to_print+"Lunch: Meal "+meal.get(i)+" ";
            }
            if((i-2)%3==0){
                to_print=to_print+"Dinner: Meal "+meal.get(i);
            }
        }
        System.out.println(to_print);
    }
    
    public void EditPlan(){
        PrintPlan();
        System.out.println("Enter the no of Day whose Plan you wish to change.");
        Scanner in = new Scanner(System.in);
        int plan_no = in.nextInt();
        if(plan_no < meal.size()){
            Meal m = new Meal();
            int choice;
            System.out.println("Enter 1 to change breakfast or 2 to change Lunch or 3 to change Dinner.");
            choice = in.nextInt();
            int temp;
            if(choice==1){
                System.out.println("Enter a new meal number.");
                temp=in.nextInt();
                if(m.CheckMeal(temp)){
                    meal.set(3*(plan_no-1),Integer.toString(temp));
                }
            }
            if(choice==2){
                System.out.println("Enter a new meal number.");
                temp=in.nextInt();
                if(m.CheckMeal(temp)){
                    meal.set(3*(plan_no-1)+1, Integer.toString(temp));
                }
            }
            if(choice==3){
                System.out.println("Enter a new meal number.");
                temp=in.nextInt();
                if(m.CheckMeal(temp)){
                    meal.set(3*(plan_no-1)+2, Integer.toString(temp));
                }
            }
        }else{
            System.out.println("This Day doesn't exist.");
        }
        UpdateText();
    }
    
    public void UpdateText(){
        text="";
        for(int i=0; i < meal.size();i++){
                if(i%3==0){
                    text=text+meal.get(i)+"\t";}
                else if((i-1)%3==0){
                    text=text+meal.get(i)+"\t";}
                else{
                    text=text+meal.get(i)+"\n";}
            }
        File fold=new File("test/database.txt");
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
}
