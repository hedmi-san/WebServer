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
                        request.append(line + "\n");
                        line = in.readLine();
                    }
                    // Change response based on route ?
                    String[] firstLine = request.toString().split("\n")[0].split(" ");
                    switch (firstLine[1]) {
                        // Send back an image?
                        case "/image":
                            FileInputStream image = new FileInputStream("C:\\Users\\LAPTOP SPIRIT\\Documents\\Learning\\Java\\WebServer\\src\\res\\hitler.jfif");
                            out.write(("HTTP/1.1 200 OK\n").getBytes());
                            out.write(("\r\n").getBytes());
                            out.write(image.readAllBytes());
                            out.write(("HTTP/1.1 200 OK\n").getBytes());
                            out.flush();
                            break;
                        // Just send back a simple "Hello world"
                        case "/hello":
                            out.write(("HTTP/1.1 200 OK\n").getBytes());
                            out.write(("\n").getBytes());
                            out.write(("Hello World").getBytes());
                            out.write(("HTTP/1.1 200 OK\n").getBytes());
                            out.flush();
                            break;
                        default:
                            out.write(("HTTP/1.1 200 OK\n").getBytes());
                            out.write(("\n").getBytes());
                            out.write(("What are you looking for?").getBytes());
                            out.write(("HTTP/1.1 200 OK\n").getBytes());
                            out.flush();
                            break;
                    }
                    // Send response - send our replay
                    
                // Get ready for the next message
                client.close();
                }  
            }
        }  
    }
}
