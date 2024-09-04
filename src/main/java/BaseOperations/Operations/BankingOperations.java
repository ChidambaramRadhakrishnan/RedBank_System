package BaseOperations.Operations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import POJO.CustomerOperations.AuthenticateUser;
import POJO.CustomerOperations.CustomerDetails;

public interface BankingOperations {

	Connection establishDBConnection();
	
	Statement getStatement(Connection connection);
	
	int UpdateBalance(Connection connection,String Accountno, int Amount);
	
	String addUser(CustomerDetails user);
	
	int UpdateLogInTime(String AccountNo, String Time);
	
	int DepositAmount( String AccountNo, String Amount);
	
	Boolean MTPINVerification(String AcconutNo, String MTPIN);
	
	Boolean MPINVerification(String AccountNo, String MPIN);
	
	String LoginCustomer(AuthenticateUser user);
	
	int UpdateSingleField(String AcconutNo, String FieldName,String Value);
	
	int UpdateMultipleField(String AccontNo, String FieldName,String FirstValue,String FieldName2 ,String SecondValue);

	String AccountNo();
	String Password();
	
	String getCurrentCustomerData(String AccountNo, int ColumnIndex);
	
	String FundTransfer(String BeneficiaryAccountNo,String CurrentCustomerAccountNo,String Amount);
	
	
}
