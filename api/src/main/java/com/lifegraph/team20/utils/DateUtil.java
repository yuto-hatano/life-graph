package com.lifegraph.team20.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.util.StringUtils;

public class DateUtil {

  private static final ZoneId ZONE_ID_UTC = ZoneId.of("UTC");
  private static final ZoneId ZONE_ID_JST = ZoneId.of("Asia/Tokyo");
  private static final String FORMAT_FOR_RESULT = "yyyy-MM-dd HH:mm:ss";
  private static final String FORMAT_FOR_PARAM = "yyyy-MM-dd";
  private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  public static String convertForQuery(String strTimestamp, boolean isTo) {
    Date date = toDate(strTimestamp, FORMAT_FOR_PARAM, ZONE_ID_JST);
    LocalDateTime jst = LocalDateTime.ofInstant(date.toInstant(), ZONE_ID_JST);
    if (isTo) {
      jst = jst.plusDays(1);
    }
    ZonedDateTime utc = jst.atZone(ZONE_ID_JST).withZoneSameInstant(ZONE_ID_UTC);
    return utc.format(dtf);
  }

  // convert utc to jst after select
  public static LocalDateTime toJSTLocalDateTime(String strTimestamp) {
    if (StringUtils.isEmpty(strTimestamp)) {
      return null;
    }
    Date formatDate = toDate(strTimestamp, FORMAT_FOR_RESULT, ZONE_ID_UTC);
    LocalDateTime def = LocalDateTime.ofInstant(formatDate.toInstant(), ZONE_ID_UTC);
    ZonedDateTime jst = def.atZone(ZONE_ID_UTC).withZoneSameInstant(ZONE_ID_JST);
    return jst.toOffsetDateTime().toLocalDateTime();
  }

  private static Date toDate(String strTimestamp, String format, ZoneId zondId) {
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    sdf.setTimeZone(TimeZone.getTimeZone(zondId));
    Date formatDate = null;
    try {
      formatDate = sdf.parse(strTimestamp);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return formatDate;
  }
}
