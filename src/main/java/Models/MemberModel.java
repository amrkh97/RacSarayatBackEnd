package Models;

public class MemberModel {
	
	private String firstName;
	private String lastName;
	private Integer age;
	private String email;
	private String password;
	private String rotaryID;
	private Integer yearsInClub;
	private String position;
	private Integer privilege;
	
	public Integer getYearsInClub() {
		return yearsInClub;
	}
	public void setYearsInClub(Integer yearsInClub) {
		this.yearsInClub = yearsInClub;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRotaryID() {
		return rotaryID;
	}
	public void setRotaryID(String rotaryID) {
		this.rotaryID = rotaryID;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Integer getPrivilege() {
		return privilege;
	}
	public void setPrivilege(Integer privilege) {
		this.privilege = privilege;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
