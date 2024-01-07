package org.dili;

import java.lang.instrument.Instrumentation;

public class AgentTest {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("Premain Start:");
        ClassInfoTransformer transformer = new ClassInfoTransformer();
        inst.addTransformer(transformer);
    }
}
