package util;

import java.util.UUID;

//82
abstract class Benchmark {
	abstract void benchmark();

	public final long repeat(int count) {
		long start = System.nanoTime();
		for (int i = 0; i < count; i++)
			benchmark();
		return (System.nanoTime() - start);
	}
}

class MethodBenchmark extends Benchmark {
	void benchmark() {
	}
}

public class abstractDemo {
	public static void main(String[] args) {
		Benchmark bm;
//		int count = Integer.parseInt(args[0]);
//		long time = new MethodBenchmark().repeat(count);
//		System.out.println(count + "methods in" + time + "  毫微秒 nanoseconds");
		System.out.println(UUID.randomUUID());
	}

}
