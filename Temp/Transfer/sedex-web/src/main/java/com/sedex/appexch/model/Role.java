package com.sedex.appexch.model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.sedex.appexch.model.base.Account;
import com.sedex.appexch.model.base.SimpleEntity;

@Entity
@Table(name = "Roles")
@AttributeOverrides({
		@AttributeOverride(name = SimpleEntity.PROP_NAME, column = @Column(name = "role_name", unique = true, nullable = false)),
		@AttributeOverride(name = SimpleEntity.PROP_CODE, column = @Column(name = "role_value", unique = true, nullable = false)) })
public class Role extends SimpleEntity {

	private List<Account> accounts;

	public Role() {
		this(null);
	}

	public Role(Long id) {
		super.setId(id);
	}

	@ManyToMany(targetEntity = Account.class, mappedBy = Account.PROP_ROLES, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE })
	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
}
