package cz.cvut.fit.hybljan2.apitestingcg.configuration;

import com.sun.jnlp.ApiDialog;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.Configuration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.WhitelistRule;
import cz.cvut.fit.hybljan2.apitestingcg.ngenerator.ExtenderGenerator;
import cz.cvut.fit.hybljan2.apitestingcg.ngenerator.Generator;
import cz.cvut.fit.hybljan2.apitestingcg.scanner.APIScanner;
import cz.cvut.fit.hybljan2.apitestingcg.scanner.SourceScanner;
import cz.cvut.fit.hybljan2.apitestingcg.test.TestUtils;
import junitx.framework.FileAssert;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        FileAssert.assertEquals(expected, fb);
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

}
