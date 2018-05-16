package org.iot.platform.storage.sql.repositories;

import java.util.List;
import javax.transaction.Transactional;
import org.iot.platform.storage.sql.domain.OmaResource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Transactional
public interface ResourceRepository extends CrudRepository<OmaResource, Integer> {
  @Query(value = "select r from OmaResource r where r.resourceId = :id")
  public List<OmaResource> findResourcesWithOmaId(@Param("id") int id);

  @Query(value = "select r from OmaResource r where r.resourceId = :id AND r.smartobject.id = :objectId")
  public OmaResource findResourceWithOmaIdAndResourceId(@Param("objectId") int objectId ,@Param("id") int id);

  @Query(value = "select r from OmaResource r where r.smartobject.id = :objectId")
  public List<OmaResource> findResourcesWithObjectId(@Param("objectId") int objectId);

  @Query(value = "delete from OmaResource r where r.resourceId = :id")
  public void deleteResourceWithOmaId(@Param("id") int id);
}
