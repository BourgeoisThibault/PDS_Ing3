package com.esipe.esibankm.esibankm;

import android.util.Log;

import com.esipe.esibankm.esibankm.services.ConnectSocket;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
    public void testSocketPingPong(){

    }

    @Test
    public void reader() throws IOException {
        final Socket s = mock(Socket.class);
        final ConnectSocket connectSocket = mock(ConnectSocket.class);
        InputStream anyInputStream = new ByteArrayInputStream("test".getBytes());

        BufferedInputStream reader = new BufferedInputStream(anyInputStream);
        when(connectSocket.read(reader)).thenReturn("test");
        Assert.assertEquals("test",connectSocket.read(reader));

    }

}
