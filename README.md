# Demo

```java
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.stream.Collectors;

import static com.michaelsnowden.errors.Errors.unchecked;

class Example {
    public static void main(String[] args) {
        System.out.println(Arrays.stream(new File(System.getProperty("user.home"), ".ssh").listFiles(File::isFile))
                .flatMap(unchecked(file -> new BufferedReader(new FileReader(file)).lines()))
                .sorted((o1, o2) -> Integer.valueOf(o2.length()).compareTo(o1.length()))
                .limit(10)
                .collect(Collectors.joining(System.lineSeparator())));
    }
}
```