package com.spring.boot.rocks.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "app_role")
@NamedQueries({ @NamedQuery(name = "AppRole.findAll", query = "SELECT a FROM AppRole a"),
		@NamedQuery(name = "AppRole.findById", query = "SELECT a FROM AppRole a WHERE a.id = :id"),
		@NamedQuery(name = "AppRole.findByName", query = "SELECT a FROM AppRole a WHERE a.name = :name") })
public class AppRole implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "name")
	private String name;

	public AppRole() {
	}

	public AppRole(Integer id) {
		this.id = id;
	}

	public AppRole(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof AppRole)) {
			return false;
		}
		AppRole other = (AppRole) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "AppRole [id=" + id + ", name=" + name + "]";
	}

	
}
