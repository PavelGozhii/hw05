package com.company;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
	// write your code here
        MyMap<String, Integer> myhashMap = new MyHashMap();
        myhashMap.put("Hello", 4);
        myhashMap.put("HelloW", 4);
        myhashMap.clear();
        myhashMap.put("String", 4);
        myhashMap.put("String1", 3);
        myhashMap.put("String", 3);
        myhashMap.put("String", 10);
        myhashMap.put("QWER", 3);
        myhashMap.put(null, 1);
        myhashMap.put("hash", null);
        myhashMap.put("hash", 123);
        System.out.println(myhashMap.toString());

        int a = myhashMap.remove(null);
        System.out.println(myhashMap.get("hash"));
        System.out.println(myhashMap.remove("hash"));
        System.out.println(a);
        System.out.println(myhashMap.toString());
        System.out.println(myhashMap.get(null));
    }
}

