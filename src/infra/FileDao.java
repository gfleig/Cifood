package infra;

import java.util.Map;

import business.model.Entity;
import business.model.Item;

public interface FileDao {

	public void saveEntity(Map<String, Entity> entities, String arq);
	
	public Map<String, Entity> loadEntities(String arq) throws InfraException;
	
	public void deleteEntity(Map<String, Entity> entities, String arq);
}
