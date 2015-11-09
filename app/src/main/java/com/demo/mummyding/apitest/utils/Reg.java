package com.demo.mummyding.apitest.utils;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mummyding on 15-11-8.
 */
public class Reg {
    public static String RegexUtil(String regex,String match){
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(match);
        String s = null;
        while(m.find()){
            s = m.group();
        }
        return s.substring(1,s.length()-1);
    }
    public static String RegexReplaceUtil(String regex,String match,String str){
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(match);
        return m.replaceAll(str);
    }
}
