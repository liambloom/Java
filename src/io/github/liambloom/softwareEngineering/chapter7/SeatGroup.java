package io.github.liambloom.softwareEngineering.chapter7;

public interface SeatGroup {
    public void add();
    public boolean isFull();
    public String getName();

    public static void main(SeatGroup firstClass, SeatGroup secondClass) {
        Ask.seperator = '?';
        while (!firstClass.isFull() || !secondClass.isFull()) {
            SeatGroup selected;
            SeatGroup backup;
            switch (Ask.forString("Would you like first or second class").strip()) {
                case "1":
                case "1st":
                case "first":
                    selected = firstClass;
                    backup = secondClass;
                    break;
                case "2":
                case "2nd":
                case "second":
                    selected = secondClass;
                    backup = firstClass;
                    break;
                default:
                    System.out.println("That's not a valid class");
                    continue;
            }
            if (!selected.isFull())
                selected.add();
            else if (!backup.isFull() && askBackup(selected.getName(), backup.getName()))
                backup.add();
        }
    }

    private static boolean askBackup(String failed, String instead) {
        failed = Character.toUpperCase(failed.charAt(0)) + failed.substring(1);
        switch (Ask.forString(failed + " class is unavailable, would you like " + instead + " class instead (y/n)")
                .strip()) {
            case "y":
            case "yes":
                return true;
            case "n":
            case "no":
                return false;
            default:
                System.out.println("That's not a valid answer");
                return askBackup(failed, instead);
        }
    }
}
