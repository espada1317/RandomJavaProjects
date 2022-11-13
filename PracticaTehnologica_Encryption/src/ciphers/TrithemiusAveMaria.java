package ciphers;

import java.util.ArrayList;
import java.util.HashMap;

public class TrithemiusAveMaria extends CipherClass
{
    private static final String cipherName = "AveMaria";

    public static final Boolean isRequiredKey = false;
    private static final Boolean isRequirePrivateKey = false;
    public static final Boolean isRequiredGeneratedKey = false;

    public static final String descriptionCipher = "Trithemius' Ave Maria is a steganographic process invented by the benedictine monk Johannes Trithemius around 1518." +
            "   It replaces each letter of the plaintext by a group of words which looks like a poem.";

    private static final HashMap<String, ArrayList<String>> aveMariaDictionary;
    static {
        aveMariaDictionary = new HashMap<>();
        // define words substitution for a/A
        ArrayList<String> a_def = new ArrayList<>();
        a_def.add("deus");  a_def.add("clemens");
        aveMariaDictionary.put("a", a_def);
        // define words substitution for b/B
        ArrayList<String> b_def = new ArrayList<>();
        b_def.add("creator");  b_def.add("clementissimus");
        aveMariaDictionary.put("b", b_def);
        // define words substitution for c/C
        ArrayList<String> c_def = new ArrayList<>();
        c_def.add("conditor");  c_def.add("pius");
        aveMariaDictionary.put("c", c_def);
        // define words substitution for d/D
        ArrayList<String> d_def = new ArrayList<>();
        d_def.add("opifex");  d_def.add("piissium");
        aveMariaDictionary.put("d", d_def);
        // define words substitution for e/E
        ArrayList<String> e_def = new ArrayList<>();
        e_def.add("dominus");  e_def.add("magnus");
        aveMariaDictionary.put("e", e_def);
        // define words substitution for f/F
        ArrayList<String> f_def = new ArrayList<>();
        f_def.add("dominator");  f_def.add("excelsus");
        aveMariaDictionary.put("f", f_def);
        // define words substitution for g/G
        ArrayList<String> g_def = new ArrayList<>();
        g_def.add("consolator");  g_def.add("maximus");
        aveMariaDictionary.put("g", g_def);
        // define words substitution for h/H
        ArrayList<String> h_def = new ArrayList<>();
        h_def.add("arbiter");  h_def.add("optimus");
        aveMariaDictionary.put("h", h_def);
        // define words substitution for i/I
        ArrayList<String> i_def = new ArrayList<>();
        i_def.add("iudex");  i_def.add("sapientissimus");
        aveMariaDictionary.put("i", i_def);
        // define words substitution for j/J
        aveMariaDictionary.put("j", i_def);
        // define words substitution for k/K
        ArrayList<String> k_def = new ArrayList<>();
        k_def.add("illuminator");  k_def.add("inuisibilis");
        aveMariaDictionary.put("k", k_def);
        // define words substitution for l/L
        ArrayList<String> l_def = new ArrayList<>();
        l_def.add("illustrator");  l_def.add("immortalis");
        aveMariaDictionary.put("l", l_def);
        // define words substitution for m/M
        ArrayList<String> m_def = new ArrayList<>();
        m_def.add("rector");  m_def.add("aeternus");
        aveMariaDictionary.put("m", m_def);
        // define words substitution for n/N
        ArrayList<String> n_def = new ArrayList<>();
        n_def.add("rex");  n_def.add("sempiternus");
        aveMariaDictionary.put("n", n_def);
        // define words substitution for o/O
        ArrayList<String> o_def = new ArrayList<>();
        o_def.add("imperator");  o_def.add("glorious");
        aveMariaDictionary.put("o", o_def);
        // define words substitution for p/P
        ArrayList<String> p_def = new ArrayList<>();
        p_def.add("gubernator");  p_def.add("fortissium");
        aveMariaDictionary.put("p", p_def);
        // define words substitution for q/Q
        ArrayList<String> q_def = new ArrayList<>();
        q_def.add("factor");  q_def.add("sanctissium");
        aveMariaDictionary.put("q", q_def);
        // define words substitution for r/R
        ArrayList<String> r_def = new ArrayList<>();
        r_def.add("fabricator");  r_def.add("incompraehensibilis");
        aveMariaDictionary.put("r", r_def);
        // define words substitution for s/S
        ArrayList<String> s_def = new ArrayList<>();
        s_def.add("conseruator");  s_def.add("omnipotens");
        aveMariaDictionary.put("s", s_def);
        // define words substitution for t/T
        ArrayList<String> t_def = new ArrayList<>();
        t_def.add("redemptor");  t_def.add("pacificus");
        aveMariaDictionary.put("t", t_def);
        // define words substitution for u/U
        ArrayList<String> u_def = new ArrayList<>();
        u_def.add("auctor");  u_def.add("misericors");
        aveMariaDictionary.put("u", u_def);
        // define words substitution for v/V
        aveMariaDictionary.put("v", u_def);
        // define words substitution for w/W
        aveMariaDictionary.put("w", u_def);
        // define words substitution for x/X
        ArrayList<String> x_def = new ArrayList<>();
        x_def.add("princeps");  x_def.add("misericordissimus");
        aveMariaDictionary.put("x", x_def);
        // define words substitution for y/Y
        ArrayList<String> y_def = new ArrayList<>();
        y_def.add("pastor");  y_def.add("conctipotens");
        aveMariaDictionary.put("y", y_def);
        // define words substitution for z/Z
        ArrayList<String> z_def = new ArrayList<>();
        z_def.add("moderator");  z_def.add("magnificus");
        aveMariaDictionary.put("z", z_def);
    }

    public static String returnKeyDictionary(String value)
    {
        for (String key: aveMariaDictionary.keySet())
        {
            ArrayList<String> tempArray = aveMariaDictionary.get(key);

            if (tempArray.contains(value))
            {
                return key;
            }
        }
        return null;
    }

    public String encrypt(String message, String key)
    {
        String result = "";
        String[] messageSplit =  message.toLowerCase().split("");

        for (String x: messageSplit)
        {
            if (aveMariaDictionary.containsKey(x))
            {
                ArrayList<String> tempArray = aveMariaDictionary.get(x);
                int randomPos = (int) ((Math.random() * tempArray.size()));
                result += tempArray.get(randomPos) + " ";
            }
            else {
                result += x + " ";
            }
        }

        return result.trim();
    }

    public String decrypt(String message, String key)
    {
        String result = "";
        String[] messageSplit =  message.toLowerCase().split(" ");

        for (String x: messageSplit)
        {
            String tempKey = returnKeyDictionary(x);
            if (tempKey == null)
            {
                result += x;
            }
            else
            {
                result += tempKey;
            }
        }
        return result;
    }

    @Override
    public String getDescription() {
        return descriptionCipher;
    }

    @Override
    public Boolean getRequireKey() {
        return isRequiredKey;
    }

    @Override
    public Boolean getRequireGeneratedKey() {
        return isRequiredGeneratedKey;
    }

    @Override
    public Boolean getRequirePrivateKey() {
        return isRequirePrivateKey;
    }

    @Override
    public String getNameCipher() {
        return cipherName;
    }
}