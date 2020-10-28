package com.vetologic.cms.jwt_security_config.model.repository;

import com.vetologic.cms.dto.user.UserDto;

public interface UserRepository {
	UserDto findByUsername(String username);
}