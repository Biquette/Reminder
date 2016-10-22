package com.itametis.sandbox.drools;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.command.CommandFactory;
import org.drools.compiler.DroolsParserException;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;
import org.junit.Assert;
import org.junit.Test;

public class HelloWorldTest {

    @Test
    public void shouldFireHelloWorld() throws IOException, DroolsParserException {

        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

        String ruleFile = "helloworld.drl";
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(ruleFile);
        kbuilder.add(ResourceFactory.newInputStreamResource(resourceAsStream), ResourceType.DRL);
        Assert.assertFalse(kbuilder.hasErrors());
        if (kbuilder.hasErrors()) {
            System.out.println(kbuilder.getErrors());
        }
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

        StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();

        //The scoped global variable are thread safe ?
        List cmds = new ArrayList<>();
        ///cmds.add(CommandFactory.newSetGlobal("resultList", new ArrayList()));
        Message helloMessage = new Message();
        helloMessage.setType("Hello");
        cmds.add(CommandFactory.newInsert(helloMessage));
        Action action = new Action();
        cmds.add(CommandFactory.newInsert(action));

        ksession.execute(CommandFactory.newBatchExecution(cmds));
        //ksession.execute(helloMessage);

        // List result = (List) ksession.getGlobals().get("resultList");
        // System.out.println("list : " + result.toString());
        Assert.assertEquals("Action{message=Message{type=Hello, messageValue=0}}",action.toString());
    }

}
