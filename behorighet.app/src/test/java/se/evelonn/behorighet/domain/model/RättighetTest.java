package se.evelonn.behorighet.domain.model;

import static org.junit.Assert.fail;

import org.junit.Test;

public class RättighetTest {

	@Test
	public void skaInteGåAttSkapaRättighetUtanNamn() {
		try {
			Rättighet.skapa(null);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			// expected
			return;
		}
		fail("Ett IllegalArgumentException borde ha kastats");
	}
}