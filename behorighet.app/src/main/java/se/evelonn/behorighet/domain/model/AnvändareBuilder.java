package se.evelonn.behorighet.domain.model;

import java.util.UUID;

public class AnvändareBuilder {

	public interface Id {
		public Användarnamn ny();

		public Användarnamn medId(UUID id);
	}

	public interface Användarnamn {
		public Förnamn medAnvändarnamn(String användarnamn);
	}

	public interface Förnamn {
		public Efternamn medFörnamn(String förnamn);
	}

	public interface Efternamn {
		public Epost medEfternamn(String efternamn);
	}

	public interface Epost {
		public Build medEpost(String epost);
	}

	public interface Build {
		public Användare build();
	}

	public static Id builder() {
		return new InternalAnvändareBuilder();
	}

	public static class InternalAnvändareBuilder implements Id, Användarnamn, Förnamn, Efternamn, Epost, Build {

		private UUID id;
		private String användarnamn;
		private String förnamn;
		private String efternamn;
		private String epost;

		@Override
		public Användarnamn ny() {
			this.id = UUID.randomUUID();
			return this;
		}

		@Override
		public Användarnamn medId(UUID id) {
			this.id = id;
			return this;
		}

		@Override
		public Användare build() {
			Användare användare = new Användare(id, användarnamn, förnamn, efternamn, epost);
			användare.validera();
			return användare;
		}

		@Override
		public Build medEpost(String epost) {
			this.epost = epost;
			return this;
		}

		@Override
		public Epost medEfternamn(String efternamn) {
			this.efternamn = efternamn;
			return this;
		}

		@Override
		public Efternamn medFörnamn(String förnamn) {
			this.förnamn = förnamn;
			return this;
		}

		@Override
		public Förnamn medAnvändarnamn(String användarnamn) {
			this.användarnamn = användarnamn;
			return this;
		}
	}
}