package cz.cvut.fit.hybljan2.apitestingcg.scanner;

import cz.cvut.fit.hybljan2.apitestingcg.configuration.model.ScannerConfiguration;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;

/**
 *
 * @author Jan Hýbl
 */
public interface APIScanner {
    public API scan();

    public void setConfiguration(ScannerConfiguration sc);
}
