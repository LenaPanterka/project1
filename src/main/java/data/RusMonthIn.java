package data;

public enum RusMonthIn {
    январе(0,"январе"),
    феврале(1,"феврале"),
    марте(2,"марте"),
    апреле(3,"апреле"),
    мае(4,"мае"),
    июне(5,"июне"),
    июле(6,"июле"),
    августе(7,"августе"),
    сентябре(8,"сентябре"),
    октябре(9,"октябре"),
    ноябре(10,"ноябре"),
    декабре(11,"декабре");

    private String month;
    private int order;
    RusMonthIn(int order, String month){
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
