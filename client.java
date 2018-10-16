
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
public class client {

    public static void main(String args[]) throws IOException, InterruptedException {
        String endl = System.getProperty("line.separator");
        String word1 = "", temp1 = null;
        boolean understood = false;
        int port = 3248;
        boolean end = false;
        boolean valid = false;
        String ipAddress = "127.0.0.1";
        int count = 0;
        int i = 0;
        String answer;
        int MAX = 141000;
        BufferedReader reader = new BufferedReader(new FileReader("dictionary.dat"));
        String[] dictionary = new String[MAX];
        List<String> realwords = new ArrayList<>();
        String line;
        while (i < MAX) {
            line = reader.readLine();
            if (line == null) {
                break;
            }
            dictionary[i] = line;
            i++;
        }
        Scanner sc = null;
        
        BufferedReader br = null;
        Scanner sc1;
        Socket s; 
        s = new Socket(ipAddress, port);
             PrintStream p = new PrintStream(s.getOutputStream());
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        while (end == false) {
            count = 0;
             valid = false;
             understood = false;
                sc = new Scanner(System.in);
                System.out.println("Enter any string of 7 or fewer letters");
            while (valid == false) {
                word1 = sc.next();
                word1 = word1.toLowerCase();
                if (word1.length() > 7) {
                    System.out.println("the string you entered was too long, ");
                } else {
                    for (int r = 0; r < word1.length(); r++) {
                        if (word1.charAt(r) < 'a' || word1.charAt(r) > 'z') {
                            System.out.println("the string you enter must be all letters, ");
                            break;
                        } else {
                            valid = true;
                        }
                    }
                }

                if (valid == false) {
                    System.out.println("please try again" + endl + endl);
                }

            }
            p.println(word1);
            sc1 = new Scanner(br);
            System.out.println(endl);
            for (int k = sc1.nextInt(); k > 0; k--) {
                temp1 = null;
                temp1 = sc1.next();
                if (temp1 == null) {
                    break;
                }
                System.out.println(temp1);
                if ((Arrays.asList(dictionary).contains(temp1))) {
                    realwords.add(temp1);
                }
                count++;
            }
            System.out.println("'" + word1 + "'" + " has a total of " + (count) + " possible different combinations");
            System.out.println("There is " + realwords.size() + " # of words which are as follows:");
            for (int m = 0; m < realwords.size(); m++) {
                System.out.println(realwords.get(m));
                System.out.println(endl);
            }
            realwords.clear();
            while (understood == false) {
            System.out.println(endl + "Enter another word? enter yes or no");
            sc = new Scanner(System.in);
            answer = sc.next().toLowerCase();
            if ("yes".equals(answer)) {
                end = false;
                understood = true;
            } else if ("no".equals(answer)) {
                end = true;
                understood = true;
            } else {
                System.out.println("Please make sure to enter yes, or no");
                understood = false;
            }           
          }
        }
    }
}

      
