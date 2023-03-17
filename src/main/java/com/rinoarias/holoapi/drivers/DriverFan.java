package com.rinoarias.holoapi.drivers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;
import java.util.logging.Level;

public class DriverFan {
    // Servidor Led Fan
    // Servidor | Host
    private static final String DEFAULT_SERVER_IP = "192.168.4.1";
    // Puerto de escucha del servidor. TCP
    private static final int DEFAULT_SERVER_PORT = 5233;

    static int powerFlag = 1;

    // Comandos
    private static final String playNext = "32";
    private static final String playlast = "33";
    private static final String playPause = "34";
    private static final String playStart = "35";
    private static final String playModelSingle = "36";
    private static final String playModelLoop = "37";
    private static final String getFileList = "38";
    private static final String playFile = "40";

    private static final String lightDown = "43";
    private static final String lightUp = "44";
    private static final String DEFAULT_NO_DATA_LENTH = "abc";
    private static final String DEFAULT_HAS_2_DATA_LENTH = "abe";
    private static final String end = "a4a8c2e3";
    private static final String sendAPInfo = "99";
    private static final String contentDefaultValue = "00";

    private static final String powerOff = "94";
    private static final String powerOn = "95";

    static DataInputStream in;
    static DataOutputStream out;

    public String mediaPlayerPlayNext() {
        return connect(noDataContentPackage(playNext));
    }
    public String mediaPlayerPlaylast() {
        return connect(noDataContentPackage(playlast));
    }
    public String mediaPlayerPlayPause() {
        return connect(noDataContentPackage(playPause));
    }
    public String mediaPlayerPlayStart() {
        return connect(noDataContentPackage(playStart));
    }
    public String mediaPlayerLightDown() {
        return connect(noDataContentPackage(lightDown));
    }
    public String mediaPlayerLightUp() {
        return connect(noDataContentPackage(lightUp));
    }

    public String playVideoWithId(String videoID) {
//        String command = "c31c" + playFile + DEFAULT_HAS_2_DATA_LENTH + intTo2Str(Integer.parseInt(videoID)) + end;
//        return connect(command);
        return connect(fixedDataContentPackage(playFile, intTo2Str(Integer.parseInt(videoID))));
    }

    public String selectSingleVideoPlaybackMode() {
        String command = "c31c" + playModelSingle + DEFAULT_NO_DATA_LENTH + end;
        return connect(command);
    }

    public String selectLoopPlaybackMode() {
        String command = "c31c" + playModelLoop + DEFAULT_NO_DATA_LENTH + end;
        return connect(command);
    }

    public String fetchVideoList() {
        String command = "c31c" + getFileList + DEFAULT_NO_DATA_LENTH + end;
        return connectRead(command);
    }

    private static String noDataContentPackage(String command) {
        return "c31c" + command + DEFAULT_NO_DATA_LENTH + end;
    }

    private static String fixedDataContentPackage(String command, String value) {
        return "c31c" + command + DEFAULT_HAS_2_DATA_LENTH + value + end;
    }


    private static String intTo2Str(int i) {
        if (i >= 0 && i < 10) {
            return "0" + i;
        } else if (i < 10 || i >= 100) {
            return i >= 100 ? sendAPInfo : contentDefaultValue;
        } else {
            return String.valueOf(i);
        }
    }

    //
    private static String connect(String message) {
        try {
            Socket client = new Socket(DEFAULT_SERVER_IP, DEFAULT_SERVER_PORT);
//            client.setSoTimeout(50000);
//            client.setTcpNoDelay(true);
//            client.setKeepAlive(false);

            byte[] data = message.getBytes();

            in = new DataInputStream(client.getInputStream());
            out = new DataOutputStream(client.getOutputStream());



            out.write(data);
            System.out.println("comando = " + message);

            in.close();
            out.close();
            client.close();

        } catch (IOException exception) {
            System.out.println("exception = " + exception);
            Logger.getLogger(DriverFan.class.getName()).log(Level.SEVERE, null, exception);
            return exception.getMessage();
        }

        return "Command successfull";
    }


    private static String connectRead(String message) {
        try {
            Socket client = new Socket(DEFAULT_SERVER_IP, DEFAULT_SERVER_PORT);
//            client.setSoTimeout(50000);
//            client.setTcpNoDelay(true);
//            client.setKeepAlive(false);

            byte[] data = message.getBytes();

            in = new DataInputStream(client.getInputStream());
            out = new DataOutputStream(client.getOutputStream());

            System.out.println("comando = " + message);

            out.write(data);

            byte[] bytes = new byte[1024];
            int count = in.read(bytes, 0, bytes.length);
            String str = new String(bytes, 0, count, StandardCharsets.US_ASCII);

            in.close();
            out.close();
            client.close();
            return str;

        } catch (Exception exception) {
            System.out.println("exception = " + exception);
            Logger.getLogger(DriverFan.class.getName()).log(Level.SEVERE, null, exception);
            return exception.getMessage();
        }
    }
}
