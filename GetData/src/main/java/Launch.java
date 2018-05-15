import com.mongodb.spark.MongoSpark;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.bson.Document;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

public class Launch {


    public static void main(String[] args) throws TwitterException {


        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey("gtUvue6zKvsPpsL3M222p5fxb")
                .setOAuthConsumerSecret("7REQlfoVtKzpQjm4QeCQHQ0KaKFiiPsgOuHmk1eiVrbvaJdaU5")
                .setOAuthAccessToken("994653025309425665-VDT7lfNcCrCo23QUqSd8vGhA4iCzNi0")
                .setOAuthAccessTokenSecret("1giTVvAR81KC3SU3vmYj5izrPbcHs2L2GktOix3V5Rchu");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter tw = tf.getInstance();
        List<Status> status = tw.getHomeTimeline();
        ReadFile readFile = new ReadFile();

        for(Status s: status){

            JavaRDD<Status> rdd = readFile.getJSC().parallelize(status);
            //JavaRDD<String> document = readFile.getJSC().textFile(s.getText().replaceAll("https?://[a-zA-Z0-9\\.-]+\\.[a-zA-Z]{2,4}(/\\S*)?","lien"));
            System.out.print("Début traitement");

            JavaRDD<Document> documentJavaRDD = rdd.map
                    (new Function<Status, Document>() {
                        public Document call(final Status status) throws Exception {
                            Document doc = new Document();

                            doc.put("tweetAuthor", status.getUser().getName());
                            doc.put("tweet", status.getText());
                            return doc;
                        }
                    });

            MongoSpark.save(documentJavaRDD);

            System.out.print("Fin traitement");
            System.out.print(s.getCreatedAt()+ s.getUser().getName() + " " + s.getText());
        }
    }
}

