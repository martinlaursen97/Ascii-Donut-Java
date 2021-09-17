import java.util.ArrayList;

public class Donut {
    private static final float TWO_PI = 6.28318F;
    private static final float INC_A = 0.065F;
    private static final float INC_B = 0.01F;

    private static ArrayList<Vector3D> getCircle(int radius, int offset) {
        ArrayList<Vector3D> points = new ArrayList<>();

        Vector3D center = new Vector3D(offset, 0, 0);
        for (float theta = 0; theta < TWO_PI; theta += INC_A) {
            float cos = (float) Math.cos(theta);
            float sin = (float) Math.sin(theta);

            Vector3D point = new Vector3D(offset + radius * cos, radius * sin, 0);
            Vector3D normal = Util.vectorSubtract(center, point);
            Vector3D pn = new Vector3D(point, normal);
            points.add(pn);
        }
        return points;
    }

    public static ArrayList<Vector3D> getDonut(int radius, int offset) {
        ArrayList<Vector3D> points = new ArrayList<>();

        for (Vector3D p : getCircle(radius, offset)) {
            for (float theta = 0; theta < TWO_PI; theta += INC_B) {
                Vector3D pN = new Vector3D(Util.getVectorRotateY(p, theta));
                Vector3D normalN = new Vector3D(Util.getVectorRotateY(p.normal, theta));
                points.add(new Vector3D(pN, normalN));
            }
        }
        return points;
    }
}
