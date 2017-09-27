/*
 * Copyright (C) 2017 zsel
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.neology.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author obsidiam
 */
public class UDPThread extends Thread implements Runnable {
    private Thread TH;
    private UDPConnector uc;
    
    public UDPThread(UDPConnector uc){
        this.uc = uc;
    }
    
    @Override
    public void start(){
        if(TH == null){
            TH = new Thread(this,"UDPThread");
            TH.start();
        }
    }
    
    @Override
    public void run(){
        while(TH != null){
            if(!TH.isInterrupted()){
                try {
                    DatagramPacket dtg = uc.readDatagramPacket();
                    processData(dtg.getData());
                    
                } catch (IOException ex) {
                    Logger.getLogger(UDPThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    private void processData(byte[] buffer){
        System.out.print(new String(buffer));
    }
    
    @Override
    public void interrupt(){
    }
    
}