package webserver;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
public class WebServer {

    public static void main(String[] args) throws Exception{
        // Start Receiving messages - ready to receive messages!
        try(ServerSocket serverSocket = new ServerSocket(8080)){
            
            System.out.println("Server Started.\n Listening for messages.");
            
            while (true) {                
                // Handle the request - listen to the message 
                try (Socket client = serverSocket.accept()){
                    
                    System.out.println("Debug : got new message "+ client.toString());
                    
                    InputStreamReader isr = new InputStreamReader(client.getInputStream());
                    
                    BufferedReader in = new BufferedReader(isr);
                    
                    OutputStream out = client.getOutputStream();
                    
                    
                    StringBuilder request = new StringBuilder();
                    String line;
                    line = in.readLine();
                    while(!line.isBlank()){
                        request.append(line + "\r\n");
                        line = in.readLine();
                    }
                    
                    System.out.println("--REQUEST--");
                    System.out.println(request);
                    String[] firstLine = request.toString().split("\r\n")[0].split(" ");
                    switch (firstLine[1]) {
                        case "/image":
                            FileInputStream image = new FileInputStream("C:\\Users\\LAPTOP SPIRIT\\Documents\\Learning\\Java\\WebServer\\src\\res\\hitler.jfif");
                            out.write(("HTTP/1.1 200 OK\r\n").getBytes());
                            out.write(("\r\n").getBytes());
                            out.write(image.readAllBytes());
                            out.flush();
                            break;
                        case "/Hello":
                            out.write(("HTTP/1.1 200 OK\r\n").getBytes());
                            out.write(("\r\n").getBytes());
                            out.write(("Hello World").getBytes());
                            out.flush();
                        case "/abdo":
                            out.write(("HTTP/1.1 200 OK\r\n").getBytes());
                            out.write(("\r\n").getBytes());
                            out.write(("Hello abdo").getBytes());
                            out.flush();
                        case "/???":
                            out.write(("HTTP/1.1 200 OK\r\n").getBytes());
                            out.write(("\r\n").getBytes());
                            out.write(("What are you looking for?").getBytes());
                            out.flush();
                        default:
                            System.out.println("hehheehe");
                    }
                        // Just send back a simple "Hello world"
                        
                        // Send back an image?
                            
                        // Change response based on route ?

                    // Send response - send our replay
                    
                // Get ready for the next message
                client.close();
                }  
            }
        }  
    }
}
