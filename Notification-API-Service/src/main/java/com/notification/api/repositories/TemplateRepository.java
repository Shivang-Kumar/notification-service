package com.notification.api.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.notification.api.models.entity.Template;
@Repository
public interface TemplateRepository extends MongoRepository<Template, UUID>{
  Optional<Template> findByNameIgnoreCaseAndTenantId(String name,UUID templateId);
}
