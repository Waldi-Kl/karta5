package com.waldi.karta.repository;

import java.util.Optional;

import com.waldi.model.UserInfo;

public interface UserRepository {
	Optional<UserInfo> findByEmail(String email);
	 Optional<UserInfo> findByResetToken(String resetToken);
}
