package ar.com.ovittata.club.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class MapperFromJson {

  private static final ObjectMapper objectMapper = new ObjectMapper();
  private static final Logger logger = LoggerFactory.getLogger(MapperFromJson.class);

  private MapperFromJson() {
  }

  public static <T> T fromJsonToClazz(String json, Class<T> clazz) {
    try {
      return objectMapper.readValue(json, clazz);
    } catch (JsonProcessingException jsonProcessingException) {
      return null;
    }
  }

  public static boolean isValidJson(String json, String schemaFileName) {
    var isValid = false;
    try {
      JsonNode jsonNode = JsonLoader.fromString(json);
      JsonNode validSchema = JsonLoader.fromResource(schemaFileName);
      JsonSchema schema = JsonSchemaFactory.byDefault().getJsonSchema(validSchema);
      ProcessingReport r1 = schema.validate(jsonNode);
      isValid = r1.isSuccess();
    } catch (IOException | ProcessingException exception) {
      logger.info(exception.getMessage());
    }
    return isValid;
  }
}
