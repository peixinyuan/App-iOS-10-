import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.gaofeiyang.wms.service.model.User;

import java.util.HashMap;
import java.util.Map;

public class TestJson {
    private final static ObjectMapper OBJECT_MAPPER = JsonMapper.builder()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .build()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    public static void main(String[] args) throws Exception {
        Map<String, Object> record = new HashMap<>();
        record.put("id", "2020213588");
        record.put("Name", "高飞扬");
        String json = OBJECT_MAPPER.writeValueAsString(record);
        System.out.println(json);

        User user = OBJECT_MAPPER.readValue(json, User.class);
        System.out.println(user.getId());
        System.out.println(user.getName());
    }
}
