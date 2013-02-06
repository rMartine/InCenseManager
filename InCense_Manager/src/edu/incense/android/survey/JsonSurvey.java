package edu.incense.android.survey;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;


public class JsonSurvey {
    public final static String SURVEY = "survey";
    public final static String ID = "id";
    public final static String TITLE = "title";
    public final static String QUESTIONS = "questions";

    private ObjectMapper mapper;

    public JsonSurvey() {
        mapper = new ObjectMapper(); // can reuse, share globally
    }

    public JsonSurvey(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public Survey toSurvey(JsonNode root) {
        Survey survey = null;
        try {
            survey = new Survey();

            JsonNode attribute = root.get(TITLE);
            survey.setTitle(attribute.getValueAsText());

            attribute = root.get(ID);
            survey.setId(attribute.getValueAsInt());

            attribute = root.get(QUESTIONS);
            List<Question> questions = mapper.readValue(attribute,
                    new TypeReference<List<Question>>() {
                    });
            if (questions != null)
                survey.setQuestions(questions);
            else {
            }
        } catch (JsonParseException e) {
            return null;
        } catch (JsonMappingException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
        return survey;
    }

    public void toJson(String fileName, Answer[] answers) {
        toJson(new File(fileName), answers);
    }

    public void toJson(File file, Answer[] answers) {
        try {
            mapper.writeValue(file, answers);
        } catch (JsonParseException e) {
        } catch (JsonMappingException e) {
        } catch (IOException e) {
        }
    }

}
