package com.example.task04;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Task04MainTest {

    private void check(String input, String output) throws IOException {
        InputStream sin = System.in;
        PrintStream sout = System.out;
        try {
            ByteArrayOutputStream resultStream = new ByteArrayOutputStream();

            System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
            System.setOut(new PrintStream(resultStream, true, StandardCharsets.UTF_8.name()));

            Task04Main.main(new String[]{});

            System.out.flush();
            String result = new String(resultStream.toByteArray(), StandardCharsets.UTF_8);
            Assertions.assertThat(result.trim()).as("Input: %s", input).isEqualTo(output.trim());

        } finally {
            System.setIn(sin);
            System.setOut(sout);
        }
    }

    @Test
    public void testExample1() throws IOException {

        check("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sodales consectetur purus at faucibus. Donec mi quam, tempor vel ipsum non, faucibus suscipit massa. Morbi lacinia velit blandit tincidunt efficitur. Vestibulum eget metus imperdiet sapien laoreet faucibus. Nunc eget vehicula mauris, ac auctor lorem. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer vel odio nec mi tempor dignissim.",
                ("consectetur\n" +
                        "faucibus\n" +
                        "ipsum\n" +
                        "lorem\n" +
                        "adipiscing\n" +
                        "amet\n" +
                        "dolor\n" +
                        "eget\n" +
                        "elit\n" +
                        "mi"));

    }

    @Test
    public void testExample2() throws IOException {

        check("Мама мыла-мыла-мыла раму!", "мыла\nмама\nраму");

    }

    @Test
    public void testEmpty() throws IOException {

        check("", "");

    }

    @Test
    public void testMarshak() throws IOException {

        check(("Вот дом,\n" +
                        "Который построил Джек.\n" +
                        "\n" +
                        "А это пшеница,\n" +
                        "Которая в тёмном чулане хранится\n" +
                        "В доме,\n" +
                        "Который построил Джек.\n" +
                        "\n" +
                        "А это весёлая птица-синица,\n" +
                        "Которая часто ворует пшеницу,\n" +
                        "Которая в тёмном чулане хранится\n" +
                        "В доме,\n" +
                        "Который построил Джек.\n" +
                        "\n" +
                        "Вот кот,\n" +
                        "Который пугает и ловит синицу,\n" +
                        "Которая часто ворует пшеницу,\n" +
                        "Которая в тёмном чулане хранится\n" +
                        "В доме,\n" +
                        "Который построил Джек.\n" +
                        "\n" +
                        "Вот пёс без хвоста,\n" +
                        "Который за шиворот треплет кота,\n" +
                        "Который пугает и ловит синицу,\n" +
                        "Которая часто ворует пшеницу,\n" +
                        "Которая в тёмном чулане хранится\n" +
                        "В доме,\n" +
                        "Который построил Джек.\n" +
                        "\n" +
                        "А это корова безрогая,\n" +
                        "Лягнувшая старого пса без хвоста,\n" +
                        "Который за шиворот треплет кота,\n" +
                        "Который пугает и ловит синицу,\n" +
                        "Которая часто ворует пшеницу,\n" +
                        "Которая в тёмном чулане хранится\n" +
                        "В доме,\n" +
                        "Который построил Джек.\n" +
                        "\n" +
                        "А это старушка, седая и строгая,\n" +
                        "Которая доит корову безрогую,\n" +
                        "Лягнувшую старого пса без хвоста,\n" +
                        "Который за шиворот треплет кота,\n" +
                        "Который пугает и ловит синицу,\n" +
                        "Которая часто ворует пшеницу,\n" +
                        "Которая в тёмном чулане хранится\n" +
                        "В доме,\n" +
                        "Который построил Джек.\n" +
                        "\n" +
                        "А это ленивый и толстый пастух,\n" +
                        "Который бранится с коровницей строгою,\n" +
                        "Которая доит корову безрогую,\n" +
                        "Лягнувшую старого пса без хвоста,\n" +
                        "Который за шиворот треплет кота,\n" +
                        "Который пугает и ловит синицу,\n" +
                        "Которая часто ворует пшеницу,\n" +
                        "Которая в тёмном чулане хранится\n" +
                        "В доме,\n" +
                        "Который построил Джек.\n" +
                        "\n" +
                        "Вот два петуха,\n" +
                        "Которые будят того пастуха,\n" +
                        "Который бранится с коровницей строгою,\n" +
                        "Которая доит корову безрогую,\n" +
                        "Лягнувшую старого пса без хвоста,\n" +
                        "Который за шиворот треплет кота,\n" +
                        "Который пугает и ловит синицу,\n" +
                        "Которая часто ворует пшеницу,\n" +
                        "Которая в тёмном чулане хранится\n" +
                        "В доме,\n" +
                        "Который построил Джек."
                ),
                ("который\n" +
                        "которая\n" +
                        "в\n" +
                        "джек\n" +
                        "построил\n" +
                        "доме\n" +
                        "и\n" +
                        "тёмном\n" +
                        "хранится\n" +
                        "чулане"
                ));

    }

}
