num_clients=500000
num_transactions=5000000

function randomString() {
  var chars = "ABCDEFGHIJKLMNOPQRSTUVWXTZ";
	var string_length = 8;
	var result = '';
	for (var i=0; i<string_length; i++) {
		var rnum = Math.floor(Math.random() * chars.length);
		result += chars.substring(rnum,rnum+1);
	}
	return result;
}

function addClients()
{
	for(var i=1;i<=num_clients;i++)
	{
		db.clients.insert(
			{
				"client_id" : i, 
				"first_name" : randomString(),
				"last_name" : randomString(),
				"account_id": 1000000+i
			}
		);
	}
}

function randomDate(start, end) {
    return new Date(start.getTime() + Math.random() * (end.getTime() - start.getTime()));
}

function addTransactions()
{
	for(var i=1;i<=num_transactions;i++)
	{
		db.transactions.insert(
			{
				"client_id" : Math.floor(Math.random() * 1000) + 1, 
				"amount": Math.random() * (500) + 1,
				"date": randomDate(new Date(2017, 0, 1), new Date()),
				"balance": Math.random() * (10000) - 1000,
				"transaction_type":"CB",
				"card_type": Math.floor(Math.random() * 4) + 1
			}
		);
	}
}


function addCheckTransactions()
{
	for(var i=1;i<=3000;i++)
	{
		db.transactions.insert(
			{
				"client_id" : Math.floor(Math.random() * 1000) + 1, 
				"amount": Math.random() * (500) + 1,
				"date": randomDate(new Date(2017, 0, 1), new Date()),
				"balance": Math.random() * (10000) - 1000,
				"transaction_type":"check",
				"card_type": ""
			}
		);
	}
}
