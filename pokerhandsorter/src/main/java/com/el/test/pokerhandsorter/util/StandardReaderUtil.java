package com.el.test.pokerhandsorter.util;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class StandardReaderUtil {

    public List<String> read(InputStream inputStream) {
        return internalRead(new Scanner(inputStream));
    }

    public List<String> read(Path file)  {
        try {
            return internalRead(new Scanner(file));
        } catch (IOException e) {
            throw new RuntimeException("Error in reading file!");
        }
    }

    public List<String> internalRead(Scanner scanner) {
        List<String> inputLines = new ArrayList<>();
        String currentLine;
        while (scanner.hasNextLine()  && !(currentLine = scanner.nextLine()).isBlank()) {
            inputLines.add(currentLine);
        }
        return inputLines;
    }
}
