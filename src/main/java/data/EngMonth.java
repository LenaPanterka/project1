package data;

public enum EngMonth {
    JANUARY(0,"Jan"),
    FEBRUARY(1,"Feb"),
    MARCH(2,"Mar"),
    APRIL(3,"Apr"),
    MAY(4,"May"),
    JUNE(5,"Jun"),
    JULY(6,"Jul"),
    AUGUST(7,"Aug"),
    SEPTEMBER(8,"Sep"),
    OCTOBER(9,"Oct"),
    NOVEMBER(10,"Nov"),
    DECEMBER(11,"Dec");

    private String month;
    private int order;
    EngMonth(int order, String month){
        this.month = month;
        this.order = order;
    }

    public String getMonth(){
        return month;
    }

    public int getOrder(){
        return order;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
