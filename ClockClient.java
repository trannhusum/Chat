package Chat;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClockClient extends JFrame {
	public static JLabel timeLabel;

    public ClockClient() {
        setTitle("Clock Client");
        setSize(200, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        timeLabel = new JLabel();
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
        add(timeLabel, BorderLayout.CENTER);

        setVisible(true);

        // Bắt đầu gửi yêu cầu "time" định kỳ
        Timer timer = new Timer(1000, e -> getTimeFromServer());
        timer.start();
    }

    private void getTimeFromServer() {
        final String SERVER_ADDRESS = "localhost";
        final int PORT = 32345;

        try {
            Socket socket = new Socket(SERVER_ADDRESS, PORT);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            out.println("time");
            String response = in.readLine();
            if (response != null) {
                timeLabel.setText(response);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ClockClient();
    }

}