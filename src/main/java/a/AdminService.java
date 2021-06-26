package a;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
	@Autowired
	private AdminRepository repo;
	
	public List<Admin> listAll() {
		
		return repo.findAll();
	}
	
	public void save(Admin product) {
		repo.save(product);
	}     
	
	public Admin get(long id) {
		return repo.findById(id).get();
	}     
	
	public void delete(long id) {
		repo.deleteById(id);
	}
	
}

