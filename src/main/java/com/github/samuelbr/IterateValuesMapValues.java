package com.github.samuelbr;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class IterateValuesMapValues {
	
	@Param({ "2" })
	public int size;
	
	private List<Long> list;
	
	private Map<String, Long> map;
	
	@Setup(Level.Trial)
	public void buildData() throws Exception {
		list = new ArrayList<Long>();
		map = new LinkedHashMap<String, Long>();
		
		for (long l = 0; l<size; l++) {
			list.add(l);
			map.put(Long.toString(l), Long.valueOf(l));
		}
	}
	
	@Benchmark
	public long sumIteratorOverList() {
		long sum = 0;
		for (Long value: list) {
			sum += value;
		}
		return sum;
	}

	@Benchmark
	public long sumIteratorOverMap() {
		long sum = 0;
		for (Long value: map.values()) {
			sum += value;
		}
		return sum;
	}	
}
