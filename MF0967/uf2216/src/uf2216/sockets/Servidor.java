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

			Thread t;

			do {
				Socket s = ss.accept();
				t = new Thread(() -> procesarCliente(s));
				t.start();
			} while (true); // TODO cambiar por una posibilidad de parada externa
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void procesarCliente(Socket socket) {
		log.info("Conexión de cliente recibida");

		try (PrintWriter pw = new PrintWriter(socket.getOutputStream(), AUTO_FLUSH);
				Scanner sc = new Scanner(socket.getInputStream())) {
			pw.println("Bienvenido a TRADUCTATOR");
			log.info("Enviado mensaje de bienvenida");

			String texto;

			do {
				texto = sc.nextLine();
				log.info("Recibido el texto " + texto);

				if (texto.equals("SALIR")) {
					break;
				}

				pw.println(dic.get(texto));
				log.info("Enviada traducción");
			} while (true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
