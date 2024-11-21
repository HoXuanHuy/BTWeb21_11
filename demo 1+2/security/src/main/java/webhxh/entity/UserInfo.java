package webhxh.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // dung de khai bao voi Spring Boot rang day la 1 entity bieu dien table trong db
@Data // annotation nay se tu dong khai bao getter va setter cho class
@AllArgsConstructor // dung de khai bao constructor voi tat ca cac properties
@NoArgsConstructor // dung de khai bao constructor rong khong co param
public class UserInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String password;
	private String roles;

}
