package org.iot.platform.storage.sql.repositories;

import org.iot.platform.storage.sql.domain.SmartObject;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface SmartObjectRepository extends CrudRepository<SmartObject,Integer> {

}
