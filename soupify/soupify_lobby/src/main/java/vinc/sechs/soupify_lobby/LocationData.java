package vinc.sechs.soupify_lobby;

public class LocationData {
    public String world;
    public double x, y, z;
    public float yaw, pitch;

    public LocationData(String world, double x, double y, double z, float yaw, float pitch) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }
}
