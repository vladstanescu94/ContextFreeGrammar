package com.company;


public class Main {

    public static void main(String[] args) {
        //instantiate grammar object
        Grammar gr = new Grammar();
        //set input file
        gr.readInput("input2.txt");
        //generate string
        System.out.println("Start is : " + gr.Start);
        System.out.println("Terminal is : " + gr.T);
        System.out.println("Nonterminal is : " + gr.N);
        System.out.println("Production is : " + gr.P + " Size : " +gr.P.size());
        StringBuffer str = gr.generate();
        System.out.println("Generated String : " + str);
        System.out.println("String size : " + str.length());

    }
}
