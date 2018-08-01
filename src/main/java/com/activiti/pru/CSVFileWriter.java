package com.activiti.pru;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.activiti.pru.model.CashPayment;

public class CSVFileWriter {
	
	private static final Logger logger = LogManager.getLogger(CSVFileWriter.class);
	//Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";

	//CSV file header
	private static final String FILE_HEADER = "Policy Number,Value Date,Transaction Date,Credit Amount,Product Description,Cheque Number,Transaction Type,Bank";

	public static void writeCsvFile(String fileName, ArrayList<CashPayment> cashPayments) {
		
	FileWriter fileWriter = null;
	File file=null;		
		try {
			file=new File(fileName+".csv");
			file.createNewFile();
			fileWriter = new FileWriter(file);

			//Write the CSV file header
			fileWriter.append(FILE_HEADER.toString());
			
			//Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);
			
			//Write a new student object list to the CSV file
			for (CashPayment cashPayment : cashPayments) {
				fileWriter.append(String.valueOf(cashPayment.getPolicyNumber()));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(cashPayment.getValueDate());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(cashPayment.getTransactionDate());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(cashPayment.getCreditAmount());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(cashPayment.getProductDescription());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(cashPayment.getChequeNumber());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(cashPayment.getTransactionType());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(cashPayment.getBank());
				fileWriter.append(NEW_LINE_SEPARATOR);
			}

			
			
			logger.info("CSV file was created successfully !!!");
			
		} catch (Exception e) {
			logger.info("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				logger.info("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
			}
			
		}
	}
}
