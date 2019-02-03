package Utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class Network {
    public static String getHostAddresses() {
        StringBuilder result = new StringBuilder();
        try {
            Enumeration e = NetworkInterface.getNetworkInterfaces();
            while (e.hasMoreElements()) {
                NetworkInterface n = (NetworkInterface) e.nextElement();
                Enumeration ee = n.getInetAddresses();
                while (ee.hasMoreElements()) {
                    InetAddress i = (InetAddress) ee.nextElement();
                    if (i.isSiteLocalAddress())
                        result.append(i.getHostAddress()).append("\n");
                }
            }
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred while finding IPs... :(";
        }
    }
}
