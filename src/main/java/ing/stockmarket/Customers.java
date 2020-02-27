package ing.stockmarket;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Customer")
public class Customers {

	@Id
	@Column(name = "CUSTOMER_ID")
	private int ID;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "AGE", nullable = false)
	private int age;

	@Column(name = "ADDRESS", nullable = false)
	private String address;

	@Column(name = "SEX", nullable = false)
	private char sex;

	@Column(name = "PHONE", nullable = false)
	private int phone;

	public int getID() {

		return ID;

	}

	public void setID(int iD) {

		ID = iD;

	}

	public String getName() {

		return name;

	}

	public void setName(String name) {

		this.name = name;

	}

	public int getAge() {

		return age;
	}

	public void setAge(int age) {

		this.age = age;

	}

	public String getAddress() {

		return address;

	}

	public void setAddress(String address) {

		this.address = address;

	}

	public char getSex() {

		return sex;

	}

	public void setSex(char sex) {

		this.sex = sex;

	}

	public int getPhone() {

		return phone;

	}

	public void setPhone(int phone) {

		this.phone = phone;

	}

}
