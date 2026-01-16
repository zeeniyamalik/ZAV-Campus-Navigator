import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;

public class CampusNavigator extends JFrame {

    private Map<String, Point> locations; // building names -> coordinates
    private MapPanel mapPanel;
    private JComboBox<String> fromCombo;
    private JComboBox<String> toCombo;

    public CampusNavigator() {
        setTitle("Campus Navigator");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        locations = new HashMap<>();
        locations.put("Parking Lot", new Point(500, 100));
        locations.put("100 Campus", new Point(400, 300));
        locations.put("99 Campus", new Point(200, 150));
        locations.put("98 Campus", new Point(500, 100));
        locations.put("154 Campus", new Point(500, 100));
        locations.put("153 Campus", new Point(700, 150));
        locations.put("79 Campus - Main Library", new Point(500, 100));

        locations.put("100 Stationary Shop", new Point(500, 100));
        locations.put("154 Stationary Shop", new Point(500, 100));


        locations.put("Master's Library", new Point(500, 100));
        locations.put("Law Library", new Point(500, 100));

        locations.put("100 Cafeteria", new Point(500, 100));
        locations.put("99 Cafeteria", new Point(500, 100));
        locations.put("154 Cafeteria", new Point(500, 100));

        locations.put("Admissions Office", new Point(500, 100));
        locations.put("SZABIST Student Council Room", new Point(500, 100));
        locations.put("Academics Office", new Point(500, 100));
        locations.put("Finance Office", new Point(500, 100));

        locations.put("Seminar Hall", new Point(500, 100));
        locations.put("Sports Room", new Point(500, 100));
        locations.put("100 Reception", new Point(500, 100));

        // Top bar
        JPanel topBar = new JPanel();
        List<String> locList = new ArrayList<>(locations.keySet());
        Collections.sort(locList);
        fromCombo = new JComboBox<>(locList.toArray(new String[0]));
        toCombo = new JComboBox<>(locList.toArray(new String[0]));
        JButton startButton = new JButton("Start");

        topBar.add(new JLabel("From:"));
        topBar.add(fromCombo);
        topBar.add(new JLabel("To:"));
        topBar.add(toCombo);
        topBar.add(startButton);
        add(topBar, BorderLayout.NORTH);

        // Map panel
        mapPanel = new MapPanel();
        add(mapPanel, BorderLayout.CENTER);

        // Button action
        startButton.addActionListener(e -> {
            String from = (String) fromCombo.getSelectedItem();
            String to = (String) toCombo.getSelectedItem();

            if (from.equals(to)) {
                JOptionPane.showMessageDialog(this, "You are already at the destination!");
                return;
            }

            mapPanel.setPath(locations.get(from), locations.get(to));
        });

        setVisible(true);
    }

    // Panel to draw map and path
    class MapPanel extends JPanel {
        private Image mapImage;
        private Point currentLocation;
        private Point destination;

        public MapPanel() {
            mapImage = new ImageIcon("campus_map.png").getImage(); // your campus map image
        }

        public void setPath(Point from, Point to) {
            this.currentLocation = from;
            this.destination = to;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(mapImage, 0, 0, getWidth(), getHeight(), this);

            if (currentLocation != null && destination != null) {
                // Draw current location
                g.setColor(Color.BLUE);
                g.fillOval(currentLocation.x - 10, currentLocation.y - 10, 20, 20);

                // Draw destination
                g.setColor(Color.RED);
                g.fillOval(destination.x - 10, destination.y - 10, 20, 20);

                // Draw path line
                g.setColor(Color.GREEN);
                g.drawLine(currentLocation.x, currentLocation.y, destination.x, destination.y);
            }
        }
    }

    public static void main(String[] args) {
        new CampusNavigator();
    }
}
