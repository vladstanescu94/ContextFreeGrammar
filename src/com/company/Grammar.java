package com.company;

import com.sun.xml.internal.xsom.impl.scd.Step;

import javax.management.StringValueExp;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Grammar {
    public StringBuffer Start;
    public ArrayList<String> T = new ArrayList<>();
    public ArrayList<String> N = new ArrayList<>();
    public ArrayList<ArrayList<String>> P = new ArrayList<>();

    private Random r = new Random();


    public  void readInput(String path){

        File file = new File(path);
        try {
            Scanner sc = new Scanner(file);
            Start = new StringBuffer();
            Start.append(sc.nextLine());
            if(sc.nextLine().startsWith("<T>")) {
                String[] line = sc.nextLine().split(",");
                Collections.addAll(T, line);
            }
            if(sc.nextLine().startsWith("<N>")) {
                String[] line = sc.nextLine().split(",");
                Collections.addAll(N, line);
            }
            if (sc.nextLine().startsWith("<P>")) {
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    if (N.contains(line)) {
                        ArrayList<String> str = new ArrayList<>();
                        String[] production = sc.nextLine().split(",");
                        Collections.addAll(str, production);
                        P.add(new ArrayList<>(str));
                    }
                }
            }
            sc.close();
        }
        catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }
    }

    private Boolean checkString(StringBuffer S, ArrayList<String> A){
        int y = 0;
        for (int i = 0 ; i<S.length(); i++) {
            for (String str: A) {
                if (Character.toString(S.charAt(i)).equals(str)) {
                    y = 1;
                    break;
                }
            }
            if (y == 1){
                break;
            }
        }
        return (y == 1);
    }
    private int checkIndex(StringBuffer S, ArrayList<String> A){
        int index = -1;
        for (int i = 0; i<S.length(); i ++){
            if (A.contains(Character.toString(S.charAt(i)))){
                index = i;
            }
        }
        return index;
    }
    private StringBuffer clearLambda(StringBuffer S){
        for (int i = 0 ; i< S.length(); i ++){
            if (S.charAt(i) == 'Ω'){
                S = S.deleteCharAt(i);
            }
        }
        return S;
    }

    public StringBuffer generate(){
        int seed;
        while (Start.length()<60 && checkString(Start, N)){
            int indexN = checkIndex(Start, N);
            int indexOfRule = N.indexOf(Character.toString(Start.charAt(indexN)));
            int sizeOfRule = P.get(indexOfRule).size();
            seed =r.nextInt(sizeOfRule);
            Start = Start.replace(indexN,indexN+1, P.get(indexOfRule).get(seed));
            Start = clearLambda(Start);
            System.out.println(Start);
        }
        return Start;

    }

}
