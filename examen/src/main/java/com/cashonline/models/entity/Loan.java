package com.cashonline.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name = "loans") 
public class Loan implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idLoan;
	
	@Column(nullable = false)
	private float total;
	
	@ManyToOne
	@JoinColumn(name = "idUser", nullable = false)
	private User user;

        public Loan() {
        }

        public Loan(float total, User user) {
            this.total = total;
            this.user = user;
        }
        
	public Long getIdLoan() {
		return idLoan;
	}

	public void setIdLoan(Long idLoan) {
		this.idLoan = idLoan;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}
	
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idUser")
	@JsonIdentityReference(alwaysAsId = true) 
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idLoan == null) ? 0 : idLoan.hashCode());
		result = prime * result + Float.floatToIntBits(total);
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Loan other = (Loan) obj;
		if (idLoan == null) {
			if (other.idLoan != null)
				return false;
		} else if (!idLoan.equals(other.idLoan))
			return false;
		if (Float.floatToIntBits(total) != Float.floatToIntBits(other.total))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}


	
	
}