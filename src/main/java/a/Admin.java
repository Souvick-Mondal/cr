package a;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Admin {
	 private Long id;
	    private String fullName;
	    private String password;
		
	    public Admin() {
			// TODO Auto-generated constructor stub
		}

		
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		public Long getId() {
			return id;
		}



		public void setId(Long id) {
			this.id = id;
		}



		public String getFullName() {
			return fullName;
		}

		public void setFullName(String fullName) {
			this.fullName = fullName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	    
	    
}
