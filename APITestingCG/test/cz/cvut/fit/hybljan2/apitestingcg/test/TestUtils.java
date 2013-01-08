/*
 * Copyright(c) Jan Hybl, FIT CTU in Prague. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Common Developmen and Distribution License (CDDL). You can obtain a copy of the CDDL at http://www.netbeans.org/cddl.html.
 */

package cz.cvut.fit.hybljan2.apitestingcg.test;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIClassTest;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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


}
