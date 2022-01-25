package com.example.calculator;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.File;
import java.util.ArrayList;
import javafx.scene.media.*;

public class CalculatorController {
    @FXML
    private TextField display;
    private String expression = "";
    private ArrayList<String> arrayExpression = new ArrayList<>();
    private static final int TextfieldMaxLength = 20;

    @FXML
    public void buttonOnClickBasic(Event event) {

        Button button = (Button) event.getTarget();
        if (expression.length()< TextfieldMaxLength){
            expression += button.getText();
        }
        display.setText(expression);
        //String sound = "C:\\Users\\99926\\Desktop\\Rick Astley - Never Gonna Give You Up (Official Music Video).mp3";
        //Media song = new Media(new File(sound).toURI().toString());
        //MediaPlayer songs = new MediaPlayer(song);
        //songs.play();
    }
    //Arithmetic symbol:  +, −, ×, ÷
    public void buttonEqual(){
        String result = expression;
        String num = "";
        double num1;
        if(errorDetector1(result)){
            for(int i = 0; i < result.length(); i++){
                if((result.charAt(i)>='0'&&result.charAt(i)<='9')||result.charAt(i)=='.' ){
                    num += result.charAt(i);
                    if(i == result.length()-1){
                        arrayExpression.add(num);
                    }
                }else if (result.charAt(i)=='+'||result.charAt(i)=='−'||result.charAt(i)=='×'||result.charAt(i)=='÷'){
                    arrayExpression.add(num);
                    num = Character.toString(result.charAt(i));
                    arrayExpression.add(num);
                    num ="";
                }
            }
            if (errorDetector2(arrayExpression)) {
                while (arrayExpression.contains("×") || arrayExpression.contains("÷")) {
                    for (int i = 0; i < arrayExpression.size(); i++) {
                        if (arrayExpression.get(i).equals("×")) {
                            num1 = Double.parseDouble(arrayExpression.get(i - 1)) * Double.parseDouble(arrayExpression.get(i + 1));
                            for (int ind = 0; ind < 3; ind++) {
                                arrayExpression.remove(i - 1);
                            }
                            arrayExpression.add(i - 1, String.valueOf(num1));

                        } else if (arrayExpression.get(i).equals("÷")) {
                            num1 = Double.parseDouble(arrayExpression.get(i - 1)) / Double.parseDouble(arrayExpression.get(i + 1));
                            for (int ind = 0; ind < 3; ind++) {
                                arrayExpression.remove(i - 1);
                            }
                            arrayExpression.add(i - 1, String.valueOf(num1));
                        }
                    }
                }
                while (arrayExpression.contains("+") || arrayExpression.contains("−")) {
                    for (int i = 0; i < arrayExpression.size(); i++) {
                        if (arrayExpression.get(i).equals("+")) {
                            num1 = Double.parseDouble(arrayExpression.get(i - 1)) + Double.parseDouble(arrayExpression.get(i + 1));
                            for (int ind = 0; ind < 3; ind++) {
                                arrayExpression.remove(i - 1);
                            }
                            arrayExpression.add(i - 1, String.valueOf(num1));

                        } else if (arrayExpression.get(i).equals("−")) {
                            num1 = Double.parseDouble(arrayExpression.get(i - 1)) - Double.parseDouble(arrayExpression.get(i + 1));
                            for (int ind = 0; ind < 3; ind++) {
                                arrayExpression.remove(i - 1);
                            }
                            arrayExpression.add(i - 1, String.valueOf(num1));
                        }
                    }
                }
                expression = arrayExpression.get(0);
                display.setText(expression);
                arrayExpression.clear();
            }else{
               expression = "              SYNTAX ERROR";
               display.setText(expression);
            }

        }else{
            expression = "              SYNTAX ERROR";
            display.setText(expression);
        }
    }
    public void buttonDelete(){
        String expressionAD = "";
        if(!expression.equals("              SYNTAX ERROR")){
            for(int i = 0; i < expression.length()-1; i++){
                expressionAD += expression.charAt(i);
            }
            expression = expressionAD;
            display.setText(expressionAD);
        }
    }
    public void buttonClear(){
        expression = "";
        display.setText(expression);
        for(int i = 0; i<arrayExpression.size();i++){
            arrayExpression.remove(0);
        }
    }
    public boolean errorDetector1(String error){
        for(int i1 = 0; i1 < error.length(); i1++){
           boolean b = error.charAt(i1) == '+' || error.charAt(i1) == '−' || error.charAt(i1) == '×' || error.charAt(i1) == '÷';
           boolean b1  = error.charAt(i1)==' '||(error.charAt(i1)>='A'&&error.charAt(i1)<='Z');

           if (b){
               if(i1+1!=error.length()) {
                   if (error.charAt(i1 +1)=='+'||error.charAt(i1 +1)=='−'||error.charAt(i1 +1)=='×'||error.charAt(i1 +1)=='÷'){
                       return false;
                   }else if (i1 == 0){
                       return false;
                   }
               }else if(i1+1 == error.length()){
                   return false;
               }
           }else if(b1){
               return false;
           }else if(error.charAt(i1) =='.'){
               if(i1 + 1!= error.length()&&i1!=0){
                   if(error.charAt(i1+1)=='.'){
                       return false;
                   }else if ((error.charAt(i1 + 1) == '+' || error.charAt(i1 + 1) == '−' || error.charAt(i1 + 1) == '×' || error.charAt(i1 + 1) == '÷')&&(error.charAt(i1-1)=='.'||error.charAt(i1 -1)=='+'||error.charAt(i1 -1)=='−'||error.charAt(i1 -1)=='×'||error.charAt(i1 -1)=='÷')){
                       return false;
                  }
               }else if(i1 +1 == error.length()){
                   if(error.charAt(i1 -1)=='+'||error.charAt(i1 -1)=='−'||error.charAt(i1 -1)=='×'||error.charAt(i1 -1)=='÷'){
                       return false;
                   }
               } else if (i1 ==0) {
                   if (error.charAt(i1+1)=='.'||error.charAt(i1 + 1) == '+' || error.charAt(i1 + 1) == '−' || error.charAt(i1 + 1) == '×' || error.charAt(i1 + 1) == '÷') {
                       return false;
                   }
               }
           }
       }
       return true;
    }
    public boolean errorDetector2(ArrayList<String> error2){
        for(int i = 0; i< error2.size(); i++){
            int num = 0;
            for(int ind = 0; ind< error2.get(i).length();ind++){
                if(error2.get(i).charAt(ind)=='.'){
                    num++;
                }
            }
            if(num > 1){
                return false;
            }
        }
        return true;
    }
    public void sounds(){

    }

}
