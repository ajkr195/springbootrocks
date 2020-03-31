package com.spring.boot.rocks.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "persistent_logins")
@NamedQueries({ @NamedQuery(name = "PersistentLogins.findAll", query = "SELECT p FROM PersistentLogins p"),
		@NamedQuery(name = "PersistentLogins.findByUsername", query = "SELECT p FROM PersistentLogins p WHERE p.username = :username"),
		@NamedQuery(name = "PersistentLogins.findBySeries", query = "SELECT p FROM PersistentLogins p WHERE p.series = :series"),
		@NamedQuery(name = "PersistentLogins.findByToken", query = "SELECT p FROM PersistentLogins p WHERE p.token = :token"),
		@NamedQuery(name = "PersistentLogins.findByLastUsed", query = "SELECT p FROM PersistentLogins p WHERE p.lastUsed = :lastUsed") })
public class PersistentLogins implements Serializable {

	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column(name = "username")
	private String username;
	@Id
	@Basic(optional = false)
	@Column(name = "series")
	private String series;
	@Basic(optional = false)
	@Column(name = "token")
	private String token;
	@Basic(optional = false)
	@Column(name = "last_used")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUsed;

	public PersistentLogins() {
	}

	public PersistentLogins(String series) {
		this.series = series;
	}

	public PersistentLogins(String series, String username, String token, Date lastUsed) {
		this.series = series;
		this.username = username;
		this.token = token;
		this.lastUsed = lastUsed;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getLastUsed() {
		return lastUsed;
	}

	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (series != null ? series.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof PersistentLogins)) {
			return false;
		}
		PersistentLogins other = (PersistentLogins) object;
		if ((this.series == null && other.series != null)
				|| (this.series != null && !this.series.equals(other.series))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.spring.boot.rocks.model.PersistentLogins[ series=" + series + " ]";
	}

}
