#uploading train and test data
train <- read.csv(file='/home/esibank/leaving_customers_train.csv', header=TRUE, sep=';', encoding="UTF-8")
test <- read.csv(file='/home/esibank/leaving_customers_test.csv', header=TRUE, sep=';', encoding="UTF-8")

#Uploading prediction library
library(randomForest)

#Creation of predictor using randomForest method
model_rf <- randomForest::randomForest(train$Y~., data=train)

#Prediction new Y (customers leaving' probability)
results_rf <- predict(model_rf, test)

# print results
results_rf

#export result to csv File
write.csv(results_rf,"/home/esibank/leaving_customers_results.csv")
