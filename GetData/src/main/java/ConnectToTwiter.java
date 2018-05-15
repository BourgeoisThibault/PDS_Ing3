import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class ConnectToTwiter{

    private ConfigurationBuilder cb;
    private TwitterFactory tf;
    private Twitter tw;

    private ReadFile readFile;


    public ConnectToTwiter(){

        this.cb= new ConfigurationBuilder();
        cb.setOAuthConsumerKey("gtUvue6zKvsPpsL3M222p5fxb")
                .setOAuthConsumerSecret("7REQlfoVtKzpQjm4QeCQHQ0KaKFiiPsgOuHmk1eiVrbvaJdaU5")
                .setOAuthAccessToken("994653025309425665-VDT7lfNcCrCo23QUqSd8vGhA4iCzNi0")
                .setOAuthAccessTokenSecret("1giTVvAR81KC3SU3vmYj5izrPbcHs2L2GktOix3V5Rchu");
        this.tf = new TwitterFactory(cb.build());
        this.tw = tf.getInstance();


    }

}
