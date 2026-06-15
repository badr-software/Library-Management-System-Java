package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class RegisterClient {

    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 5000;

    public static boolean sendRegisterData(String name, String email, String phone, String password) {
        try (
                Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                DataInputStream input = new DataInputStream(socket.getInputStream())
        ) {
            String message = "REGISTER|" + name + "|" + email + "|" + phone + "|" + password;
            output.writeUTF(message);

            String response = input.readUTF();
            return response.equals("SUCCESS");
        } catch (IOException ex) {
            return false;
        }
    }
}
