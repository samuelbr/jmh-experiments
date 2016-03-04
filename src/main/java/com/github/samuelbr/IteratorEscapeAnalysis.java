package com.github.samuelbr;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
public class IteratorEscapeAnalysis {
	
	@Param({ "1000" })
	public int size;

	private List<Long> list;

	@Setup(Level.Trial)
	public void buildData() throws Exception {
		list = new ArrayList<Long>();
		for (long ii = 512; ii < size + 512; ii++) {
			list.add(ii);
		}
	}

	@Benchmark
	public long sumIteratorOverList() {
		long sum = 0;
		for (Iterator<Long> iter = list.iterator(); iter.hasNext();) {
			sum += iter.next();
		}
		return sum;
	}
}