package com.india.elects.utils;

import java.io.StringWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Text;

/**
 * @author id830602
 *
 */
public class StringUtil {
    
    private static final Logger LOG = LogManager.getLogger(StringUtil.class);

    /**
     * @param s
     * @return
     */
    public static boolean isEmpty(String s) {
        return s == null || "".equals(s.trim());
    }

    /**
     * @param s
     * @return
     */
    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

    /**
     * @param b
     * @return
     */
    public static String toString(boolean b) {

        if (b) {
            return "Y";
        } else {
            return "N";
        }
    }

    /**
     * @param i
     * @return
     */
    public static String toString(Integer i) {

        if (i == null) {
            return "";
        } else {
            return i.toString();
        }
    }

    /**
     * @param target
     * @return
     */
    public static String escapeXml(String target) {

        if (target == null) {
            return null;
        }

        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Text text = document.createTextNode(target);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(text);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.transform(source, result);
            return writer.toString();
        } catch (Exception e) {
            LOG.error("Error while escaping : ",e);
            return "Error while escaping : " + e.getMessage();
        }

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        String s = "ds>dd";
        
        LOG.info("Escaped  : " + escapeXml(s));
    }

    /**
     * This method transforms commaseperatedvalue string csvString to a Set
     * 
     * @param csvString
     * @return Set
     */
    /**
     * @param csvString
     * @return
     */
    public static Set csvStrToSet(String csvString) {
        Set defaults = new HashSet();
        if (csvString != null) {
            StringTokenizer strtok = new StringTokenizer(csvString, ",");
            while (strtok.hasMoreElements()) {
                String oneitem = strtok.nextToken();
                defaults.add(oneitem);
            }
        }
        return defaults;
    }

    /**
     * @param str
     * @return
     */
    public static String removeLinefeed(String str) {

        if (str == null) {
            return null;
        } else {
            return str.replaceAll("(\r\n|\n)", " ").trim();
        }
    }

    /**
     * @param str
     * @return
     */
    public static boolean isAlphanumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < 0x30 || (c >= 0x3a && c <= 0x40) || (c > 0x5a && c <= 0x60) || c > 0x7a){
                return false;
            }
        }
        return true;
    }

    /**
     * @param str
     * @return boolean
     * isAllCaps
     */
    public boolean isAllCaps(String str) {
        for (int i = str.length() - 1; i >= 0; i--) {
            if (!Character.isUpperCase(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param arr
     * @return String
     * print
     */
    public static String print(String[] arr) {
        StringBuilder bldr = new StringBuilder("{");
        for (String str : arr) {
            bldr.append("[");
            bldr.append(str);
            bldr.append("]");
        }
        bldr.append("}");

        return bldr.toString();
    }

}
