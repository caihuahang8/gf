package com.learn.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


import com.learn.entity.ZuoyeEntity;
import com.learn.service.ZuoyeService;
import com.learn.utils.*;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.learn.entity.RecordEntity;
import com.learn.service.RecordService;

import javax.servlet.http.HttpServletResponse;


/**
 * 作业记录
 */
@RestController
@RequestMapping("record")
public class RecordController extends AbstractController {
    @Autowired
    private RecordService recordService;

    @Autowired
    private ZuoyeService zuoyeService;

    private final  String path = "D:\\tomcat8\\tomcat-8.5.29_x64\\apache-tomcat-8.5.29\\webapps\\gf_war\\cdn\\cdn\\";

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        if (super.getUserId() > 1 && "3".equals(super.getUser().getType()))
            params.put("user", super.getUserId());


        //查询列表数据
        Query query = new Query(params);

        List<RecordEntity> recordList = recordService.queryList(query);
        int total = recordService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(recordList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 列表
     */
    @RequestMapping("/list2")
    public R list2(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<RecordEntity> recordList = recordService.queryList(query);
        return R.ok().put("list", recordList);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        RecordEntity record = recordService.queryObject(id);

        return R.ok().put("record", record);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody RecordEntity record) {

        if (record.getUser() == null)
            record.setUser(super.getUserId());


        recordService.save(record);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody RecordEntity record) {
        if ("3".equals(super.getUser().getType())) {

            Date end = this.recordService.queryObject(record.getId()).getZuoyeEntity().getEnd();
            if (System.currentTimeMillis() > end.getTime()) {
                throw new RRException("作业时间已过，禁止提交");
            }
        }


        recordService.update(record);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        recordService.deleteBatch(ids);

        return R.ok();
    }


    /**
     *
     */
    @RequestMapping("/export")
    public R export(@RequestBody Long[] ids, HttpServletResponse response) throws IOException {
        List<RecordEntity> list = new ArrayList<>();
        for (int i = 0; i<ids.length;i++   ){
            RecordEntity zuoye = recordService.queryObject(ids[i]);
            list.add(zuoye);
        }
        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet=wb.createSheet("成绩表");
        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1=sheet.createRow(0);
        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell=row1.createCell(0);
        //设置单元格内容
        cell.setCellValue("学员考试成绩一览表");
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));
        //在sheet里创建第二行
        HSSFRow row2=sheet.createRow(1);
        //创建单元格并设置单元格内容 TODO
        row2.createCell(0).setCellValue("作业名称");
        row2.createCell(1).setCellValue("内容");
        int i = 0;
        for (RecordEntity entity : list){
            ZuoyeEntity zuoyeEntity = entity.getZuoyeEntity();
            HSSFRow row3=sheet.createRow(2+i);
            //TODO  加自己业务
            row3.createCell(0).setCellValue(zuoyeEntity.getName());
            row3.createCell(1).setCellValue(entity.getContent());
            row3.createCell(2).setCellValue(zuoyeEntity.getUrl());
            i = i+1;
        }
        FileOutputStream out = new FileOutputStream("H:\\gf\\target" + "学员考试成绩一览表-" +new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString() +".xls");
//        response.setHeader("content-disposition", "attachment;filename=" + "学员考试成绩一览表-"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString() + ".xls");
        wb.write(out);
        out.write(wb.getBytes());
        out.close();
        return R.ok();
    }

    @GetMapping("download")
    public void download(HttpServletResponse response) throws Exception {
        HSSFWorkbook workbook=new HSSFWorkbook();//这里也可以设置sheet的Name
        //创建工作表对象
        HSSFSheet sheet = workbook.createSheet();
        //创建工作表的行
        HSSFRow row = sheet.createRow(0);//设置第一行，从零开始
        row.createCell(2).setCellValue("aaaaaaaaaaaa");//第一行第三列为aaaaaaaaaaaa
        row.createCell(0).setCellValue(new Date());//第一行第一列为日期
        workbook.setSheetName(0,"sheet的Name");//设置sheet的Name

        //文档输出
        FileOutputStream out = new FileOutputStream("H:\\gf\\target" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString() +".xls");
        workbook.write(out);
        out.close();
    }

    @RequestMapping("/download/{id}")
    public R download(@PathVariable("id") Long id,HttpServletResponse response) throws IOException {
        ZuoyeEntity zuoyeEntity =   zuoyeService.queryObject(id);
        String url  = zuoyeEntity.getUrl();
        String downloadpath = path + url.substring(url.lastIndexOf("/"));
        FileInputStream input = new FileInputStream(downloadpath);
        OutputStream out = response.getOutputStream();
        response.setHeader("content-disposition", "attachment;filename=filename.xls");
        byte[] bytes = IOUtils.toByteArray(input);
        out.write(bytes);
        input.close();
        out.close();
        return R.ok("下载成功");
    }




}
