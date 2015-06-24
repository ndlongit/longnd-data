package com.sedex.appexch.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.sedex.appexch.model.base.Account;

@Entity
@Table(name = "Users")
@PrimaryKeyJoinColumn(name = "user_id")
public class User extends Account {
}
