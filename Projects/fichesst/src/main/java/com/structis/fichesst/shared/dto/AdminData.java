package com.structis.fichesst.shared.dto;

import java.util.ArrayList;
import java.util.List;

import com.structis.fichesst.shared.dto.Admin;

public class AdminData {
	public static List<Admin> getAdmins() {
		List<Admin> admin = new ArrayList<Admin>();
		admin.add(new Admin("BYCN\\a"));
		admin.add(new Admin("BYCN\\b"));
		admin.add(new Admin("BYCN\\c"));
		admin.add(new Admin("BYCN\\d"));
		admin.add(new Admin("BYCN\\e"));
		admin.add(new Admin("BYCN\\f"));
		admin.add(new Admin("BYCN\\g"));
		admin.add(new Admin("BYCN\\h"));
		admin.add(new Admin("BYCN\\i"));
		admin.add(new Admin("BYCN\\j"));
		admin.add(new Admin("BYCN\\k"));
		admin.add(new Admin("BYCN\\l"));
		admin.add(new Admin("BYCN\\m"));
		return admin;
	}
}
