# FinanceGUI
Java application to calculate mortgages using CSV file and exporting PDF files
Created using JavaFX and iTextPDF.

Functions: 

1) Create a new Mortgage:
 -set initial mortgage value
 -set customer's name
 -set number of repayments
 -set the first day, month and year of the mortgage
 -set the number of repayments within a year (repayments can be every 1, 2, 3, 6 month) 
 -set the kind of depreciation for the mortgage (Italian, French)
 -set the interest rate (High Risk or Low Risk)
 
2) Show Mortgages:
show every mortgage saved in the CSV file, with informations about the start and the end date, and every rate specifying the interest quote, the capital quote, the date of repayment.

3) Save PdF:
select a mortgage to save a PdF in the pre-selected directory. The Pdf stores informations about each repayment.
