package org.java.demo.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.java.demo.model.base.Account;

@Entity
@Table(name = "Users")
@PrimaryKeyJoinColumn(name = "user_id")
public class User extends Account {
}
