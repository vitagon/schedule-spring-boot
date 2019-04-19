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
	name = "user_connections",
	uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "provider_id", "rank"}))
@IdClass(UserConnectionId.class)
public class UserConnection implements Serializable {
	
	private static final long serialVersionUID = 250651940454903010L;

	@Id
	@Column(name = "user_id", length = 255, nullable = false)
	private String userId;
	
	@Id
	@Column(name = "provider_id", length = 255, nullable = false)
	private String providerId;
	
	@Id
	@Column(name = "provider_user_id", length = 255, nullable = false)
	private String providerUserId;
	
	@Column(name = "rank", nullable = false)
	private int rank;
	
	@Column(name = "display_name", length = 255, nullable = true)
	private String displayName;
	
	@Column(name = "profile_url", length = 512, nullable = true)
	private String profileUrl;
	
	@Column(name = "image_url", length = 512, nullable = true)
	private String imageUrl;
	
	@Column(name = "access_token", length = 512, nullable = true)
	private String accessToken;
	
	@Column(name = "secret", length = 512, nullable = true)
	private String secret;
	
	@Column(name = "refresh_token", length = 512, nullable = true)
	private String refreshToken;
	
	@Column(name = "expire_time", nullable = true)
	private Long expireTime;
}
