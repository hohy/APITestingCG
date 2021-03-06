/*
 * Copyright(c) Jan Hybl, FIT CTU in Prague. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Common
 * Development and Distribution License (CDDL). You can obtain a copy of
 * the CDDL at http://www.netbeans.org/cddl.html.
 */

package cz.cvut.fit.hybljan2.apitestingcg.configuration;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.Configuration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.WhitelistRule;
import cz.cvut.fit.hybljan2.apitestingcg.generator.ExtenderGenerator;
import cz.cvut.fit.hybljan2.apitestingcg.generator.Generator;
import cz.cvut.fit.hybljan2.apitestingcg.scanner.APIScanner;
import cz.cvut.fit.hybljan2.apitestingcg.scanner.SourceScanner;
import cz.cvut.fit.hybljan2.apitestingcg.test.TestUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static cz.cvut.fit.hybljan2.apitestingcg.test.TestUtils.assertEqualFiles;
import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 6.2.12
 * Time: 11:33
 */
public class GeneratorJobConfigurationTest {

    private static ConfigurationReader reader;

    @BeforeClass
    public static void setup() throws IOException {
        reader = new ConfigurationReader();
        // delete output files from previous run of whiteListTest2
        TestUtils.delete(new File("output/tests/configuration"));
        TestUtils.delete(new File("output/tests/configuration2"));
        TestUtils.delete(new File("output/tests/configuration3"));
        TestUtils.delete(new File("output/tests/deprecated"));
    }


    @Test
    public void whiteListTest() {

        Configuration c = reader.parseConfiguration("testres/configuration/whitelist.xml");
        WhitelistRule rule1 = new WhitelistRule();
        rule1.setItem(WhitelistRule.RuleItem.ALL);
        rule1.setRule("org.xml.sax.ext");

        WhitelistRule rule2 = new WhitelistRule();
        rule2.setItem(WhitelistRule.RuleItem.EXTENDER);
        rule2.setRule("Attributes2Impl.addAttribute");

        WhitelistRule rule3 = new WhitelistRule();
        rule3.setItem(WhitelistRule.RuleItem.IMPLEMENTER);
        rule3.setRule("Attributes2Impl.Attributes2Impl");

        List<WhitelistRule> rules = new LinkedList<WhitelistRule>();
        rules.add(rule1);
        rules.add(rule2);
        rules.add(rule3);

        assertEquals(rules, c.getGeneratorJobConfigurations().get(0).getWhitelistRules());
    }

    @Test
    public void whiteListTest2() {

        Configuration configuration = reader.parseConfiguration("testres/configuration/whitelist_lib.xml");
        APIScanner scanner = new SourceScanner();
        scanner.setConfiguration(configuration.getApiConfigurations().get(0));
        API api = scanner.scan();

        Generator generator = new ExtenderGenerator(configuration.getGeneratorConfiguration());
        generator.generate(api, configuration.getGeneratorJobConfigurations().get(0));

        File fa = new File("output/tests/configuration/lib/ClassAExtender.java");
        File fb = new File("output/tests/configuration/lib/ClassBExtender.java");
        assertTrue(fa.exists());
        assertFalse(fb.exists());
    }

    @Test
    public void whiteListTest3() {

        Configuration configuration = reader.parseConfiguration("testres/configuration/whitelist_lib2.xml");
        APIScanner scanner = new SourceScanner();
        scanner.setConfiguration(configuration.getApiConfigurations().get(0));
        API api = scanner.scan();

        Generator generator = new ExtenderGenerator(configuration.getGeneratorConfiguration());
        generator.generate(api, configuration.getGeneratorJobConfigurations().get(0));

        File fa = new File("output/tests/configuration2/lib/ClassAExtender.java");
        File fb = new File("output/tests/configuration2/lib/ClassBExtender.java");
        assertFalse(fa.exists());
        assertTrue(fb.exists());

        File expected = new File("testres/configuration/expected/ClassBExtender.java");
        assertEqualFiles(expected, fb);
    }

    @Test
    public void blackListTest() {

        Configuration configuration = reader.parseConfiguration("testres/configuration/blacklist_lib.xml");
        APIScanner scanner = new SourceScanner();
        scanner.setConfiguration(configuration.getApiConfigurations().get(0));
        API api = scanner.scan();

        Generator generator = new ExtenderGenerator(configuration.getGeneratorConfiguration());
        generator.generate(api, configuration.getGeneratorJobConfigurations().get(0));

        File fa = new File("output/tests/configuration3/lib/ClassAExtender.java");
        File fb = new File("output/tests/configuration3/lib/ClassBExtender.java");
        assertFalse(fa.exists());
        assertTrue(fb.exists());
    }

    @Test
    public void deprecatedTest() {
        Configuration configuration = reader.parseConfiguration("testres/configuration/deprecated.xml");
        APIScanner scanner = new SourceScanner();
        scanner.setConfiguration(configuration.getApiConfigurations().get(0));
        API api = scanner.scan();

        Generator generator = new ExtenderGenerator(configuration.getGeneratorConfiguration());
        generator.generate(api, configuration.getGeneratorJobConfigurations().get(0));

        File fa = new File("output/tests/deprecated/all/lib/ClassAExtender.java");
        File fb = new File("output/tests/deprecated/all/lib/ClassBExtender.java");
        assertTrue(fa.exists());
        assertTrue(fb.exists());

        generator.generate(api, configuration.getGeneratorJobConfigurations().get(1));

        fa = new File("output/tests/deprecated/nodeprecated/lib/ClassAExtender.java");
        fb = new File("output/tests/deprecated/nodeprecated/lib/ClassBExtender.java");
        assertFalse(fa.exists());
        assertTrue(fb.exists());

        File fe = new File("testres/configuration/lib-deprecated-expected/ClassBExtender.java");
        assertEqualFiles(fe, fb);
    }

}
