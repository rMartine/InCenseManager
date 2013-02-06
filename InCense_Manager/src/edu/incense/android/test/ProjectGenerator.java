/**
 * 
 */
package edu.incense.android.test;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.*;

import edu.incense.android.datatask.model.*;
import edu.incense.android.project.Project;
import edu.incense.android.session.Session;
import edu.incense.android.survey.Survey;

/**
 * Project examples for testing
 * 
 * @author Moises Perez (mxpxgx@gmail.com)
 * @version 0.1, May 20, 2011
 * 
 */
public class ProjectGenerator {
    private static void writeProject(String destination, ObjectMapper mapper, Project project) {
        try {
            File file = new File(destination);
            //OutputStream output = context.openFileOutput(projectFilename, 0);
            mapper.writeValue(file, project);
            //mapper.writeValue(output, project);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
            System.out.println(e);
        } catch (JsonMappingException e) {
            e.printStackTrace();
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public static void buildProjectJsonU() {
        ObjectMapper mapper = new ObjectMapper();
        
        // Session
        Session session = new Session();
        session.setDurationUnits(24L * 7L); // 90Days
        session.setDurationMeasure("hours");

        List<Task> tasks = new ArrayList<Task>();

        // Sensors
        Task audioSensor = new Task(); //TaskGenerator.createAudioSensor(mapper, 8000, 1000 * 60 * 2); // rate: 8000Hz, duration: 2 minutes
        audioSensor.setTriggered(false);
        tasks.add(audioSensor);
        
        //Filters
        Task vad16Bit = new Task(); //TaskGenerator.createVAD16BitFilter(mapper, 1000 * 60 * 2);
        tasks.add(vad16Bit);

        // Sinks
        Task dataSink = new Task(); //TaskGenerator.createTaskWithPeriod(mapper, "DataSink", TaskType.DataSink, 1000 * 60 * 2);
        tasks.add(dataSink);

        List<TaskRelation> relations = Arrays.asList(
        		new TaskRelation[] {
                        new TaskRelation(audioSensor.getName(), vad16Bit.getName()),
                        new TaskRelation(vad16Bit.getName(), dataSink.getName())
        		}
        );

        session.setTasks(tasks);
        session.setRelations(relations);

        //Write project
        Project project = new Project();
        project.setSessionsSize(1);
        project.put("mainSession", session);
        project.setSurveysSize(0);
        writeProject("C:/Users/Roberto/Desktop/InCense Manager Designing/project.json", mapper, project);
    }
    
    /**
     * Survey + Shake
     * 
     * @param resources
     */
    /*public static void buildProjectJsonW() {
    	ObjectMapper mapper = new ObjectMapper();

        // Session
        Session session = new Session();
        session.setDurationUnits(24L * 7L); // 90Days
        session.setDurationMeasure("hours");

        List<Task> tasks = new ArrayList<Task>();
        
        // Surveys
        Survey surveyINICIO = SurveyGenerator.createInicioSurvey();
        Survey surveySUBIDA = SurveyGenerator.createSubidaSurvey();
        Survey surveyBAJADA = SurveyGenerator.createBajadaSurvey();
        Survey surveyPARADA = SurveyGenerator.createParadaSurvey();

        Task nfcSensor = new Task(); //TaskGenerator.createNfcSensor(mapper, 500);
        tasks.add(nfcSensor);
        
        Task gpsSensor = new Task(); //TaskGenerator.createGpsSensor(mapper, 60000L); //each minute
        gpsSensor.setTriggered(true);
        tasks.add(gpsSensor);
        
        //Trigger GPS
        Condition ifGpsStart = TaskGenerator.createCondition("message", GeneralTrigger.DataType.TEXT.name(), GeneralTrigger.textOperators[0], null, "INICIO"); // "is false"
        ArrayList<Condition> conditions = new ArrayList<Condition>();
        conditions.add(ifGpsStart);
        
        Task gpsTrigger = TaskGenerator.createTrigger(mapper, "GpsTrigger", 1000, GeneralTrigger.matches[0], conditions);
        tasks.add(gpsTrigger);

        Condition ifGpsStop = TaskGenerator.createCondition("message", GeneralTrigger.DataType.TEXT.name(), GeneralTrigger.textOperators[0], null, "FIN"); // "is true"
        conditions = new ArrayList<Condition>();
        conditions.add(ifGpsStop);
    
        Task gpsStopTrigger = new Task(); //TaskGenerator.createStopTrigger(mapper, "GpsStopTrigger", 1000, GeneralTrigger.matches[0], conditions);
        tasks.add(gpsStopTrigger);
        
        //Trigger de INICIO
        /*Condition ifSurveyINICIO = TaskGenerator.createCondition("message", GeneralTrigger.DataType.TEXT.name(), GeneralTrigger.textOperators[0], null, "INICIO"); // "is true"
        ArrayList<Condition> conditionsINICIO = new ArrayList<Condition>();
        conditionsINICIO.add(ifSurveyINICIO);
        
        Task surveyTriggerINICIO = TaskGenerator.createTrigger(mapper, "SurveyTriggerINICIO", 1000, GeneralTrigger.matches[0], conditionsINICIO);
        tasks.add(surveyTriggerINICIO);
        
        //Trigger de SUBIDA
        Condition ifSurveySUBIDA = TaskGenerator.createCondition("message", GeneralTrigger.DataType.TEXT.name(), GeneralTrigger.textOperators[0], null, "SUBIDA"); // "is true"
        ArrayList<Condition> conditionsSUBIDA = new ArrayList<Condition>();
        conditionsSUBIDA.add(ifSurveySUBIDA);
        
        Task surveyTriggerSUBIDA = TaskGenerator.createTrigger(mapper, "SurveySUBIDA", 1000, GeneralTrigger.matches[0], conditionsSUBIDA);
        tasks.add(surveyTriggerSUBIDA);
        
        //Trigger de BAJADA
        Condition ifSurveyBAJADA = TaskGenerator.createCondition("message", GeneralTrigger.DataType.TEXT.name(), GeneralTrigger.textOperators[0], null, "BAJADA"); // "is true"
        ArrayList<Condition> conditionsBAJADA = new ArrayList<Condition>();
        conditionsBAJADA.add(ifSurveyBAJADA);
        
        Task surveyTriggerBAJADA = TaskGenerator.createTrigger(mapper, "SurveyBAJADA", 1000, GeneralTrigger.matches[0], conditionsBAJADA);
        tasks.add(surveyTriggerBAJADA);
        
        //Trigger de PARADA
        Condition ifSurveyPARADA = TaskGenerator.createCondition("message", GeneralTrigger.DataType.TEXT.name(), GeneralTrigger.textOperators[0], null, "PARADA"); // "is true"
        ArrayList<Condition> conditionsPARADA = new ArrayList<Condition>();
        conditionsPARADA.add(ifSurveyPARADA);
        
        Task surveyTriggerPARADA = TaskGenerator.createTrigger(mapper, "SurveyPARADA", 1000, GeneralTrigger.matches[0], conditionsPARADA);
        tasks.add(surveyTriggerPARADA);*/
        
        // Sinks
        /*Task dataSink = new Task(); //TaskGenerator.createTaskWithPeriod(mapper, "DataSink", TaskType.DataSink, 1000);
        tasks.add(dataSink);

        List<TaskRelation> relations = Arrays.asList(
			new TaskRelation[] {
				new TaskRelation(nfcSensor.getName(), dataSink.getName()),
				new TaskRelation(gpsSensor.getName(), dataSink.getName()),
				
	            new TaskRelation(nfcSensor.getName(), surveyTriggerINICIO.getName()),
	            new TaskRelation(surveyTriggerINICIO.getName(), "InicioSurvey"),
	            new TaskRelation(nfcSensor.getName(), surveyTriggerSUBIDA.getName()),
	            new TaskRelation(surveyTriggerSUBIDA.getName(), "SubidaSurvey"),
	            new TaskRelation(nfcSensor.getName(), surveyTriggerBAJADA.getName()),
	            new TaskRelation(surveyTriggerBAJADA.getName(), "BajadaSurvey"),
	            new TaskRelation(nfcSensor.getName(), surveyTriggerPARADA.getName()),
	            new TaskRelation(surveyTriggerPARADA.getName(), "ParadaSurvey"),
	            
	            new TaskRelation(nfcSensor.getName(), gpsTrigger.getName()),
	            new TaskRelation(gpsTrigger.getName(), gpsSensor.getName()),
	            new TaskRelation(nfcSensor.getName(), gpsStopTrigger.getName()),
	            new TaskRelation(gpsStopTrigger.getName(), gpsSensor.getName())
	        }
		);

        session.setTasks(tasks);
        session.setRelations(relations);

        Project project = new Project();
        project.setSessionsSize(1);
        project.put("mainSession", session);
        project.setSurveysSize(4);
        project.put("InicioSurvey", surveyINICIO);
        project.put("SubidaSurvey", surveySUBIDA);
        project.put("BajadaSurvey", surveyBAJADA);
        project.put("ParadaSurvey", surveyPARADA);

        writeProject(context, mapper, project);
    }
    
    public static void buildProjectJsonX(Context context) {
    	ObjectMapper mapper = new ObjectMapper();

        // Session
        Session session = new Session();
        session.setDurationUnits(24L * 7L); // 90Days
        session.setDurationMeasure("hours");

        List<Task> tasks = new ArrayList<Task>();
        
        Task nfcSensor = TaskGenerator.createNfcSensor(mapper, 1000);
        tasks.add(nfcSensor);
        
        Task gpsSensor = TaskGenerator.createGpsSensor(mapper, 60000L); //each minute
        gpsSensor.setTriggered(true);
        tasks.add(gpsSensor);
        
        //Trigger GPS
        Condition ifGpsStart = TaskGenerator.createCondition("message", GeneralTrigger.DataType.TEXT.name(), GeneralTrigger.textOperators[0], null, "INICIO"); // "is false"
        ArrayList<Condition> conditions = new ArrayList<Condition>();
        conditions.add(ifGpsStart);
        
        Task gpsTrigger = TaskGenerator.createTrigger(mapper, "GpsTrigger", 1000, GeneralTrigger.matches[0], conditions);
        tasks.add(gpsTrigger);

        Condition ifGpsStop = TaskGenerator.createCondition("message", GeneralTrigger.DataType.TEXT.name(), GeneralTrigger.textOperators[0], null, "FIN"); // "is true"
        conditions = new ArrayList<Condition>();
        conditions.add(ifGpsStop);
    
        Task gpsStopTrigger = TaskGenerator.createStopTrigger(mapper, "GpsStopTrigger", 1000, GeneralTrigger.matches[0], conditions);
        tasks.add(gpsStopTrigger);
        
        // Sinks
        Task dataSink = TaskGenerator.createTaskWithPeriod(mapper, "DataSink", TaskType.DataSink, 1000);
        tasks.add(dataSink);

        List<TaskRelation> relations = Arrays.asList(
			new TaskRelation[] {
				new TaskRelation(nfcSensor.getName(), dataSink.getName()),
				new TaskRelation(gpsSensor.getName(), dataSink.getName()),
				
	            new TaskRelation(nfcSensor.getName(), gpsTrigger.getName()),
	            new TaskRelation(gpsTrigger.getName(), gpsSensor.getName()),
	            new TaskRelation(nfcSensor.getName(), gpsStopTrigger.getName()),
	            new TaskRelation(gpsStopTrigger.getName(), gpsSensor.getName())
	        }
		);

        session.setTasks(tasks);
        session.setRelations(relations);

        Project project = new Project();
        project.setSessionsSize(1);
        project.put("mainSession", session);
        project.setSurveysSize(0);
        writeProject(context, mapper, project);
    }*/
}
