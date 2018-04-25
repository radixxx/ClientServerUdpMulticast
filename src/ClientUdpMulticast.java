import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ClientUdpMulticast {

    private static InetAddress address;
    private static byte[] buffer;
    private static DatagramPacket packet;
    private static String str;
    private static MulticastSocket socket;


    public static void main(String arg[]) throws Exception {
        System.out.println("Waiting for a message from the server");
        try {
// Создание объекта MulticastSocket для получения
// данных от группы, используя номер порта 1502
            socket = new MulticastSocket(1502);
            address = InetAddress.getByName("233.0.0.1");
// Регистрация клиента в группе
            socket.joinGroup(address);
            while (true) {
                buffer = new byte[256];
                packet = new DatagramPacket(buffer, buffer.length);

                //Получение данных от сервера
                socket.receive(packet);
                str = new String(packet.getData());
                System.out.println("Received message: " + str.trim());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                socket.leaveGroup(address);
                socket.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
