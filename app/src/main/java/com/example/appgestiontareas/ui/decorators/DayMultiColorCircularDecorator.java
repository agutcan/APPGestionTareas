package com.example.appgestiontareas.ui.decorators;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

public class DayMultiColorCircularDecorator implements DayViewDecorator {

    private final CalendarDay day;
    private final int color1;
    private final float percent1;
    private final Integer color2;
    private final Float percent2;

    // Constructor con 1 color
    public DayMultiColorCircularDecorator(CalendarDay day, int color1, float percent1) {
        this.day = day;
        this.color1 = color1;
        this.percent1 = percent1;
        this.color2 = null;
        this.percent2 = null;
    }

    // Constructor con 2 colores
    public DayMultiColorCircularDecorator(CalendarDay day,
                                          int color1, float percent1,
                                          int color2, float percent2) {
        this.day = day;
        this.color1 = color1;
        this.percent1 = percent1;
        this.color2 = color2;
        this.percent2 = percent2;
    }

    @Override
    public boolean shouldDecorate(CalendarDay calendarDay) {
        return calendarDay.equals(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        if (color2 == null) {
            view.addSpan(new MultiColorCircularSpan(color1, percent1));
        } else {
            view.addSpan(new MultiColorCircularSpan(color1, percent1, color2, percent2));
        }
    }
}