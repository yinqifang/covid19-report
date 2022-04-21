package com.yinqifang.covid19report.common;

import org.springframework.util.StringUtils;

import java.text.MessageFormat;

/**
 * @author Chris Yin
 * @date 2022-04-21
 */
public class AdHoc {

    private void process() {
        String ss =
            "时间\t新增本土\t新增无症状\t社会面本土\t社会面无症状\t松江\t普陀\t浦东\n" + "04-20\t2634\t15861\t199\t242\t165+511\t49+506\t756+3709\n"
                + "04-19\t2494\t16407\t161\t229\t58+267\t100+428\t720+4926\n"
                + "04-18\t3084\t17332\t216\t334\t191+571\t21+350\t1075+7756\n"
                + "04-17\t2417\t19831\t155\t406\t153+752\t330+895\t521+7219\n"
                + "04-16\t3238\t21582\t307\t415\t87+323\t145+1186\t1002+9789\n"
                + "04-15\t3590\t19923\t357\t495\t198+575\t66+360\t1142+9140\n"
                + "04-14\t3200\t19872\t346\t378\t178+583\t45+219\t1252+10404\n"
                + "04-13\t2573\t25146\t259\t598\t131+526\t45+430\t1139+13888\n"
                + "04-12\t1189\t25141\t299\t641\t77+635\t50+1120\t192+10857\n"
                + "04-11\t994\t22348\t282\t504\t21+670\t37+1841\t493+7813\n"
                + "04-10\t914\t25173\t303\t943\t291+1534\t7+994\t212+6520\n"
                + "04-09\t1006\t23937\t587\t525\t10+490\t24+629\t517+10613\n"
                + "04-08\t1015\t22609\t294\t756\t108+663\t21+1073\t348+6938\n"
                + "04-07\t824\t20398\t380\t600\t107+644\t11+946\t307+8743\n"
                + "04-06\t322\t19660\t295\t633\t2+779\t27+1006\t151+8306\n"
                + "04-05\t311\t16766\t267\t510\t14+782\t7+476\t162+7983\n"
                + "04-04\t268\t13086\t250\t494\t6+553\t4+250\t205+6866\n"
                + "04-03\t425\t8581\t347\t661\t6+257\t21+300\t140+3514\n"
                + "04-02\t438\t7788\t349\t1015\t8+559\t39+349\t108+1930\n"
                + "04-01\t260\t6051\t250\t649\t19+474\t13+232\t79+2505\n"
                + "03-31\t358\t4144\t330\t434\t5+179\t6+9\t180+2227\n"
                + "03-30\t355\t5298\t329\t821\t8+238\t6+140\t249+1957\n"
                + "03-29\t326\t5656\t291\t525\t15+213\t6+107\t169+2014\n"
                + "03-28\t96\t4381\t68\t557\t4+90\t3+78\t39+2467";

        String[] lines = ss.split("\n");
        int i = 0;
        for (String line : lines) {
            if (i++ == 0) {
                continue;
            }
            //            System.out.println("XXXX " + line);
            String[] cols = line.split("\t");
            String date = cols[0];
            date = "2022-" + date;

            printDistrict(cols[5], "松江", date);
            printDistrict(cols[6], "普陀", date);
            printDistrict(cols[7], "浦东", date);

        }

    }

    private void printDistrict(String districtData, String districtName, String date) {
        String[] cols = StringUtils.split(districtData, "+");
        String confirmed = cols[0];
        String asymptomatic = cols[1];
        String sql =
            "insert into t_covid19_daily_increment(report_date, area_name, area_type, confirmed, asymptomatic) values(''{0}'', ''{1}'', "
                + "4,{2}, {3});";
        String strSql = MessageFormat.format(sql, date, districtName, confirmed, asymptomatic);
        System.out.println(strSql);
    }

    public static void main(String[] args) {
        new AdHoc().process();

    }
}
