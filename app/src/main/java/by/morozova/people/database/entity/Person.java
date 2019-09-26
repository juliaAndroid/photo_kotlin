package by.morozova.people.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "People")
public class Person {

	public Person(int id, String name, boolean sexMan, String phone, String city){
		this.id = id;
		this.name = name;
		this.sexMan = sexMan;
		this.phone = phone;
		this.city = city;
	}

	@PrimaryKey(autoGenerate = true)
	private int id;

	@ColumnInfo(name = "name")
	private String name;

	@ColumnInfo(name = "sexMan")
	private boolean sexMan;

	@ColumnInfo(name = "phone")
	private String phone;

	@ColumnInfo(name = "city")
	private String city;

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setId(int id) {

		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSexMan(boolean sexMan) {
		this.sexMan = sexMan;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getId() {

		return id;
	}

	public String getName() {
		return name;
	}

	public boolean isSexMan() {
		return sexMan;
	}

	public String getPhone() {
		return phone;
	}

	public String getCity() {
		return city;
	}
}
