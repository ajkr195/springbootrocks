package com.spring.boot.rocks.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.springframework.data.annotation.Transient;
//import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "app_user")
@NamedQueries({ @NamedQuery(name = "AppUser.findAll", query = "SELECT a FROM AppUser a"),
		@NamedQuery(name = "AppUser.findById", query = "SELECT a FROM AppUser a WHERE a.id = :id"),
		@NamedQuery(name = "AppUser.findByUsername", query = "SELECT a FROM AppUser a WHERE a.username = :username"),
		@NamedQuery(name = "AppUser.findByPassword", query = "SELECT a FROM AppUser a WHERE a.password = :password"),
		@NamedQuery(name = "AppUser.findByUseremail", query = "SELECT a FROM AppUser a WHERE a.useremail = :useremail"),
		@NamedQuery(name = "AppUser.findByUserfirstname", query = "SELECT a FROM AppUser a WHERE a.userfirstname = :userfirstname"),
		@NamedQuery(name = "AppUser.findByUserlastname", query = "SELECT a FROM AppUser a WHERE a.userlastname = :userlastname"),
		@NamedQuery(name = "AppUser.findByUseraddress", query = "SELECT a FROM AppUser a WHERE a.useraddress = :useraddress"),
		@NamedQuery(name = "AppUser.userdatecreated", query = "SELECT a FROM AppUser a WHERE a.userdatecreated = :userdatecreated"),
		@NamedQuery(name = "AppUser.usercreatedby", query = "SELECT a FROM AppUser a WHERE a.usercreatedby = :usercreatedby"),
		@NamedQuery(name = "AppUser.userdatemodified", query = "SELECT a FROM AppUser a WHERE a.userdatemodified = :userdatemodified"),
		@NamedQuery(name = "AppUser.usermodifiedby", query = "SELECT a FROM AppUser a WHERE a.usermodifiedby = :usermodifiedby") })
@XmlRootElement(name = "user")
public class AppUser implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Long id;
	@Basic(optional = false)
	@Column(name = "username")
	private String username;
	@Basic(optional = false)
	@Column(name = "password")
	private String password;
	@Transient
	private String passwordConfirm;
	@Basic(optional = false)
	@Column(name = "useremail")
	private String useremail;
	@Basic(optional = false)
	@Column(name = "userfirstname")
	private String userfirstname;
	@Basic(optional = false)
	@Column(name = "userlastname")
	private String userlastname;
	@Basic(optional = false)
	@Column(name = "useraddress")
	private String useraddress;

	@Column(name = "userdatecreated")
	private Date userdatecreated;

	@Column(name = "usercreatedby")
	private String usercreatedby;

	@Column(name = "userdatemodified")
	private Date userdatemodified;

	@Column(name = "usermodifiedby")
	private String usermodifiedby;

	public AppUser() {
	}

	public AppUser(Long id) {
		this.id = id;
	}

	public AppUser(Long id, String username, String password, String passwordConfirm, String useremail,
			String userfirstname, String userlastname, String useraddress, Date userdatecreated, String usercreatedby,
			Date userdatemodified, String usermodifiedby) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
		this.useremail = useremail;
		this.userfirstname = userfirstname;
		this.userlastname = userlastname;
		this.useraddress = useraddress;
		this.userdatecreated = userdatecreated;
		this.usercreatedby = usercreatedby;
		this.userdatemodified = userdatemodified;
		this.usermodifiedby = usermodifiedby;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "app_user_role", joinColumns = @JoinColumn(name = "userid"), inverseJoinColumns = @JoinColumn(name = "roleid"))

	private List<AppRole> roles;

	public List<AppRole> getRoles() {
		return roles;
	}

	public void setRoles(List<AppRole> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	@XmlElement
	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	@XmlElement
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getUseremail() {
		return useremail;
	}

	@XmlElement
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getUserfirstname() {
		return userfirstname;
	}

	@XmlElement
	public void setUserfirstname(String userfirstname) {
		this.userfirstname = userfirstname;
	}

	public String getUserlastname() {
		return userlastname;
	}

	@XmlElement
	public void setUserlastname(String userlastname) {
		this.userlastname = userlastname;
	}

	public String getUseraddress() {
		return useraddress;
	}

	@XmlElement
	public void setUseraddress(String useraddress) {
		this.useraddress = useraddress;
	}

	public Date getUserdatecreated() {
		return userdatecreated;
	}

	public void setUserdatecreated(Date userdatecreated) {
		this.userdatecreated = userdatecreated;
	}

	public String getUsercreatedby() {
		return usercreatedby;
	}

	public void setUsercreatedby(String usercreatedby) {
		this.usercreatedby = usercreatedby;
	}

	public Date getUserdatemodified() {
		return userdatemodified;
	}

	public void setUserdatemodified(Date userdatemodified) {
		this.userdatemodified = userdatemodified;
	}

	public String getUsermodifiedby() {
		return usermodifiedby;
	}

	public void setUsermodifiedby(String usermodifiedby) {
		this.usermodifiedby = usermodifiedby;
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
		if (!(object instanceof AppUser)) {
			return false;
		}
		AppUser other = (AppUser) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.spring.boot.rocks.model.AppUser[ id=" + id + " ]";
	}

}
