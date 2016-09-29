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
public class Greeter {
    
    int choice;
    public void Start(){
        int choice = 1;
        while(choice != -1){
            System.out.println("Enter 1 to browse the Recipes." );
            System.out.println("Enter 2 to browse the Meals." );
            System.out.println("Enter 3 to browse the Plan Manager." );
            System.out.println("Enter 4 to edit the Recipes." );
            System.out.println("Enter 5 to edit the Meals." );
            System.out.println("Enter 6 to edit Plan Manager");
            System.out.println("Enter -1 to exit.");
            Scanner in = new Scanner(System.in);
            choice = in.nextInt();
            if(choice == -1){
                System.out.println("Thank-you for using IIKH.");
                break;
            }
            switch(choice) {
                case 1:
                    Recipe r = new Recipe();
                    r.PrintRecipes();
                    break;
                case 2:
                    Meal m = new Meal();
                    m.PrintMeals();
                    break;
                case 3:
                    Plan p = new Plan();
                    p.PrintPlan();
                    break;
                case 4:
                    Recipe r1 = new Recipe();
                    System.out.println("Press 1 to edit existing recipes. Press 2 to Add a new recipe.");
                    int x = in.nextInt();
                    if(x==2){
                        r1.AddRecipe();
                    }else
                    {
                        r1.EditRecipe();
                    }
                    break;
                case 5:
                    Meal m1 = new Meal();
                    System.out.println("Press 1 to edit existing meals. Press 2 to Add a new meal.");
                    int y = in.nextInt();
                    if(y==2){
                        m1.AddMeal();
                    }else
                    {
                        m1.EditMeals();
                    }
                    break;
                case 6:
                    Plan p1 = new Plan();
                    p1.EditPlan();
                    break;
                }
        }
    }
    
}
