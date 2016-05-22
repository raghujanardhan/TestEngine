package testengine.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.sql.*;

import testengine.beans.CandidateInfo;
import testengine.utility.ConnectionFactory;
import testengine.utility.JDBCConnection;
import testengine.utility.Pagination;
import testengine.utility.Suppliments;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;



public class CandidateImpl implements CandidateInterface{
	
	private static final String CLASS_DATA_QRY = " SELECT * FROM CLASS ";
	private static final String ADD_CANDIDATE_QRY = "INSERT INTO CANDIDATEREG(candidateid,candidatename,classid,sectionid,candidateaddress, "+
		" candidateemail,candidatephone,lastupddate) values (?,?,?,?,?,?,?,?)";
	private static final String DEL_CANDIDATE_QRY = "delete from candidatereg where candidateid = ?";
	private static final String GET_CANDIDATE_DETAILS_QRY = "SELECT * FROM CANDIDATEREG WHERE CANDIDATEID = ?";
	private static final String EDIT_CANDIDATE_QUERY = "UPDATE CANDIDATEREG SET CANDIDATENAME=?,CLASSID=?,SECTIONID=?,CANDIDATEADDRESS=?,CANDIDATEEMAIL=?,CANDIDATEPHONE=?,LASTUPDDATE=? WHERE CANDIDATEID=?";
	
	
	Connection connection;
    Statement stmt;
    private int noOfRecords;
    Pagination pagination;
    private int nor=0;
	
	public ArrayList<CandidateInfo> getClassData() {
		Connection con = null;
		ResultSet rs = null;
		ArrayList<CandidateInfo> classList = new ArrayList<CandidateInfo>();
		try
		{
			Class.forName(JDBCConnection.getDriver());
			 con = DriverManager.getConnection(JDBCConnection.getUrl(),JDBCConnection.getUserName(),JDBCConnection.getPassword());
			Statement stat = con.createStatement();
			 rs = stat.executeQuery(CLASS_DATA_QRY);
			
			while(rs.next()){
				
				CandidateInfo candidateInfo = new CandidateInfo();
				candidateInfo.setClassId(rs.getInt("classid"));
				candidateInfo.setClassName(rs.getString("classname"));
				classList.add(candidateInfo);
			}
			
			
			
		}
		catch(Exception e){
			
			e.printStackTrace();
			
		}
		finally{
			try
			{
			closeConnection(con,rs);
			}
			catch(SQLException sqlexp){
				sqlexp.printStackTrace();
			}
		}
		return classList;
		
	}
	
	public int addCandidate(CandidateInfo candidateInfo) {
		Connection con = null;
		GregorianCalendar cal = new GregorianCalendar();
		
		try
		{
			Class.forName(JDBCConnection.getDriver());
			con = DriverManager.getConnection(JDBCConnection.getUrl(),JDBCConnection.getUserName(),JDBCConnection.getPassword());
			PreparedStatement stat = con.prepareStatement(ADD_CANDIDATE_QRY);
			System.out.println(ADD_CANDIDATE_QRY);
			
			stat.setString(1, candidateInfo.getCandidateId());
			stat.setString(2, candidateInfo.getCandidateName());
			stat.setInt(3, candidateInfo.getClassId());
			stat.setInt(4, candidateInfo.getSectionId());
			stat.setString(5, candidateInfo.getAddress());
			stat.setString(6, candidateInfo.getEmail());
			stat.setString(7, candidateInfo.getPhone());
			stat.setLong(8, cal.getTimeInMillis());
			
			return stat.executeUpdate();
		}
		catch(Exception e){
			
			e.printStackTrace();
			
		}
		
		
		
		return 0;
		
	}
	
	private static Connection getConnection() throws SQLException, ClassNotFoundException 
    {
        Connection con = ConnectionFactory.getInstance().getConnection();
        return con;
    }
	
	public List<CandidateInfo> listCandidates(int offset, int noOfRecords) {
		
		String query = "select SQL_CALC_FOUND_ROWS * from candidatereg limit "
	                + offset + ", " + noOfRecords;
		ResultSet rs= null;
			
	       List<CandidateInfo> list = new ArrayList<CandidateInfo>();
	       CandidateInfo candidateInfo = null;
	       try {
	           connection = getConnection();
	           stmt = connection.createStatement();
	            rs = stmt.executeQuery(query);
	           while (rs.next()) {
	        	   candidateInfo = new CandidateInfo();
	               candidateInfo.setCandidateId(rs.getString("candidateid"));
	               candidateInfo.setClassId((rs.getInt("classid")));
	               candidateInfo.setCandidateName(rs.getString("candidatename"));
	               candidateInfo.setClassName(Suppliments.getClass(rs.getInt("classid")));
	               candidateInfo.setSectionId((rs.getInt("sectionid")));
	               candidateInfo.setSectionName(Suppliments.getSection(rs.getInt("sectionid")));
	               candidateInfo.setEmail(rs.getString("candidateemail"));
	               candidateInfo.setPhone(rs.getString("candidatephone"));
	               candidateInfo.setAddress(rs.getString("candidateaddress"));
	               list.add(candidateInfo);
	           }
	           rs.close();
	            
	           /*rs = stmt.executeQuery("SELECT FOUND_ROWS()");
	           if(rs.next()){
	        	   System.out.println("Entering this");
	               this.noOfRecords = rs.getInt(1);
	             System.out.println("JHJHJH"+this.noOfRecords);
	        	   
	           }*/
	       } catch (SQLException e) {
	           e.printStackTrace();
	       } catch (ClassNotFoundException e) {
	           e.printStackTrace();
	       }finally
	       {
	           try {
	               closeConnection(connection, rs);
	               } catch (SQLException e) {
	               e.printStackTrace();
	           }
	       }
	       nor = this.noOfRecords;
	       return list;
	}
	
