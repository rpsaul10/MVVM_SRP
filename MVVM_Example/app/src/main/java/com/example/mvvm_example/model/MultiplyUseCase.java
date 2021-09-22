package com.example.mvvm_example.model;

public class MultiplyUseCase {

    public static String doMultiply(String n1,String n2) {
        try {
            return String.valueOf(Double.parseDouble(n1) * Double.parseDouble(n2));
        } catch (NumberFormatException e) {
            return "Wrong Input";
        }
    }
}
