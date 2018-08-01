package com.activiti.pru;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.activiti.pru.model.CashPayment;

public class DatabaseReader {

	private static final Logger logger = LogManager.getLogger(DatabaseReader.class);
	Properties prop = new Properties();
	InputStream input = null;
	ArrayList<CashPayment> cashArrayList=new ArrayList<CashPayment>();
	
	public void readDB() {
		try {
			input = this.getClass().getResourceAsStream("/META-INF/activiti-app/activiti-app.properties");
			prop.load(input);

			// get the property value and print it out
			String myDriver = prop.getProperty("datasource.driver");
			String myUrl = prop.getProperty("datasource.url");
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, prop.getProperty("datasource.username"),
					prop.getProperty("datasource.password"));
			String query = "SELECT * FROM cashpaymentdetails";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				CashPayment model=new CashPayment();
				model.setPolicyNumber(rs.getInt("policyNumber"));
				model.setTransactionDate(rs.getString("transactionDate"));
				model.setProductDescription(rs.getString("productDescription"));
				model.setCreditAmount(rs.getString("creditAmount"));
				model.setBank(rs.getString("bank"));
				model.setTransactionType(rs.getString("transactionType"));
				model.setChequeNumber(rs.getString("chequeNumber"));
				model.setValueDate(rs.getString("valueDate"));
				cashArrayList.add(model);				
				logger.info("One Record is retrieved From the Database"+model);
			}
			CSVFileWriter.writeCsvFile("ILFile", cashArrayList);
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
