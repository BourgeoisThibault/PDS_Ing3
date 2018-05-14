import com.mongodb.spark.MongoSpark;
import com.mongodb.spark.rdd.api.java.JavaMongoRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.bson.Document;
import twitter4j.TwitterException;

public class Algorithm {

    public static void main(String[] args) throws TwitterException {


        ReadFile row = new ReadFile();


        Function<Document, Boolean> filter1 = e -> e.toJson().contains("Agence");
        Function<Document, Boolean> filter2 = e -> e.toJson().contains("Plainte");
        Function<Document, Boolean> filter3 = e -> e.toJson().contains("banque");
        Function<Document, Boolean> filter4 = e -> e.toJson().contains("client");

        JavaMongoRDD<Document> rdd = MongoSpark.load(row.getJSC());

        System.out.print("Document avec le filtre 1");
        JavaRDD<Document> rdd1 = rdd.filter(filter1);
        System.out.print(rdd1.collect());

        System.out.print("Document avec le filtre 2");
        JavaRDD<Document> rdd2 = rdd.filter(filter2);
        System.out.print(rdd2.collect());

        System.out.print("Document avec le filtre 3");
        JavaRDD<Document> rdd3 = rdd.filter(filter3);
        System.out.print(rdd3.collect());

        System.out.print("Document avec le filtre 4");
        JavaRDD<Document> rdd4 = rdd.filter(filter4);
        System.out.print(rdd4.collect());


        //System.out.println(rdd.count());
        //System.out.println(rdd.first().toJson());

        row.getJSC().close();

    }

}
