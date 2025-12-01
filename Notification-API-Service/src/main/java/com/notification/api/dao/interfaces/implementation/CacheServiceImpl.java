package com.notification.api.dao.interfaces.implementation;

import java.util.Optional;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notification.api.constants.ErrorConstants;
import com.notification.api.dao.interfaces.CacheService;
import com.notification.api.exception.ValidationException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CacheServiceImpl implements CacheService {

	private final RedisTemplate<String, String> redisTemplate;
	private final ObjectMapper objectMapper;

	@Override
	public <T> void putById(String tenantId, String id, T value) {
		put(tenantId, "BY_ID:".concat(id), value);
	}
	@Override
	public <T> void putByName(String tenantId, String name, T value) {
		put(tenantId, "BY_NAME:".concat(name), value);

	}

	@Override
	public void deletedById(String tenantId, String id) {
		delete(tenantId, "BY_ID:".concat(id));
	}
	@Override
	public void deletedByName(String tenantId, String name) {
		delete(tenantId, "BY_NAME:".concat(name));

	}

	@Override
	public <T> Optional<T> getById(String tenantId, String id, Class<T> clazz) {
		return get(tenantId, "BY_ID:".concat(id), clazz);
	}
	@Override
	public <T> Optional<T> getByName(String tenantId, String name, Class<T> clazz) {
		return get(tenantId, "BY_NAME:".concat(name), clazz);

	}
	
	private <T> Optional<T> get(String tenantId, String hashKey,Class<T> clazz)
	{
		HashOperations<String,String,String>ops=redisTemplate.opsForHash();
		String jsonData=ops.get(getTenantCacheKey(tenantId), hashKey);
		try {
			return Optional.ofNullable(objectMapper.readValue(jsonData, clazz));
		}  
		catch (Exception e) {
			throw new ValidationException(ErrorConstants.CACHE_PARSING_ERROR, HttpStatus.BAD_REQUEST.value());
		}
	}
	
	private <T> void put(String tenantId, String hashKey, T data) {
		try {
			String json = objectMapper.writeValueAsString(data);
			redisTemplate.opsForHash().put(getTenantCacheKey(tenantId), hashKey, json);
		} catch (JsonProcessingException e) {
			throw new ValidationException(ErrorConstants.PUT_CACHING_ERROR, HttpStatus.BAD_REQUEST.value());
		}
	}
	
	private <T> void delete(String tenantId, String hashKey) {
		redisTemplate.opsForHash().delete(getTenantCacheKey(tenantId), hashKey);
	}

	private String getTenantCacheKey(String tenantId) {
		return ErrorConstants.TEMPLATE_REDIS_PREFIX.concat(tenantId);
	}


}
