## Why do I want this?

Let's say you had some method like this

```java
String getFileContents(String path) throws IOException {
    return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
}
```

It's really useful, but it throws an exception, so it doesn't play nice in Java 8.

```java
List<String> getAllFileContents(List<String> paths) {
    return paths.stream().map(path -> {
        try {
            return getFileContents(path);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }).collect(Collectors.toList());
}
```

Suddenly we've lost the awesome brevity Java 8 gave us. Using this library, you can rewrite the above like this:

```java
List<String> getAllFileContents(List<String> paths) {
    return paths.stream().map(rethrow(this::getFileContents)).collect(Collectors.toList());
}
```

And this works for almost all methods used in the streams API. Here's a `BiConsumer` example.

```java
void printItem(String name, int price) throws Exception {
    if (price < 0) {
        throw new IllegalArgumentException("Price cannot be negative");
    }
    System.out.println(name + " costs $" + price);
}

public void run() {
    Map<String, Integer> prices = new HashMap<>();
    prices.put("banana", 3);
    prices.put("apple", 4);
    prices.forEach(rethrow(this::printItem));
}
```

Essentially, the most common functional interfaces have a corresponding `rethrow` method for them. These functional interfaces include

- `Runnable`
- `Consumer<T>`
- `BiConsumer<T, U>`
- `Function<T, R>`
- `BiFunction<T, U, R>`

If you want to rethrow your own exception, you can always use something like

```java
rethrow(someExceptionalFunction, MyException::new)
```

You can also default methods to other methods if they fail using the `fallback` method.

```java
fallback(f1, fallback(f2, f3))
```

Where `f1` and `f2` implement one of the provided functional interfaces except they throw an exception. However, `f3` must not throw an exception. If you don't have a final fallback method to throw an exception, then this would work well with rethrow, i.e.

```java
fallback(f1, rethrow(f2))
```

Just take a look at the `Errors` class for any other questions. 

## Example

Check out https://github.com/MichaelSnowden/errors_demo

## pom.xml

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.MichaelSnowden</groupId>
        <artifactId>errors</artifactId>
        <version>master-SNAPSHOT or commit number</version>
    </dependency>
</dependencies>
```
