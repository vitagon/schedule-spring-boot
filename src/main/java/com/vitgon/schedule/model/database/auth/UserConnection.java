package com.vitgon.schedule.model.database.auth;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.vitgon.schedule.model.database.auth.pk.UserConnectionId;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(
	name = "userconnection",
	uniqueConstraints = @UniqueConstraint(columnNames = {"userId", "providerId", "rank"}))
@IdClass(UserConnectionId.class)
public class UserConnection implements Serializable {
	
	private static final long serialVersionUID = 250651940454903010L;

	@Id
	@Column(name = "userId", length = 255, nullable = false)
	private String userId;
	
	@Id
	@Column(name = "providerId", length = 255, nullable = false)
	private String providerId;
	
	@Id
	@Column(name = "providerUserId", length = 255, nullable = false)
	private String providerUserId;
	
	@Column(name = "rank", nullable = false)
	private int rank;
	
	@Column(name = "displayName", length = 255, nullable = true)
	private String displayName;
	
	@Column(name = "profileUrl", length = 512, nullable = true)
	private String profileUrl;
	
	@Column(name = "imageUrl", length = 512, nullable = true)
	private String imageUrl;
	
	@Column(name = "accessToken", length = 512, nullable = true)
	private String accessToken;
	
	@Column(name = "secret", length = 512, nullable = true)
	private String secret;
	
	@Column(name = "refreshToken", length = 512, nullable = true)
	private String refreshToken;
	
	@Column(name = "expireTime", nullable = true)
	private Long expireTime;
}
