package com.ss.training.spring.lms.dao;

import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ss.training.spring.lms.entity.Publisher;

@Repository
public interface PublisherDAO extends JpaRepository<Publisher, Long> {

	List<Publisher> findByPublisherName(String publisherName);
	
}
//
//	public Integer addPublisher(Publisher publisher) throws ClassNotFoundException, SQLException{
//		return saveWithPK("INSERT INTO tbl_publisher (publisherName, publisherAddress, publisherPhone) VALUES (?, ?, ?)", 
//				new Object[] {publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone()});
//	}
//
//	public void updatePublisher(Publisher publisher)  throws ClassNotFoundException, SQLException{
//		save("UPDATE tbl_publisher SET publisherName = ? , publisherAddress = ?, publisherPhone = ? WHERE publisherId = ?", 
//				new Object[] {publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone(), publisher.getPublisherId()});
//	}
//
//	public void deletePublisher(Publisher publisher)  throws ClassNotFoundException, SQLException{
//		save("DELETE FROM tbl_publisher WHERE publisherId = ?", new Object[]{publisher.getPublisherId()});
//	}
//	
//	public List<Publisher> readAllPublishers() throws ClassNotFoundException, SQLException{
//		return read("SELECT * FROM tbl_publisher", null);
//	}
//	
//	public List<Publisher> readPublisherById(Integer publisherId) throws ClassNotFoundException, SQLException {
//		return read("SELECT * FROM tbl_publisher WHERE publisherId=?", new Object[] { publisherId });
//	}
//	
//	public List<Publisher> readPublisherByName(String name) throws ClassNotFoundException, SQLException {
//		return read("SELECT * FROM tbl_publisher WHERE publisherName=?", new Object[] { name });
//	}
//
//	@Override
//	public List<Publisher> extractData(ResultSet rs) throws SQLException {
//		List<Publisher> publishers = new ArrayList<>();
//		while(rs.next()){
//			Publisher publisher = new Publisher();
//			publisher.setPublisherId(rs.getInt("publisherId"));
//			publisher.setPublisherName(rs.getString("publisherName"));
//			publisher.setPublisherAddress(rs.getString("publisherAddress"));
//			publisher.setPublisherPhone(rs.getString("publisherPhone"));
//			publishers.add(publisher);
//		}
//		return publishers;
//	}
//
//}
