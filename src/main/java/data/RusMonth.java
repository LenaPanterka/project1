package data;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public enum RusMonth {
    января(0, Month.JANUARY.getDisplayName(TextStyle.FULL , new Locale("ru"))),
    февраля(1,Month.FEBRUARY.getDisplayName(TextStyle.FULL , new Locale("ru"))),
    марта(2,Month.MARCH.getDisplayName(TextStyle.FULL , new Locale("ru"))),
    апреля(3,Month.APRIL.getDisplayName(TextStyle.FULL , new Locale("ru"))),
    мая(4,Month.MAY.getDisplayName(TextStyle.FULL , new Locale("ru"))),
    июня(5,Month.JUNE.getDisplayName(TextStyle.FULL , new Locale("ru"))),
    июля(6,Month.JULY.getDisplayName(TextStyle.FULL , new Locale("ru"))),
    августа(7,Month.AUGUST.getDisplayName(TextStyle.FULL , new Locale("ru"))),
    сентября(8,Month.SEPTEMBER.getDisplayName(TextStyle.FULL , new Locale("ru"))),
    октября(9,Month.OCTOBER.getDisplayName(TextStyle.FULL , new Locale("ru"))),
    ноября(10,Month.NOVEMBER.getDisplayName(TextStyle.FULL , new Locale("ru"))),
    декабря(11,Month.DECEMBER.getDisplayName(TextStyle.FULL , new Locale("ru")));

    private String month;
    private int order;
    RusMonth(int order, String month){
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
