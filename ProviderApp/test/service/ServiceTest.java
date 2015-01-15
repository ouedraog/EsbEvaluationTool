/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

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
public class ServiceTest {

    public ServiceTest() {
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
     * Test of operation method, of class Service.
     */
    @Test
    public void testOperation() {
        System.out.println("Testing operation web service");
        String request = "";
        int dataSize = 2;
        int processingTime = 10000;
        Service instance = new Service();
        String expResult = "";
        String result = instance.operation(request, dataSize, processingTime);
        assertNotNull(expResult);
        System.err.println(result);
    }

}
