package Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.opencsv.CSVWriter;
import pds.esibank.models.InvestCustomer;

/**
 * Created by SarahAllouche on 07/02/2018.
 */
public class MakeCSV {

    public void MakeCsvFromList(List<String[]> listForCsv) throws IOException
    {
        Integer i;
        //Csv Name
        String csv = "InvestCustomer_test.csv";
        //Create Csv File with ';' separators
        CSVWriter writer = new CSVWriter(new FileWriter(csv), ';');
        for(i = 0; i < listForCsv.size(); i++)
        {	//Write Line
            writer.writeNext(listForCsv.get(i));
        }
        writer.close();
    }

    public List<String[]> BeanToRecord(List<InvestCustomer> ListOfInvestCustomer)
    {
        List<String[]> listForCsv = new ArrayList<>();
        Iterator<InvestCustomer> i = ListOfInvestCustomer.iterator();
        //Columns names
        String [] columnsName = {"Id","FirstName", "LastName", "Salary", "Sold", "Sold1", "Sold2", "Status"};
        // Add column on first line
        listForCsv.add(columnsName);

        while(i.hasNext())
        {	//Add object in the listForCsv ArrayList of String[]
            listForCsv.add(i.next().ToString());

        }
        return listForCsv;
    }

}
