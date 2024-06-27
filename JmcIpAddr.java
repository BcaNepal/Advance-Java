import java.net.*;

public class JmcIpAddr {
  public static void main(String[] args) {
    try {
      // InetAddress address = InetAddress.getByName("www.janamaitri.edu.np");
      InetAddress address = InetAddress.getByName("172.67.163.124");
      // System.out.println(address);
      System.out.println(address.getHostName());
    } catch (UnknownHostException ex) {
      System.err.println("Could not find website " + ex);
    }
  }
}
