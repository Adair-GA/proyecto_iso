package modelo;

import javax.swing.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class ResourceManager {
    private static ResourceManager instance = null;
    private static HashMap<String,String> sprites = new HashMap<>();
    private ResourceManager() {

    }
    public static ResourceManager getInstance() {
        if (instance == null) {
            instance = new ResourceManager();
            sprites.put("grave","grave");
            sprites.put("main","main");
            sprites.put("PLANTA1","bulbasaur");
            sprites.put("PLANTA2","ivysaur");
            sprites.put("PLANTA3","venusaur");
            sprites.put("FUEGO1","charmander");
            sprites.put("FUEGO2","charmeleon");
            sprites.put("FUEGO3","charizard");
            sprites.put("AGUA1","squirtle");
            sprites.put("AGUA2","wartortle");
            sprites.put("AGUA3","blastoise");
            sprites.put("ELECTRICO1","pichu");
            sprites.put("ELECTRICO2","pikachu");
            sprites.put("ELECTRICO3","raichu");
        }
        return instance;
    }

    public ImageIcon getSprite(String name) {
        return new ImageIcon(Objects.requireNonNull(getClass().getResource("/sprites/" + sprites.get(name) + ".png")));
    }
    public ImageIcon getSprite(PokemonTypes type, int level) {
        String key = type.toString() + level;
        return getSprite(key);
    }
    public ImageIcon getSprite(String type, int level) {
        String key = type + level;
        return getSprite(key);
    }

    public ImageIcon getRandomTrainer() {
        Random random = new Random();
        int i = random.nextInt(5);
        return new ImageIcon(Objects.requireNonNull(getClass().getResource("/sprites/trainer"+ i +".png")));
    }

public ImageIcon getGrave() {
        return getSprite("grave");
    }

}
