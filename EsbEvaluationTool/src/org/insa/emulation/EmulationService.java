/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.emulation;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.insa.model.Model;

/**
 *
 * @author JusteAbel
 */
public class EmulationService extends Service{
    private Model model;
    
    public EmulationService(Model model){
        this.model = model;
    }
    @Override
    protected Task createTask() {
        return new EmulationTask(model);
    }

    public Model getModel() {
        return model;
    }
    
}
