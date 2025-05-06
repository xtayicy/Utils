package harry.test;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class DurationTest {

	public static void main(String[] args) {
		int[] time1 = {7, 55}; // 第一个时间：08:30
        int[] time2 = {10, 05}; // 第二个时间：13:45
        
        int[] difference = calculateTimeDifference(time1, time2);
        System.out.printf("时间差: %d 小时 %d 分钟", difference[0], difference[1]);
	}
	
	public static int[] calculateTimeDifference(int[] time1, int[] time2) {
        // 将输入转换为LocalTime对象
        LocalTime t1 = LocalTime.of(time1[0], time1[1]);
        LocalTime t2 = LocalTime.of(time2[0], time2[1]);
        
        // 计算总分钟差
        long totalMinutes = ChronoUnit.MINUTES.between(t1, t2);
        
        // 处理反向时间差（如果t2在t1之前）
        if (totalMinutes < 0) {
            totalMinutes += 24 * 60; // 加上一天的分钟数
        }
        
        // 转换为小时和分钟
        int hours = (int) totalMinutes / 60;
        int minutes = (int) totalMinutes % 60;
        
        return new int[]{hours, minutes};
    }

}
