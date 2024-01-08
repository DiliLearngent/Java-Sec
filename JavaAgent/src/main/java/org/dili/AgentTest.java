package org.dili;

import java.lang.instrument.Instrumentation;

public class AgentTest {
    // Test Main in JavaBase.src.java.org.dili.javaagent.Main
    // 此模块打包成jar包即可，在运行上述类时加入-javaagent:jar包即可
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("Premain Start:");
        ClassInfoTransformer transformer = new ClassInfoTransformer();
        inst.addTransformer(transformer);
    }
}
