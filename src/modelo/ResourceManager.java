package modelo;

import java.net.URL;
import java.util.Random;

public class ResourceManager {
    private static ResourceManager instance = null;
    private ResourceManager() {

    }
    public static ResourceManager getInstance() {
        if (instance == null) {
            instance = new ResourceManager();
        }
        return instance;
    }

    public URL getSprite(String name) {
        return getClass().getResource("/sprites/" + name + ".png");
    }
}
