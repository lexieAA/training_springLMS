package com.ss.training.spring.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.training.spring.lms.entity.BookLoan;
import com.ss.training.spring.lms.entity.LibraryBranch;

public class LibraryBranchDAO extends BaseDAO<LibraryBranch>{

	public LibraryBranchDAO(Connection conn) {
		super(conn);
	}

	public Integer addLibraryBranch(LibraryBranch branch) throws ClassNotFoundException, SQLException{
		return saveWithPK("INSERT INTO tbl_library_branch (branchName, branchAddress) VALUES (?, ?)", 
				new Object[] {branch.getBranchName(), branch.getBranchAddress()});
	}

	public void updateLibraryBranch(LibraryBranch branch)  throws ClassNotFoundException, SQLException{
		save("UPDATE tbl_library_branch SET branchName = ? , branchAddress = ? WHERE branchId = ?", 
				new Object[] {branch.getBranchName(), branch.getBranchAddress(), branch.getBranchId()});
	}

	public void deleteLibraryBranch(LibraryBranch branch)  throws ClassNotFoundException, SQLException{
		save("DELETE FROM tbl_library_branch WHERE branchId = ?", new Object[]{branch.getBranchId()});
	}
	
	public List<LibraryBranch> readAllLibraryBranches() throws ClassNotFoundException, SQLException{
		return read("SELECT * FROM tbl_library_branch", null);
	}
	
	public List<LibraryBranch> readLibraryBranchById(Integer branchId) throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM tbl_library_branch WHERE branchId=?", new Object[] { branchId });

	}
	
	public List<LibraryBranch> readLibraryBranchByName(String name) throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM tbl_library_branch WHERE branchName=?", new Object[] { name });

	}
	
//	public List<LibraryBranch> readAllBorrowerByBranches(LibraryBranch branch) throws ClassNotFoundException, SQLException{
//		return read("SELECT * FROM tbl_book_loans WHERE branchId = ?", new Object[]{branch.getBranchId()});
//	}
	public List<LibraryBranch> readBranchesByCardNo(BookLoan loan) throws ClassNotFoundException, SQLException{
		return read("SELECT * FROM tbl_library_branch where bookId (SELECT branchId FROM tbl_book_loans WHERE cardNo = ?)", new Object[]{loan.getCardNo()});
	}
	
	public List<LibraryBranch> readBranchesByCardNo(Integer cardNo) throws ClassNotFoundException, SQLException{
		return read("SELECT DISTINCT * FROM tbl_library_branch WHERE branchId IN (SELECT branchId FROM tbl_book_loans WHERE cardNo = ?)", new Object[]{cardNo});
	}

	@Override
	public List<LibraryBranch> extractData(ResultSet rs) throws SQLException {
		List<LibraryBranch> branchs = new ArrayList<>();
		while(rs.next()){
			LibraryBranch branch = new LibraryBranch();
			branch.setBranchId(rs.getInt("branchId"));
			branch.setBranchName(rs.getString("branchName"));
			branch.setBranchAddress(rs.getString("branchAddress"));
			branchs.add(branch);
		}
		return branchs;
	}

}
