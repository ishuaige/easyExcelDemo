package com.niuma.easyexceldemo.excel.listeners;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson2.JSON;
import com.niuma.easyexceldemo.excel.model.ExcelUserData;
import com.niuma.easyexceldemo.service.UserService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * ExcelModelListener 不能被spring管理，要每次读取 excel 都要 new,然后里面用到 spring 可以构造方法传进去
 *
 * @author niumazlb
 * @create 2023-01-19 20:59
 */
@Slf4j
public class UserDataListener implements ReadListener<ExcelUserData> {


    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理 list ，方便内存回收，避免 OOM
     */
    private static final int BATCH_COUNT = 100;
    /**
     * 缓存的数据，在 invoke 函数中存储每次读到的数据，这里的泛型虽业务变化而变化，存储的可以是excel表数据处理后的数据
     * 假如我要啊存入数据库中就需要将 ExcelUserData 转换成 User 那么这里的泛型就是User，在 invoke 中处理后添加
     */
    private List<ExcelUserData> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    /**
     * 这个是一个DAO，当然有业务逻辑这个也可以是一个service。可以用来解析数据后操作数据库
     */
    private UserService userService;

    /**
     * 每读到一条数据都会调用这个函数，可以在这里对数据的预处理
     *
     * @param excelUserData
     * @param analysisContext
     */
    @Override
    public void invoke(ExcelUserData excelUserData, AnalysisContext analysisContext) {
        log.info("解析到一条数据:{}", JSON.toJSONString(excelUserData));
        cachedDataList.add(excelUserData);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cachedDataList.size() >= BATCH_COUNT) {
            log.info("已达到BATCH_COUNT，共{}条数据", cachedDataList.size());

            // 调用储存数据函数
            saveData();

            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    /**
     * 所有数据解析完成了 都会来调用 做收尾工作，确保最后遗留的数据也持久化（存储到数据库）
     *
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();

        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", cachedDataList.size());

        //  TODO 数据存储,使用批处理操作防止多次连接数据库，例如 userService.saveBatch();

        log.info("存储数据库成功！");
    }
}
