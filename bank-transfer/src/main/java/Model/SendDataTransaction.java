package Model;

import Service.ParserXML;
import org.apache.log4j.Logger;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jeremy on 03/12/2017.
 */
public class SendDataTransaction {

    private final Logger logger = Logger.getLogger(SendDataTransaction.class);

    public void postMessage(){
        try{
            logger.info("Start method postMessage ...");
            final String uri = "http://192.154.88.155:9001/si-externe/transaction/send";
            //final String uri = "http://localhost:9001/transaction/send";
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            logger.info("Start connexion to : " + url);
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            OutputStream os = connection.getOutputStream();

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            ParserXML ps = new ParserXML();
            FileReader fileReader = new FileReader(ps.getNameFile());
            StreamSource source = new StreamSource(fileReader);
            StreamResult result = new StreamResult(os);
            transformer.transform(source, result);

            os.flush();
            logger.info("sending a request POST");
            connection.getResponseCode();
            logger.info("Response SI-Externe :"+connection.getResponseCode());
            logger.info("End connexion ... ");
            connection.disconnect();
            logger.info("Stop Method");
        }
        catch (FileNotFoundException e) {
            logger.error("File not found");
            e.printStackTrace();
        } catch (ConnectException e){
            logger.error("Le si-externe est injoignable");
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("Exception during execution");
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }


    }


}
