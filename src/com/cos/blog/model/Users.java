package com.cos.blog.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {
	private int id;
	private String username;
	private String password;
	private String email;
	private String address;
	private String userProfile;
	private String userRole;	// userRole은 사실은 RoleType 타입으로 설정되어야 할 필드임. 아래 설명 참조
	private Timestamp createDate;
}

/*
userRole은 사실은 RoleType 타입으로 설정되어야 할 필드임.
대신 이렇게 할 경우 테이블에서 넣고 뺄 때 많이 복잡하다고 해서 그냥 String 타입으로 한 것
https://github.com/codingspecialist/java-swing-address/blob/master/src/address/model/Member.java
에서 Member 클래스 필드가 GroupType으로 설정된 게 원칙에 맞는 것.

*/