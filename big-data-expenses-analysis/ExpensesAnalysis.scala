import com.mongodb.spark._
import com.mongodb.spark.config._
import org.apache.spark.sql.functions.{to_date, to_timestamp}

val cl_rdd = sc.loadFromMongoDB(ReadConfig(Map("uri" -> "mongodb://127.0.0.1/pds_db.clients")))
val tr_rdd = sc.loadFromMongoDB(ReadConfig(Map("uri" -> "mongodb://127.0.0.1/pds_db.transactions")))
val dfCardTypes=sqlContext.read.format("csv").option("header", "false").load("/home/tarik/card_types")

val cl_df = cl_rdd.toDF()
val tr_df = tr_rdd.toDF()

val cb_df = tr_df.filter(tr_df("transaction_type")==="CB")

val df = cb_df.withColumn("date", weekofyear(to_date($"date", "dd-MMM-yyyy")).alias("week"))

val results = df.select("client_id","date", "balance", "amount", "card_type").groupBy("client_id","card_type","date").agg(sum("amount"), min("balance")).sort("client_id")
val cl = cl_df.select("client_id", "frist_name", "last_name")
results.show

println("exporting results to csv...")
results.write.format("com.databricks.spark.csv").option("header", "true").save("results.csv")
cl.write.format("com.databricks.spark.csv").option("header", "true").save("cl_df.csv")
dfCardTypes.write.format("com.databricks.spark.csv").option("header", "true").save("dfCardTypes.csv")
println("done.")

exit 0