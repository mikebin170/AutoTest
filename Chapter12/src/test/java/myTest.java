import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class myTest {

    @Test
    public void longTermParkingWarningTest() {
//        String str = " <input type=\"hidden\" name=\"lt\" value=\"LT-10000-d762K69NRP9iLicidCBcxGdW0DRUCS-1\" />";
//        String rgex = "value=\"(.*?)\" />";
        String str = "<input type='hidden' name='user_token' value='efb23f389886717dc0a1d2c8341b4718' />";
        String rgex = "value='(.*?)' />";
        System.out.println(getSubUtil(str, rgex));

    }

    public static String getSubUtil(String soap, String rgex) {
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(soap);
        while (m.find()) {
            return m.group(1);

        }
        return "";
    }

}
