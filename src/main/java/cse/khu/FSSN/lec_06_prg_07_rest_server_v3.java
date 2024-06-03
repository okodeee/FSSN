package cse.khu.FSSN;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class lec_06_prg_07_rest_server_v3 {

    public static void main(String[] args) {
        SpringApplication.run(lec_06_prg_07_rest_server_v3.class, args);
    }
}

@RestController
@RequestMapping("/membership_api")
class lec_06_prg_07_rest_server_v3Controller {

    @Autowired
    private lec_06_prg_07_rest_server_v3Handler lec_06_prg_07_rest_server_v3Handler;

    @PostMapping("/{member_id}")
    public Map<String, String> create(@PathVariable("member_id") String memberId, @RequestParam String value) {
        return lec_06_prg_07_rest_server_v3Handler.create(memberId, value);
    }

    @GetMapping("/{member_id}")
    public Map<String, String> read(@PathVariable("member_id") String memberId) {
        return lec_06_prg_07_rest_server_v3Handler.read(memberId);
    }

    @PutMapping("/{member_id}")
    public Map<String, String> update(@PathVariable("member_id") String memberId, @RequestParam String value) {
        return lec_06_prg_07_rest_server_v3Handler.update(memberId, value);
    }

    @DeleteMapping("/{member_id}")
    public Map<String, String> delete(@PathVariable("member_id") String memberId) {
        return lec_06_prg_07_rest_server_v3Handler.delete(memberId);
    }
}

@Service
class lec_06_prg_07_rest_server_v3Handler {
    private Map<String, String> database = new HashMap<>();

    public Map<String, String> create(String id, String value) {
        if (database.containsKey(id)) {
            return Map.of(id, "None");
        } else {
            database.put(id, value);
            return Map.of(id, database.get(id));
        }
    }

    public Map<String, String> read(String id) {
        if (database.containsKey(id)) {
            return Map.of(id, database.get(id));
        } else {
            return Map.of(id, "None");
        }
    }

    public Map<String, String> update(String id, String value) {
        if (database.containsKey(id)) {
            database.put(id, value);
            return Map.of(id, database.get(id));
        } else {
            return Map.of(id, "None");
        }
    }

    public Map<String, String> delete(String id) {
        if (database.containsKey(id)) {
            database.remove(id);
            return Map.of(id, "Removed");
        } else {
            return Map.of(id, "None");
        }
    }
}