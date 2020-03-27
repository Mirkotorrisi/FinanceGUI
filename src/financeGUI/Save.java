package finance;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;

public interface SavePdF {
    static void SavePdf(double[][] quotes, FinancialAccounts account) throws FileNotFoundException, DocumentException {
        //Method to save pdf for each mortgage selected. Uses Pdf Writer
        DecimalFormat f = new DecimalFormat("##.00");
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\User\\Desktop\\mortgages\\"+account.getName()+" Mortgage.pdf"));
        document.open();

        PdfPTable table = new PdfPTable(4);
        table.addCell("DATE");
        table.addCell("REPAYMENT");
        table.addCell("CAPITAL QUOTE");
        table.addCell("INTEREST QUOTE");

        for(int i = 0; i < quotes[0].length; i++) {
            account.adjustRate();
            table.addCell(account.calcDates()[i]);
            table.addCell(f.format(quotes[0][i]));
            table.addCell(f.format(quotes[1][i]));
            table.addCell(f.format(quotes[2][i]));
        }
        document.add(table);
        Phrase phrase = new Phrase();
        phrase.add("Name: "+ account.getName()+"\nMortgage: "+ account.mortgage+"$");
        phrase.add("\nAmmortization: "+ account.mortType);
        phrase.add("\n"+account.riskProfile + " profile");
        document.add(phrase);
        document.close();

    }
}
