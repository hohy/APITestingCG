package cz.cvut.fit.hybljan2.apitestingcg.scanner;

import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;
import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.ScannerConfiguration;

/**
 * @author Jan Hýbl
 */
public interface APIScanner {
    public API scan();

    public void setConfiguration(ScannerConfiguration sc);
}
