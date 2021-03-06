import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class View extends JPanel implements ActionListener, KeyListener, MouseWheelListener {
    private final String NAME = "Rotating Donut";
    public static final int WIDTH  = 800;
    public static final int HEIGHT = 800;
    private static final int PIXEL_SCALE  = 13;
    private static final int FONT_SIZE    = 13;
    private static final String FONT_NAME = "Cambria";

    private static float fov = 55;
    private final float FAR   = 1000;
    private final float NEAR  = 0.1F;
    public static final int DISTANCE = 6;

    private static float rotateX = 0;
    private static float rotateY = 0;
    private static float rotateZ = 0;

    private ArrayList<Vector3D> points = Donut.getDonut(1, 3);
    private static Vector3D light = new Vector3D(0, 20, 7);
    private Shader shader = new Shader();

    private static boolean ascii = true;

    private static int frames = 0;
    private static int fps = 0;

    Timer t = new Timer(0, this);

    void run() {
        JFrame frame = new JFrame(NAME);
        frame.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(this);
        frame.addMouseWheelListener(this);
        frame.pack();
        frame.add(new View());
        frame.setVisible(true);
        fps();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        float[][] zBuffer = new float[WIDTH][HEIGHT];
        char[][] grid = new char[WIDTH][HEIGHT];

        Font font = new Font(FONT_NAME, Font.PLAIN, FONT_SIZE);
        g.setFont(font);

        for (Vector3D v : points) {
            Vector3D projectedPoint = Util.projection(v, Util.projM(fov, FAR, NEAR));

            // Centers the projected points, and rounds to nearest scale value.
            int dx = (int) (Math.round((WIDTH /2. + projectedPoint.x)/PIXEL_SCALE) * PIXEL_SCALE);
            int dy = (int) (Math.round((HEIGHT/2. + projectedPoint.y)/PIXEL_SCALE) * PIXEL_SCALE);

            float ooz = 1 / (v.z + DISTANCE);

            if (dx >= 0 && dx < WIDTH && dy > 0 && dy < HEIGHT) {
                if (ooz > zBuffer[dx][dy]) {
                    zBuffer[dx][dy] = ooz;
                    Vector3D lightRay = Util.vectorSubtract(v, light);
                    Util.normalize(lightRay);
                    float dotP = Util.dotP(lightRay, v.normal);

                    if (ascii) {
                        grid[dx][dy] = shader.getBrightness(dotP);
                    } else {
                        int gradient = (int) Math.toDegrees(-dotP) + 60;
                        g.setColor(new Color(gradient, gradient, gradient));
                        g.fillRect(dx, dy, PIXEL_SCALE, PIXEL_SCALE);
                    }
                }
            }
        }

        g.setColor(Color.WHITE);

        if (ascii) {
            for (int x = 0; x < WIDTH; x++) {
                for (int y = 0; y < HEIGHT; y++) {
                    if (grid[x][y] != 0) {
                        g.drawString(String.valueOf(grid[x][y]), x, y);
                    }
                }
            }
        }

        g.drawString("FPS | " + fps, 10, 20);

        frames++;
        t.start();
    }

    public synchronized void fps() {
        try {
            while (true) {
                Thread.sleep(1000);
                fps = frames;
                frames = 0;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Vector3D v : points) {
            Util.rotate(v, rotateX, rotateY, rotateZ);
            Util.rotate(v.normal, rotateX, rotateY, rotateZ);
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            // Change rotation speed
            case KeyEvent.VK_1 -> rotateX += 0.001F;
            case KeyEvent.VK_2 -> rotateY += 0.001F;
            case KeyEvent.VK_3 -> rotateZ += 0.001F;

            // Move light point
            case KeyEvent.VK_A -> light.setLocation(light.x += 3, light.y, light.z);
            case KeyEvent.VK_D -> light.setLocation(light.x -= 3, light.y, light.z);
            case KeyEvent.VK_W -> light.setLocation(light.x, light.y += 3, light.z);
            case KeyEvent.VK_S -> light.setLocation(light.x, light.y -= 3, light.z);

            // Change lights z value
            case KeyEvent.VK_Q -> light.setLocation(light.x, light.y, light.z += 3);
            case KeyEvent.VK_E -> light.setLocation(light.x, light.y, light.z -= 3);

            // Reverse rotation
            case KeyEvent.VK_R -> {
                rotateX = -rotateX;
                rotateY = -rotateY;
                rotateZ = -rotateZ;
            }

            // Stop rotation
            case KeyEvent.VK_SPACE -> {
                rotateX = 0;
                rotateY = 0;
                rotateZ = 0;
            }

            // Switch between ascii and colored pixels
            case KeyEvent.VK_T -> ascii = !ascii;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int i = e.getWheelRotation();
        if (i == 1) {
            if (fov < 90) {
                fov++;
            }
        } else {
            if (fov > 45) {
                fov--;
            }
        }
    }
}
