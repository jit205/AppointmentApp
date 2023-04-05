package com.example.demo.Services.Repositery;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.example.demo.Services.Model.Services;

public interface ServicesRepo extends MongoRepository<Services, String> {

	// public List<Services> findByCategory(String category);

	@Query(value = "{'username':?0}", fields = "{'username':1,'category':1,'serviceName':1,'serviceTime':1,'breakTime':1,'appoinmentTime':1,'location':1,'description':1,'createdTime':1,'createdDate':1}")
	public List<Services> findByUsername(String username);

	public Services findBySID(String sID);

	@Query(value = "{'category':?0,'location.city':?1,'location.pincode':?2}", fields = "{'username':1,'category':1,'serviceName':1,'serviceTime':1,'breakTime':1,'appoinmentTime':1,'location':1,'description':1,'createdTime':1,'createdDate':1}")
	public List<Services> findByCategoryAndCityAndPincode(String category, String city, String pincode);

	// @Query(value="{'_id':?0}",fields="{'status':1")

	// public Services findByAppoinment(String _id);

	//

	// public findByAID(String AID);
	@Query(value = "{'appoinmentList.AID':?0}", fields = "{'appoinmentList':1}")
	public List<Services> findByAppoinmentList(String AID);

	@Query(value = "{'appoinmentList._id':?0}", fields = "{'appoinmentList.$':1}")
	public Services hello(String AID);

	@Query(value = "{ '_id':?0,'appoinmentList.dateTime.date ' : ?1 }")
	public Services getallbookappionmet(String SID, String date);

	@Query(value = "{'_id':?0}")
	public Services all(String SID);
	// appoinmentList': {$elemMatch: {$dateTime.date: ?1} }
	// @Query(value="{'appoinmentList.dateTime.date':?0}",fields="{'appoinmentList.$':1}")
}