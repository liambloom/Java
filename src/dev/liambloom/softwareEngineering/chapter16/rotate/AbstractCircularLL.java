package dev.liambloom.softwareEngineering.chapter16.rotate;

import dev.liambloom.softwareEngineering.chapter4.Ask;

public abstract class AbstractCircularLL<N extends AbstractCircularLL<N>.Node> {
    N head;
    N tail;
    int size;

    public static <N extends AbstractCircularLL<N>.Node> void main(AbstractCircularLL<N> list) {
        Ask.seperator = '?';
        //final AbstractCircularLL list = new CircularDoubleLL(Ask.forInt("How long is the list", 0, Integer.MAX_VALUE));
        System.out.println();
        System.out.println("The numbers are: " + list);
        char choice = 'a';
        do {
            if (choice == 'a') {
                System.out.println("(a)   Care to rotate");
                System.out.println("(b)   Quit");
            }
            Ask.seperator = ' ';
            switch (choice = Character.toLowerCase(Ask.forChar("  Enter a choice:"))) {
                case 'a':
                    Ask.seperator = '?';
                    list.rotate(Ask.forInt("What is the rotate value"));
                    System.out.println(" The list is now: " + list);
                    System.out.println();
                case 'b':
                    break;
                default:
                    System.out.println("Please enter either 'a' or 'b'.");
            }
        } while (choice != 'b');
    }

    public AbstractCircularLL(final int length) {
        for (int i = 1; i <= length; i++)
            add(i);
        close();
        size = length;
    }

    protected abstract class Node {
        protected Integer data;
        protected N next = null;

        public Node(Integer data) {
            this.data = data;
        }
    }

    /**
     * This adds a new node to the end of this list. It does NOT grantee that
     * it will remain circular, the new node's {@code next} is not set to 
     * {@code head}. To do this, call {@link #close()}. It also does not change
     * {@code size}. The size is set in the constructor, and this method should
     * not be called after the object is initialized.
     * 
     * @param data the data in the new node
     */
    protected abstract void add(Integer data);

    /**
     * This sets {@code tail.next = head}, and anything else necessary to make 
     * this linked list circular
     */
    protected void close() {
        tail.next = head;
    }

    public void rotate(int n) {
        n = n % size;
        if (n < 0)
            n += size * (n / size + 1);
        N current = tail;
        for (int i = 0; i < n; i++)
            current = current.next;
        tail = current;
        head = current.next;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        N current = head;
        for (int i = 0; i < size; i++, current = current.next) {
            if (current != head)
                builder.append(", ");
            builder.append(current.data);
        }
        return builder.toString();
    }
}
