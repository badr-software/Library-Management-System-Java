package Client;

import java.io.*;
import java.net.*;

public class ClientProgram {

    public static void sendData(

            String name,
            String email,
            String phone,
            String password)

    {

        try {

            Socket socket=
                    new Socket(
                            "localhost",
                            6000
                    );

            DataOutputStream out=
                    new DataOutputStream(
                            socket.getOutputStream()
                    );

            out.writeUTF(name);
            out.writeUTF(email);
            out.writeUTF(phone);
            out.writeUTF(password);

            out.close();

            socket.close();

        }

        catch(Exception e){

            e.printStackTrace();

        }

    }

}