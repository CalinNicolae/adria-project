package be.howest.adria.domain;

import java.util.Map;
import java.util.Objects;

public class Statistic {

	private final String name;
	/** Mapping from type to amount */
	private final Map<String,Integer> values;

	public Statistic(String name, Map<String, Integer> values) {
		this.name = Objects.requireNonNull(name);
		this.values = Map.copyOf(values);
	}

	public String getName() {
		return name;
	}

	public Map<String, Integer> getValues() {
		return values;
	}

}
