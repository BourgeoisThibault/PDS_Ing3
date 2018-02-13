package service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.opencsv.CSVWriter;
import pds.esibank.models.LeavingCustomer;

public class CSVMaker {

    public void listToCSV(List<String[]> l) throws IOException
    {
        //Create CSV
        String csv = "leaving_customers_test.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csv), ';');
        for(int i = 0; i < l.size(); i++)
        {	//Write Line
            writer.writeNext(l.get(i));
        }
        writer.close();
    }

    public List<String[]> beanToRecord(List<LeavingCustomer> lcList)
    {
        List<String[]> listForCsv = new ArrayList<>();
        Iterator<LeavingCustomer> i = lcList.iterator();
        //Columns names
        String [] columnsName = {"Y","balances", "csp", "transactions", "connections", "transfers", "charges", "appreciations"};
        // Add column on first line
        listForCsv.add(columnsName);

        while(i.hasNext())
        {	//Add object in the listForCsv ArrayList of String[]
            listForCsv.add(i.next().toStringArray());
        }
        return listForCsv;
    }

}
