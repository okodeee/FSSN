package cse.khu.FSSN;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;

public class lec_06_prg_04_json_example {

    public static void main(String[] args) {
        // Create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        // Create JSON object
        ObjectNode superHeroes = objectMapper.createObjectNode();
        superHeroes.put("squadName", "Super hero squad");
        superHeroes.put("homeTown", "Metro City");
        superHeroes.put("formed", 2016);
        superHeroes.put("secretBase", "Super tower");
        superHeroes.put("active", true);

        ArrayNode members = objectMapper.createArrayNode();

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

        superHeroes.set("members", members);

        // Print specific values
        System.out.println(superHeroes.get("homeTown").asText());
        System.out.println(superHeroes.get("active").asBoolean());
        JsonNode secondMember = superHeroes.get("members").get(1);
        JsonNode powers = secondMember.get("powers");
        System.out.println(powers.get(2).asText());

        // Write JSON object to a file
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/main/java/cse/khu/FSSN/lec-06-prg-04-json-example.json"), superHeroes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
