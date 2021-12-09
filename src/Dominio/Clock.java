package Dominio;

/**
 * La clase Clock es la que controla los
 * tiempos del juego y las velocidades
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
	 * Este metodo crea el tiempo con el que comienza la velocidad del juego
	 * @param cyclesPerSecond, son los ciclos por segundo  para la velocidad
	 *                         con la que el juego inicia
	 */
	public Clock(float cyclesPerSecond) {
		setCyclesPerSecond(cyclesPerSecond);
		reset();
	}

	/**
	 * Este metodo coloca los ciclos por segundo
	 * @param cyclesPerSecond, son los ciclos por segundo  para la velocidad
	 * 	 *                     con la que el juego inicia
	 */
	public void setCyclesPerSecond(float cyclesPerSecond) {
		this.millisPerCycle = (1.0f / cyclesPerSecond) * 1000;
	}

	/**
	 * Este metodo restablece los tiempos y la velocidad del juego
	 */
	public void reset() {
		this.elapsedCycles = 0;
		this.excessCycles = 0.0f;
		this.lastUpdate = getCurrentTime();
		this.isPaused = false;
	}

	/**
	 * Este metodo actualiza los tiempos y la velocidad del juego
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
	 * Este metodo coloca en pausa el juego
	 * @param paused, es True or False
	 */
	public void setPaused(boolean paused) {
		this.isPaused = paused;
	}

	/**
	 * Es metodo dice si el juego esta pausado o no
	 * @return isPaused, es True or False
	 */
	public boolean isPaused() {return isPaused;}

	/**
	 * Este metodo retorna si ha transcurrido un ciclo o no
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
	 * Este metodo retorna el tiempo actual del juego
	 * @return tiempo actual
	 */
	private static final long getCurrentTime() {
		return (System.nanoTime() / 1000000L);
	}

}