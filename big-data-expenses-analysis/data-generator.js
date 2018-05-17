num_clients=500000
num_transactions=3000000

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
				"account_id":"123456"
			}
		);
	}
}

function addTransactions()
{
	for(var i=1;i<=num_transactions;i++)
	{
		db.transactions.insert(
			{
				"client_id" : Math.floor(Math.random() * num_clients) + 1, 
				"amount":50,
				"date":"15/02/2018",
				"balance":1200,
				"transaction_type":"CB",
				"card_type":1
			}
		);
	}
}
