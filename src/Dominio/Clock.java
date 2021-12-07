package Dominio;

/**
 * The Clock class is in charge of controlling
 * game times as speeds
 *
 * @author Cesar Vasquez - Ronaldo Henao
 * @version 1.0  (December 07, 2021)
 */
public class Clock {

	private float millisPerCycle;
	private long lastUpdate;
	private int elapsedCycles;
	private float excessCycles;
	private boolean isPaused;

	/**
	 * Create the time with which the speed of he game starts
	 * @param cyclesPerSecond, are the cycles per seconds with
	 *                         which the game starts
	 */
	public Clock(float cyclesPerSecond) {
		setCyclesPerSecond(cyclesPerSecond);
		reset();
	}

	/**
	 * This method set the cycles Per Second
	 * @param cyclesPerSecond, are the cycles per seconds with
	 *                         which the game starts
	 */
	public void setCyclesPerSecond(float cyclesPerSecond) {
		this.millisPerCycle = (1.0f / cyclesPerSecond) * 1000;
	}

	/**
	 * This method resets the times and speed of the game
	 */
	public void reset() {
		this.elapsedCycles = 0;
		this.excessCycles = 0.0f;
		this.lastUpdate = getCurrentTime();
		this.isPaused = false;
	}

	/**
	 * This method updates game times and speed
	 */
	public void update() {
		long currUpdate = getCurrentTime();
		float delta = (float)(currUpdate - lastUpdate) + excessCycles;
		
		if(!isPaused) {
			this.elapsedCycles += (int)Math.floor(delta / millisPerCycle);
			this.excessCycles = delta % millisPerCycle;
		}
		
		this.lastUpdate = currUpdate;
	}

	/**
	 * This method assign game paused
	 * @param paused, paused is is True or False
	 */
	public void setPaused(boolean paused) {
		this.isPaused = paused;
	}

	/**
	 * This method says if the game is paused or not
	 * @return isPaused, isPaused is True or False
	 */
	public boolean isPaused() {return isPaused;}

	/**
	 * This method returns if a cycle has elapsed
	 * @return True or False
	 */
	public boolean hasElapsedCycle() {
		if(elapsedCycles > 0) {
			this.elapsedCycles--;
			return true;
		}
		return false;
	}

	/*
	 * This method returns the current time of the game
	 * @return current time
	 */
	private static final long getCurrentTime() {
		return (System.nanoTime() / 1000000L);
	}

}