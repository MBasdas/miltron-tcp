package com.miltron.miltron.connection;

import com.miltron.miltron.crc16.Crc16;
import com.miltron.miltron.crc16.CrcCalculator;
import com.miltron.miltron.utils.RawDataToTelemetryModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Arrays;

public class ConnectionTcpServer implements Runnable {
    private volatile String host;
    private SimpMessagingTemplate client;
    private volatile int port;


    public ConnectionTcpServer(SimpMessagingTemplate client,String host, int port) {
        this.port = port;
        this.client = client;
        this.host = host;
    }


    @Override
    public void run() {

        try {
            System.out.println("host: "+host+ ": port: "+port);
            Socket socket = new Socket(host, port);
            InputStream in = socket.getInputStream();
            byte[] buf = new byte[36];
            while ((in.read(buf)) != -1) {
                in.read(buf);
                byte[] check = Arrays.copyOfRange(buf, 0, 35);
                CrcCalculator crcCalculator = new CrcCalculator(Crc16.Crc16Buypass);
                long result = crcCalculator.Calc(check, 0, check.length);
                if(result == crcCalculator.getParams().Check){
                    client.convertAndSend("/topic/" + port, RawDataToTelemetryModel.getModel(buf));
                }else{
                    System.out.println("Invalid data > " + result);
                }

            }
        } catch (IOException e) {
            System.out.println("Conneciton refused> " + e.getLocalizedMessage());
        }
    }
}
