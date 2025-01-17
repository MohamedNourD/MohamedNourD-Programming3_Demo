package Notifications;

import java.awt.*;

public class Notification extends Thread {
    String title;
    String description;

    public Notification (String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void run () {
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported on this system.");
            return;
        }

        SystemTray tray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");

        TrayIcon trayIcon = new TrayIcon(image, "Notifications.Notification Example");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("Java Notifications.Notification");

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
