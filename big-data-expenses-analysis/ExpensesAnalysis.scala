import com.mongodb.spark._
import com.mongodb.spark.config._

val cl_rdd = sc.loadFromMongoDB(ReadConfig(Map("uri" -> "mongodb://127.0.0.1/pds_db.clients")))
val tr_rdd = sc.loadFromMongoDB(ReadConfig(Map("uri" -> "mongodb://127.0.0.1/pds_db.transactions")))

val dfCardTypes=sqlContext.read.format("csv").option("header", "false").load("/home/tarik/card_types")

val dfCardTransactions = dfTransactions.filter(dfTransactions("card_type") === "CB")

val dfTmp = dfCardTransactions.join(dfClientCards, dfCardTransactions.col("account_id") === dfClientCards.col("account_id"))

val df = dfTmp.join(dfCardTypes, dfTmp.col("type_id") === dfCardTypes.col("type_id"))

val average_spend = df.select("account_id", "type_name").groupBy("account_id").agg(avg("transaction_amount").alias("average_spend")).sort(desc("average_spend"))


