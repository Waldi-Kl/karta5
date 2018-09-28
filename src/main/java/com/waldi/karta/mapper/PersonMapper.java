package com.waldi.karta.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.waldi.model.Person;
import com.waldi.model.UserInfo;

public class PersonMapper implements RowMapper<Person> {
	
	public static final String BASE_SQL = 

	"Select p.id, p.pesel, p.surname, p.name_1, p.name_2, p.birth_date, p.city from person p ";
	
	private int personId ;
	private String pesel;
	private String surname;
	private String name_1;
	private String name_2;
	private Date date;
	private String city;


	@Override
	public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		this.personId = rs.getInt("id");
		this.pesel = rs.getString("pesel");
		this.surname = rs.getString("surname");
		this.name_1 = rs.getString("name_1");
		this.name_2 = rs.getString("name_2");
		this.date = rs.getDate("birth_date");
		this.city = rs.getString("city");

		this.peselMaker();

        Person person = new Person();
        person.setPersonId(this.personId);
        person.setSurname(this.surname);
        person.setPesel(this.pesel);
        person.setName_1(this.name_1);
        person.setName_2(this.name_2);
        person.setDate(this.date);
        person.setCity(this.city);
        
		return person;
	}
	
	private void peselMaker() {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(this.date);
		String day = "";
		String manth = "";
		String year = "";
		
		if (cal.get(Calendar.DAY_OF_MONTH) < 10) day = "0" + Integer.toString(cal.get(Calendar.DAY_OF_MONTH)); else day = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
		if ((cal.get(Calendar.MONTH)+1) < 10) manth = "0" + Integer.toString(cal.get(Calendar.MONTH)+1);else day = Integer.toString(cal.get(Calendar.MONTH)+1);
		 year = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
		
		this.pesel = year.substring(2, 2) + manth + day + pesel;
		System.out.println("Pesel = " + pesel);
		System.out.println("ROk = " + Integer.toString(cal.get(Calendar.YEAR)));
		System.out.println("MIESI = " + Integer.toString(cal.get(Calendar.MONTH)));
		System.out.println("Dzie� = " + year);
		
	}

}