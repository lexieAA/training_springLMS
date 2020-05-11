package com.ss.training.spring.lms.dao;

import org.springframework.stereotype.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ss.training.spring.lms.entity.Borrower;

@Repository
public interface BorrowerDAO extends JpaRepository<Borrower, Long> {

	List<Borrower> findByBorrowerName(String name);
	
}

//
//	public Integer addBorrower(Borrower borrower) throws ClassNotFoundException, SQLException{
//		return saveWithPK("INSERT INTO tbl_borrower (name, address, phone) VALUES (?, ?, ?)", 
//				new Object[] {borrower.getBorrowerName(), borrower.getBorrowerAddress(), borrower.getBorrowerPhone()});
//	}
//
//	public void updateBorrower(Borrower borrower)  throws ClassNotFoundException, SQLException{
//		save("UPDATE tbl_borrower SET name = ? , address = ?, phone = ? WHERE cardNo = ?", 
//				new Object[] {borrower.getBorrowerName(), borrower.getBorrowerAddress(), borrower.getBorrowerPhone(), borrower.getCardNo()});
//	}
//
//	public void deleteBorrower(Borrower borrower)  throws ClassNotFoundException, SQLException{
//		save("DELETE FROM tbl_borrower WHERE cardNo = ?", new Object[]{borrower.getCardNo()});
//	}
//	
//	public List<Borrower> readAllBorrowers() throws ClassNotFoundException, SQLException{
//		return read("SELECT * FROM tbl_borrower", null);
//	}
//	
//	public List<Borrower> readNameBorrower(Borrower borrower) throws ClassNotFoundException, SQLException{
//		return read("SELECT * FROM tbl_borrower WHERE cardNo = ?", new Object[]{borrower.getCardNo()});
//	}
//	
//	public List<Borrower> readBorrowerById(Integer cardNo) throws ClassNotFoundException, SQLException {
//		return read("SELECT * FROM tbl_borrower WHERE cardNo=?", new Object[] { cardNo });
//	}
//	
//	public List<Borrower> readBorrowerByName(String name) throws ClassNotFoundException, SQLException {
//		return read("SELECT * FROM tbl_borrower WHERE name=?", new Object[] { name });
//	}
//
//	@Override
//	public List<Borrower> extractData(ResultSet rs) throws SQLException {
//		List<Borrower> borrowers = new ArrayList<>();
//		while(rs.next()){
//			Borrower borrower = new Borrower();
//			borrower.setCardNo(rs.getInt("cardNo"));
//			borrower.setBorrowerName(rs.getString("name"));
//			borrower.setBorrowerAddress(rs.getString("address"));
//			borrower.setBorrowerPhone(rs.getString("phone"));
//			borrowers.add(borrower);
//		}
//		return borrowers;
//	}
//
//}
