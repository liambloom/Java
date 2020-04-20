package liam.chapter3;

/* This is an extremely inefficient way to write code, but it demonstrates
   what you can do with classes, and shows how they are pretty much
   a new type, which is awesome */
public class ClassExample {
    public static void main () {
        System.out.print(bar(new Foo()));
    }
    private static int bar (Foo x) {
        return x.bar().ten;
    }
}
class Foo {
    public Foo bar () {
        return this;
    }
    public final int ten = 10;
}