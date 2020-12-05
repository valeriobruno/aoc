package it.valeriobruno.aoc2019.ampification.circuits;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import it.valeriobruno.aoc2019.intcode.IntCodeComputer;
import it.valeriobruno.aoc2019.intcode.io.FixedIntCodeInput;
import it.valeriobruno.aoc2019.intcode.io.IntCodePipe;

public class Thrusters {
	
	private final List<Integer> amplifierControllerSoftware;
	
	
	public Thrusters(List<Integer> amplifierControllerSoftware) {
		this.amplifierControllerSoftware = amplifierControllerSoftware;
		
	}
	
	public int findMaxPhaseSequence(int minPhase, int maxPhase)
	{
		int max = -1;
		for(int a=minPhase;a<maxPhase+1;a++)
			for(int b=minPhase;b<maxPhase+1;b++)
				for(int c=minPhase;c<maxPhase+1;c++)
					for(int d=minPhase;d<maxPhase+1;d++)
						for(int e=minPhase;e<maxPhase+1;e++)
						{
							if(a==b || a==c || a==d || a==e || b ==c || b ==d || b == e || c==d || c ==e || d == e)
								continue;
							
							System.out.printf("Tersting: %d%d%d%d%d\n",a,b,c,d,e);
							//int res = runPhaseSequence(Arrays.asList(a,b,c,d,e));
							int res = runPhaseSequenceWithLoop(Arrays.asList(a,b,c,d,e));
							
							System.out.println("Result: "+res);
						
							if(res > max)
								max = res;
						}
							
		return max;
	}
	
	public List<Integer> runAmplifierControllerSoftware(List<Integer> input)
	{
		FixedIntCodeInput ioSys = new FixedIntCodeInput(input);
		IntCodeComputer computer = new IntCodeComputer(amplifierControllerSoftware, ioSys,ioSys);
		computer.run();
		return ioSys.getOutput();
		
	}
	
	public int runPhaseSequence(List<Integer> phaseSettings)
	{
		int res = 0;
		
		for(int i=0;i<phaseSettings.size();i++)
		{
			List<Integer> input = new ArrayList<>(2);
			input.add(phaseSettings.get(i));
			input.add(res);
			res = runAmplifierControllerSoftware(input).get(0);
		}
		
		return res;
	}
	
	public int runPhaseSequenceWithLoop(List<Integer> phaseSettings)
	{
		
		IntCodePipe pipeEA = new IntCodePipe();
		pipeEA.writeInt(phaseSettings.get(0));
		pipeEA.writeInt(0);
		
		
		IntCodePipe pipeAB = new IntCodePipe();
		pipeAB.writeInt(phaseSettings.get(1));
		
		IntCodePipe pipeBC = new IntCodePipe();
		pipeBC.writeInt(phaseSettings.get(2));
		
		IntCodePipe pipeCD = new IntCodePipe();
		pipeCD.writeInt(phaseSettings.get(3));
		
		IntCodePipe pipeDE = new IntCodePipe();
		pipeDE.writeInt(phaseSettings.get(4));


		ExecutorService threadPool = Executors.newFixedThreadPool(5, new ThreadFactory() {
			
			@Override
			public Thread newThread(Runnable target) {
				Thread t = new Thread(target);
				t.setDaemon(false);
				return t;
			}
		});
		CompletableFuture<Void> ampA = CompletableFuture.runAsync( () -> new IntCodeComputer(amplifierControllerSoftware, pipeEA,pipeAB).run(), threadPool );
		
		CompletableFuture<Void> ampB = CompletableFuture.runAsync( () -> new IntCodeComputer(amplifierControllerSoftware, pipeAB,pipeBC).run(), threadPool );
		CompletableFuture<Void> ampC = CompletableFuture.runAsync( () -> new IntCodeComputer(amplifierControllerSoftware, pipeBC,pipeCD).run(), threadPool );
		CompletableFuture<Void> ampD = CompletableFuture.runAsync( () -> new IntCodeComputer(amplifierControllerSoftware, pipeCD,pipeDE).run(), threadPool );
		CompletableFuture<Integer> ampE = CompletableFuture.supplyAsync( () -> new IntCodeComputer(amplifierControllerSoftware, pipeDE,pipeEA).run(), threadPool );
		
		//CompletableFuture.allOf(ampA,ampB, ampC, ampD, ampE).join();
		int result = ampE.join();
		threadPool.shutdownNow();
		return result;
	}
	
	
	
	public static void main(String[] args) throws IOException {

		List<Integer> amplProgram = IntCodeComputer.readProgramFromFile(Paths.get("./input-amplifiers.txt"));
		Thrusters tru = new Thrusters(amplProgram);
		System.out.println("Max is "+tru.findMaxPhaseSequence(5,9));
		
	}
}
