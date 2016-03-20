package se.evelonn.behorighet.infrastructure.persistence;

import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;

import com.googlecode.flyway.core.Flyway;
import com.googlecode.flyway.core.api.MigrationVersion;

@Singleton(name = "dbmigrator")
@Startup
@TransactionManagement(TransactionManagementType.BEAN)
public class DatabaseSchemaMigrator {

	@Resource(mappedName = "java:jboss/datasources/behorighet")
	private DataSource dataSource;

	@PostConstruct
	public void migrateDatabaseSchema() {

		Flyway flyway = new Flyway();

		flyway.setDataSource(dataSource);
		flyway.setInitVersion(MigrationVersion.fromVersion("0"));
		flyway.setInitOnMigrate(true);
		Stream.of(flyway.info().pending()).forEach(p -> System.out.println("Migrating version: " + p.getVersion()));

		flyway.migrate();
	}
}
