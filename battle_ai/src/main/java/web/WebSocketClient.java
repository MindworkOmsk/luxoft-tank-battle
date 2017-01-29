package web;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketClientFactory;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: serhiy.zelenin
 * Date: 4/8/13
 * Time: 11:42 PM
 */
public class WebSocketClient {

    private static final String SERVER = "ws://battlecity.luxoft.com:8080/codenjoy-contest/ws";

    private static String userName = "IKarmatskikh@luxoft.com";

    private static Pattern urlPattern = Pattern.compile("^board=(.*)$");
    private static WebSocketClient client;

    private WebSocket.Connection connection;
    private Solver solver;
    private WebSocketClientFactory factory;

    public WebSocketClient(Solver solver) {
        this.solver = solver;
    }

    public static void main(String[] args) throws Exception {
        client = new WebSocketClient(new Solver());
        client.start();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    client.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void stop() throws Exception {
        connection.close();
        factory.stop();
    }

    private void start() throws Exception {
        factory = new WebSocketClientFactory();
        factory.start();

        org.eclipse.jetty.websocket.WebSocketClient client = factory.newWebSocketClient();
        connection = client.open(new URI(SERVER + "?user=" + userName), new WebSocket.OnTextMessage() {
            public void onOpen(Connection connection) {
                System.out.println("Opened");
            }

            public void onClose(int closeCode, String message) {
                System.out.println("Closed");
            }

            public void onMessage(String data) {
                //System.out.println("data = " + data);

                Matcher matcher = urlPattern.matcher(data);
                if (!matcher.matches()) {
                    throw new RuntimeException("WTF? " + data);
                }
                String clearString = matcher.group(1);
                String answer = solver.answer(clearString);
                int strLength = (int) Math.sqrt(clearString.length());
                StringBuilder out = new StringBuilder();
                for (int i = 0; i < clearString.length(); i = i + strLength) {
                    out.append(clearString.substring(i, i + strLength)).append("\n");
                }

                try {
                    connection.sendMessage(answer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).get(500, TimeUnit.MILLISECONDS);
    }
}
