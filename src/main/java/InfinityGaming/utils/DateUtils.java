package InfinityGaming.utils;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
//Modify LocalDate... into Date
@Service
public class DateUtils {

    public String getDateFormat(LocalDate localDate, String format) {
        return getDateFormat(localDate.atTime(0, 0), format);
    }

    public String getDateFormat(LocalDateTime localDate, String format) {
        Date date = Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
        return new SimpleDateFormat(format).format(date);
    }
}
