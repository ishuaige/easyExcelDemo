package com.niuma.easyexceldemo.excel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author niumazlb
 * @create 2023-01-19 20:35
 */
@Data
public class ExcelUserData {

    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("上月积分")
    private Integer oldScore;
    @ExcelProperty("总积分")
    private Integer currentScore;
    @ExcelProperty("增长积分")
    private Integer growScore;

}
