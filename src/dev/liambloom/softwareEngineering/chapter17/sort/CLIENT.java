package dev.liambloom.softwareEngineering.chapter17.sort;

import java.util.ArrayList;

public class CLIENT {
    public static void main(final String[] args) {
        final ArrayList<PersonNode> list = new ArrayList<>();

        list.add(new PersonNode("Marques", "Paul", "The-Great", "123-45-678"));
        list.add(new PersonNode("Kent", "Clark", "Kryptonite", "932-00-918"));
        list.add(new PersonNode("Luthor", "Lex", "King-of-Evil", "632-95-174"));
        list.add(new PersonNode("Lane", "Lois", "Lipstick", "390-29-4945"));
        list.add(new PersonNode("Bunny", "Bugs", "Hoppy", "383-92-484"));
        list.add(new PersonNode("Duck", "Daffy", "Dippy", "395-10-573"));
        list.add(new PersonNode("Flintstone", "Fred", "RockHead", "592-96-285"));
        list.add(new PersonNode("Ruble", "Barney", "ShortStone", "116-30-590"));
        list.add(new PersonNode("Flintstone", "Wilma", "Mary", "883-95-499"));
        list.add(new PersonNode("Ruble", "Betty", "Boop", "501-57-295"));
        list.add(new PersonNode("Man", "Pac", "Hungry", "492-95-100"));
        list.add(new PersonNode("Invader", "Space", "1980sVideoGame", "385-10-395"));
        list.add(new PersonNode("Bert", "Q", "-", "385-19-305"));

        new Tree(list, new NameComparator()).printInOrder();
        new Tree(list, new IdComparator()).printInOrder();
    }
}
