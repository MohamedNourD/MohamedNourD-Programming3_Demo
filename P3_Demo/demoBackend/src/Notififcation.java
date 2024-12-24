import java.awt.*;

public class Notififcation {
    public void getNotification (String title, String description) {
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported on this system.");
            return;
        }

        SystemTray tray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");

        // Create a TrayIcon instance
        TrayIcon trayIcon = new TrayIcon(image, "Notification Example");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("Java Notification");

        try {
            tray.add(trayIcon);

            trayIcon.displayMessage(title,
                    description,
                    TrayIcon.MessageType.INFO);
        } catch (AWTException e) {
            System.err.println("Error adding tray icon: " + e.getMessage());
        }
    }
}
