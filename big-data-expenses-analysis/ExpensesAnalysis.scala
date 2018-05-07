val dfTransactions=sqlContext.read.format("csv").option("header", "false").load("/home/tarik/transactions")
val dfClientCards=sqlContext.read.format("csv").option("header", "false").load("/home/tarik/cards")
val dfCardTypes=sqlContext.read.format("csv").option("header", "false").load("/home/tarik/card_types")

val dfCardTransactions = dfTransactions.filter(dfTransactions("type") === "CB")

val dfTmp = dfCardTransactions.join(dfClientCards, dfCardTransactions.col("account_id") === dfClientCards.col("account_id"))

val df = dfTmp.join(dfCardTypes, dfTmp.col("type_id") === dfCardTypes.col("type_id"))

val average_spend = df.select("account_id", "type_name").groupBy("account_id").agg(avg("transaction_amount").alias("average_spend")).sort(desc("average_spend"))