	public List<CandidateInfo> listCandidates(int offset, int noOfRecords,String canId,String canName,String order) {
		
		String query=null;
		System.out.println("ID-->"+canId);
		System.out.println("NAME-->"+canName);
		System.out.println("ORDER-->"+order);
		
		if(canId!=null && canId.trim().length()!=0){
			query = "select SQL_CALC_FOUND_ROWS * from candidatereg where candidateid='"+canId +"' limit "
	                + offset + ", " + noOfRecords;
		}
		else if(canName != null && canName.trim().length()!=0){
			query = "select SQL_CALC_FOUND_ROWS * from candidatereg where candidatename='"+canName +"' limit "
	                + offset + ", " + noOfRecords;
			
		}
		else if(order != null){
			
			query = "select SQL_CALC_FOUND_ROWS * from candidatereg order by candidatename "+ order + " limit "
	                + offset + ", " + noOfRecords;
			
			
		}
		
		System.out.println(query);
		ResultSet rs= null;
			
	       List<CandidateInfo> list = new ArrayList<CandidateInfo>();
	       CandidateInfo candidateInfo = null;
	       try {
	           connection = getConnection();
	           stmt = connection.createStatement();
	            rs = stmt.executeQuery(query);
	           while (rs.next()) {
	        	   candidateInfo = new CandidateInfo();
	               candidateInfo.setCandidateId(rs.getString("candidateid"));
	               candidateInfo.setCandidateName(rs.getString("candidatename"));
	               candidateInfo.setClassId(rs.getInt("classid"));
	               candidateInfo.setSectionId(rs.getInt("sectionid"));
	               candidateInfo.setClassName(Suppliments.getClass(rs.getInt("classid")));
	               candidateInfo.setSectionName(Suppliments.getSection(rs.getInt("sectionid")));
	               candidateInfo.setEmail(rs.getString("candidateemail"));
	               candidateInfo.setPhone(rs.getString("candidatephone"));
	               candidateInfo.setAddress(rs.getString("candidateaddress"));
	               list.add(candidateInfo);
	           }
	           rs.close();
	            
	           /*rs = stmt.executeQuery("SELECT FOUND_ROWS()");
	           if(rs.next()){
	        	   System.out.println("Entering this");
	               this.noOfRecords = rs.getInt(1);
	             System.out.println("JHJHJH"+this.noOfRecords);
	        	   
	           }*/
	       } catch (SQLException e) {
	           e.printStackTrace();
	       } catch (ClassNotFoundException e) {
	           e.printStackTrace();
	       }finally
	       {
	           try {
	               closeConnection(connection, rs);
	               } catch (SQLException e) {
	               e.printStackTrace();
	           }
	       }
	       nor = this.noOfRecords;
	       return list;
	
		
		
	}
	
	
	public int getNumOfRecords() {
		ResultSet rs=null;
		try {
	           connection = getConnection();
	           stmt = connection.createStatement();
	            rs = stmt.executeQuery("SELECT count(*) as rowcount FROM candidatereg ");
	            if(rs.next()){
		        	   System.out.println("Entering this");
		               this.noOfRecords = rs.getInt("rowcount");
		             System.out.println("JHJHJH"+this.noOfRecords);
		        	   
		           }
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		finally
		{
			try {
	               closeConnection(connection, rs);
	               } catch (SQLException e) {
	               e.printStackTrace();
	           }
		}
		
		return this.noOfRecords;
		
		
	}
	public int deleteCandidate(String candidateId) {
		
		 
		int delete = 0;
		PreparedStatement stmt=null;
		try {
			
	           connection = getConnection();
	           stmt = connection.prepareStatement(DEL_CANDIDATE_QRY);
	           stmt.setString(1, candidateId);
	           delete = stmt.executeUpdate();
	           
		}
		catch(Exception e) {
			
			e.printStackTrace();
			
		}
		finally{
			try
			{
				closeConnection1(connection, stmt);
			}
			catch(SQLException s){
				
				
			}
		}
		return delete;
		
	}
	public ArrayList<CandidateInfo> getCandidate(String candidateId) {
		ArrayList<CandidateInfo> candidateList = new ArrayList<CandidateInfo>();
		ResultSet rs= null;
		
		System.out.println("Here....");
		PreparedStatement statement=null;
	       
	       CandidateInfo candidateInfo = null;
	       try {
	           connection = getConnection();
	           statement = connection.prepareStatement(GET_CANDIDATE_DETAILS_QRY);
	           statement.setString(1, candidateId);
	            rs = statement.executeQuery();
	           while (rs.next()) {
	        	   candidateInfo = new CandidateInfo();
	               candidateInfo.setCandidateId(rs.getString("candidateid"));
	               candidateInfo.setCandidateName(rs.getString("candidatename"));
	               candidateInfo.setClassId(rs.getInt("classid"));
	               candidateInfo.setSectionId(rs.getInt("sectionid"));
	               candidateInfo.setClassName(Suppliments.getClass(rs.getInt("classid")));
	               candidateInfo.setSectionName(Suppliments.getSection(rs.getInt("sectionid")));
	               candidateInfo.setEmail(rs.getString("candidateemail"));
	               candidateInfo.setPhone(rs.getString("candidatephone"));
	               candidateInfo.setAddress(rs.getString("candidateaddress"));
	               candidateList.add(candidateInfo);
	           }
	       }
	       catch(Exception e){
	    	   e.printStackTrace();
	       }
		
	       System.out.println(candidateList.get(0));
		return candidateList;
	}
	
	public boolean editCandidate(CandidateInfo candidateInfo) {
		
		System.out.println("Impl"+candidateInfo.getCandidateName());
		

		int update = 0;
		boolean updated = false;
		PreparedStatement stmt=null;
		GregorianCalendar cal = new GregorianCalendar();
		try {
			
	           connection = getConnection();
	           stmt = connection.prepareStatement(EDIT_CANDIDATE_QUERY);
	           System.out.println(EDIT_CANDIDATE_QUERY);
	           stmt.setString(1, candidateInfo.getCandidateName());
	           stmt.setInt(2, candidateInfo.getClassId());
	           stmt.setInt(3, candidateInfo.getSectionId());
	           stmt.setString(4, candidateInfo.getAddress());
	           stmt.setString(5, candidateInfo.getEmail());
	           stmt.setString(6, candidateInfo.getPhone());
	           stmt.setLong(7, cal.getTimeInMillis());
	           
	           stmt.setString(8, candidateInfo.getCandidateId());
	           
	           
	           update = stmt.executeUpdate();
	           if(update>0){
	        	   updated = true;
	           }
	           
		}
		catch(Exception e) {
			
			e.printStackTrace();
			
		}
		finally{
			try
			{
				closeConnection1(connection, stmt);
			}
			catch(SQLException s){
				System.out.println("Exception is "+s);
				
			}
		}
		return updated;
		
	
		
	}
	public int insertFileDataToDB(String pathandname) {
		GregorianCalendar cal = new GregorianCalendar();
		int counter = 0;
		HSSFSheet sheet = null;
		try
		{
					
		           connection = getConnection();
		           connection.setAutoCommit(false);
		           PreparedStatement pstm = null ;
		            FileInputStream input = new FileInputStream(pathandname);
		            POIFSFileSystem fs = new POIFSFileSystem( input );
		            HSSFWorkbook wb = new HSSFWorkbook(fs);
		            sheet = wb.getSheetAt(0);
		            Row row;
		            for(int i=1; i<=sheet.getLastRowNum(); i++){
		                row = sheet.getRow(i);
		                String name = row.getCell(0).getStringCellValue();
		                int classid = (int) row.getCell(1).getNumericCellValue();
		                int sectionid = (int) row.getCell(2).getNumericCellValue();
		                String address = row.getCell(3).getStringCellValue();
		                String email = row.getCell(4).getStringCellValue();
		                long phone = (long)row.getCell(5).getNumericCellValue();
		                String candidateId = Suppliments.generateIdSequence("candidate");
		                
		                pstm =connection.prepareStatement(ADD_CANDIDATE_QRY);
		                pstm.setString(1, candidateId);
		                pstm.setString(2, name);
		                pstm.setInt(3, classid);
		                pstm.setInt(4, sectionid);
		                pstm.setString(5, address);
		                pstm.setString(6, email);
		                pstm.setString(7, Long.toString(phone));
		                pstm.setLong(8, cal.getTimeInMillis());
		                
		                
		               pstm.executeUpdate();
		                counter++;
		}
		            connection.commit();
		}
		catch(Exception e){
			
			System.out.println("Exceptio i s    -->"+e);
			
		}
		if(counter == sheet.getLastRowNum()){
			
			return 1;
			
		}
		else{
			return -1;
		}
		
		
	}
	
	
	private void closeConnection(Connection con, ResultSet rs) throws SQLException{
		// TODO Auto-generated method stub
		con.close();
		rs.close();
	}
	private void closeConnection1(Connection con, PreparedStatement stat) throws SQLException{
		// TODO Auto-generated method stub
		con.close();
		stat.close();
	}
	
	
	

}
