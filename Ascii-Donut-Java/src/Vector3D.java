public class Vector3D {
    float x;
    float y;
    float z;
    float w = 1;
    Vector3D normal;

    Vector3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    Vector3D(Vector3D v, Vector3D normal) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
        this.normal = normal;
    }

    Vector3D(Vector3D v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    void setLocation(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
