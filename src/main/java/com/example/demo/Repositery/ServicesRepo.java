package com.example.demo.Repositery;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.Model.service.Services;

public interface ServicesRepo extends MongoRepository<Services, String> {

	// public List<Services> findByCategory(String category);

	@Query(value = "{'username':?0}", fields = "{'sid':1,'username':1,'category':1,'serviceName':1,'serviceTime':1,'breakTime':1,'appoinmentTime':1,'location':1,'description':1,'createdAt':1,'updatedAt':1,'holidays':1}")
	public List<Services> findByUsername(String username);

	@Query(value = "{'sid':?0}", fields = "{'sid':1,'username':1,'category':1,'serviceName':1,'serviceTime':1,'breakTime':1,'appoinmentTime':1,'location':1,'description':1,'createdAt':1,'updatedAt':1,'holidays':1}")
	public Services getServiceData(String sid);

	@Query(value = "{'category':?0,'location.city':?1,'location.pincode':?2}", fields = "{'sid':1,'username':1,'category':1,'serviceName':1,'serviceTime':1,'breakTime':1,'appoinmentTime':1,'location':1,'description':1,'createdAt':1,'updatedAt':1,'holidays':1}")
	public List<Services> findByCategoryAndCityAndPincode(String category, String city, String pincode);

	@Query(value = "{'category':?0,'location.city':?1}", fields = "{'sid':1,'username':1,'category':1,'serviceName':1,'serviceTime':1,'breakTime':1,'appoinmentTime':1,'location':1,'description':1,'createdAt':1,'updatedAt':1,'holidays':1}")
	public List<Services> findByCategoryAndCity(String category, String city);

	@Query(value = "{'category':?0,'location.pincode':?1}", fields = "{'sid':1,'username':1,'category':1,'serviceName':1,'serviceTime':1,'breakTime':1,'appoinmentTime':1,'location':1,'description':1,'createdAt':1,'updatedAt':1,'holidays':1}")
	public List<Services> findByCategoryAndPincode(String category, String pincode);

	// @Query(value="{'_id':?0}",fields="{'status':1")

	// public Services findByAppoinment(String _id);

	//

	// public findByAID(String aid);
	@Query(value = "{'appointmentList.aid':?0}", fields = "{'appointmentList':1}")
	public List<Services> findByAppoinmentList(String aid);

	@Query(value = "{'appointmentList._id':?0}", fields = "{'appointmentList.$':1}")
	public Services hello(String aid);

	@Query(value = "{ '_id':?0,'appointmentList.dateTime.date ' : ?1 }")
	public Services getallbookappionmet(String sid, String date);

	@Query(value = "{'_id':?0}")
	public Services all(String sid);

	// appointmentList': {$elemMatch: {$dateTime.date: ?1} }
	// @Query(value="{'appointmentList.dateTime.date':?0}",fields="{'appointmentList.$':1}")
	@Query(value = "{'sid':?0}", fields = "{'serviceTime':1,'breakTime':1,'appoinmentTime':1,'holidays':1,'appointmentList':1}")
	public Services getAppointmentList(String sid);
}