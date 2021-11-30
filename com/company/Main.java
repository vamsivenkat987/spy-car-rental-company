package com.company;

import java.io.*;
import java.util.*;

public class Main {
    public static Kattio io;
    public static Set<String> spies = new TreeSet<>();
    public static Map<String,Integer> catalog = new HashMap<>();
    public static Map<String,Integer> pickup = new HashMap<>();
    public static Map<String,Integer> cost = new HashMap<>();
    public static Map<String,String> finalList = new HashMap<>();
    public static Map<String,Integer> cal = new HashMap<>();
    public static Map<String,Integer> last= new HashMap<>();
    public static void main(String[] args) {
        io = new Kattio(System.in, System.out);
        int test = io.getInt();
        if (test<101 ) {
            int d=0;
            while (d<test){
                int test1 = io.getInt();
                int test2 = io.getInt();
                if ((test1>=0 && test1<=500) && (test2>=0 && test2<=10000)) {
                    for (int j = 0; j < test1; j++) {
                        carNeeds();
                    }
                    for (int k = 0; k < test2; k++) {
                        spiesNeeds();
                    }
                }
                showOutput(spies);
                spies.clear();
                d++;
            }
        }
        io.close();
    }
    public static void showOutput(Set<String> a){
        if (a.size()<501) {
            for (Object name : a) {
                if (cal.containsKey(name)) {
                    io.println(name + " " + cal.get(name));
                } else {
                    io.println(name + " " + "INCONSISTENT");
                }
            }
        }
    }
    public static void spiesNeeds(){
        int time = io.getInt();
        String spyName = io.getWord();
        String letter = io.getWord();
        String p = io.getWord();
        char let = letter.charAt(0);
        int time1 =0;
        spies.add(spyName);
        if (time - time1 >= 0 && (spyName.length()>0 && spyName.length()<=40) && (time<=100000)) {
            time1 += time;
            int z = last.getOrDefault(spyName, 0);
            int h = cal.getOrDefault(spyName, 0);
            if (let == 'p') {
                finalList.put(spyName, p);
            } else if (let == 'r') {
                if (finalList.containsKey(spyName)) {
                    int catalogPrice = cost.get(finalList.get(spyName)), pickupCost = pickup.get(finalList.get(spyName)), a = Integer.parseInt(p);
                    if (a >= 0 && a <= 1000) {
                        int percent = a * catalogPrice;
                        h = h + percent + pickupCost + z;
                        if (cal.containsKey(spyName)) {
                            cal.replace(spyName, h);
                        } else {
                            cal.put(spyName, h);
                        }
                    }
                }
            } else if (let == 'a') {
                if (finalList.containsKey(spyName)) {
                    int catalogPrice = catalog.get(finalList.get(spyName)), a = Integer.parseInt(p);
                    int near = Math.round(catalogPrice / a);
                    z = z + near;
                    if (a >= 0 && a <= 100) {
                        if (last.containsKey(spyName)) {
                            last.replace(spyName, z);
                        } else {
                            last.put(spyName, z);
                        }
                    }
                }
            }

        }
    }
    public static void carNeeds(){
        String carName = io.getWord();
        int catalogPrice = io.getInt();
        int pickupCost = io.getInt();
        int costPerKilo = io.getInt();
        if (carName.length()<=40 && carName.length()>0) {
            if ((catalogPrice>0 && catalogPrice<=100000) && (pickupCost>0 && pickupCost<=1000)
                    && (costPerKilo>0 && costPerKilo<=100)) {
                catalog.put(carName,catalogPrice);
                pickup.put(carName,pickupCost);
                cost.put(carName,costPerKilo);
            }
        }
    }

}
class Kattio extends PrintWriter {

    public Kattio(InputStream i) {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }
    public Kattio(InputStream i, OutputStream o) {
        super(new BufferedOutputStream(o));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() {
        return peekToken() != null;
    }

    public int getInt() {
        return Integer.parseInt(nextToken());
    }

    public double getDouble() {
        return Double.parseDouble(nextToken());
    }

    public long getLong() {
        return Long.parseLong(nextToken());
    }

    public String getWord() {
        return nextToken();
    }



    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
        if (token == null)
            try {
                while (st == null || !st.hasMoreTokens()) {
                    line = r.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) { }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }


}







