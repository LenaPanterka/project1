package data;

public enum CategoryData {
    PROGRAMMING("Программирование");

    private String name;
    CategoryData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
