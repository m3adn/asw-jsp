/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back_end.controller.classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import io.github.cdimascio.dotenv.Dotenv;

/**
 *
 * @author epilif3sotnas
 */
public class Modem {
    private final static short PIN = 1234;
    private final static String PORT_NAME = "/dev/ttyUSB0";
    private final static String PROJECT = "ASW";
    private final static short PORT = 10000;
    private final static short PORT_SPEED = 19200;
    private static SerialPort SP;
    
    static {
        try {
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(PORT_NAME);
            SerialPort sp = (SerialPort) portIdentifier.open(PROJECT, PORT);
            sp.setSerialPortParams(PORT_SPEED, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            Modem.SP = sp;
        } catch (NoSuchPortException | PortInUseException | UnsupportedCommOperationException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public boolean sendMessage (int number, String msg) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(SP.getOutputStream()));
            BufferedReader br = new BufferedReader(new InputStreamReader(SP.getInputStream()));

            bw.write("at\r");
            bw.flush();

            if (br.readLine().equals("OK")) {
                bw.write("at+cpin?\r");
                bw.flush();
                
                if (br.readLine().equals("READY")) {
                    
                } else {
                    bw.write("at+cpin=" + Dotenv.load().get("pin") + "\r");
                    bw.flush();

                    if (br.readLine().equals("OK")) {
                        bw.write("at+cmgf=1\r");
                        bw.flush();
                        bw.write("at+cmgs=" + number + "\r");
                        bw.flush();
                        bw.write(msg + "\r");
                        bw.flush();
                        
                        if (br.readLine().equals("OK")) {
                            SP.close();
                            return true;
                        }
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        SP.close();
        return false;
    }
}
