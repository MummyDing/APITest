package com.demo.mummyding.apitest.cache.tables;

/**
 * Created by mummyding on 15-11-9.
 */
public class PolicyTable {
    public static final String NAME ="policy";
    public static final String ID="id";
    public static final String TITLE="title";
    public static final String DESCRIPTION="description";
    public static final String DATE="date";
    public static final String LINK="link";
    public static final String CREATE_TABLE=
            "create table "+NAME+"("+
                    ID + " integer primary key autoincrement,"+
                    TITLE+" text,"+
                    DESCRIPTION+" text,"+
                    DATE+" text,"+
                    LINK+" text)";
}
