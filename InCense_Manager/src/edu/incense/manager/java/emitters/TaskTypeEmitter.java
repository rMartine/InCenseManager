package edu.incense.manager.java.emitters;

import java.util.*;
import edu.incense.manager.java.model.*;

public class TaskTypeEmitter
{
  protected static String nl;
  public static synchronized TaskTypeEmitter create(String lineSeparator)
  {
    nl = lineSeparator;
    TaskTypeEmitter result = new TaskTypeEmitter();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = " /**" + NL + "  * This class must replace the one existing in InCense Data Package" + NL + "  * It's only purpose is to maintain the list of DataTypes available" + NL + "  * Modified ";
  protected final String TEXT_2 = NL + "  */" + NL + "" + NL + "package edu.incense.android.datatask.model;" + NL + " " + NL + "public enum TaskType {" + NL + "\tNULL, " + NL + "    AccelerometerSensor, " + NL + "    GyroscopeSensor, " + NL + "    AudioSensor, " + NL + "    BluetoothSensor, " + NL + "    GpsSensor, " + NL + "    CallSensor, " + NL + "    StateSensor, " + NL + "    WifiScanSensor, " + NL + "    WifiConnectionSensor, " + NL + "    AccelerometerMeanFilter, " + NL + "    DataSink, " + NL + "    AudioSink, " + NL + "    SessionTrigger, " + NL + "    SurveyTrigger, " + NL + "    Trigger," + NL + "    ShakeFilter, " + NL + "    BluetoothConnectionSensor," + NL + "    NfcSensor," + NL + "    WifiTimeConnectedFilter," + NL + "    StopTrigger," + NL + "    TimerSensor," + NL + "    PowerConnectionSensor," + NL + "    MovementFilter," + NL + "    FalseTimerFilter," + NL + "    MovementTimeFilter," + NL + "    AccHighPassFilter," + NL + "    AccVectorMagFilter," + NL + "    StepsFilter," + NL + "    CaloriesFilter," + NL + "    AccRMSFilter," + NL + "    AccActivityDetectorSMOFilter," + NL + "    VAD16BitFilter," + NL + "    NFCPublicTransportFilter,";
  protected final String TEXT_3 = NL + " ";
  protected final String TEXT_4 = NL + "};";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    ComponentStructure component = (ComponentStructure) argument;
    Date date = new Date();
    stringBuffer.append(TEXT_1);
    stringBuffer.append( date.toString() );
    stringBuffer.append(TEXT_2);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(component.getComponentName());
    stringBuffer.append(TEXT_4);
    return stringBuffer.toString();
  }
}
