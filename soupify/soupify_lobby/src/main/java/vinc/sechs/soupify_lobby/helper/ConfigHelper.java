package vinc.sechs.soupify_lobby.helper;

import vinc.sechs.soupify_lobby.LocationData;
import vinc.sechs.soupify_lobby.Soupify_Lobby;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class ConfigHelper {

    private final File file;
    private JSONObject json;

    public ConfigHelper() {
        file = new File(Soupify_Lobby.instance.getDataFolder(), "config.json");
        if (!Soupify_Lobby.instance.getDataFolder().exists()) {
            Soupify_Lobby.instance.getDataFolder().mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
                json = new JSONObject(); // leeres JSON
                save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            load();
        }
    }

    // Lädt die JSON-Datei
    private void load() {
        try (FileReader reader = new FileReader(file)) {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(reader);
            json = (JSONObject) obj;
        } catch (FileNotFoundException e) {
            json = new JSONObject();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            json = new JSONObject();
        }
    }

    // Speichert die JSON-Datei
    private void save() {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(json.toJSONString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Einzelne Property
    public void setProperty(String key, String value) {
        json.put(key, value);
        save();
    }

    public String getProperty(String key) {
        Object val = json.get(key);
        return val != null ? val.toString() : null;
    }

    // LocationData speichern
    public void setLocation(String key, LocationData loc) {
        JSONObject locJson = new JSONObject();
        locJson.put("world", loc.world);
        locJson.put("x", loc.x);
        locJson.put("y", loc.y);
        locJson.put("z", loc.z);
        locJson.put("yaw", loc.yaw);
        locJson.put("pitch", loc.pitch);

        json.put(key, locJson);
        save();
    }

    // LocationData laden
    public LocationData getLocation(String key) {
        Object obj = json.get(key);
        if (obj == null || !(obj instanceof JSONObject)) return null;

        JSONObject locJson = (JSONObject) obj;

        try {
            String world = locJson.get("world").toString();
            double x = Double.parseDouble(locJson.get("x").toString());
            double y = Double.parseDouble(locJson.get("y").toString());
            double z = Double.parseDouble(locJson.get("z").toString());
            float yaw = Float.parseFloat(locJson.get("yaw").toString());
            float pitch = Float.parseFloat(locJson.get("pitch").toString());

            return new LocationData(world, x, y, z, yaw, pitch);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}