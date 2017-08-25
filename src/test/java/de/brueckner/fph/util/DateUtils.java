package de.brueckner.fph.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.testng.Assert;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtils {

    private static final String INPUT_FORMAT = "yyyy-MM-dd HH:mm:ssZ";
    private static final String SPACE_SEPARATOR = " ";
    private static final String DOUBLE_SPACE_SEPARATOR = "  ";
    private static final String DASH_SEPARATOR = "  -  ";

    // date format dd.MM.yyyy HH:mm:ss
    public static final String DEFAULT_LONG_DATE_FORMAT = DateFormatLongOption.OPTION_WITH_POINT.getOptionText()
            + SPACE_SEPARATOR
            + TimeFormatLongOption.OPTION_WITH_COLON.getOptionText();

    // date format dd.MM.yy HH:mm
    public static final String DEFAULT_SHORT_DATE_FORMAT = DateFormatShortOption.OPTION_WITH_POINT.getOptionText()
            + SPACE_SEPARATOR
            + TimeFormatShortOption.OPTION_WITH_COLON.getOptionText();

    // date format dd.MM.yyHH:mm
    public static final String DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR = DateFormatShortOption.OPTION_WITH_POINT.getOptionText()
            + TimeFormatShortOption.OPTION_WITH_COLON.getOptionText();

    // date format dd.MM.yy  HH:mm
    public static final String DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE = DateFormatShortOption.OPTION_WITH_POINT.getOptionText()
            + DOUBLE_SPACE_SEPARATOR
            + TimeFormatShortOption.OPTION_WITH_COLON.getOptionText();

    // date format dd.MM.yy HH:mm:ss
    public static final String DEFAULT_SHORT_DATE_LONG_TIME_FORMAT = DateFormatShortOption.OPTION_WITH_POINT.getOptionText()
            + SPACE_SEPARATOR
            + TimeFormatLongOption.OPTION_WITH_COLON.getOptionText();

    // date format dd.MM.yyyy  HH:mm
    public static final String DEFAULT_LONG_DATE_SHORT_TIME_FORMAT = DateFormatLongOption.OPTION_WITH_POINT.getOptionText()
            + DOUBLE_SPACE_SEPARATOR
            + TimeFormatShortOption.OPTION_WITH_COLON.getOptionText();

    // date format dd.MM.yy  HH:mm:ss
    public static final String DEFAULT_SHORT_DATE_LONG_TIME_FORMAT_DOUBLE_SPACE = DateFormatShortOption.OPTION_WITH_POINT.getOptionText()
            + DOUBLE_SPACE_SEPARATOR
            + TimeFormatLongOption.OPTION_WITH_COLON.getOptionText();

    private DateUtils() {
    }

    /**
     * Parse date into local time zone
     *
     * @param stringDate   date to parse in format: YYYY-MM-dd HH:mm:ss
     * @param outputFormat desired format for output
     * @return formated date in local time zone
     */
    public static String date(String stringDate,
                              String outputFormat) {

        return DateTimeFormat.forPattern(outputFormat)
                .print(date(stringDate));
    }

    public static DateTime date(String stringDate) {
        return DateTimeFormat.forPattern(INPUT_FORMAT)
                .parseDateTime(stringDate + "Z");

    }

    public static Date convertDate(String date,
                                   String format) {
        DateFormat df = new SimpleDateFormat(format);
        try {
            return df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void compareDatesBefore(String format,
                                          String dateBefore,
                                          String dateAfter) {
        int comparison = convertDate(dateBefore, format).compareTo(convertDate(dateAfter, format));
        if (comparison != -1) {
            Assert.fail();
        }
    }

    public static void compareDates(String firstDate,
                                    String lastDate) {
        int comparison = firstDate.compareTo(lastDate);
        if (comparison != 0) {
            Assert.fail();
        }
    }

    /**
     * @param dateFormatLong Date long format
     * @param timeFormatLong Time long format
     * @return
     */
    public static String getNewDateTimeFormatLong(DateFormatLongOption dateFormatLong, TimeFormatLongOption timeFormatLong) {
        return dateFormatLong.getOptionText() +
                SPACE_SEPARATOR +
                timeFormatLong.getOptionText();
    }

    /**
     * Return content specific format with space separator between date and time
     *
     * @param dateFormatShort Date short format
     * @param timeFormatShort Time short format
     * @return
     */
    public static String getNewDateTimeFormatShort(DateFormatShortOption dateFormatShort, TimeFormatShortOption timeFormatShort) {
        return dateFormatShort.getOptionText() +
                SPACE_SEPARATOR +
                timeFormatShort.getOptionText();
    }

    /**
     * Returns datepicker specific format with double space separator between date and time
     *
     * @param dateFormatLong Date long format
     * @param timeFormatLong Time long format
     * @return
     */
    public static String getNewDatepickerDateTimeFormatLong(DateFormatLongOption dateFormatLong, TimeFormatLongOption timeFormatLong) {
        return dateFormatLong.getOptionText() +
                DOUBLE_SPACE_SEPARATOR +
                timeFormatLong.getOptionText();
    }

    /**
     * Returns datepicker specific format with double space separator between date and time
     *
     * @param dateFormatShort Date short format
     * @param timeFormatShort Time short format
     * @return
     */
    public static String getNewDatepickerDateTimeFormatShort(DateFormatShortOption dateFormatShort, TimeFormatShortOption timeFormatShort) {
        return dateFormatShort.getOptionText() +
                DOUBLE_SPACE_SEPARATOR +
                timeFormatShort.getOptionText();
    }

    /**
     * Returns datepicker specific format with double space separator between date and time
     *
     * @param dateFormatLong  Date long format
     * @param timeFormatShort Time short format
     * @return
     */
    public static String getNewDatepickerDateLongTimeShort(DateFormatLongOption dateFormatLong, TimeFormatShortOption timeFormatShort) {
        return dateFormatLong.getOptionText() +
                DOUBLE_SPACE_SEPARATOR +
                timeFormatShort.getOptionText();
    }

    public static String getNewDatepickerDateShortTimeLong(DateFormatShortOption dateFormatLong, TimeFormatLongOption timeFormatShort) {
        return dateFormatLong.getOptionText() +
                DOUBLE_SPACE_SEPARATOR +
                timeFormatShort.getOptionText();
    }

    public static String getTreeDatepickerDateString(String startDate, String endDate, String formatDates) {
        return DateUtils.date(startDate, formatDates) + DASH_SEPARATOR + DateUtils.date(endDate, formatDates);
    }
}