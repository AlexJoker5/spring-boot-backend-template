package com.main.java.common;

import java.util.ArrayList;
import java.util.List;

public class CommonUtils {
	
	public List<String> getRoles() {
		
		List<String> roleList = new ArrayList<>();
		roleList.add(CommonConstants.ROLE_USER);
		roleList.add(CommonConstants.ROLE_ADMIN);
		return roleList;
		
	}

}
