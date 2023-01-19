package com.niuma.easyexceldemo;

import com.alibaba.excel.EasyExcel;
import com.niuma.easyexceldemo.excel.listeners.UserDataListener;
import com.niuma.easyexceldemo.excel.model.ExcelUserData;
import com.niuma.easyexceldemo.model.domain.User;
import com.niuma.easyexceldemo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author niumazlb
 * @create 2023-01-19 20:39
 */
@SpringBootTest
public class easyExcelTest {
    @Resource
    UserService userService;

    public static final String PATH = "D:\\code\\IDEA\\easyExcelDemo\\";
    public static final String fileName = "testEasyExcelWrite.xlsx";
    public static final String pathName = PATH + fileName;

    @Test
    public void testEasyExcelWrite() {
        List<User> userList = userService.list();
        List<ExcelUserData> excelDataList = userList.stream().map(user -> {
            ExcelUserData excelData = new ExcelUserData();
            excelData.setName(user.getName());
            excelData.setOldScore(user.getOldScore());
            excelData.setCurrentScore(user.getCurrentScore());
            excelData.setGrowScore(user.getCurrentScore() - user.getOldScore());
            return excelData;
        }).sorted(Comparator.comparingInt(ExcelUserData::getGrowScore)).collect(Collectors.toList());

        EasyExcel.write(pathName,ExcelUserData.class).sheet().doWrite(excelDataList);
    }

    @Test
    public void testRead(){
        EasyExcel.read(pathName,ExcelUserData.class,new UserDataListener()).sheet().doRead();
    }


}
