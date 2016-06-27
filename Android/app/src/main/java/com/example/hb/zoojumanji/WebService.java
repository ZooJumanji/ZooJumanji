package com.example.hb.zoojumanji;

/**
 * Created by hb on 22/06/2016.
 */
public class WebService {

    // IP local          "192.168.1.27"
    // IP virtual device "10.0.2.22"
    // IP Baya           "172.16.110.169"
    private static String ip = "172.16.110.169";

    public static String getIP() {
        return ip;
    }

    public static void setIP(String newIP) {
        ip = newIP;
    }
}
