package cse.khu.FSSN;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.HashMap;
import java.util.Map;

public class lec_06_prg_05_json_example {

    public static void main(String[] args) {
        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Create JSON object
            Map<String, Object> superHeroesSource = new HashMap<>();
            superHeroesSource.put("squadName", "Super hero squad");
            superHeroesSource.put("homeTown", "Metro City");
            superHeroesSource.put("formed", 2016);
            superHeroesSource.put("secretBase", "Super tower");
            superHeroesSource.put("active", true);

            // Create members array
            ArrayNode members = objectMapper.createArrayNode();

            // Add first member
            ObjectNode member1 = objectMapper.createObjectNode();
            member1.put("name", "Molecule Man");
            member1.put("age", 29);
            member1.put("secretIdentity", "Dan Jukes");
            ArrayNode powers1 = objectMapper.createArrayNode();
            powers1.add("Radiation resistance");
            powers1.add("Turning tiny");
            powers1.add("Radiation blast");
            member1.set("powers", powers1);
            members.add(member1);

            // Add second member
            ObjectNode member2 = objectMapper.createObjectNode();
            member2.put("name", "Madame Uppercut");
            member2.put("age", 39);
            member2.put("secretIdentity", "Jane Wilson");
            ArrayNode powers2 = objectMapper.createArrayNode();
            powers2.add("Million tonne punch");
            powers2.add("Damage resistance");
            powers2.add("Superhuman reflexes");
            member2.set("powers", powers2);
            members.add(member2);

            // Add third member
            ObjectNode member3 = objectMapper.createObjectNode();
            member3.put("name", "Eternal Flame");
            member3.put("age", 1000000);
            member3.put("secretIdentity", "Unknown");
            ArrayNode powers3 = objectMapper.createArrayNode();
            powers3.add("Immortality");
            powers3.add("Heat Immunity");
            powers3.add("Inferno");
            powers3.add("Teleportation");
            powers3.add("Interdimensional travel");
            member3.set("powers", powers3);
            members.add(member3);

            superHeroesSource.put("members", members);

            // Convert to JSON string
            ObjectWriter writer = objectMapper.writer(SerializationFeature.INDENT_OUTPUT);
            String superHeroes = writer.writeValueAsString(superHeroesSource);

            // Print JSON string
            System.out.println(superHeroes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
