package com.esipe.esibankm.esibankm;

import com.esipe.esibankm.esibankm.services.ConnectSocket;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Usman ABID BUTT on 20/11/2017.
 */

public class ConnectSocketTest  {

    @Mock
    ConnectSocket connectSocket;
    @Mock
    Socket socket;



    @Test
    public void testSimpleSendMessage() throws IOException {
        final ConnectSocket connectSocket = mock(ConnectSocket.class);
        PrintWriter printWriter = mock(PrintWriter.class);

        final Socket socket = mock(Socket.class);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        when(connectSocket.send("message",printWriter)).thenReturn(true);

        Assert.assertTrue(connectSocket.send("message", printWriter));

    }

    @Test
    public void testSimpleReadMessage() throws IOException {
        final ConnectSocket connectSocket = mock(ConnectSocket.class);
        BufferedInputStream buf = mock(BufferedInputStream.class);
        when(connectSocket.read(buf)).thenReturn("");

       Assert.assertEquals("",connectSocket.read(buf));
    }

    @Test
    public void testSendMessageToService(){

    }
    @Test
    public void testSocketCommunicationToServer(){

    }

    @Test
    public void testSocketPingPong(){

    }
    @Test
    public void testSwitchedConnection(){
        //test if retrying works or not
        //test if socket was created or not
    }

    @Test
    public void testLostNetwork(){
        //test if socket was created or not
        //test if retrying works or not

    }
    @Test
    public void testCheckTokendFormat(){

    }
}
