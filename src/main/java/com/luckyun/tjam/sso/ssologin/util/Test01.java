package com.luckyun.tjam.sso.ssologin.util;

import java.util.HashMap;
import java.util.Map;

public class Test01 {

    public static void main(String[] args) {

        String str = "sdnasjhdasdaksnfcjdshdfufhaosinfdsjncxkjz";
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        char[] arr = str.toCharArray();

        for (char ch : arr) {
            if (map.containsKey(ch)) {
                Integer old = map.get(ch);
                map.put(ch, old + 1);
            } else {
                map.put(ch,1);
            }
        }
        System.out.println(map);
        for (Character k: map.keySet()){
            Integer integer = map.get(k);
            System.out.println(integer);
        }
    }
}


        //int count = getCount("sxhhxhxhxh", "x");

       /* System.out.println(count);
        System.out.println("111111111111");*/


   /* public static int getCount(String str,String key){

        int count = 0;
        int i = 0;

        while((i=str.indexOf(key))!=-1){

            count ++;
             str = str.substring(i + key.length());
        }

        return count;
    }*/

