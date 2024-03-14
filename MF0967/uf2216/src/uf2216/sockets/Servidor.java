package uf2216.sockets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.logging.Logger;

public class Servidor {

	private static final Logger log = Logger.getLogger(Servidor.class.getName());

	private static final boolean AUTO_FLUSH = true;
	private static final TreeMap<String, String> dic = new TreeMap<>();

	static {
		dic.put("casa", "home");
		dic.put("perro", "dog");
		dic.put("gato", "cat");
	}

	public static void main(String[] args) {
		try (ServerSocket ss = new ServerSocket(1234)) {
			log.info("Servidor iniciado");

			try (Socket s = ss.accept()) {
				log.info("Conexión de cliente recibida");

				PrintWriter pw = new PrintWriter(s.getOutputStream(), AUTO_FLUSH);
				Scanner sc = new Scanner(s.getInputStream());

				pw.println("Bienvenido a TRADUCTATOR");
				log.info("Enviado mensaje de bienvenida");

				String espanol = sc.nextLine();

				log.info("Recibido el texto " + espanol);

				pw.println(dic.get(espanol));

				log.info("Enviada traducción");
			} catch (IOException e) {
				throw e;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
