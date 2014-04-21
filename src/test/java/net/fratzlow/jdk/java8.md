Generics & Lambda
=================

- how to make use of "super" and "extends" in conjunction with functional interfaces
- Producer and Consumer examples
http://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html

- use method references with named methods as data
- try example of method reference with constructor + factory
    static method -> ContainingClass::staticMethodName
    instance method of a particular object -> ContainingObject::instanceMethodName
    instance method of an arbitrary object of a particular type	-> ContainingType::methodName
    Reference to a constructor	-> ClassName::new // this is cool!
http://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html

Streams
=======
A stream is a sequence of elements
Pipelines work on streams. pipeline starts with a source (stream), 0-n intermediate operations e.g.
The filter operation returns a new stream that contains elements that match its predicate
A terminal operation produces a non-stream result

double average = roster
    .stream()
    .filter(p -> p.getGender() == Person.Sex.MALE)
    .mapToInt(Person::getAge)
    .average()
    .getAsDouble();

terminal operations such as average that return one value by combining the contents of a stream. These operations are
called reduction operations
http://docs.oracle.com/javase/tutorial/collections/streams/index.html


Default methods on interfaces
=============================
- "default" keyword before method implementation on interface
- when extending interface with default method then you can
    Not mention default method, thus inherit default method.
    Redeclare default method, makes it abstract.
    Redefine default method, which overrides it.
http://docs.oracle.com/javase/tutorial/java/IandI/defaultmethods.html

static methods on interface
---------------------------
- could be passed as

Comparator
----------
myDeck.sort(
    Comparator.comparing(Card::getRank)
        .reversed()
        .thenComparing(Comparator.comparing(Card::getSuit)));