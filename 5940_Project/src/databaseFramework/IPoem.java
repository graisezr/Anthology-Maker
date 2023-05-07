/**
 * Interface for Poem
 */

package databaseFramework;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public interface IPoem {
    
 
    
    // Construct the Map of main themes to words
    public static Map<String, HashSet<String>> THEMES = new HashMap<>() {{
            THEMES.put(THEMESARRAY[0], LOVEWORDS);
            THEMES.put(THEMESARRAY[1], DEATHWORDS);
            THEMES.put(THEMESARRAY[2], JOYWORDS);
            THEMES.put(THEMESARRAY[3], SADNESSWORDS);
            THEMES.put(THEMESARRAY[4], WARWORDS);
        }};
        
    // Constant array of themes. Note: 'other' should be last element
    public static String[] THEMESARRAY = { "love", "death", "joy", "sadness", "war", "other" }; 
                
    /*
     * Build HashSets for generic themes
     */
        
    // Love
    public static String[] LOVEW = { "adoration", "affection", "allure", "alluring", 
        "amour", "ardor", "attachment", "attraction", "beloved", "caring", "cherish", 
        "compassion", "courtship", "crush", "darling", "deeply", "desire", "desires", 
        "devotion", "embrace", "enchantment", "eros", "fidelity", "flame", "fondness", 
        "heartfelt", "hearts", "hugs", "infatuation", "intimacy", "kisses", "longing", 
        "lovers", "lovesick", "lovingly", "loyalty", "lust", "lustful", "partnership", 
        "passion", "relationship", "romance", "sensuality", "sentiment", "soulmates", 
        "sweetheart", "swoon", "tenderness", "togetherness", "true love", "union", 
        "warmth", "yearning" };
    public static HashSet<String> LOVEWORDS = new HashSet<>(Arrays.asList(LOVEW));
    
    // Death
    public static String[] DEATHW = { "afterlife", "bereavement", "beyond", "burial", 
        "cemetery", "crypt", "darkness", "deceased", "demise", "departed", "elegy", 
        "entropy", "ephemeral", "eulogy", "eternal", "extinguish", "farewell", "finality", 
        "funeral", "ghost", "goodbye", "grave", "grief", "grim reaper", "heaven", "hell", 
        "hereafter", "immortality", "inevitable", "lament", "lifeless", "loss", "memory", 
        "mortality", "mourn", "mourning", "obituary", "passing", "requiem", "rest", 
        "reincarnation", "respite", "shroud", "silence", "sleep", "soul", "spirit", "tomb", 
        "transient", "transition", "void" };
    public static HashSet<String> DEATHWORDS = new HashSet<>(Arrays.asList(DEATHW));
    
    // Joy
    public static String[] JOYW = { "bliss", "celebration", "cheer", "contentment", "delight",
        "ecstasy", "elation", "euphoria", "exhilaration", "exuberance", "festivity", "fulfillment",
        "gaiety", "glee", "gratification", "happiness", "harmony", "jubilation", "laughter",
        "lightness", "merriment", "optimism", "overjoyed", "peace", "pleasure", "radiance",
        "rapture", "rejoicing", "satisfaction", "serenity", "smiles", "success", "thrill",
        "triumph", "uplifted", "victory", "warmth", "well-being", "wholeness", "wonder", "zest" };
    public static HashSet<String> JOYWORDS = new HashSet<>(Arrays.asList(JOYW));
    
    // Sadness
    public static String[] SADNESSW = { "anguish", "bleak", "blue", "despair", "desolation",
        "disappointment", "discontent", "distress", "dolor", "forlorn", "gloom", "grief",
        "heartache", "heaviness", "hopelessness", "lament", "loneliness", "longing", "loss",
        "melancholy", "misery", "mournful", "pain", "regret", "remorse", "sigh", "sob",
        "sorrow", "suffering", "tears", "torment", "tragedy", "unhappiness", "wistful",
        "woe", "woeful" };
    public static HashSet<String> SADNESSWORDS = new HashSet<>(Arrays.asList(SADNESSW));
    
    // War
    public static String[] WARW = 
        { "aggression", "alliance", "ambush", "annihilation", "armistice", "armor", "arsenal",
        "artillery", "atrocity", "barrage", "battles", "bloodshed", "bombardment",
        "bravery", "casualties", "combat", "conflict", "conquest", "destruction",
        "fortress", "frontline", "heroism", "hostilities", "invasion", "martial",
        "military", "munition", "occupation", "peace", "plunder", "prisoners",
        "rebellion", "resistance", "siege", "skirmish", "soldiers", "strategy",
        "surrender", "tactics", "truce", "trenches", "troops", "valor", "victory",
        "violence", "warfare", "warriors", "weapons" };
    public static HashSet<String> WARWORDS = new HashSet<>(Arrays.asList(WARW));
}
