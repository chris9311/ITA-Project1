package com.ita.server;
import java.net.Socket;

public abstract class Server implements Runnable{
	
	protected Socket socket;

	public Server(Socket socket) {
		super();
		this.socket = socket;
	}
	
	public abstract void connect();

	@Override
	public void run(){}

}
