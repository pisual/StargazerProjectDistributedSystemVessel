package com.stargazerproject.queue.resources;

import java.util.concurrent.locks.LockSupport;

import com.lmax.disruptor.AlertException;
import com.lmax.disruptor.Sequence;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.WaitStrategy;

public class LowOccupancyWaitStrategy implements WaitStrategy{
	  private static final int DEFAULT_RETRIES = 200;

	    private final int retries;
	    
	    private final long longSleepTime;

	    public LowOccupancyWaitStrategy(){
	        this(DEFAULT_RETRIES, 1000000000);
	    }

	    public LowOccupancyWaitStrategy(int retries, long longSleepTime){
	    		this.longSleepTime = longSleepTime;
	    		this.retries = retries;
	    }
	    

	    @Override
	    public long waitFor(
	        final long sequence, Sequence cursor, final Sequence dependentSequence, final SequenceBarrier barrier)
	        throws AlertException, InterruptedException
	    {
	        long availableSequence;
	        int counter = retries;

	        while ((availableSequence = dependentSequence.get()) < sequence)
	        {
	            counter = applyWaitMethod(barrier, counter);
	        }

	        return availableSequence;
	    }

	    @Override
	    public void signalAllWhenBlocking()
	    {
	    }

	    private int applyWaitMethod(final SequenceBarrier barrier, int counter)
	        throws AlertException
	    {
	        barrier.checkAlert();

	        if (counter > 10)
	        {
	            --counter;
	        }
	        else if (counter > 0)
	        {
	            --counter;
	            Thread.yield();
	        }
	        else
	        {
	            LockSupport.parkNanos(longSleepTime);
	        }

	        return counter;
	    }

}
