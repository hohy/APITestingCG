/*
 * Copyright(c) Jan Hybl, FIT CTU in Prague. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Common
 * Development and Distribution License (CDDL). You can obtain a copy of
 * the CDDL at http://www.netbeans.org/cddl.html.
 */

package cz.cvut.fit.hybljan2.apitestingcg.test;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIClassTest;
import org.junit.Test;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;


/**
 * Class contains some useful methods for testing.
 *
 * @author Jan Hýbl
 */
public class TestUtils {
    public static String readFileToString(String fileName) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append('\n').append(line);
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("Cant read file: " + fileName);
        } catch (IOException ex) {
            Logger.getLogger(APIClassTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(APIClassTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return sb.toString().substring(1);
    }

    public static void delete(File f) throws IOException {
        if (f.exists()) {
            if (f.isDirectory()) {
                for (File c : f.listFiles())
                    delete(c);
            }
            if (!f.delete())
                throw new FileNotFoundException("Failed to delete file: " + f);
        }
    }

    /**
     * Compares a content of two files.
     * @param expected   File with expected content.
     * @param actual     File with actual content.
     */
    public static void assertEqualFiles(File expected, File actual) {
        try {
            BufferedReader expReader = new BufferedReader(new FileReader(expected));
            BufferedReader actReader = new BufferedReader(new FileReader(actual));

            String expLine, actLine;
            int lineCounter = 0;

            while((expLine = expReader.readLine()) != null) { // go through whole file by lines
                lineCounter++;
                actLine = actReader.readLine();
                if (!expLine.equals(actLine)) {  // compare lines
                    // there is some difference in this lines, files are not equal
                    System.out.println("There is difference on the line " + lineCounter + ":");
                    // print both lines
                    System.out.println("Expected: \"" + expLine + "\"");
                    System.out.println("Actual:   \"" + expLine + "\"");
                    // and show the difference
                    if(expLine.length() > actLine.length()) {
                        System.out.println("Expected line is longer than actual.");
                    } else if (expLine.length() < actLine.length()) {
                        System.out.println("Expected line is shorter than actual.");
                    } else {
                        byte[] diffLine = new byte[expLine.length()];
                        for (int i = 0; i < expLine.length(); i++) {
                            if (expLine.charAt(i) != actLine.charAt(i)) {
                                diffLine[i] = '^';
                            }
                        }
                        System.out.println("Difference:" + diffLine);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Files comparison failed: " + e.getMessage());
        }
    }

    /**
     * Test of the assertEqualFiles method.
     */
    @Test
    public void TestCompareFiles() {
        File fileA = new File("testres/utils/FileA.txt");
        assertEqualFiles(fileA, fileA);
    }

    /**
     * Test of the assertEqualFiles method.
     */
    @Test
    public void TestCompareFiles2() {
        File fileA = new File("testres/utils/FileA.txt");
        File fileB = new File("testres/utils/FileB.txt");

        assertEqualFiles(fileA, fileB);
    }

    /**
     * Test of the assertEqualFiles method.
     */
    @Test
    public void TestCompareFiles3() {
        File fileA = new File("testres/utils/FileA.txt");
        File fileC = new File("testres/utils/FileC.txt");
        assertEqualFiles(fileA, fileC);
    }
}
