/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.model.parser;

import java.io.File;
import org.insa.model.Model;
import org.insa.model.Scenario;
import org.insa.model.data.Data;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JusteAbel
 */
public class XmlParserTest {

    public XmlParserTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of saveScenario method, of class XmlParser.
     */
    @Test
    public void testSaveScenario() throws Exception {
        System.out.println("saveScenario");
        Data data = new Data();
        File output = new File("Scenario.xml");
        data.createSampleScenario(output);

        System.out.println("Saved scenario in " + output.getName());
        System.out.println("--------------------------------");
    }

    /**
     * Test of readScenarioFile method, of class XmlParser.
     */
    @Test
    public void testReadScenarioFile() throws Exception {
        System.out.println("readScenarioFile");
        File input = new File("Scenario.xml");
        Scenario expResult = null;
        Scenario result = XmlParser.readScenarioFile(input);
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
        System.out.println(result.printScenario());
        System.out.println("--------------------------------");

    }

}
