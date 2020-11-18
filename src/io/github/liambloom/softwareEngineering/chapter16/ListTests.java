package io.github.liambloom.softwareEngineering.chapter16;

import java.util.*;
import java.util.function.UnaryOperator;
import java.lang.reflect.*;

public class ListTests {
    private final Random r = new Random();

    protected List<Integer> subject;
    protected List<Integer> control = new ArrayList<>();

    public static void main(String[] args) {
        List<Integer> foo = new java.util.LinkedList<>();
        foo.add(10);
        System.out.println(foo.size());
        var bar = new ArrayList<>();
        bar.add(10);
        System.out.println(bar.equals(foo));
        System.out.println(bar);
        System.out.println(foo);
        new ListTests(new io.github.liambloom.softwareEngineering.chapter16.LinkedList<>());
    }

    public ListTests(List<Integer> subject) {
        this.subject = subject;
        
        /*var si = subject.iterator();
        var ci = control.iterator();
        while (ci.hasNext()) {
            var s1 = si.next();
            var c1 = ci.next();
            System.out.printf("%d %c= %d%n", si.next(), s1 == c1 ? '=' : '!', ci.next());
        }*/

        long seed = r.nextLong();
        r.setSeed(seed);
        System.out.println("Test seed: " + seed);

        System.out.println(control.equals(subject));
        //System.out.println(control.equals(new LinkedList<>()));

        for (Method method : List.class.getDeclaredMethods()) {
            if (control.size() == 0) {
                // Add test data
                int testLength = r.nextInt(10000) + 1;
                for (int j = 0; j < testLength; j++)
                    subject.add(r.nextInt());
                control.addAll(subject);
            }
            
            if (control.size() == 0)
                throw new Error("Either size() or addAll() don't work");

            Class<?>[] params = method.getParameterTypes();
            Object[] args = new Object[params.length];
            for (int i = 0; i < params.length; i++) {
                // int, E, Collection<?>, Collection<? extends E>, Object, Comparator<? super E>, UnaryOperator<E>, T[], 
                Class<?> type = params[i];
                if (type == int.class)
                    args[i] = r.nextInt(control.size());
                else if (type == Collection.class) {
                    int capacity = r.nextInt(control.size());
                    List<Integer> temp = new ArrayList<>(capacity);
                    for (int j = 0; j < capacity; j++)  
                        temp.add(Integer.valueOf(r.nextInt()));
                    args[i] = temp;
                }
                else if (type == Integer.class || type == Object.class)
                    args[i] = Integer.valueOf(r.nextInt());
                else if (type == Comparator.class)
                    args[i] = Comparator.naturalOrder();
                else if (type == UnaryOperator.class)
                    args[i] = (UnaryOperator<Integer>)((n) -> n + 1);
                else if (type == Object[].class)
                    args[i] = new Integer[r.nextInt(10000)];
            }

            boolean ok;
            String errMsg = null;
            try {
                Object res1 = method.invoke(control, args);
                
                try {
                    if (res1 instanceof Iterator) {
                        @SuppressWarnings("unchecked")
                        Iterator<Integer> iter1 = (Iterator<Integer>) res1;
                        @SuppressWarnings("unchecked")
                        Iterator<Integer> iter2 = (Iterator<Integer>) method.invoke(subject, args);
                        ok = true;
                        while (iter1.hasNext() && iter2.hasNext()) {
                            if (!iter1.next().equals(iter2.next())) {
                                ok = false;
                                errMsg = "Iterators contained differing values";
                                break;
                            }
                        }
                        if (ok == true) {
                            ok = iter1.hasNext() == iter2.hasNext();
                            errMsg = "Iterators were different lengths";
                        }
                    }
                    else if (res1 instanceof Spliterator) {
                        // I'm not doing this one
                        ok = true;
                    }
                    else {
                        Object res2 = method.invoke(subject, args);
                        if (res1 == null) 
                            ok = res2 == null;
                        else if (method.getReturnType().isArray())
                            ok = Arrays.equals((Object[]) res1, (Object[]) res2);
                        else
                            ok = res1.equals(res2);
                        errMsg = String.format("Returned different values. control: %s; subject: %s", res1 == null ? "null" : res1.toString(), res2 == null ? "null" : res1.toString());
                    }
                } catch (Exception e) {
                    ok = false;
                    errMsg = "Only second threw and error. Err: " + e;
                }
            }
            catch (Exception e1) {
                try {
                    method.invoke(control, args);
                    ok = false;
                }
                catch (Exception e2) {
                    ok = e1.getClass() == e2.getClass();
                }
            }

            System.out.print(method + "..");
            if (ok && control.equals(subject))
                System.out.println("\u001b[32mok\u001b[0m");
            else {
                System.out.println("\u001b[31mfail\u001b[0m");
                System.out.printf("returned same: %b, post-values same: %b%n", ok, control.equals(subject));
                System.out.println(errMsg);
                //System.out.printf("control: %s; subject: %s%n", control, subject);
                return;
            }
        }
    }
}