import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.bson.Document;
import org.apache.spark.api.java.function.Function;
import com.mongodb.spark.MongoSpark;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadFile {

    private File getFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource("ratings.txt").getFile());
    }


    private Path getFilePath(File file) {
        Path path = Paths.get(file.toURI());
        return path;
    }

    public static void main(String[] args) {

        SparkSession spark = SparkSession.builder()
                .master("local")
                .appName("MongoSparkConnectorIntro")
                .config("spark.mongodb.input.uri", "mongodb://127.0.0.1/complaintDB.consumerCPT2")
                .config("spark.mongodb.output.uri", "mongodb://127.0.0.1/complaintDB.consumerCPT2")
                .getOrCreate();

        ReadFile runner = new ReadFile();
        SparkConf conf = new SparkConf().setAppName("Job Mariam").setMaster("local[*]");
        JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext());
        Path path = runner.getFilePath(runner.getFile());

        JavaRDD<String> lines = jsc.textFile(path.toString());

        JavaRDD<Document> documentJavaRDD = lines.map
                (new Function<String, Document>() {
                    public Document call(final String stringRDD) throws Exception {
                        Document doc = new Document();
                        doc.put("message", stringRDD);
                        return doc;
                    }
                });

        MongoSpark.save(documentJavaRDD);

        jsc.close();

}

}
