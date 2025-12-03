package com.notification.processor.dao.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.notification.processor.models.entity.Template;
@Repository
public interface TemplateRepository extends MongoRepository<Template, String>{
  Optional<Template> findByNameIgnoreCaseAndTenantId(String name,String templateId);

Optional<Template> findByIdAndTenantId(String id, String tenantId);
}
