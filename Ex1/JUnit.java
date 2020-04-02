import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JUnit {
	Pais principal, copia, menorArea;
	static int id = 5;

	@Before
	public void setup() {
		principal = new Pais(id, "Argentina", 44270000, 2780000.0);
		copia = new Pais(id, "Argentina", 44270000, 2780000.0);
	}

	@Test
	public void test1Read() {
		Pais fixture = new Pais(1, "Madagascar", 25570000, 587041.0);
		Pais novo = new Pais();
		novo = MYSQL.read("Madagascar");
		assertEquals(fixture, novo);
	}

	@Test
	public void test2Create() {
		MYSQL.create(principal);
		assertEquals(principal, copia);
	}

	@Test
	public void test3Update() {
		principal.setPopulacao(1000);
		copia.setPopulacao(1000);
		principal.setArea(1000.0);
		copia.setArea(1000.0);
		MYSQL.update("Argentina", principal);
		assertEquals(principal, copia);
	}

	@Test
	public void test4Delete() {
		MYSQL.delete("Argentina");
		principal = MYSQL.read("Argentina");
		Pais vazio = new Pais();
		assertEquals(principal, vazio);
	}
	
	@Test
	public void test5MenorArea() {
		MYSQL.create(principal);
		copia = MYSQL.menorArea();
		assertEquals(principal, copia);
		MYSQL.delete("Argentina");
	}

	@Test
	public void test6MaiorPopulacao() {
		MYSQL.create(principal);
		copia = MYSQL.maiorPopulacao();
		assertEquals(principal, copia);
		MYSQL.delete("Argentina");
	}

	@Test
	public void test7TresPaises() {
		Pais vetor[] = MYSQL.tresPaises();
		Pais pais[] = { new Pais(1, "Madagascar", 25570000, 587041.0), new Pais(2, "Nova Zelandia", 4794000, 268021.0),
				new Pais(3, "Equador", 16320000, 283560.0) };
		assertArrayEquals(pais, vetor);
	}

}