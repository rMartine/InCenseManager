package edu.incense.manager.java.emitters;

import java.util.*;
import edu.incense.manager.java.model.*;

public class DataComponentEmitter
{
  protected static String nl;
  public static synchronized DataComponentEmitter create(String lineSeparator)
  {
    nl = lineSeparator;
    DataComponentEmitter result = new DataComponentEmitter();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = " /**" + NL + "  * New Component ";
  protected final String TEXT_2 = " generated automatically ";
  protected final String TEXT_3 = NL + "  */" + NL + "" + NL + "package edu.incense.android.datatask.filter;" + NL + "" + NL + "import edu.incense.android.datatask.data.*;" + NL + " " + NL + "public class ";
  protected final String TEXT_4 = " extends DataFilter {" + NL + "\t/*Section to declare configuration variables*/" + NL + "\t";
  protected final String TEXT_5 = "private ";
  protected final String TEXT_6 = " ";
  protected final String TEXT_7 = ";";
  protected final String TEXT_8 = NL + "\t/*Section to declare  and initialize operation variables*/" + NL + "\t";
  protected final String TEXT_9 = "private ";
  protected final String TEXT_10 = " ";
  protected final String TEXT_11 = " = ";
  protected final String TEXT_12 = ";";
  protected final String TEXT_13 = NL + "\t" + NL + "\t";
  protected final String TEXT_14 = "public ";
  protected final String TEXT_15 = " (";
  protected final String TEXT_16 = " ";
  protected final String TEXT_17 = ",";
  protected final String TEXT_18 = " ";
  protected final String TEXT_19 = ",";
  protected final String TEXT_20 = " ";
  protected final String TEXT_21 = ") {" + NL + "\t\tsuper();" + NL + "\t\tsetFilterName(this.getClass().getName());" + NL + "\t\t";
  protected final String TEXT_22 = NL + "\t\tthis.";
  protected final String TEXT_23 = " = ";
  protected final String TEXT_24 = ";" + NL + "\t\t";
  protected final String TEXT_25 = NL + "\t}" + NL + "\t";
  protected final String TEXT_26 = "public ";
  protected final String TEXT_27 = "() {" + NL + "\t\tsuper();" + NL + "\t\tsetFilterName(this.getClass().getName());" + NL + "\t}";
  protected final String TEXT_28 = NL + "\t" + NL + "\t@Override" + NL + "\tpublic void start(){" + NL + "\t\tsuper.start();" + NL + "\t}" + NL + "\t" + NL + "\t@Override" + NL + "\tprotected void computeSingleData(Data data) {" + NL + "        Data newData = processData(data);" + NL + "        pushToOutputs(newData);" + NL + "    }" + NL + "    ";
  protected final String TEXT_29 = NL + "    ";
  protected final String TEXT_30 = NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    ComponentStructure component = (ComponentStructure) argument;
    byte i = 0;
    Date date = new Date();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(component.getComponentName());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(date.toString());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(component.getComponentName());
    stringBuffer.append(TEXT_4);
    for (i = 0; i<component.getConfigurationVariables().length; i++) {
    stringBuffer.append(TEXT_5);
    stringBuffer.append(component.getConfigurationVariables()[i][0]);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(component.getConfigurationVariables()[i][1]);
    stringBuffer.append(TEXT_7);
    }
    stringBuffer.append(TEXT_8);
    for (i = 0; i<component.getOperationVariables().length; i++) {
    stringBuffer.append(TEXT_9);
    stringBuffer.append(component.getOperationVariables()[i][0]);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(component.getOperationVariables()[i][1]);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(component.getOperationVariables()[i][2]);
    stringBuffer.append(TEXT_12);
    }
    stringBuffer.append(TEXT_13);
    if (component.getConfigurationVariables().length > 0){
    stringBuffer.append(TEXT_14);
    stringBuffer.append(component.getComponentName());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(component.getConfigurationVariables()[0][0]);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(component.getConfigurationVariables()[0][1]);
    if (component.getConfigurationVariables().length > 1){
    stringBuffer.append(TEXT_17);
    for (i = 1; i<component.getConfigurationVariables().length - 1; i++) {
    stringBuffer.append(component.getConfigurationVariables()[i][0]);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(component.getConfigurationVariables()[i][1]);
    stringBuffer.append(TEXT_19);
    }
    stringBuffer.append(component.getConfigurationVariables()[i][0]);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(component.getConfigurationVariables()[i][1]);
    }
    stringBuffer.append(TEXT_21);
    for (i = 0; i<component.getConfigurationVariables().length; i++) {
    stringBuffer.append(TEXT_22);
    stringBuffer.append(component.getConfigurationVariables()[i][1]);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(component.getConfigurationVariables()[i][1]);
    stringBuffer.append(TEXT_24);
    }
    stringBuffer.append(TEXT_25);
    } else {
    stringBuffer.append(TEXT_26);
    stringBuffer.append(component.getComponentName());
    stringBuffer.append(TEXT_27);
    }
    stringBuffer.append(TEXT_28);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(component.getProcessDataCode());
    stringBuffer.append(TEXT_30);
    return stringBuffer.toString();
  }
}
