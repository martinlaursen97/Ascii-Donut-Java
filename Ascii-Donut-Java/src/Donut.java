import java.util.ArrayList;

public class Donut {
    private static ArrayList<Vector3D> getCircle(int radius, int offset) {
        ArrayList<Vector3D> points = new ArrayList<>();

        Vector3D center = new Vector3D(offset, 0, 0);
        for (float i = 0; i < 360; i += 4) {
            float cos = (float) Math.cos(Math.toRadians(i));
            float sin = (float) Math.sin(Math.toRadians(i));

            Vector3D point = new Vector3D(offset + radius * cos, radius * sin, 0);
            Vector3D normal = Tools.vectorSubtract(center, point);
            Vector3D pn = new Vector3D(point, normal);
            points.add(pn);
        }
        return points;
    }

    public static ArrayList<Vector3D> getDonut(int radius, int offset) {
        ArrayList<Vector3D> points = new ArrayList<>();

        for (Vector3D p : getCircle(radius, offset)) {
            for (double i = 0; i < 360; i += 0.5) {
                Vector3D pN = new Vector3D(rotateY(p, i));
                Vector3D normalN = new Vector3D(rotateY(p.normal, i));
                points.add(new Vector3D(pN, normalN));

            }
        }
        return points;
    }

    private static Vector3D rotateY(Vector3D v, double degrees) {
        float cos = (float) Math.cos(Math.toRadians(degrees));
        float sin = (float) Math.sin(Math.toRadians(degrees));
        float negSin = (float) -Math.sin(Math.toRadians(degrees));
        float dx = cos    * v.x + sin  * v.z;
        float dz = negSin * v.x + cos  * v.z;
        return new Vector3D(dx, v.y, dz);
    }
}
