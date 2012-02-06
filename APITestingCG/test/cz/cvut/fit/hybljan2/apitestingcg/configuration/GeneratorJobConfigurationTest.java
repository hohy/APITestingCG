package cz.cvut.fit.hybljan2.apitestingcg.configuration;

import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.Configuration;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.WhitelistRule;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 6.2.12
 * Time: 11:33
 */
public class GeneratorJobConfigurationTest {

    private static ConfigurationReader reader;
    
    @BeforeClass
    public static void setup() {
        reader = new ConfigurationReader();
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

}
