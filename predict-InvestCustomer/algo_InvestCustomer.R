library(rpart)

data = read.csv("/home/esibank/default_train.csv",header = TRUE, sep = ";")

ad.data <- rpart (Invest ~ Salary + Sold + Sold1 + Sold2 + Status, data)

data2 = read.csv("/home/esibank/InvestCustomer_test.csv",header = TRUE, sep = ";")

predict(ad.data, data2) 

ypred = (predict(ad.data, data2)>0.5)*1

write.csv(ypred,"/home/esibank/result_predictionInvest.csv")
