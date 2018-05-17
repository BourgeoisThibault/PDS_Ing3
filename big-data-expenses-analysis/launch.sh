./spark-shell --packages com.databricks:spark-csv_2.10:1.4.0 \
	--jars /spark-mongodb-core-0.8.7.jar,/casbah-commons_2.10-2.8.0.jar,\
	/casbah-core_2.10-2.8.0.jar,/casbah-query2.10-2.8.0.jar,\
	/mongo-java-driver-2.13.0.jar \
	-i ExpensesAnalysis.scala
