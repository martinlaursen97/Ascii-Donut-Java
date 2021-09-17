public class Util {
    static Vector3D projection(Vector3D p, float[][] matrix) {
        float x = p.x * matrix[0][0] + p.y * matrix[1][0] + (p.z+View.DISTANCE) * matrix[2][0] + matrix[3][0];
        float y = p.x * matrix[0][1] + p.y * matrix[1][1] + (p.z+View.DISTANCE) * matrix[2][1] + matrix[3][1];
        float z = p.x * matrix[0][2] + p.y * matrix[1][2] + (p.z+View.DISTANCE) * matrix[2][2] + matrix[3][2];
        float w = p.x * matrix[0][3] + p.y * matrix[1][3] + (p.z+View.DISTANCE) * matrix[2][3] + matrix[3][3];
        x *= 0.5 * View.WIDTH;
        y *= 0.5 * View.HEIGHT;

        if (w != 1) {
            x /= w;
            y /= w;
            z /= w;
        }
        return new Vector3D(x, y, z);
    }

    // Projection matrix from: https://www.scratchapixel.com/lessons/3d-basic-rendering/perspective-and-orthographic-projection-matrix/building-basic-perspective-projection-matrix
    static float[][] projM(float fov, float far, float near) {
        float scale = (float) (1 / Math.tan(Math.toRadians(fov)));
        float[][] matrix = new float[4][4];
        matrix[0][0] = scale;
        matrix[1][1] = scale;
        matrix[2][2] = -far / (far - near);
        matrix[3][2] = -far * near / (far - near);
        matrix[2][3] = 1;
        matrix[3][3] = 0;
        return matrix;
    }

    private static void rotateY(Vector3D v, float degrees) {
        float cos = (float) Math.cos(degrees);
        float sin = (float) Math.sin(degrees);
        float negSin = (float) -Math.sin(degrees);
        float dx = cos    * v.x + sin  * v.z;
        float dz = negSin * v.x + cos  * v.z;
        v.setLocation(dx, v.y, dz);
    }

    public static Vector3D getVectorRotateY(Vector3D v, float degrees) {
        float cos = (float) Math.cos(degrees);
        float sin = (float) Math.sin(degrees);
        float negSin = (float) -Math.sin(degrees);
        float dx = cos    * v.x + sin  * v.z;
        float dz = negSin * v.x + cos  * v.z;
        return new Vector3D(dx, v.y, dz);
    }

    private static void rotateX(Vector3D v, float degrees) {
        float cos = (float) Math.cos(degrees);
        float sin = (float) Math.sin(degrees);
        float negSin = (float) -Math.sin(degrees);
        float dy = cos *  v.y + negSin * v.z;
        float dz = sin *  v.y + cos    * v.z;
        v.setLocation(v.x, dy, dz);
    }

    private static void rotateZ(Vector3D v, float degrees) {
        float cos = (float) Math.cos(degrees);
        float sin = (float) Math.sin(degrees);
        float negSin = (float) -Math.sin(degrees);
        float dx = cos * v.x + negSin * v.y;
        float dy = sin * v.x + cos    * v.y;
        v.setLocation(dx, dy, v.z);
    }

    static void rotate(Vector3D v, float x, float y, float z) {
        rotateX(v, x);
        rotateY(v, y);
        rotateZ(v, z);
    }

    static Vector3D vectorSubtract(Vector3D p1, Vector3D p2) {
        return new Vector3D(p2.x-p1.x, p2.y-p1.y, p2.z-p1.z);
    }

    static float dotP(Vector3D p1, Vector3D p2) {
        return (p1.x * p2.x) + (p1.y * p2.y) + (p1.z * p2.z);
    }

    static float len(Vector3D v) {
        return (float) Math.sqrt((v.x*v.x) + (v.y*v.y) + (v.z*v.z));
    }

    static void normalize(Vector3D v) {
        float len = len(v);
        v.setLocation(v.x/len, v.y/len, v.z/len);
    }
}
