/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back_end.controller.classes.request;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author epilif3sotnas
 */
public class Data_Format {
    private String name;
    private String value;

    public Data_Format () {}
    
    public Data_Format (String _name, String _value) {
        if (_name != null && _value != null) {
            
        } else {
            throw new IllegalArgumentException("Data not valid!!");
        }
        this.name = _name;
        this.value = _value;
    }

    public String getName () {
        return name;
    }

    public String getValue () {
        return value;
    }
    
    public ArrayList format (String data) {
        ArrayList<Data_Format> dataFormated = new ArrayList<>();
        if (data == null) return dataFormated;
        String[] dataSepareted = data.split("&");
        System.out.println(Arrays.toString(dataSepareted));
        for (String each : dataSepareted) {
            String[] values = each.split("=");
            dataFormated.add(new Data_Format(values[0], values[1]));
        }
        
        return dataFormated;
    }
}
