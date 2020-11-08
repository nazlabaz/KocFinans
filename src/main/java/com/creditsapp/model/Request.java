package com.creditsapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "requests")
public class Request {

	final static int CREDIT_LIMIT_MULTIPLIER = 4;
	
	private long id;
	private long identification;
	private String firstName;
	private String lastName;
	private String phoneNo;
	private int income;
	private int limit;
	private int creditScore;	
	private String response;
	
	private Client client;
	
	
	public Request() {
		
	}
	
	public Request(long identification, String firstName, String lastName, String phoneno, int income, int limit, int creditScore) {
		this.identification = identification;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNo = phoneno;
		this.income = income;
		this.limit = limit;
		this.creditScore = creditScore;
		client = client.getById(id);
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "identification", nullable = false)
	public long getIdentification() {
		return identification;
	}
	public void setIdentification(long identification) {
		this.identification = identification;
	}
	
	@Column(name = "first_name", nullable = false)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name = "last_name", nullable = false)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name = "phone_no", nullable = false)
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	@Column(name = "income", nullable = false)
	public int getIncome() {
		return income;
	}
	public void setIncome(int income) {
		this.income = income;
	}
	
	@Column(name = "client_limit")
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	@Column(name = "credit_score")
	public int getCreditScore() {
		return creditScore;
	}
	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
	}
	
	@Column(name = "response")
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	
	public void calcCredit() {
		
		response = "RED";
		
		if( creditScore < 500) {
			response = "RED";
		}
		else if( creditScore >= 500 && creditScore < 1000 && income < 5000){//check details
			limit = 10000;
			response =  "ONAY";
		}		
		else if(creditScore >= 1000 ){//check details
			limit = income * CREDIT_LIMIT_MULTIPLIER;
			response = "ONAY";
		}
	}

}
