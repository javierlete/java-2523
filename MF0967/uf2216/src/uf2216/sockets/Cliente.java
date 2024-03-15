package uf2216.sockets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Logger;

public class Cliente {

	private static final Logger log = Logger.getLogger(Servidor.class.getName());

	private static final boolean AUTO_FLUSH = true;

	public static void main(String[] args) {
		try (Socket s = new Socket("localhost", 1234)) {
			log.info("Conexión de cliente iniciada");

			try (PrintWriter pw = new PrintWriter(s.getOutputStream(), AUTO_FLUSH);
					Scanner sc = new Scanner(s.getInputStream())) {
				log.info(sc.nextLine());

				pw.println("casa");

				log.info("Recibido el texto " + sc.nextLine());
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
