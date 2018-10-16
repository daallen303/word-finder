
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author D-apple
 */
public class server {

    public static int factorial(int num) {
        int j = 1;
        for (int i = 1; i < num + 1; i++) {
            j *= i;
        }
        return num = j;
    }
    public static int combinations(int combo, List ch)
    {
        combo = factorial(ch.size());
        int repeat = 1;
        int counter = 0;
        for (int n = 0; n < ch.size(); n++) {
            boolean same = false;
            repeat = 1;
            if (n > 0) {
                counter++;
                for (int z = n - counter; z < n; z++) {
                    if (Objects.equals(ch.get(n), ch.get(z))) {
                        same = true;
                        break;
                    }
                }
                if (same == false) {
                    for (int m = n+1; m < ch.size(); m++) {
                        if (Objects.equals(ch.get(n), ch.get(m))) {
                            repeat++;
                        }
                    }
                }
            } else {
                for (int m = n + 1; m < ch.size(); m++) {
                    if (Objects.equals(ch.get(n), ch.get(m))) {
                        repeat++;
                    }
                }
            }
            System.out.println(repeat);
            combo /= factorial(repeat);
        }
        return combo;
    }
//    private static void repeat(Character c, List ch) {
//
//    }

    public static void main(String args[]) throws IOException, InterruptedException {
        
        String word = " ", temp;
        ServerSocket s1 = new ServerSocket(3248);
        Socket ss = s1.accept();
        List<Character> ch = new ArrayList<>();
        String[] string; 
        for(;;){
        Scanner sc;
        int combo = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(ss.getInputStream()));
        br = new BufferedReader(new InputStreamReader(ss.getInputStream()));
        sc = new Scanner(br);
        word = sc.next();
        for (char c : word.toCharArray()) {
            ch.add(c);
        }
        combo = combinations(combo,ch);
            System.out.println(combo);
            string = new String[combo + 1];
            PrintStream p = new PrintStream(ss.getOutputStream());
            p.println(combo);
            int j;
            for (j = 0; j < combo; j++) {
                temp = " ";
                Collections.shuffle(ch);
                StringBuilder sb = new StringBuilder();
                for (char c : ch) {
                    sb.append(c);
                }
                temp = sb.toString();
                while ((Arrays.asList(string).contains(temp))) {
                    Collections.shuffle(ch);
                    sb.delete(0, sb.length());
                    for (char c : ch) {
                        sb.append(c);
                    }
                    temp = sb.toString();
                }
                string[j + 1] = temp;
                p.println(temp);
        }
            for (char c : word.toCharArray()) {
            ch.clear();
            }
            for (int l = 0; l < combo; l++) {
            string[l] = "";
            }
        }
    }
  }
