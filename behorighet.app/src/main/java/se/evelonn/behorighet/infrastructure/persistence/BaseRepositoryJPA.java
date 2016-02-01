package se.evelonn.behorighet.infrastructure.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseRepositoryJPA {

	@PersistenceContext(name = "se.evelonn.behorighet")
	protected EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
