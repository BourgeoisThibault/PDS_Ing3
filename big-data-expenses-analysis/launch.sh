./spark-shell --packages com.databricks:spark-csv_2.10:1.4.0 \
			--conf "spark.mongodb.input.uri=mongodb://127.0.0.1/pds_db.clients?readPreference=primaryPreferred" \
			--conf "spark.mongodb.output.uri=mongodb://127.0.0.1/pds_db.clients" \
			--packages org.mongodb.spark:mongo-spark-connector_2.11:2.2.2 \
			-i /ExpensesAnalysis.scala