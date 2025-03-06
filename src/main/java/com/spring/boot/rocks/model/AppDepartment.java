package com.spring.boot.rocks.model;

import java.io.Serializable;
import java.util.List;

import com.spring.boot.rocks.model.audit.Auditable;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "AppDepartment")
@Table(name = "app_department")
public class AppDepartment extends Auditable<String> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Long id;
	@Column(name = "departmentuuid", unique = true)
	private String departmentuuid;

	@PrePersist
	protected void onCreate() {
		setDepartmentuuid(java.util.UUID.randomUUID().toString());
	}

	@Basic(optional = false)
	@Column(name = "departmentname")
	private String departmentname;

	@Basic(optional = false)
	@Column(name = "departmentheadname")
	private String departmentheadname;

	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "userid", referencedColumnName = "id")
	private AppUser appUser;

	@Basic(optional = false)
	@Column(name = "departmentemaildistlist")
	private String departmentemaildistlist;

	@ManyToMany(mappedBy = "departments", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	private List<AppUser> users;

}
