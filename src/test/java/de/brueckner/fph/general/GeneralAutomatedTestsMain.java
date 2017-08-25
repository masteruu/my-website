package de.brueckner.fph.general;

import org.testng.TestNG;
import org.testng.xml.XmlPackage;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.List;

public class GeneralAutomatedTestsMain {

    public static void main(String[] args) {

        List<XmlSuite> suites = getXmlSuitesToRun(args);
        TestNG testng = new TestNG();
        testng.setXmlSuites(suites);
        testng.setVerbose(2);
        testng.setPreserveOrder(true);
        testng.run();

    }

    private static List<XmlSuite> getXmlSuitesToRun(String[] args) {
        XmlSuite suite = new XmlSuite();
        suite.setName("generalAutomatesTest");
        suite.setFileName("generalAutomatesTestSuite");

        XmlTest test = new XmlTest(suite);
        test.setName("general-automated-Test");
        if (args.length > 0) {
            test.addParameter("appUrl", args[0]);
        }
        if (args.length > 2) {
            test.addParameter("user", args[1]);
            test.addParameter("password", args[2]);
        }
        List<XmlPackage> packages = new ArrayList<XmlPackage>();
        packages.add(new XmlPackage("de.brueckner.fph.general.tests.*"));
        test.setPackages(packages);

        List<XmlSuite> suites = new ArrayList<XmlSuite>();
        suites.add(suite);
        return suites;
    }
}
