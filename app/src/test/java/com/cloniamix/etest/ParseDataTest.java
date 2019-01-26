package com.cloniamix.etest;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ParseDataTest {
/*
    private static final File FILE = new File("test_file");

    private ParseData mParseData = new ParseData(FILE);

    @Test
    public void getGroup_is3() throws Exception {
        PrintWriter writer = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(new FileOutputStream(FILE),UTF_8)), true);
        writer.println("{groupNum : 3}");
        writer.close();

        int groupNum = mParseData.readFile().getGroupNum();
        Assert.assertEquals(groupNum,3);
        FILE.delete();
    }

    @Test
    public void getGroup_not2() throws Exception{
        PrintWriter writer = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(new FileOutputStream(FILE),UTF_8)), true);
        writer.println("{groupNum : 3}");
        writer.close();

        int groupNum = mParseData.readFile().getGroupNum();
        Assert.assertNotEquals(groupNum,2);
        FILE.delete();
    }*/
}
