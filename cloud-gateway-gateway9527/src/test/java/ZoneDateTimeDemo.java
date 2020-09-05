import java.time.ZonedDateTime;

/**
 * @Classname ZoneDateTimeDemo
 * @Description 时区demo类
 * @Date 2020/9/5 19:23
 * @Created by SGZ
 */
public class ZoneDateTimeDemo {
    public static void main(String[] args) {
        ZonedDateTime zoneDatetime = ZonedDateTime.now();
        System.out.println(zoneDatetime);
    }
}
