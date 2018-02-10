package Model;

import Service.ParserXML;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.*;
import java.nio.channels.Channel;

/**
 * Created by jeremy on 03/02/2018.
 */
public class GetDataTransaction {

    public void GetDataTransaction() {
        String SFTPHOST = "192.154.88.151";
        int SFTPPORT = 22;
        String SFTPUSER = "esibank";
        String SFTPPASS = "esibankpds";
        String SFTPWORKINGDIR = "/home/SI-Externe/Input/";

        Session session = null;
        Channel channel = null;
        ChannelSftp channelSftp = null;

        try {
            JSch jsch = new JSch();
            session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
            session.setPassword(SFTPPASS);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            channel = (Channel) session.openChannel("sftp");
            channelSftp.connect();
            channelSftp = (ChannelSftp) channel;
            channelSftp.cd(SFTPWORKINGDIR);
            byte[] buffer = new byte[1024];
            BufferedInputStream bis = new BufferedInputStream(channelSftp.get(ParserXML.getNameFile()));
            File newFile = new File("/home/esibank/transactionesibank/Output/"+ ParserXML.getNameFile());
            OutputStream os = new FileOutputStream(newFile);
            BufferedOutputStream bos = new BufferedOutputStream(os);
            int readCount;
            while ((readCount = bis.read(buffer)) > 0) {
                System.out.println("Writing: ");
                bos.write(buffer, 0, readCount);
            }
            bis.close();
            bos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}


