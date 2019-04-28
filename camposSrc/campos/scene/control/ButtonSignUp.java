package campos.scene.control;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import campos.net.IPv4;
import campos.net.SocketType;
import campos.scene.layout.SignUpPane;
import campos.util.FXUtil;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ButtonSignUp extends Button {
	private SignUpPane signUpPane;
	
	public ButtonSignUp(SignUpPane signUpPane) {
		super("Sign Up");
		this.signUpPane = signUpPane;
		setPrefWidth(FXUtil.BT_WIDTH);
		setOnAction(new SignUpHandler());
	}
	
	private class SignUpHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			try {
				if (signUpPane.fieldsAreValid()) {
					Socket socket = new Socket(IPv4.HOST, IPv4.PORT);
					new Thread(new ClientHandler(socket)).start();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	private class ClientHandler implements Runnable {
		private Socket socket;

		public ClientHandler(Socket socket) { // Constructor
			this.socket = socket;
		}
		
		@Override
		public void run() {
			try {
				System.out.println("Attempting to Sign Up...(WIP)");
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				oos.writeObject(SocketType.SignUp);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
