package com.learn.forever.web.controller;

import com.bluewhale.dto.ResultDTO;
import com.bluewhale.image.client.ImageRpc;
import com.bluewhale.image.client.domain.resp.ImageDTO;
import com.learn.forever.client.GenIdRpc;
import com.learn.forever.common.exception.LearnException;
import com.learn.forever.common.utils.ParameterUtils;
import com.learn.forever.core.service.RedisService;
import com.learn.forever.core.spi.SeedSpi;
import com.learn.forever.core.template.AbstractResultTemplate;
import com.learn.forever.damin.Person;
import com.learn.forever.service.EsService;
import com.learn.forever.web.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: learnForForever
 * @className: Test
 * @author: denghaitao
 * @date: 2020/1/4
 * @version: 1.0
 * @E-mail: denghaitao@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
@RestController
@RequestMapping("test")
@Slf4j
public class Test {

    @Autowired
    private SeedSpi seedSpi;


    @Value("${test.value}")
    private Integer testValue;

    @Autowired
    private GenIdRpc genIdRpc;

    @Reference
    private ImageRpc imageRpc;

    @Autowired
    private EsService esService;

    private final static String url="http://ebizdemo.datasink.sensorsdata.cn/sa?project=default&token=******";

    @Autowired
    private RedisService redisService;

    @GetMapping("insert")
    public void getByes(Person person){
        esService.add(person);
    }

    @GetMapping("getOne")
    public Object getOne(Person person){
        Person byUserId = esService.findByUserId(person);
        return byUserId;
    }

    @GetMapping("test")
    public Object test(){
        Long id = genIdRpc.getId("test");
        log.info("[===============>id= {}]", id);
        redisService.set("idTest", id.toString());
        String idTest = redisService.get("idTest");
        return idTest;
    }

    @GetMapping("testValue")
    public Object test1(){
        return testValue;
    }

    @GetMapping("template")
    public Object template(String id){
        return new AbstractResultTemplate<Void>() {
            @Override
            public void checkParam() throws LearnException {
                ParameterUtils.checkEmpty(id, "id不能为空");

            }

            @Override
            public Void bizExecute() throws Exception {

                return null;
            }
        }.executeApi(id);
    }

    @GetMapping("exportItem")
    public void exportItem(Long fileId, Long shopId){
        ResultDTO<ImageDTO> imageInfo = imageRpc.getImageInfo(fileId);
        if (imageInfo.isSuccess()) {
            String url = imageInfo.getModule().getUrl();
            InputStream inputStream = null;
            Workbook workbook ;
            try {
                URL url1 = new URL(url);
                inputStream = url1.openStream();
                String substring = url.substring(url.lastIndexOf("."));
                if(substring.equals(".xls")){
                    workbook = new HSSFWorkbook(inputStream);
                }else if(substring.equals(".xlsx")){
                    workbook = new XSSFWorkbook(inputStream);
                }else {
                    return;
                }
                Sheet sheetAt = workbook.getSheetAt(0);
                int lastRowIndex = sheetAt.getLastRowNum();
                List<String[]> list = new ArrayList<>();
                for(int i=1; i<lastRowIndex; i++){
                    String[] str = new String[5];
                    Row row = sheetAt.getRow(i);
                    if(null != row){
                        str[0] = ExcelUtils.getValue(row.getCell(0));
                        str[1] = ExcelUtils.getValue(row.getCell(1));
                        str[2] = ExcelUtils.getValue(row.getCell(2));
                        str[3] = ExcelUtils.getValue(row.getCell(3));
                        str[4] = ExcelUtils.getValue(row.getCell(4));
                        list.add(str);
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
