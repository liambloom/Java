package dev.liambloom.softwareEngineering.chapter16.uniSproutFamilyTree;

public final class RelativeListBuilder {
    private boolean isFirst = true;
    private StringBuilder builder = new StringBuilder();
    private String relationship;

    public RelativeListBuilder(String relationship) {
        this.relationship = relationship;
    }

    private boolean isFirst() {
        final boolean r = isFirst;
        isFirst = false;
        return r;
    }

    public void append(Node relative) {
        if (!isFirst())
            builder.append(", ");
        builder.append(relative.getName());
    }

    @Override
    public String toString() {
        return relationship + "s: " + (isFirst ? "None" : builder);
    }
}
