package edu.incense.manager.java.emitters;

import java.util.*;
import edu.incense.manager.java.model.*;

public class DataEmitter
{
  protected static String nl;
  public static synchronized DataEmitter create(String lineSeparator)
  {
    nl = lineSeparator;
    DataEmitter result = new DataEmitter();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = " /**" + NL + "  * New Data structure ";
  protected final String TEXT_2 = " generated automatically ";
  protected final String TEXT_3 = NL + "  */" + NL + "" + NL + "package edu.incense.android.datatask.data;" + NL + "" + NL + "import edu.incense.android.datatask.data.*;" + NL + " " + NL + "public class ";
  protected final String TEXT_4 = " extends Data {" + NL + "\t/*Literals*/" + NL + "\t";
  protected final String TEXT_5 = NL + "\tpublic final static ";
  protected final String TEXT_6 = " ";
  protected final String TEXT_7 = " = ";
  protected final String TEXT_8 = ";" + NL + "\t";
  protected final String TEXT_9 = NL + "\t/*Outputs*/" + NL + "\t";
  protected final String TEXT_10 = NL + "\tprivate\t";
  protected final String TEXT_11 = " ";
  protected final String TEXT_12 = ";" + NL + "\t";
  protected final String TEXT_13 = NL + "\t/*Constructors*/" + NL + "\t";
  protected final String TEXT_14 = "private ";
  protected final String TEXT_15 = " (DataType type," + NL + "\t\t";
  protected final String TEXT_16 = " ";
  protected final String TEXT_17 = "," + NL + "\t\t";
  protected final String TEXT_18 = NL + "\t\t";
  protected final String TEXT_19 = " ";
  protected final String TEXT_20 = ",";
  protected final String TEXT_21 = NL + "\t\t";
  protected final String TEXT_22 = " ";
  protected final String TEXT_23 = ") {" + NL + "\t\t" + NL + "\t\tsuper(type);" + NL + "\t\t";
  protected final String TEXT_24 = NL + "\t\tthis.";
  protected final String TEXT_25 = " = ";
  protected final String TEXT_26 = ";";
  protected final String TEXT_27 = NL + "\t}" + NL + "\t\t" + NL + "\tpublic ";
  protected final String TEXT_28 = " (";
  protected final String TEXT_29 = " ";
  protected final String TEXT_30 = "," + NL + "\t\t";
  protected final String TEXT_31 = NL + "\t\t";
  protected final String TEXT_32 = " ";
  protected final String TEXT_33 = ",";
  protected final String TEXT_34 = NL + "\t\t";
  protected final String TEXT_35 = " ";
  protected final String TEXT_36 = ") {" + NL + "\t\t" + NL + "\t\tthis (DataType.";
  protected final String TEXT_37 = ", ";
  protected final String TEXT_38 = " ";
  protected final String TEXT_39 = ", ";
  protected final String TEXT_40 = " ";
  protected final String TEXT_41 = ", ";
  protected final String TEXT_42 = ");" + NL + "\t}" + NL + "\t";
  protected final String TEXT_43 = NL + "\tprivate ";
  protected final String TEXT_44 = " (DataType type){" + NL + "\t\tsuper(type);" + NL + "\t}" + NL + "\t\t" + NL + "\tpublic ";
  protected final String TEXT_45 = " (){" + NL + "\t\tthis (DataType.";
  protected final String TEXT_46 = ");" + NL + "\t}" + NL + "\t";
  protected final String TEXT_47 = NL + "\t";
  protected final String TEXT_48 = NL + "\tpublic ";
  protected final String TEXT_49 = " get";
  protected final String TEXT_50 = "(){" + NL + "\t\treturn this.";
  protected final String TEXT_51 = NL + "\t}" + NL + "\t" + NL + "\tpublic void set";
  protected final String TEXT_52 = "(";
  protected final String TEXT_53 = " ";
  protected final String TEXT_54 = "){" + NL + "\t\tthis.";
  protected final String TEXT_55 = " = ";
  protected final String TEXT_56 = ";" + NL + "\t}" + NL + "\t";
  protected final String TEXT_57 = NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    ComponentStructure component = (ComponentStructure) argument;
    byte i = 0;
    Date date = new Date();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(component.getOutputName());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(date.toString());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(component.getOutputName());
    stringBuffer.append(TEXT_4);
    for (i = 0; i<component.getDataLiterals().length; i++) {
    stringBuffer.append(TEXT_5);
    stringBuffer.append(component.getDataLiterals()[i][0]);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(component.getDataLiterals()[i][1]);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(component.getDataLiterals()[i][2]);
    stringBuffer.append(TEXT_8);
    }
    stringBuffer.append(TEXT_9);
    for (i = 0; i<component.getOutputs().length; i++) {
    stringBuffer.append(TEXT_10);
    stringBuffer.append(component.getOutputs()[i][0]);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(component.getOutputs()[i][1]);
    stringBuffer.append(TEXT_12);
    }
    stringBuffer.append(TEXT_13);
    if (component.getOutputs().length > 0){
    stringBuffer.append(TEXT_14);
    stringBuffer.append(component.getOutputName());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(component.getOutputs()[0][0]);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(component.getOutputs()[0][1]);
    if (component.getOutputs().length > 1){
    stringBuffer.append(TEXT_17);
    for (i = 1; i<component.getOutputs().length - 1; i++) {
    stringBuffer.append(TEXT_18);
    stringBuffer.append(component.getOutputs()[i][0]);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(component.getOutputs()[i][1]);
    stringBuffer.append(TEXT_20);
    }
    stringBuffer.append(TEXT_21);
    stringBuffer.append(component.getOutputs()[i][0]);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(component.getOutputs()[i][1]);
    }
    stringBuffer.append(TEXT_23);
    for (i = 0; i<component.getOutputs().length; i++) {
    stringBuffer.append(TEXT_24);
    stringBuffer.append(component.getOutputs()[i][1]);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(component.getOutputs()[i][1]);
    stringBuffer.append(TEXT_26);
    }
    stringBuffer.append(TEXT_27);
    stringBuffer.append(component.getOutputName());
    stringBuffer.append(TEXT_28);
    stringBuffer.append(component.getOutputs()[0][0]);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(component.getOutputs()[0][1]);
    if (component.getOutputs().length > 1){
    stringBuffer.append(TEXT_30);
    for (i = 1; i<component.getOutputs().length - 1; i++) {
    stringBuffer.append(TEXT_31);
    stringBuffer.append(component.getOutputs()[i][0]);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(component.getOutputs()[i][1]);
    stringBuffer.append(TEXT_33);
    }
    stringBuffer.append(TEXT_34);
    stringBuffer.append(component.getOutputs()[i][0]);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(component.getOutputs()[i][1]);
    }
    stringBuffer.append(TEXT_36);
    stringBuffer.append(component.getOutputType());
    stringBuffer.append(TEXT_37);
    stringBuffer.append(component.getOutputs()[0][0]);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(component.getOutputs()[0][1]);
    if (component.getOutputs().length > 1){
    stringBuffer.append(TEXT_39);
    for (i = 1; i<component.getOutputs().length - 1; i++) {
    stringBuffer.append(component.getOutputs()[i][0]);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(component.getOutputs()[i][1]);
    stringBuffer.append(TEXT_41);
    }}
    stringBuffer.append(TEXT_42);
    } else {
    stringBuffer.append(TEXT_43);
    stringBuffer.append(component.getOutputName());
    stringBuffer.append(TEXT_44);
    stringBuffer.append(component.getOutputName());
    stringBuffer.append(TEXT_45);
    stringBuffer.append(component.getOutputType());
    stringBuffer.append(TEXT_46);
    }
    stringBuffer.append(TEXT_47);
    for (i = 0; i<component.getOutputs().length; i++) {
    stringBuffer.append(TEXT_48);
    stringBuffer.append(component.getOutputs()[i][0]);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(component.getOutputs()[i][1].substring(0, 1).toUpperCase().concat(component.getOutputs()[i][1].substring(1, component.getOutputs()[i][1].length())));
    stringBuffer.append(TEXT_50);
    stringBuffer.append(component.getOutputs()[i][1]);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(component.getOutputs()[i][1].substring(0, 1).toUpperCase().concat(component.getOutputs()[i][1].substring(1, component.getOutputs()[i][1].length())));
    stringBuffer.append(TEXT_52);
    stringBuffer.append(component.getOutputs()[i][0]);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(component.getOutputs()[i][1]);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(component.getOutputs()[i][1]);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(component.getOutputs()[i][1]);
    stringBuffer.append(TEXT_56);
    }
    stringBuffer.append(TEXT_57);
    return stringBuffer.toString();
  }
}
