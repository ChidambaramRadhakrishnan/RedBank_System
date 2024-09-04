package BaseOperations.Operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import POJO.CustomerOperations.AuthenticateUser;
import POJO.CustomerOperations.CustomerDetails;
import UtilityOperations.Operations.Operations;

public class BankingOperationsKeywords implements BankingOperations{
	
	static String AccountNo = Operations.accountNumberGenerator();
	static private String Password;
	

	@Override
	public Connection establishDBConnection() {
		Connection connect = null;
		try {
	        connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/RedBank","ChidambaramDemoMYDB","@1mysqlpass1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connect;
	}

	@Override
	public Statement getStatement(Connection connection) {
		Statement statement = null;
		try {
			 statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return statement;
	}
	
	@Override
	public int UpdateBalance(Connection connection,String Accountno, int Amount) {
		
		int count = 0;
		
		try {
			PreparedStatement preparedStatment = connection.prepareStatement("update userdetails set Balance = ? where AccountNumber = ?");
			preparedStatment.setInt(1, Amount);
			preparedStatment.setString(2, Accountno);
			
			count = preparedStatment.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
	
	@Override
	public String addUser(CustomerDetails user) {
		Connection connection = establishDBConnection();
		
		Password = user.getConfirmPassword();
		
//		String update = "userNotAdded.jsp";
		
		String update = "";
		
		try {
			
			PreparedStatement queries  = connection.prepareStatement("insert into userdetails values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			queries.setString(1, user.getFirstName());
			queries.setString(2, user.getLastName());
			queries.setString(3, user.getPhoneNumber());
			queries.setString(4, user.getEmail());
			queries.setString(5, user.getCity());
			queries.setString(6, user.getState());
			queries.setString(7, user.getAccountType());
			queries.setString(8, user.getBalance());
			queries.setString(9, user.getPassword());
			queries.setString(10, user.getConfirmPassword());
			queries.setString(11, user.getMPIN());
			queries.setString(12, user.getMTPIN());
			queries.setString(13, AccountNo);
			queries.setString(14, "Not set Yet");
			
			int updationResponse = queries.executeUpdate();
			
			if(updationResponse >=1) {
				update = "AccountCreateConfirmation.jsp";
			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return update;
	}
	
	@Override
	public Boolean MTPINVerification(String AcconutNo, String MTPIN) {
		Connection connection =establishDBConnection();
		boolean status = false;
		try {
			ResultSet result = getStatement(connection).executeQuery(String.format("select MTPIN from userdetails where AccountNumber = '%s'",AcconutNo));
			while(result.next()) {
				if(result.getString(1).equals(MTPIN)) {
					status = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return status;
	}

	@Override
	public String AccountNo() {
		
		return AccountNo;
	}

	@Override
	public String Password() {
		
		return Password;
	}

	@Override
	public String LoginCustomer(AuthenticateUser user) {
		Connection connection = establishDBConnection();
		
		String page = "LoginFailed.jsp";
		
		try {
			
			ResultSet result = getStatement(connection).executeQuery(String.format("select AccountNumber,ConfirmPassword from userdetails where AccountNumber='%s'", user.getAccountNumber()));
			while(result.next()) {
				if(user.getAccountNumber().equals(result.getString(1)) && user.getPassword().equals(result.getString(2))) {
					page = "Home.jsp";
				}
				break;
			}	
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return page;
	}


	@Override
	public String getCurrentCustomerData(String accountNo, int columnIndex) {

		Connection connection = establishDBConnection();
		ResultSet result;
		String Data = "";
		
		try {
			result = getStatement(connection).executeQuery(String.format("select * from userdetails where AccountNumber ='%s'",accountNo));
			while(result.next()) {
				Data = result.getString(columnIndex);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Data;
	}

	@Override
	public String FundTransfer(String BeneficiaryAccountNo, String CurrentCustomerAccountNo, String Amount) {
		
		Connection connection = establishDBConnection();
		ResultSet BeneficiaryResultset;
		ResultSet CustomerResultSet;
		
		String page = "";
		
		try {
			
			int  BeneficiaryBalance = 0;
			
			int CurrentCustomerBalance = 0;
			
			//Fetching Current login customer balance
			
			CustomerResultSet = getStatement(connection).executeQuery(String.format("select Balance from userdetails where AccountNumber = '%s'", CurrentCustomerAccountNo));
			
			while(CustomerResultSet.next()) {
				CurrentCustomerBalance = Operations.GetInteger(CustomerResultSet.getString(1));
			}
			
			//Fetching Beneficiary customer balance
			
			BeneficiaryResultset = getStatement(connection).executeQuery(String.format("select Balance from userdetails where AccountNumber ='%s'", BeneficiaryAccountNo));
			
			while(BeneficiaryResultset.next()) {
				BeneficiaryBalance = Operations.GetInteger(BeneficiaryResultset.getString(1));
			}
			
			// Start doing fund transfer
			
			int CurrentCustomerUpdatedBalance = CurrentCustomerBalance - Operations.GetInteger(Amount); // 5000 - 1000 = 4000
			
			if(CurrentCustomerUpdatedBalance < 0) {
				page = "FundTransactionFail.jsp";
			}else {
				
				// deduct Customer balance
				
				UpdateBalance(connection, CurrentCustomerAccountNo, CurrentCustomerUpdatedBalance);
				
				//Fetching Beneficiary balance and Updating new Balance into Database 
				
				int BeneficiaryUpdatedBalance = BeneficiaryBalance + Operations.GetInteger(Amount);
				
				UpdateBalance(connection, BeneficiaryAccountNo, BeneficiaryUpdatedBalance);
				page = "FundTransactionSuccess.jsp";
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return page;
		
	}

	@Override
	public Boolean MPINVerification(String AccountNo, String MPIN) {
		Connection connection =establishDBConnection();
		boolean status = false;
		
		
		try {
			ResultSet result = getStatement(connection).executeQuery(String.format("select MPIN from userdetails where AccountNumber = '%s'",AccountNo));
			while(result.next()) {
				if(result.getString(1).equals(MPIN) && !(result.getString(1)==null)) {
					status = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return status;
	}


	@Override
	public int DepositAmount(String AccountNo, String Amount) {
		
		Connection connection = establishDBConnection();
		int CustomerBankBalance = 0;
		int count = 0;
		
		try {
			ResultSet result = getStatement(connection).executeQuery(String.format("select Balance from userdetails where AccountNumber= '%s'",AccountNo));
			
			while(result.next()) {
				CustomerBankBalance = Operations.GetInteger((result.getString(1)));
			}
			
			int UpdatedCustomerBankBalance = CustomerBankBalance + Operations.GetInteger(Amount);
			
			count =UpdateBalance(connection, AccountNo, UpdatedCustomerBankBalance);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}

	@Override
	public int UpdateLogInTime(String AccountNo, String Time) {
		
		Connection connection = establishDBConnection();
		
		int count =0;
		
		try {
			PreparedStatement preparedStatment = connection.prepareStatement("update userdetails set LastLoginTime = ? where AccountNumber = ?");
			preparedStatment.setString(1, Time);
			preparedStatment.setString(2, AccountNo);
			count = preparedStatment.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}

	@Override
	public int UpdateMultipleField(String AccountNo, String FieldName, String FirstValue, String FieldName2,String SecondValue) {
		
		Connection connection = establishDBConnection();
		int count = 0;
		
		try {
			PreparedStatement preparedStatment = connection.prepareStatement(String.format("update userdetails set %s = ?,%s = ? where AccountNumber = ?",FieldName,FieldName2));
			preparedStatment.setString(1, FirstValue);
			preparedStatment.setString(2, SecondValue);
			preparedStatment.setString(3, AccountNo);
			
			count = preparedStatment.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}

	@Override
	public int UpdateSingleField(String AccountNo, String FieldName,String Value) {
		Connection connection = establishDBConnection();
		int count = 0;
		
		try {
			PreparedStatement preparedStatment = connection.prepareStatement(String.format("update userdetails set %s = ? where AccountNumber = ?",FieldName));
			preparedStatment.setString(1, Value);
			preparedStatment.setString(2, AccountNo);
			
			count = preparedStatment.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}

	

	

	
	

	

	



}
