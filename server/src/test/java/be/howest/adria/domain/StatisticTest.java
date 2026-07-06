package be.howest.adria.domain;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatisticTest {

	private Statistic statistic;

	@BeforeEach
	public void setUp() {
		final Map<String,Integer> values = new HashMap<>();

		values.put("one", 1);
		values.put("two", 2);
		values.put("three", 3);

		statistic = new Statistic("numbers", values);
	}

	@Test
	void testGetStatName() {
		assertEquals("numbers", statistic.getName());
	}

	@Test
	void testGetQueryStat() {
		assertEquals(1, statistic.getValues().get("one"));
		assertEquals(2, statistic.getValues().get("two"));
		assertEquals(3, statistic.getValues().get("three"));
	}

	@Test
	void testUnmodifiableMap() {
		assertThrows(UnsupportedOperationException.class, () -> //NOSONAR: We need this for the test
				statistic.getValues().put("three", 4));
		assertThrows(UnsupportedOperationException.class, () -> //NOSONAR: We need this for the test
				statistic.getValues().put("four", 2));
	}

}
