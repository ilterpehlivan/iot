package org.iot.platform.storage.sql;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import org.eclipse.leshan.core.model.ObjectLoader;
import org.eclipse.leshan.core.model.ObjectModel;
import org.iot.platform.storage.sql.services.ResourceService;
import org.iot.platform.storage.sql.services.SmartObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;

@SpringBootApplication
public class SqlApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(SqlApplication.class, args);
  }

  @Value("${smartobject.path}")
  private String objectPath;

  @Autowired private SmartObjectService smartObjectService;
  @Autowired private ResourceService resourceService;
  @Autowired private ResourceLoader resourceLoader;

  @Override
  public void run(String... args) throws Exception {
    System.out.println("Starting the application");

    System.out.println("Loading Smart Objects from Path->" + objectPath);
    List<ObjectModel> objectModels =
        ObjectLoader.loadObjectsFromDir(resourceLoader.getResource(objectPath).getFile());
    /*Map<Integer, OmaResource> resources = objectModels
    .stream()
    .flatMap(obj -> obj.resources.values().stream())
    .filter(distinctByKey(r->r.id)).collect(Collectors.toMap(p -> p.id, p -> convertResourceModel2Resource(p)));*/

    /*List<OmaResource> resources =
        objectModels
            .stream()
            .flatMap(obj -> obj.resources.values().stream())
            .filter(distinctByKey(r -> r.id))
            .map(
                p -> {
                  return convertResourceModel2Resource(p);
                })
            .collect(Collectors.toList());
    System.out.println(resources);
    resourceService.saveAll(resources);
    System.out.println("Resources are saved"); */
    //      objectModels.forEach(o->System.out.println(o.toString()));
    smartObjectService.saveAllObjModel(objectModels);
    System.out.println("Objects are saved into DB->" + objectModels.size());
  }

  public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
    Map<Object, Boolean> map = new ConcurrentHashMap<>();
    return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
  }
}
