package cse.khu.FSSN;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class lec_06_prg_03_json_example {

    public static void main(String[] args) {
        String filePath = "src/main/java/cse/khu/FSSN/lec-06-prg-03-json-example.json";

        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Read JSON file and convert to JsonNode
            JsonNode superHeroes = objectMapper.readTree(new File(filePath));

            // Extract and print specific values
            System.out.println(superHeroes.get("homeTown").asText());
            System.out.println(superHeroes.get("active").asBoolean());

            // Extract nested values
            JsonNode members = superHeroes.get("members");
            JsonNode secondMember = members.get(1);
            JsonNode powers = secondMember.get("powers");
            System.out.println(powers.get(2).asText());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
