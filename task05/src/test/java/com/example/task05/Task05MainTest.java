package com.example.task05;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;

public class Task05MainTest {

    static ITests impl;

    /**
     * Тесты лежат в исходниках (TestsImpl.java) и компилируются прямо во время запуска,
     * потому что они обращаются к классам, которых в этом репозитории еще нет:
     * их предстоит написать в рамках задания.
     */
    @BeforeAll
    public static void before() throws Exception {
        File source = new File(Task05MainTest.class.getResource("/TestsImpl.java").toURI());
        File classes = Files.createTempDirectory("task05-tests").toFile();
        classes.deleteOnExit();

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            throw new IllegalStateException("Тесты нужно запускать на JDK, а не на JRE");
        }

        StringWriter compilerOutput = new StringWriter();
        boolean compiled;
        try (StandardJavaFileManager fileManager =
                     compiler.getStandardFileManager(null, null, StandardCharsets.UTF_8)) {
            compiled = compiler.getTask(
                    compilerOutput,
                    fileManager,
                    null,
                    Arrays.asList(
                            "-classpath", System.getProperty("java.class.path"),
                            "-d", classes.getAbsolutePath()
                    ),
                    null,
                    fileManager.getJavaFileObjects(source)
            ).call();
        }

        if (!compiled) {
            throw new AssertionError("Не удалось скомпилировать " + source.getName()
                    + ", реализуйте недостающие классы:\n" + compilerOutput);
        }

        try (URLClassLoader classLoader = new URLClassLoader(
                new URL[]{classes.toURI().toURL()}, Task05MainTest.class.getClassLoader())) {
            // проверки в TestsImpl написаны через оператор assert, без этого они молча не выполняются
            classLoader.setDefaultAssertionStatus(true);
            impl = (ITests) classLoader.loadClass("com.example.task05.TestsImpl")
                    .getDeclaredConstructor()
                    .newInstance();
        }
    }

    @Test
    public void testExample1() {
        impl.testExample();
    }

}
