package edu.incense.manager.java.emitters;

import java.util.*;
import edu.incense.manager.java.model.*;

public class DataTypeEmitter
{
  protected static String nl;
  public static synchronized DataTypeEmitter create(String lineSeparator)
  {
    nl = lineSeparator;
    DataTypeEmitter result = new DataTypeEmitter();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = " /**" + NL + "  * This class must replace the one existing in InCense Data Package" + NL + "  * It's only purpose is to maintain the list of DataTypes available" + NL + "  * Modified ";
  protected final String TEXT_2 = NL + "  */" + NL + "" + NL + "package edu.incense.android.datatask.data;" + NL + " " + NL + "public enum DataType {" + NL + "\tNULL," + NL + "    ACCELEROMETER," + NL + "    AUDIO," + NL + "    BLUETOOTH," + NL + "    GPS, " + NL + "    CALLS," + NL + "    STATES," + NL + "    WIFI," + NL + "    BATTERY_LEVEL," + NL + "    BOOLEAN, " + NL + "    NFC," + NL + "    GYROSCOPE," + NL + "    ACCHIGHPASSFRAME," + NL + "    ACCVECMAG," + NL + "    STEPSCOUNTER," + NL + "    CALORIES," + NL + "    ACCRMS," + NL + "    ACCACTxINACT," + NL + "    VAD16BIT," + NL + "    NFCTAG,";
  protected final String TEXT_3 = NL + " ";
  protected final String TEXT_4 = NL + "};";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    ComponentStructure component = (ComponentStructure) argument;
    Date date = new Date();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(date.toString());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(component.getOutputType());
    stringBuffer.append(TEXT_4);
    return stringBuffer.toString();
  }
}
