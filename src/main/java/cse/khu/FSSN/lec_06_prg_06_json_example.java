package cse.khu.FSSN;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class lec_06_prg_06_json_example {

    public static void main(String[] args) {
        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Create JSON object
            ObjectNode superHeroesSource = objectMapper.createObjectNode();
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

            superHeroesSource.set("members", members);

            // Convert to JSON string
            String superHeroesMid = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(superHeroesSource);

            // Parse JSON string back to JSON object
            JsonNode superHeroes = objectMapper.readTree(superHeroesMid);

            // Print specific value
            System.out.println(superHeroes.get("homeTown").asText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
