package network;

import javax.imageio.IIOException;
import java.io.IOException;
import java.net.Socket;

/**
 * ServerHandler
 *
 * Server side class to prepare and wait for messages from  a client specified
 * by_socket
 * */

public class ServerHandler extends  Thread{
    /**
     * The socket to receive message
     * */
    private Socket socket = null;
    /**
     * used for callback
     * */
    public  ServerManager srvrMgr= null;
    public  ServerHandler(ServerManager srvrMgr,Socket socket){
        this.srvrMgr= srvrMgr;
        this.socket=socket;
    }

    /**
     * Keep running a loop to receive messages from a client specified by socket
     * once the connection is broken, call ServerManager to remove this client
     *
     * */
    @Override
    public void run() {
        while(true){
            try{
                srvrMgr.receiveMessage(socket);
            }catch (IOException | ClassNotFoundException e){
                srvrMgr.clientDisconnected(socket);
                break;
            }
        }
    }
}
