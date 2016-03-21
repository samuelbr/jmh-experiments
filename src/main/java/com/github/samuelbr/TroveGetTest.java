package com.github.samuelbr;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import gnu.trove.map.hash.THashMap;

@State(Scope.Thread)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(3)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class TroveGetTest {

	@Param({"1", "10", "100", "1000"})
    int size;
	
	private Map<String, Object> map;
	
	private THashMap<String, Object> tmap;
	
	@Setup
	public void setup() {
		map = new HashMap<String, Object>();
		tmap = new THashMap<String, Object>();
		
		for (int i=0; i<size; i++) {
			map.put(Integer.toString(i), i);
			tmap.put(Integer.toString(i), i);
		}
	}
	
	@Benchmark
	public void testMap(Blackhole bh) {
		for (int i=0; i<size; i++) {
			bh.consume(map.get(Integer.toString(i)));
		}
	}
	
	@Benchmark
	public void testTroveMap(Blackhole bh) {
		for (int i=0; i<size; i++) {
			bh.consume(tmap.get(Integer.toString(i)));
		}
	}
}
