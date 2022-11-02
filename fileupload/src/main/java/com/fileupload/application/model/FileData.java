package com.fileupload.application.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="EmployeeDetails")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter

public class FileData {
	@Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Long id;
	@Column(name = "First_Name")
	private String firstName;

	@Column(name = "Last_Name")
	private String lastName;
	   

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	@Column(name = "DateOfBirth")
	  private LocalDate dateofbirth;

	@Column(name = "Email")
	private String email;
	   
	@Column(name="AddressProof")  
	    private String filepath;

	@Column(name="Filename")  
	 private String name;

	public String getFilepath() {
	return filepath;
	}
	public void setFilepath(String filepath) {
	this.filepath = filepath;
	}

	public FileData(String firstName, String lastName, LocalDate dateofbirth, String email) {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.dateofbirth = dateofbirth;
	this.email = email;

	}

}
