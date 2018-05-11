import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

public class ConnectToTwiter{


    public static void main( String[] args ) throws TwitterException {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey("gtUvue6zKvsPpsL3M222p5fxb")
                .setOAuthConsumerSecret("7REQlfoVtKzpQjm4QeCQHQ0KaKFiiPsgOuHmk1eiVrbvaJdaU5")
                .setOAuthAccessToken("994653025309425665-VDT7lfNcCrCo23QUqSd8vGhA4iCzNi0")
                .setOAuthAccessTokenSecret("1giTVvAR81KC3SU3vmYj5izrPbcHs2L2GktOix3V5Rchu");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter tw = tf.getInstance();

        List<Status> status = tw.getHomeTimeline();
        for(Status s: status){
            System.out.print(s.getUser().getName() + " " + s.getText());
        }
    }
}
