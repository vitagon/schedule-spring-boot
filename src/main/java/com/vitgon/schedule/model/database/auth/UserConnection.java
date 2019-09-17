package com.vitgon.schedule.model.database.auth;

import com.vitgon.schedule.model.database.auth.pk.UserConnectionId;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(
	name = "userconnection",
	uniqueConstraints = @UniqueConstraint(columnNames = {"userId", "providerId", "rank"}))
@IdClass(UserConnectionId.class)
public class UserConnection implements Serializable {
	
	private static final long serialVersionUID = 250651940454903010L;
	
	public UserConnection() {
		super();
	}

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getProviderUserId() {
		return providerUserId;
	}

	public void setProviderUserId(String providerUserId) {
		this.providerUserId = providerUserId;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}
}
