/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tickRate;

/**
 *
 * @author Asus
 */
public class SecondlyJob implements Runnable{

    public void run() {
        // Do your seccondly job here.
        System.out.println("Job trigged by scheduler");
    }
}
