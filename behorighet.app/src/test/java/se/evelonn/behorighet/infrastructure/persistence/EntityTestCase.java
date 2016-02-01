package se.evelonn.behorighet.infrastructure.persistence;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

public abstract class EntityTestCase {

	protected static EntityManager entityManager;

	@BeforeClass
	public static void setupEntityManager() {
		entityManager = Persistence.createEntityManagerFactory("integration-test").createEntityManager();
	}

	@Before
	public void startTransaction() {
		entityManager.getTransaction().begin();
	}

	@After
	public void rollbackTransaction() {
		entityManager.getTransaction().rollback();
	}
}
