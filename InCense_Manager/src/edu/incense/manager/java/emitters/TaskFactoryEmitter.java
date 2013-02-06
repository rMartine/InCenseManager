package edu.incense.manager.java.emitters;

import java.util.*;
import edu.incense.manager.java.model.*;

public class TaskFactoryEmitter
{
  protected static String nl;
  public static synchronized TaskFactoryEmitter create(String lineSeparator)
  {
    nl = lineSeparator;
    TaskFactoryEmitter result = new TaskFactoryEmitter();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = " /**" + NL + "  * This class must replace the one existing in InCense DataTask Package" + NL + "  * It's only purpose is to make instances of the filters" + NL + "  * Modified ";
  protected final String TEXT_2 = NL + "  */" + NL + "" + NL + "package edu.incense.android.datatask;" + NL + "" + NL + "import java.util.List;" + NL + "import android.content.Context;" + NL + "import edu.incense.android.datatask.filter.*;" + NL + "import edu.incense.android.datatask.model.Task;" + NL + "import edu.incense.android.datatask.sink.*;" + NL + "import edu.incense.android.datatask.trigger.*;" + NL + "import edu.incense.android.sensor.*;" + NL + "" + NL + "public class DataTaskFactory {" + NL + "    public static DataTask createDataTask(Task task, Context context) {" + NL + "        DataTask dataTask = null;" + NL + "" + NL + "        switch (task.getTaskType()) {" + NL + "        case AccelerometerSensor:" + NL + "            long frameTime = task.getLong(AccelerometerSensor.ATT_FRAMETIME, 1000);" + NL + "            long duration = task.getLong(AccelerometerSensor.ATT_DURATION, 500);" + NL + "            Sensor sensor = AccelerometerSensor.createAccelerometer(" + NL + "                    context, frameTime, duration);" + NL + "            if (task.getSampleFrequency() > 0) {" + NL + "                sensor.setSampleFrequency(task.getSampleFrequency());" + NL + "            } else if (task.getPeriodTime() > 0) {" + NL + "                sensor.setPeriodTime(task.getPeriodTime());" + NL + "            }" + NL + "            dataTask = new DataSource(sensor);" + NL + "            task.setPeriodTime(1000);" + NL + "            task.setSampleFrequency(-1.0f);" + NL + "            break;" + NL + "        case TimerSensor:" + NL + "            long period = task.getLong(\"period\", 1000);" + NL + "            TimerSensor ts = new TimerSensor(context, period);" + NL + "            dataTask = new DataSource(ts);" + NL + "            ts.addSourceTask((DataSource) dataTask);" + NL + "            break;" + NL + "        case AudioSensor:" + NL + "            long audioDuration = task.getLong(\"duration\", -1);" + NL + "            AudioSensor as = new AudioSensor(context, task.getSampleFrequency());" + NL + "            dataTask = new AudioDataSource(as, audioDuration);" + NL + "            as.addSourceTask((AudioDataSource) dataTask); // AudioSensor is" + NL + "                                                          // faster than" + NL + "                                                          // DataTask" + NL + "            break;" + NL + "        case BluetoothSensor:" + NL + "            dataTask = new DataSource(new BluetoothSensor(context));" + NL + "            break;" + NL + "        case BluetoothConnectionSensor:" + NL + "            dataTask = new DataSource(new BluetoothConnectionSensor(context," + NL + "                    task.getString(\"address\", \"\")));" + NL + "            break;" + NL + "        case GpsSensor:" + NL + "            dataTask = new DataSource(new GpsSensor(context));" + NL + "            break;" + NL + "        case GyroscopeSensor:" + NL + "            long frameTime2 = task.getLong(AccelerometerSensor.ATT_FRAMETIME, 1000);" + NL + "            long duration2 = task.getLong(AccelerometerSensor.ATT_DURATION, 500);" + NL + "            Sensor sensor2 = AccelerometerSensor.createGyroscope(" + NL + "                    context, frameTime2, duration2);" + NL + "            if (task.getSampleFrequency() > 0) {" + NL + "                sensor2.setSampleFrequency(task.getSampleFrequency());" + NL + "            } else if (task.getPeriodTime() > 0) {" + NL + "                sensor2.setPeriodTime(task.getPeriodTime());" + NL + "            }" + NL + "            dataTask = new DataSource(sensor2);" + NL + "            task.setPeriodTime(frameTime2);" + NL + "            task.setSampleFrequency(-1.0f);" + NL + "            break;" + NL + "        case CallSensor:" + NL + "            dataTask = new DataSource(new PhoneCallSensor(context));" + NL + "            break;" + NL + "        case StateSensor:" + NL + "            dataTask = new DataSource(new PhoneStateSensor(context));" + NL + "            break;" + NL + "        case PowerConnectionSensor:" + NL + "            dataTask = new DataSource(new PowerConnectionSensor(context));" + NL + "            break;" + NL + "        case NfcSensor:" + NL + "            dataTask = new DataSource(new NfcSensor(context));" + NL + "            break;" + NL + "        case WifiScanSensor:" + NL + "            dataTask = new DataSource(new WifiScanSensor(context));" + NL + "            break;" + NL + "        case WifiConnectionSensor:" + NL + "            // String[] ap = task.getStringArray(\"accessPoints\");" + NL + "            // List<String> apList = Arrays.asList(ap);" + NL + "            dataTask = new DataSource(new WifiConnectionSensor(context));" + NL + "            break;" + NL + "        case AccelerometerMeanFilter:" + NL + "            dataTask = new AccelerometerMeanFilter();" + NL + "            break;" + NL + "        case DataSink:" + NL + "            // Set SinkWritter type (Json)" + NL + "            // It will write results to a JSON file" + NL + "            dataTask = new DataSink(new JsonSinkWritter(context));" + NL + "            ((DataSink) dataTask).setName(task.getName());" + NL + "            break;" + NL + "        case AudioSink:" + NL + "            // Set SinkWritter type (Json)" + NL + "            // It will write results to a RAW file" + NL + "            dataTask = new AudioSink(new RawAudioSinkWritter(context));" + NL + "            ((DataSink) dataTask).setName(task.getName());" + NL + "            break;" + NL + "        case ShakeFilter:" + NL + "            dataTask = new ShakeFilter();" + NL + "            break;" + NL + "        case MovementFilter:" + NL + "            double threshold = task.getDouble(\"threshold\", 1000);" + NL + "            dataTask = new MovementFilter();" + NL + "            ((MovementFilter) dataTask).setMovementThreshold((float)threshold);" + NL + "            break;" + NL + "        case FalseTimerFilter:" + NL + "            long timeLength = task.getLong(\"timeLength\", 1000);" + NL + "            String attributeName = task.getString(\"attributeName\", \"\");" + NL + "            dataTask = new FalseTimerFilter();" + NL + "            ((FalseTimerFilter) dataTask).setTimeLength(timeLength);" + NL + "            ((FalseTimerFilter) dataTask).setAttributeName(attributeName);" + NL + "            break;" + NL + "        case MovementTimeFilter:" + NL + "            long maxNoInput = task.getLong(\"maxNoInput\", 30000L);" + NL + "            long maxNoMovement = task.getLong(\"maxNoMovement\", 5000L);" + NL + "            dataTask = new MovementTimeFilter();" + NL + "            ((MovementTimeFilter) dataTask).setMaxNoInput(maxNoInput);" + NL + "            ((MovementTimeFilter) dataTask).setMaxNoMovement(maxNoMovement);" + NL + "            break;" + NL + "        case WifiTimeConnectedFilter:" + NL + "            dataTask = new WifiTimeConnectedFilter();" + NL + "            break;" + NL + "        case SurveyTrigger:" + NL + "            dataTask = new SurveyTrigger(context);" + NL + "            ((SurveyTrigger) dataTask).setSurveyName(\"mainSurvey\");// task.getString(\"surveyName\"," + NL + "            break;" + NL + "        case Trigger:" + NL + "            String matches = task.getString(JsonTrigger.MATCHES, null);" + NL + "            JsonTrigger jsonTrigger = new JsonTrigger();" + NL + "            List<Condition> conditionsList = jsonTrigger.toConditions(task.getJsonNode());" + NL + "            dataTask = new GeneralTrigger(context, conditionsList, matches);" + NL + "            break;" + NL + "        case StopTrigger:" + NL + "            String matches2 = task.getString(JsonTrigger.MATCHES, null);" + NL + "            JsonTrigger jsonTrigger2 = new JsonTrigger();" + NL + "            List<Condition> conditionsList2 = jsonTrigger2.toConditions(task" + NL + "                    .getJsonNode());" + NL + "            dataTask = new StopTrigger(context, conditionsList2, matches2);" + NL + "            break;" + NL + "        case AccHighPassFilter:" + NL + "        \tdataTask = new AccHighPassFilter();" + NL + "        \tbreak;" + NL + "        case AccVectorMagFilter:" + NL + "        \tdataTask = new AccVectorMagFilter();" + NL + "        \tbreak;" + NL + "        case StepsFilter:" + NL + "        \tdataTask = new StepsFilter();" + NL + "        \tbreak;" + NL + "        case CaloriesFilter:" + NL + "        \tdataTask = new CaloriesFilter();" + NL + "        \tbreak;" + NL + "        case AccRMSFilter:" + NL + "        \tdataTask = new AccRMSFilter();" + NL + "        \tbreak;" + NL + "        case AccActivityDetectorSMOFilter:" + NL + "        \tdataTask = new AccActivityDetectorSMOFilter();" + NL + "        \tbreak;" + NL + "        case VAD16BitFilter:" + NL + "        \tdataTask = new VAD16BitFilter();" + NL + "        \tbreak;" + NL + "        case NFCPublicTransportFilter:" + NL + "        \tdataTask = new NFCPublicTransportFilter();" + NL + "        \tbreak;" + NL + "        case ";
  protected final String TEXT_3 = ":" + NL + "        \t";
  protected final String TEXT_4 = " ";
  protected final String TEXT_5 = " = task.get";
  protected final String TEXT_6 = "(\"";
  protected final String TEXT_7 = "\", ";
  protected final String TEXT_8 = "\"";
  protected final String TEXT_9 = "\"";
  protected final String TEXT_10 = ");";
  protected final String TEXT_11 = NL + "        \tdataTask = new ";
  protected final String TEXT_12 = "(";
  protected final String TEXT_13 = ", ";
  protected final String TEXT_14 = ", ";
  protected final String TEXT_15 = ");";
  protected final String TEXT_16 = "dataTask = new ";
  protected final String TEXT_17 = "();";
  protected final String TEXT_18 = NL + "            break;" + NL + "        default:" + NL + "            return null;" + NL + "        }" + NL + "        if (task.getSampleFrequency() > 0) {" + NL + "            dataTask.setSampleFrequency(task.getSampleFrequency());" + NL + "        } else if (task.getPeriodTime() > 0) {" + NL + "            dataTask.setPeriodTime(task.getPeriodTime());" + NL + "        }" + NL + "        dataTask.setTaskType(task.getTaskType());" + NL + "        dataTask.setName(task.getName());" + NL + "        dataTask.setTriggered(task.isTriggered());" + NL + "        return dataTask;" + NL + "    }" + NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    ComponentStructure component = (ComponentStructure) argument;
    int i;
    Date date = new Date();
    stringBuffer.append(TEXT_1);
    stringBuffer.append( date.toString() );
    stringBuffer.append(TEXT_2);
    stringBuffer.append(component.getComponentName());
    stringBuffer.append(TEXT_3);
    if (component.getConfigurationVariables().length > 0){
    for (i = 0; i<component.getConfigurationVariables().length; i++) {
    stringBuffer.append(component.getConfigurationVariables()[i][0]);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(component.getConfigurationVariables()[i][1]);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(component.getConfigurationVariables()[i][0].substring(0, 1).toUpperCase().concat(component.getConfigurationVariables()[i][0].substring(1, component.getConfigurationVariables()[i][0].length())));
    stringBuffer.append(TEXT_6);
    stringBuffer.append(component.getConfigurationVariables()[i][1]);
    stringBuffer.append(TEXT_7);
    if ((component.getConfigurationVariables()[i][0].compareTo("String") == 0) || (component.getConfigurationVariables()[i][0].compareTo("char") == 0)){
    stringBuffer.append(TEXT_8);
    stringBuffer.append(component.getConfigurationVariables()[i][2]);
    stringBuffer.append(TEXT_9);
    } else {
    stringBuffer.append(component.getConfigurationVariables()[i][2]);
    }
    stringBuffer.append(TEXT_10);
    }
    stringBuffer.append(TEXT_11);
    stringBuffer.append(component.getComponentName());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(component.getConfigurationVariables()[0][1]);
    if (component.getConfigurationVariables().length > 1){
    stringBuffer.append(TEXT_13);
    for (i = 0; i<component.getConfigurationVariables().length - 1; i++) {
    stringBuffer.append(component.getConfigurationVariables()[i][1]);
    stringBuffer.append(TEXT_14);
    }}
    stringBuffer.append(TEXT_15);
    } else {
    stringBuffer.append(TEXT_16);
    stringBuffer.append(component.getComponentName());
    stringBuffer.append(TEXT_17);
    }
    stringBuffer.append(TEXT_18);
    return stringBuffer.toString();
  }
}
